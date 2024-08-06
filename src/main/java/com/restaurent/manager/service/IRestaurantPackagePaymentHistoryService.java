package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.RestaurantPackagePaymentHistoryRequest;
import com.restaurent.manager.dto.response.StatisticAdminTable;

import java.util.List;

public interface IRestaurantPackagePaymentHistoryService {
Long createRestaurantPackagePaymentHistory(RestaurantPackagePaymentHistoryRequest request);
Long getNewId();
void updateRestaurantPackagePaymentHistory(Long id, RestaurantPackagePaymentHistoryRequest request);

List<StatisticAdminTable> getProfitInCurrentMonth();
}
