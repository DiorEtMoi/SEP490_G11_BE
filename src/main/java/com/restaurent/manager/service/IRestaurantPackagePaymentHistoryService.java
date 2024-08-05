package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.RestaurantPackagePaymentHistoryRequest;

public interface IRestaurantPackagePaymentHistoryService {
Long createRestaurantPackagePaymentHistory(RestaurantPackagePaymentHistoryRequest request);
Long getNewId();
void updateRestaurantPackagePaymentHistory(Long id);
}
