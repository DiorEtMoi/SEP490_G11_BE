package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.order.DishOrderRequest;
import com.restaurent.manager.dto.request.order.OrderRequest;
import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.dto.response.order.OrderResponse;
import com.restaurent.manager.entity.DishOrder;
import com.restaurent.manager.entity.Order;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrderService {
    OrderResponse createOrder(OrderRequest request);
    List<DishOrderResponse> addDishToOrder(Long orderId, List<DishOrderRequest> requestList);
    List<DishOrderResponse> findDishByOrderId(Long orderId);
    List<DishOrderResponse> findDishByOrderId(Long orderId, Pageable pageable);
    Order findOrderById(Long orderId);
    List<Order> findOrderByRestaurantId(Long restaurantId);
    OrderResponse findOrderByTableId(Long tableId);
    OrderResponse findOrderAndConvertDTOByOrderId(Long orderId);
}
