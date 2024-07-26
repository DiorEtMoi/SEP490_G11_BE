package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.response.StatisticResponse;
import com.restaurent.manager.repository.BillRepository;
import com.restaurent.manager.repository.CustomerRepository;
import com.restaurent.manager.service.IBillService;
import com.restaurent.manager.service.IStatisticService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class StatisticService  implements IStatisticService{
    CustomerRepository customerRepository;
    BillRepository billRepository;
    IBillService billService;
    @Override
    public StatisticResponse getStatisticRestaurantById(Long restaurantId,int day) {
        return switch (day) {
            case 1 -> StatisticResponse.builder()
                    .numbersCustomer(customerRepository.findCustomerByRestaurant_IdInToday(restaurantId).size())
                    .numbersBill(billRepository.findByDateCreated(restaurantId,LocalDateTime.now()).size())
                    .profit(billService.getProfitRestaurantByIdAndDate(restaurantId,LocalDateTime.now()))
                    .build();
            case -1 -> StatisticResponse.builder()
                    .numbersCustomer(customerRepository.findCustomerByRestaurant_IdInYesterday(restaurantId, LocalDateTime.now().minusDays(1)).size())
                    .numbersBill(billRepository.findByDateCreated(restaurantId,LocalDateTime.now().minusDays(1)).size())
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
}
