package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.ScheduleRequest;
import com.restaurent.manager.dto.response.ScheduleResponse;
import com.restaurent.manager.dto.response.ScheduleTimeResponse;
import com.restaurent.manager.enums.SCHEDULE_STATUS;

import java.time.LocalDate;
import java.util.List;

public interface IScheduleService {
    String createSchedule(Long restaurantId,ScheduleRequest request);
    boolean checkTableIsBooked(Long tableId,ScheduleRequest request);
    List<ScheduleResponse> findScheduleRestaurantByDate(Long restaurantId,LocalDate date);
    List<ScheduleResponse> findScheduleRestaurantLate(Long restaurantId);
    List<ScheduleResponse> findScheduleRestaurantNearly(Long restaurantId);
    void updateStatusScheduleById(Long scheduleId,SCHEDULE_STATUS status);
    List<ScheduleTimeResponse> getNumberScheduleRestaurantWithTime(Long restaurantId);
}
