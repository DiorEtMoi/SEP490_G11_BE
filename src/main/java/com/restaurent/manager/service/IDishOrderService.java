package com.restaurent.manager.service;

import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.entity.DishOrder;
import com.restaurent.manager.entity.Order;
import com.restaurent.manager.enums.DISH_ORDER_STATE;

import java.util.List;

public interface IDishOrderService {
    DishOrderResponse changeStatusDishOrderById(Long id, DISH_ORDER_STATE status);
    List<DishOrderResponse> findDishOrderWaitingByAndRestaurantId(Long restaurantId);
    DishOrder findById(Long dishOrderId);
    List<DishOrderResponse> findDishOrderByOrderId(Long orderId);
    List<DishOrderResponse> findDishOrderByOrderIdAndStatus(Long orderId,DISH_ORDER_STATE state);
    List<DishOrderResponse> findDishOrderByRestaurantIdAndState(Long restaurantId,DISH_ORDER_STATE state);
}
