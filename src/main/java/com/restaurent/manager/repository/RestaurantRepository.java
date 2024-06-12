package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
List<Restaurant> findByRestaurantPackageIdIsNotNullAndExpiryDateBefore(LocalDateTime time);
}
