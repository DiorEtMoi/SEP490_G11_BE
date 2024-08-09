package com.restaurent.manager.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class RestaurantPackagePaymentHistoryRequest {
    @NotNull(message = "NOT_NULL")
    Long packageId;
    @NotNull(message = "NOT_NULL")
    Long restaurantId;
    @NotNull(message = "NOT_NULL")
    @Min(value = 0, message = "GREATER_NUMBER")
    double totalMoney;
    @NotNull(message = "NOT_NULL")
    @Min(value = 0, message = "GREATER_NUMBER")
    int months;
    @NotNull(message = "NOT_NULL")
    Long accountId;
}
