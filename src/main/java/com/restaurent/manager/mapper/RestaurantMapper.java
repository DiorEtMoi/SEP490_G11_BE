package com.restaurent.manager.mapper;

import com.restaurent.manager.dto.request.Restaurant.RestaurantRequest;
import com.restaurent.manager.dto.request.Restaurant.RestaurantUpdateRequest;
import com.restaurent.manager.dto.response.RestaurantResponse;
import com.restaurent.manager.entity.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    Restaurant toRestaurant(RestaurantRequest request);
    RestaurantResponse toRestaurantResponse(Restaurant restaurant);

    void updateRestaurant(@MappingTarget Restaurant restaurant, RestaurantUpdateRequest request);
}
