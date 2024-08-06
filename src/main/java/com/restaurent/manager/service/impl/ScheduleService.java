package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.ScheduleRequest;
import com.restaurent.manager.entity.Schedule;
import com.restaurent.manager.entity.TableRestaurant;
import com.restaurent.manager.mapper.ScheduleMapper;
import com.restaurent.manager.repository.ScheduleRepository;
import com.restaurent.manager.repository.TableRestaurantRepository;
import com.restaurent.manager.service.IScheduleService;
import com.restaurent.manager.service.ITableRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
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
    @Override
    public String createSchedule(Long restaurantId, ScheduleRequest request) {
        Schedule schedule = scheduleMapper.toSchedule(request);
        LocalTime time = LocalTime.parse(request.getTime());
        LocalTime intend_time = time.plusMinutes(request.getIntendTimeMinutes());

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
}
