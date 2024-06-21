package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.restaurant.RestaurantManagerUpdateRequest;
import com.restaurent.manager.dto.request.restaurant.RestaurantRequest;
import com.restaurent.manager.dto.request.restaurant.RestaurantUpdateRequest;
import com.restaurent.manager.dto.response.RestaurantResponse;
import com.restaurent.manager.entity.Restaurant;

import java.util.List;

public interface IRestaurantService {
RestaurantResponse initRestaurant(RestaurantRequest request);
List<RestaurantResponse> getRestaurants();

RestaurantResponse updateRestaurant(Long restaurantId,RestaurantUpdateRequest request);
RestaurantResponse updateRestaurant(Long accountId, RestaurantManagerUpdateRequest request);

Restaurant getRestaurantById(Long id);

RestaurantResponse getRestaurantByAccountId(Long accountId);

}
