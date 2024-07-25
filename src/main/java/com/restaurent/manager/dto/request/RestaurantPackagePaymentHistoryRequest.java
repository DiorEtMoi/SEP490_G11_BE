package com.restaurent.manager.dto.request;

import lombok.Data;


@Data
public class RestaurantPackagePaymentHistoryRequest {
    Long packageId;
    Long restaurantId;
    double totalMoney;
    int months;
}