package com.restaurent.manager.service;

import com.restaurent.manager.dto.response.StatisticResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface IStatisticService {
StatisticResponse getStatisticRestaurantById(Long restaurantId,int day);
StatisticResponse getStatisticByRestaurantIdBetweenStartDayToEndDay(Long restaurantId, LocalDateTime start, LocalDateTime end);
}
