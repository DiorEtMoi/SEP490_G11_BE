package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.StatisticTableResponse;
import com.restaurent.manager.dto.response.StatisticResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IStatisticService {
StatisticResponse getStatisticRestaurantById(Long restaurantId,String day);
StatisticResponse getStatisticByRestaurantIdBetweenStartDayToEndDay(Long restaurantId, LocalDateTime start, LocalDateTime end);
List<StatisticTableResponse> getDetailStatisticRestaurantEachOfDayInCurrentMonth(Long restaurantId);
List<StatisticTableResponse> getDetailStatisticRestaurantEachOfDayInLastMonth(Long restaurantId);
}
