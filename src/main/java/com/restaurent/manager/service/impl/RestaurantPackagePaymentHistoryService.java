package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.RestaurantPackagePaymentHistoryRequest;
import com.restaurent.manager.entity.RestaurantPackagePaymentHistory;
import com.restaurent.manager.mapper.RestaurantPackageHistoryMapper;
import com.restaurent.manager.repository.RestaurantPackagePaymentHistoryRepository;
import com.restaurent.manager.service.IPackageService;
import com.restaurent.manager.service.IRestaurantPackagePaymentHistoryService;
import com.restaurent.manager.service.IRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class RestaurantPackagePaymentHistoryService implements IRestaurantPackagePaymentHistoryService {
    RestaurantPackagePaymentHistoryRepository restaurantPackagePaymentHistoryRepository;
    IPackageService packageService;
    IRestaurantService restaurantService;
    RestaurantPackageHistoryMapper mapper;

    @Override
    public void createRestaurantPackagePaymentHistory(RestaurantPackagePaymentHistoryRequest request) {
        RestaurantPackagePaymentHistory restaurantPackagePaymentHistory = mapper.toRestaurantPackagePaymentHistory(request);
        packageService.findPackById(request.getPackageId());
        restaurantService.getRestaurantById(request.getRestaurantId());
        restaurantPackagePaymentHistoryRepository.save(restaurantPackagePaymentHistory);
    }
}
