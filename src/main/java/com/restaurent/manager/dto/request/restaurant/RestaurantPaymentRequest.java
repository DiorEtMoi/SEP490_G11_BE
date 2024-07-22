package com.restaurent.manager.dto.request.restaurant;

import lombok.Data;

@Data
public class RestaurantPaymentRequest {
    private String BANK_ID;
    private String ACCOUNT_NO;
    private String ACCOUNT_NAME;
}
