package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.dish.DishCategoryRequest;
import com.restaurent.manager.dto.response.DishCategoryResponse;
import com.restaurent.manager.entity.DishCategory;

import java.util.List;

public interface IDishCategoryService {
    DishCategoryResponse createDishCategory(DishCategoryRequest request);
    List<DishCategoryResponse> getAllDishCategoryByAccountId(Long accountId);
    DishCategory findById(Long id);
    DishCategory findByCode(String code);
    void deleteCategoryById(Long id);
    DishCategoryResponse updateDishCategoryById(DishCategoryRequest request, Long id);
}
