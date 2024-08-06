package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.RestaurantPackagePaymentHistoryRequest;
import com.restaurent.manager.dto.request.restaurant.RestaurantUpdateRequest;
import com.restaurent.manager.dto.response.StatisticAdminTable;
import com.restaurent.manager.entity.RestaurantPackagePaymentHistory;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.RestaurantPackageHistoryMapper;
import com.restaurent.manager.repository.RestaurantPackagePaymentHistoryRepository;
import com.restaurent.manager.service.IPackageService;
import com.restaurent.manager.service.IRestaurantPackagePaymentHistoryService;
import com.restaurent.manager.service.IRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class RestaurantPackagePaymentHistoryService implements IRestaurantPackagePaymentHistoryService {
    RestaurantPackagePaymentHistoryRepository restaurantPackagePaymentHistoryRepository;
    IPackageService packageService;
    IRestaurantService restaurantService;
    RestaurantPackageHistoryMapper mapper;

    @Override
    public Long createRestaurantPackagePaymentHistory(RestaurantPackagePaymentHistoryRequest request) {
        RestaurantPackagePaymentHistory restaurantPackagePaymentHistory = mapper.toRestaurantPackagePaymentHistory(request);
        packageService.findPackById(request.getPackageId());
        restaurantService.getRestaurantById(request.getRestaurantId());
        restaurantPackagePaymentHistory.setId(getNewId());
        restaurantPackagePaymentHistory.setDateCreated(LocalDateTime.now());
        return restaurantPackagePaymentHistoryRepository.save(restaurantPackagePaymentHistory).getId();
    }

    @Override
    public Long getNewId() {
        List<RestaurantPackagePaymentHistory> all = restaurantPackagePaymentHistoryRepository.findAll();
        return all.isEmpty() ? 1L : all.getLast().getId() + 1;
    }

    @Override
    public void updateRestaurantPackagePaymentHistory(Long id, RestaurantPackagePaymentHistoryRequest request) {
        RestaurantPackagePaymentHistory history = restaurantPackagePaymentHistoryRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        );
        restaurantService.updateRestaurant(request.getRestaurantId(), RestaurantUpdateRequest.builder()
                .months(request.getMonths())
                .packId(request.getPackageId())
                .build());
        history.setPaid(true);
        restaurantPackagePaymentHistoryRepository.save(history);
    }

    @Override
    public List<StatisticAdminTable> getProfitInCurrentMonth() {
        return null;
    }
}
