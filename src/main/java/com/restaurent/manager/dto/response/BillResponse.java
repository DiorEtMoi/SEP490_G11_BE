package com.restaurent.manager.dto.response;

import com.restaurent.manager.dto.response.order.OrderResponse;
import com.restaurent.manager.enums.MethodPayment;

import java.time.LocalDate;

public class BillResponse {
    Long id;
    OrderResponse order;
    double total;
    LocalDate dateCreated;
    MethodPayment methodPayment;
}
