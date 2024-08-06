package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.ScheduleRequest;
import com.restaurent.manager.entity.Schedule;

public interface IScheduleService {
    String createSchedule(Long restaurantId,ScheduleRequest request);
    boolean checkTableIsBooked(Long tableId,ScheduleRequest request);
}
