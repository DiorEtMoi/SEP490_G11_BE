package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.dish.DishRequest;
import com.restaurent.manager.dto.request.dish.DishUpdateRequest;
import com.restaurent.manager.dto.response.DishResponse;
import com.restaurent.manager.entity.Dish;

import java.util.List;

public interface IDishService {
    DishResponse createNewDish(DishRequest request);
    List<DishResponse> getDishesByAccountId(Long accountId);
    DishResponse updateDish(Long dishId, DishUpdateRequest request);
    Dish findByDishId(Long DishId);
    List<DishResponse> findDishesByCategoryCode(String categoryCode);
    List<DishResponse> getDishesByAccountIdAndStatus(Long accountId,boolean status);
}
