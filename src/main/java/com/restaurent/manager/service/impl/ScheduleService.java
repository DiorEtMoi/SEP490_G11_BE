package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.ScheduleRequest;
import com.restaurent.manager.dto.request.order.DishOrderRequest;
import com.restaurent.manager.dto.response.ScheduleDishResponse;
import com.restaurent.manager.dto.response.ScheduleResponse;
import com.restaurent.manager.dto.response.ScheduleTimeResponse;
import com.restaurent.manager.dto.response.order.OrderResponse;
import com.restaurent.manager.entity.*;
import com.restaurent.manager.enums.SCHEDULE_STATUS;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.ScheduleMapper;
import com.restaurent.manager.repository.CustomerRepository;
import com.restaurent.manager.repository.ScheduleRepository;
import com.restaurent.manager.repository.TableRestaurantRepository;
import com.restaurent.manager.service.*;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@Slf4j
public class ScheduleService implements IScheduleService {
    ScheduleMapper scheduleMapper;
    ScheduleRepository scheduleRepository;
    ITableRestaurantService tableRestaurantService;
    TableRestaurantRepository tableRestaurantRepository;
    IRestaurantService restaurantService;
    IScheduleDishService scheduleDishService;
    ICustomerService customerService;
    IEmployeeService employeeService;
    IOrderService orderService;
    CustomerRepository customerRepository;
    @Override
    public String createSchedule(Long restaurantId, ScheduleRequest request) {
        //handling about time
        if(request.getBookedDate().isBefore(LocalDate.now())){
            throw new AppException(ErrorCode.NOT_EXIST);
        }

        Schedule schedule = scheduleMapper.toSchedule(request);
        LocalTime time = LocalTime.parse(request.getTime());
        LocalTime intend_time = time.plusMinutes(request.getIntendTimeMinutes());

        if(request.getBookedDate().equals(LocalDate.now())){
            if(time.isBefore(LocalTime.now())){
                throw new AppException(ErrorCode.TIME_INVALID);
            }
        }
        for (Long tableId : request.getTables()){
            boolean isBooked = checkTableIsBooked(tableId,request);
            TableRestaurant tableRestaurant = tableRestaurantService.findById(tableId);
            if(isBooked){
                return "Bàn " + tableRestaurant.getName() + " đã được đặt,  vui lòng chọn bàn khác hoặc khung giờ khác !";
            }
        }
        List<TableRestaurant> tableRestaurants = tableRestaurantRepository.findAllById(request.getTables());
        schedule.setTableRestaurants(new HashSet<>(tableRestaurants));
        schedule.setIntendTime(intend_time);
        schedule.setStatus(SCHEDULE_STATUS.PENDING);
        schedule.setRestaurant(restaurantService.getRestaurantById(restaurantId));
        Schedule saved = scheduleRepository.save(schedule);
        for (DishOrderRequest dishOrderRequest : request.getScheduleDishes()){
            scheduleDishService.createScheduleDish(saved,dishOrderRequest);
        }
        return "success";
    }

    @Override
    public boolean checkTableIsBooked(Long tableId, ScheduleRequest request) {
        LocalTime time = LocalTime.parse(request.getTime());
        LocalTime intend_time = time.plusMinutes(request.getIntendTimeMinutes());
        List<Schedule> schedules = scheduleRepository.findSchedulesByTableAndDateRange(tableId,request.getBookedDate(),
                time,intend_time);
        return !schedules.isEmpty();
    }

    @Override
    public List<ScheduleResponse> findScheduleRestaurantByDate(Long restaurantId, LocalDate date) {
        return scheduleRepository.findByBookedDateAndRestaurant_IdAndStatus(date,restaurantId,SCHEDULE_STATUS.PENDING).stream().map(scheduleMapper::toScheduleResponse).toList();
    }

    @Override
    public List<ScheduleResponse> findScheduleRestaurantLate(Long restaurantId) {
        LocalTime now = LocalTime.now();
        LocalDate dateNow = LocalDate.now();
        return scheduleRepository.findByRestaurant_IdAndBookedDateAndTimeIsBeforeAndStatus(restaurantId,dateNow,now,SCHEDULE_STATUS.PENDING).stream().map(scheduleMapper::toScheduleResponse).toList();
    }

    @Override
    public List<ScheduleResponse> findScheduleRestaurantNearly(Long restaurantId) {
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusHours(1);
        LocalDate dateNow = LocalDate.now();
        return scheduleRepository.findByRestaurant_IdAndBookedDateAndTimeBetweenAndStatus(restaurantId,dateNow,startTime,endTime,SCHEDULE_STATUS.PENDING).stream().map(scheduleMapper::toScheduleResponse).toList();
    }

    @Override
    public void updateStatusScheduleById(Long scheduleId, Long employeeId,SCHEDULE_STATUS status) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        );
        schedule.setStatus(status);
        if(status.equals(SCHEDULE_STATUS.ACCEPT)){
            customerReceiveBookTable(employeeId,schedule);
        }
        scheduleRepository.save(schedule);
    }
    @Override
    public void customerReceiveBookTable(Long employeeId,Schedule schedule) {
        Customer customer;
        if(customerService.existCustomerByPhoneNumber(schedule.getCustomerPhone())){
            customer = customerService.findCustomerByPhoneNumber(schedule.getCustomerPhone());
        }else{
            customer = Customer.builder()
                    .name(schedule.getCustomerName())
                    .phoneNumber(schedule.getCustomerPhone())
                    .restaurant(schedule.getRestaurant())
                    .dateCreated(LocalDateTime.now())
                    .build();
            customer = customerRepository.save(customer);
        }
        Employee employee = employeeService.findEmployeeById(employeeId);
        List<DishOrderRequest> dishOrderRequests = new ArrayList<>();
        for (ScheduleDishResponse dish : scheduleDishService.findDishOrComboBySchedule(schedule.getId())){
            if(dish.getDish() != null){
                dishOrderRequests.add(DishOrderRequest.builder()
                        .dishId(dish.getDish().getId())
                        .quantity(dish.getQuantity())
                        .build());
            }else{
                dishOrderRequests.add(DishOrderRequest.builder()
                        .comboId(dish.getCombo().getId())
                        .quantity(dish.getQuantity())
                        .build());
            }
        }
        for (TableRestaurant tableRestaurant : schedule.getTableRestaurants()){
            Long orderId = orderService.createOrder(customer,employee,tableRestaurant,schedule.getRestaurant());
            orderService.addDishToOrder(orderId,dishOrderRequests);
        }
    }
    @Override
    public List<ScheduleTimeResponse> getNumberScheduleRestaurantWithTime(Long restaurantId) {
        LocalDate now = LocalDate.now();
        List<ScheduleTimeResponse> res = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            res.add(ScheduleTimeResponse.builder()
                            .date(now.plusDays(i))
                            .numbersSchedule(scheduleRepository.countByRestaurant_IdAndBookedDateAndStatus(restaurantId,now.plusDays(i),SCHEDULE_STATUS.PENDING))
                    .build());
        }
        return res;
    }

    @Override
    public List<ScheduleResponse> findAllScheduleRestaurant(Long restaurantId, Pageable pageable) {
        return scheduleRepository.findByRestaurant_Id(restaurantId,pageable).stream().map(scheduleMapper::toScheduleResponse).toList();
    }

}
