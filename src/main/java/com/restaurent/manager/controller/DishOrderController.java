package com.restaurent.manager.controller;

import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.enums.DISH_ORDER_STATE;
import com.restaurent.manager.service.IDishOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@RequestMapping(value = "/api/dish-order")
@SecurityRequirement(name = "bearerAuth")
public class DishOrderController {
    IDishOrderService dishOrderService;
    @MessageMapping("/dish-order/change-status")
    @PutMapping()
    @SendTo(value = "/topic/dish-order")
    public ApiResponse<DishOrderResponse> updateStatusDishOrderById(@RequestBody DishOrderResponse request){
        return ApiResponse.<DishOrderResponse>builder()
                .result(dishOrderService.changeStatusDishOrderById(request.getId(), DISH_ORDER_STATE.valueOf(request.getStatus())))
                .build();
    }
    @GetMapping(value = "/{orderId}")
    public ApiResponse<List<DishOrderResponse>> findDishOrderByOrderId(@PathVariable Long orderId){
        return ApiResponse.<List<DishOrderResponse>>builder()
                .result(dishOrderService.findDishOrderByOrderId(orderId))
                .build();
    }
    @GetMapping(value = "/{orderId}/state/{state}")
    public ApiResponse<List<DishOrderResponse>> findDishOrderByOrderIdAndState(@PathVariable Long orderId,@PathVariable DISH_ORDER_STATE state){
        return ApiResponse.<List<DishOrderResponse>>builder()
                .result(dishOrderService.findDishOrderByOrderIdAndStatus(orderId,state))
                .build();
    }
    @GetMapping(value = "/restaurant/{restaurantId}/state/{state}")
    public ApiResponse<List<DishOrderResponse>> findDishOrderByRestaurantIdAndState(@PathVariable Long restaurantId,@PathVariable DISH_ORDER_STATE state){
        return ApiResponse.<List<DishOrderResponse>>builder()
                .result(dishOrderService.findDishOrderByRestaurantIdAndState(restaurantId,state))
                .build();
    }
    @GetMapping(value = "/restaurant/{restaurantId}")
    public ApiResponse<List<DishOrderResponse>> findDishOrderWaitingByRestaurantId(@PathVariable Long restaurantId){
        return ApiResponse.<List<DishOrderResponse>>builder()
                .result(dishOrderService.findDishOrderWaitingByAndRestaurantId(restaurantId))
                .build();
    }
}
