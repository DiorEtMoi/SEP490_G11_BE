package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.ScheduleRequest;
import com.restaurent.manager.dto.response.ScheduleResponse;
import com.restaurent.manager.dto.response.ScheduleTimeResponse;
import com.restaurent.manager.entity.Schedule;
import com.restaurent.manager.entity.TableRestaurant;
import com.restaurent.manager.enums.SCHEDULE_STATUS;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.ScheduleMapper;
import com.restaurent.manager.repository.ScheduleRepository;
import com.restaurent.manager.repository.TableRestaurantRepository;
import com.restaurent.manager.service.IRestaurantService;
import com.restaurent.manager.service.IScheduleService;
import com.restaurent.manager.service.ITableRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        scheduleRepository.save(schedule);
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
        return scheduleRepository.findByBookedDateAndRestaurant_Id(date,restaurantId).stream().map(scheduleMapper::toScheduleResponse).toList();
    }

    @Override
    public List<ScheduleResponse> findScheduleRestaurantLate(Long restaurantId) {
        LocalTime now = LocalTime.now();
        LocalDate dateNow = LocalDate.now();
        return scheduleRepository.findByRestaurant_IdAndBookedDateAndTimeIsBefore(restaurantId,dateNow,now).stream().map(scheduleMapper::toScheduleResponse).toList();
    }

    @Override
    public List<ScheduleResponse> findScheduleRestaurantNearly(Long restaurantId) {
        LocalTime startTime = LocalTime.now();
        LocalTime endTime = LocalTime.now().plusHours(1);
        LocalDate dateNow = LocalDate.now();
        return scheduleRepository.findByRestaurant_IdAndBookedDateAndTimeBetweenAndStatus(restaurantId,dateNow,startTime,endTime,SCHEDULE_STATUS.PENDING).stream().map(scheduleMapper::toScheduleResponse).toList();
    }

    @Override
    public void updateStatusScheduleById(Long scheduleId, SCHEDULE_STATUS status) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        );
        schedule.setStatus(status);
        scheduleRepository.save(schedule);
    }

    @Override
    public List<ScheduleTimeResponse> getNumberScheduleRestaurantWithTime(Long restaurantId) {
        LocalDate now = LocalDate.now();
        List<ScheduleTimeResponse> res = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            res.add(ScheduleTimeResponse.builder()
                            .date(now.plusDays(i))
                            .numbersSchedule(scheduleRepository.countByRestaurant_IdAndBookedDate(restaurantId,now.plusDays(i)))
                    .build());
        }
        return res;
    }

    @Override
    public List<ScheduleResponse> findAllScheduleRestaurant(Long restaurantId, Pageable pageable) {
        return scheduleRepository.findByRestaurant_Id(restaurantId,pageable).stream().map(scheduleMapper::toScheduleResponse).toList();
    }
}
