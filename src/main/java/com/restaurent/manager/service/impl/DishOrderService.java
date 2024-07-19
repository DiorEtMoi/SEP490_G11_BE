package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.entity.Dish;
import com.restaurent.manager.entity.DishOrder;
import com.restaurent.manager.entity.Order;
import com.restaurent.manager.enums.DISH_ORDER_STATE;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.DishOrderMapper;
import com.restaurent.manager.repository.DishOrderRepository;
import com.restaurent.manager.service.IDishOrderService;
import com.restaurent.manager.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class DishOrderService implements IDishOrderService {
    DishOrderRepository dishOrderRepository;
    DishOrderMapper dishOrderMapper;
    IOrderService orderService;
    @Override
    public DishOrderResponse changeStatusDishOrderById(Long id, DISH_ORDER_STATE status) {
        DishOrder dishOrder = findById(id);
        dishOrder.setStatus(status);
        dishOrderRepository.save(dishOrder);
        return dishOrderMapper.toDishOrderResponse(dishOrder);
    }

    @Override
    public List<DishOrderResponse> findDishOrderWaitingByAndRestaurantId(Long restaurantId) {
        List<DishOrderResponse> dishOrderResponses = findDishOrderByRestaurantIdAndState(restaurantId,DISH_ORDER_STATE.WAITING);
        dishOrderResponses.addAll(findDishOrderByRestaurantIdAndState(restaurantId,DISH_ORDER_STATE.PREPARE));
        return dishOrderResponses;
    }

    @Override
    public DishOrder findById(Long dishOrderId) {
        return dishOrderRepository.findById(dishOrderId).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        );
    }

    @Override
    public List<DishOrderResponse> findDishOrderByOrderId(Long orderId) {
        return dishOrderRepository.findDishOrderByOrder_Id(orderId).stream().map(dishOrderMapper::toDishOrderResponse).toList();
    }

    @Override
    public List<DishOrderResponse> findDishOrderByOrderIdAndStatus(Long orderId, DISH_ORDER_STATE state) {
        return dishOrderRepository.findDishOrderByOrder_IdAndStatus(orderId,state).stream().map(dishOrderMapper::toDishOrderResponse).toList();
    }

    @Override
    public List<DishOrderResponse> findDishOrderByRestaurantIdAndState(Long restaurantId, DISH_ORDER_STATE state) {
        List<Order> orders = orderService.findOrderByRestaurantId(restaurantId);
        List<DishOrder> dishOrders = new ArrayList<>();
        List<DishOrder> orderDishes;

        if(!orders.isEmpty()){
            for (Order order: orders){
                orderDishes = dishOrderRepository.findDishOrderByOrder_IdAndStatus(order.getId(),state);
                if(!orderDishes.isEmpty()){
                    dishOrders.addAll(orderDishes);
                }
            }
        }
        return dishOrders.stream().map(dishOrderMapper::toDishOrderResponse).toList();
    }


}
