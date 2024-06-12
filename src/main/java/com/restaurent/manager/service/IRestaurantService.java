package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.RestaurantRequest;
import com.restaurent.manager.dto.response.RestaurantResponse;

import java.util.List;

public interface IRestaurantService {
RestaurantResponse initRestaurant(RestaurantRequest request);
List<RestaurantResponse> getRestaurants();

}
