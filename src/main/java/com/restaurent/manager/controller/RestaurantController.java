package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.restaurant.RestaurantManagerUpdateRequest;
import com.restaurent.manager.dto.request.restaurant.RestaurantPaymentRequest;
import com.restaurent.manager.dto.request.restaurant.RestaurantRequest;
import com.restaurent.manager.dto.request.restaurant.RestaurantUpdateRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.RestaurantResponse;
import com.restaurent.manager.service.IRestaurantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
public class RestaurantController {
    IRestaurantService restaurantService;
    @PostMapping("/init")
    public ApiResponse<RestaurantResponse> initRestaurant(@RequestBody @Valid RestaurantRequest request){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.initRestaurant(request))
                .build();
    }
    @GetMapping
    public ApiResponse<List<RestaurantResponse>> getRestaurants(){
        return ApiResponse.<List<RestaurantResponse>>builder()
                .result(restaurantService.getRestaurants())
                .build();
    }
    @PutMapping("/admin/{restaurantId}")
    public ApiResponse<RestaurantResponse> updateRestaurant(@PathVariable Long restaurantId ,@RequestBody @Valid RestaurantUpdateRequest request){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.updateRestaurant(restaurantId,request))
                .build();
    }
    @PutMapping("/manager/{accountId}")
    public ApiResponse<RestaurantResponse> updateRestaurantByManager(@PathVariable Long accountId,@RequestBody RestaurantManagerUpdateRequest request){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.updateRestaurant(accountId,request))
                .build();
    }
    @PutMapping("/manager/payment/{accountId}")
    public ApiResponse<RestaurantResponse> updateRestaurantPayment(@PathVariable Long accountId,@RequestBody RestaurantPaymentRequest request){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.updateRestaurant(accountId,request))
                .build();
    }
    @GetMapping("/account/{accountID}")
    public ApiResponse<RestaurantResponse> getRestaurantByAccountId(@PathVariable Long accountID){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.getRestaurantByAccountId(accountID))
                .build();
    }
    @PostMapping(value = "/{restaurantId}/pack/require-money")
    public ApiResponse<Double> getRequireMoneyToUpdatePackForRestaurant(@PathVariable Long restaurantId,@RequestBody RestaurantUpdateRequest request){
        return ApiResponse.<Double>builder()
                .result(restaurantService.getMoneyToUpdatePackForRestaurant(restaurantId,request))
                .build();
    }

}
