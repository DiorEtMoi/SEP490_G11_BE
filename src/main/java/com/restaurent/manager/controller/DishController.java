package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.dish.DishRequest;
import com.restaurent.manager.dto.request.dish.DishUpdateRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.DishResponse;
import com.restaurent.manager.service.IDishService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dish")
@FieldDefaults(makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class DishController {
    IDishService dishService;
    @PostMapping(value = "/create")
    public ApiResponse<DishResponse> createDish(@RequestBody DishRequest request){
        return ApiResponse.<DishResponse>builder()
                .result(dishService.createNewDish(request))
                .build();
    }
    @GetMapping(value = "/account/{accountId}")
    public ApiResponse<List<DishResponse>> findDishesByAccountId(@PathVariable String accountId){
        return ApiResponse.<List<DishResponse>>builder()
                .result(dishService.getDishesByAccountId(Long.parseLong(accountId)))
                .build();
    }
    @GetMapping(value = "/category/{categoryCode}")
    public ApiResponse<List<DishResponse>> findDishesByCategoryCode(@PathVariable String categoryCode){
        return ApiResponse.<List<DishResponse>>builder()
                .result(dishService.findDishesByCategoryCode(categoryCode))
                .build();
    }
    @PutMapping(value = "/{dishId}")
    public ApiResponse<DishResponse> updateDishById(@PathVariable String dishId, @RequestBody DishUpdateRequest request){
        return ApiResponse.<DishResponse>builder()
                .result(dishService.updateDish(Long.parseLong(dishId),request))
                .build();
    }
}
