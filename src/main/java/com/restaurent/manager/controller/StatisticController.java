package com.restaurent.manager.controller;

import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.StatisticResponse;
import com.restaurent.manager.service.IStatisticService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/statistic")
@FieldDefaults(makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
public class StatisticController {
    IStatisticService statisticService;
    @GetMapping(value = "/manager/restaurant/{restaurantId}")
    public ApiResponse<StatisticResponse> getStatisticRestaurantByIdInDay(@PathVariable Long restaurantId, @RequestParam(name = "day",defaultValue = "1") int day){
        return ApiResponse.<StatisticResponse>builder()
                .result(statisticService.getStatisticRestaurantById(restaurantId,day))
                .build();
    }
    @GetMapping(value = "/manager/restaurant/{restaurantId}/current-month")
    public ApiResponse<StatisticResponse> getStatisticRestaurantByIdInCurrentMonth(@PathVariable Long restaurantId){
        LocalDateTime today = LocalDateTime.now();
        LocalDateTime startDate = today.with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime endDate = today.with(TemporalAdjusters.lastDayOfMonth());
        return ApiResponse.<StatisticResponse>builder()
                .result(statisticService.getStatisticByRestaurantIdBetweenStartDayToEndDay(restaurantId,startDate,endDate))
                .build();
    }
    @GetMapping(value = "/manager/restaurant/{restaurantId}/last-month")
    public ApiResponse<StatisticResponse> getStatisticRestaurantByIdInLastMonth(@PathVariable Long restaurantId){
        LocalDateTime endDate = LocalDateTime.now();
        LocalDateTime startDate = endDate.minusMonths(1);
        return ApiResponse.<StatisticResponse>builder()
                .result(statisticService.getStatisticByRestaurantIdBetweenStartDayToEndDay(restaurantId,startDate,endDate))
                .build();
    }
}
