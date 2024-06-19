package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.dish.DishCategoryRequest;
import com.restaurent.manager.dto.response.DishCategoryResponse;

import java.util.List;

public interface IDishCategoryService {
    DishCategoryResponse createDishCategory(DishCategoryRequest request);
    List<DishCategoryResponse> getAllDishCategoryByAccountId(Long accountId);
}
