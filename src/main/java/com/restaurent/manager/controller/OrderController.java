package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.order.DishOrderRequest;
import com.restaurent.manager.dto.request.order.OrderRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.dto.response.order.OrderResponse;
import com.restaurent.manager.service.IOrderService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/order")
@FieldDefaults(makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class OrderController {
    IOrderService orderService;
    @PostMapping(value = "/create")
    public ApiResponse<OrderResponse> createNewOrder(@RequestBody OrderRequest request){
        return ApiResponse.<OrderResponse>builder()
                .result(orderService.createOrder(request))
                .build();
    }
    @PutMapping(value = "/{orderId}")
    public ApiResponse<List<DishOrderResponse>> addDishToOrder(@RequestBody List<DishOrderRequest> requestList, @PathVariable Long orderId){
        return ApiResponse.<List<DishOrderResponse>>builder()
                .result(orderService.addDishToOrder(orderId,requestList))
                .build();
    }
    @GetMapping(value = "/{orderId}")
    public ApiResponse<List<DishOrderResponse>> findDishByOrderId(@PathVariable Long orderId){
        return ApiResponse.<List<DishOrderResponse>>builder()
                .result(orderService.findDishByOrderId(orderId))
                .build();
    }
}
