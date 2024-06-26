package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.order.OrderRequest;
import com.restaurent.manager.dto.response.order.OrderResponse;

public interface IOrderService {
    OrderResponse createOrder(OrderRequest request);
}
