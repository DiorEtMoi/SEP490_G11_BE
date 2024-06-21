package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.restaurant.RestaurantRequest;
import com.restaurent.manager.dto.request.restaurant.RestaurantUpdateRequest;
import com.restaurent.manager.dto.response.RestaurantResponse;
import com.restaurent.manager.entity.Restaurant;

import java.util.List;

public interface IRestaurantService {
RestaurantResponse initRestaurant(RestaurantRequest request);
List<RestaurantResponse> getRestaurants();

RestaurantResponse updateRestaurant(RestaurantUpdateRequest request);

Restaurant getRestaurantById(Long id);

RestaurantResponse getRestaurantByAccountId(Long accountId);

}
