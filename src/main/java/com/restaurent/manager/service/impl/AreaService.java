package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.AreaRequest;
import com.restaurent.manager.dto.response.AreaResponse;
import com.restaurent.manager.entity.Area;
import com.restaurent.manager.entity.Restaurant;
import com.restaurent.manager.mapper.AreaMapper;
import com.restaurent.manager.repository.AreaRepository;
import com.restaurent.manager.service.IAreaService;
import com.restaurent.manager.service.IRestaurantService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class AreaService implements IAreaService {
    AreaRepository areaRepository;
    AreaMapper areaMapper;
    IRestaurantService restaurantService;
    @Override
    public AreaResponse createArea(AreaRequest request) {
        Area area = areaMapper.toArea(request);
        Restaurant restaurant = restaurantService.getRestaurantById(request.getRestaurantId());
        area.setRestaurant(restaurant);
        return areaMapper.toAreaResponse(
                areaRepository.save(area)
        );
    }

    @Override
    public List<AreaResponse> getAreasByRestaurantId(Long restaurantId) {
        return areaRepository.findByRestaurant_Id(restaurantId).stream().map(areaMapper::toAreaResponse).toList();
    }


}
