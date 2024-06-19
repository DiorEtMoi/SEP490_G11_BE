package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.dish.DishCategoryRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.DishCategoryResponse;
import com.restaurent.manager.service.IDishCategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dish-category")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
public class DishCategoryController {
    IDishCategoryService dishCategoryService;
    @GetMapping(value = "/{accountId}")
    public ApiResponse<List<DishCategoryResponse>> getDishCategoryByAccountId(@PathVariable String accountId){
        return ApiResponse.<List<DishCategoryResponse>>builder()
                .result(dishCategoryService.getAllDishCategoryByAccountId(Long.parseLong(accountId)))
                .build();
    }
    @PostMapping(value = "/create")
    public  ApiResponse<DishCategoryResponse> createDishCategory(@RequestBody DishCategoryRequest request){
        return ApiResponse.<DishCategoryResponse>builder()
                .result(dishCategoryService.createDishCategory(request))
                .build();
    }
}
