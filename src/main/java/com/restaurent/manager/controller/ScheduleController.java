package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.ScheduleRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.service.IScheduleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping("/api/schedule")
@SecurityRequirement(name = "bearerAuth")
public class ScheduleController {
    IScheduleService scheduleService;

    @PostMapping(value = "/restaurant/{restaurantId}/create")
    public ApiResponse<String> createSchedule(@PathVariable Long restaurantId, @RequestBody ScheduleRequest request){
        return ApiResponse.<String>builder()
                .result(scheduleService.createSchedule(restaurantId,request))
                .build();
    }
}
