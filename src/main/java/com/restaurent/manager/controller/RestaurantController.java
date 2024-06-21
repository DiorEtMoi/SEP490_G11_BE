package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.restaurant.RestaurantManagerUpdateRequest;
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
    public ApiResponse<RestaurantResponse> updateRestaurant(@PathVariable String restaurantId ,@RequestBody @Valid RestaurantUpdateRequest request){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.updateRestaurant(Long.parseLong(restaurantId),request))
                .build();
    }
    @PutMapping("/manager/{accountId}")
    public ApiResponse<RestaurantResponse> updateRestaurantByManager(@PathVariable String accountId,@RequestBody RestaurantManagerUpdateRequest request){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.updateRestaurant(Long.parseLong(accountId),request))
                .build();
    }
    @GetMapping("/account/{accountID}")
    public ApiResponse<RestaurantResponse> getRestaurantByAccountId(@PathVariable String accountID){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.getRestaurantByAccountId(Long.parseLong(accountID)))
                .build();
    }
}
