package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.StatisticTableResponse;
import com.restaurent.manager.dto.response.StatisticResponse;
import com.restaurent.manager.repository.BillRepository;
import com.restaurent.manager.repository.CustomerRepository;
import com.restaurent.manager.service.IBillService;
import com.restaurent.manager.service.IStatisticService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class StatisticService  implements IStatisticService{
    CustomerRepository customerRepository;
    BillRepository billRepository;
    IBillService billService;
    @Override
    public StatisticResponse getStatisticRestaurantById(Long restaurantId,String day) {
        return switch (day) {
            case "1" -> StatisticResponse.builder()
                    .numbersCustomer(customerRepository.findCustomerByRestaurant_IdInToday(restaurantId).size())
                    .numbersBill(billRepository.findByDateCreated(restaurantId,Date.valueOf(LocalDateTime.now().toLocalDate())).size())
                    .profit(billService.getProfitRestaurantByIdAndDate(restaurantId,LocalDateTime.now()))
                    .build();
            case "-1" -> StatisticResponse.builder()
                    .numbersCustomer(customerRepository.findCustomerByRestaurant_IdInYesterday(restaurantId, Date.valueOf(LocalDateTime.now().minusDays(1).toLocalDate())).size())
                    .numbersBill(billRepository.findByDateCreated(restaurantId,Date.valueOf(LocalDateTime.now().minusDays(1).toLocalDate())).size())
                    .profit(billService.getProfitRestaurantByIdAndDate(restaurantId,LocalDateTime.now().minusDays(1)))
                    .build();
            default -> null;
        };
    }

    @Override
    public StatisticResponse getStatisticByRestaurantIdBetweenStartDayToEndDay(Long restaurantId, LocalDateTime start, LocalDateTime end) {
        return StatisticResponse.builder()
                .numbersCustomer(customerRepository.findCustomerByRestaurantIdInStartDateAndEndDate(restaurantId, start, end).size())
                .numbersBill(billRepository.findByDateCreatedBetween(restaurantId, start, end).size())
                .profit(billService.getProfitRestaurantByIdAndDateBetween(restaurantId, start, end))
                .build();
    }

    @Override
    public List<StatisticTableResponse> getDetailStatisticRestaurantEachOfDayInCurrentMonth(Long restaurantId) {
        List<StatisticTableResponse> responses = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        int currentDay = now.getDayOfMonth();
        for (int i = 0; i < currentDay; i++) {
            LocalDate localDate = now.with(TemporalAdjusters.firstDayOfMonth()).plusDays(i).toLocalDate();
            responses.add(StatisticTableResponse.builder()
                            .time(localDate)
                            .numbersBill(billRepository.findByDateCreated(restaurantId,Date.valueOf(localDate)).size())
                            .profit(billService.getProfitRestaurantByIdAndDate(restaurantId,now.with(TemporalAdjusters.firstDayOfMonth()).plusDays(i)))
                    .build());
        }
        return responses;
    }

    @Override
    public List<StatisticTableResponse> getDetailStatisticRestaurantEachOfDayInLastMonth(Long restaurantId) {
        List<StatisticTableResponse> responses = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now().minusMonths(1);
        int currentDay = now.getDayOfMonth();
        for (int i = 0; i < currentDay; i++) {
            responses.add(StatisticTableResponse.builder()
                    .time(now.with(TemporalAdjusters.firstDayOfMonth()).plusDays(i).toLocalDate())
                    .profit(billService.getProfitRestaurantByIdAndDate(restaurantId,now.with(TemporalAdjusters.firstDayOfMonth()).plusDays(i)))
                    .build());
        }
        return responses;
    }
}
