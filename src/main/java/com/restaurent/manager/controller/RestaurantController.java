package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.Restaurant.RestaurantRequest;
import com.restaurent.manager.dto.request.Restaurant.RestaurantUpdateRequest;
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
    @PutMapping("/update")
    public ApiResponse<RestaurantResponse> updateRestaurant(@RequestBody @Valid RestaurantUpdateRequest request){
        return ApiResponse.<RestaurantResponse>builder()
                .result(restaurantService.updateRestaurant(request))
                .build();
    }
}
