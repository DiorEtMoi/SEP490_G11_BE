package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.order.DishOrderAddRequest;
import com.restaurent.manager.dto.request.order.DishOrderRequest;
import com.restaurent.manager.dto.request.order.OrderRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.dto.response.order.OrderResponse;
import com.restaurent.manager.service.IOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    IOrderService orderService;
    @PostMapping(value = "/create")
    public ApiResponse<OrderResponse> createNewOrder(@RequestBody OrderRequest request){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.createOrder(request))
                .build();
    }
    @MessageMapping("/addDishes")
    @SendTo("/topic/order")
    public ApiResponse<Void> addDishToOrder(@Payload DishOrderAddRequest request){
        log.info(request.toString());
        orderService.addDishToOrder(request.getOrderId(),request.getDishOrderRequests());
        return ApiResponse.<Void>builder()
                .message("Order Success")
                .build();
    }
    @PutMapping("/order/add-dishes")
    public ApiResponse<List<DishOrderResponse>> addDishes(@RequestBody DishOrderAddRequest request){
        log.info(request.toString());
        orderService.addDishToOrder(request.getOrderId(),request.getDishOrderRequests());
        return ApiResponse.<List<DishOrderResponse>>builder()
                .message("Order success")
                .build();
    }
    @GetMapping(value = "/{orderId}")
    public ApiResponse<List<DishOrderResponse>> findDishByOrderId(@PathVariable Long orderId){
        return ApiResponse.<List<DishOrderResponse>>builder()
                .result(orderService.findDishByOrderId(orderId))
                .build();
    }
    @MessageMapping("/message")
    @SendTo("/topic/order")
    public String sendMessage(@Payload String message){
        return message;
    }
}
