package com.restaurent.manager.mapper;

import com.restaurent.manager.dto.request.RestaurantRequest;
import com.restaurent.manager.dto.response.RestaurantResponse;
import com.restaurent.manager.dto.response.RoleResponse;
import com.restaurent.manager.entity.Restaurant;
import com.restaurent.manager.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant toRestaurant(RestaurantRequest request);
    RestaurantResponse toRestaurantResponse(Restaurant restaurant);
}
