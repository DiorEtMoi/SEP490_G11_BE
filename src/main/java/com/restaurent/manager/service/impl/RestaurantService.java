package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.RestaurantRequest;
import com.restaurent.manager.dto.response.RestaurantResponse;
import com.restaurent.manager.entity.Account;
import com.restaurent.manager.entity.Restaurant;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.RestaurantMapper;
import com.restaurent.manager.repository.AccountRepository;
import com.restaurent.manager.repository.PackageRepository;
import com.restaurent.manager.repository.RestaurantRepository;
import com.restaurent.manager.service.IRestaurantService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RestaurantService implements IRestaurantService {
    RestaurantMapper restaurantMapper;
    RestaurantRepository restaurantRepository;
    AccountRepository accountRepository;
    PackageRepository packageRepository;
    @Override
    public RestaurantResponse initRestaurant(RestaurantRequest request) {

        Restaurant restaurant = restaurantMapper.toRestaurant(request);
        Account account = accountRepository.findById(request.getAccountId()).orElseThrow(() ->
                new AppException(ErrorCode.USER_NOT_EXISTED)
        );
        restaurant.setAccount(account);
        restaurant.setRestaurantPackage(packageRepository.findByPackName("Trial"));
        restaurant.setExpiryDate(LocalDateTime.now().plusMonths(1));
        return restaurantMapper.toRestaurantResponse(restaurantRepository.save(restaurant));
    }

    @Override
    public List<RestaurantResponse> getRestaurants() {
        return restaurantRepository.findAll().stream().map(restaurantMapper::toRestaurantResponse).toList();
    }
}
