package com.restaurent.manager.repository;

import com.restaurent.manager.entity.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishCategoryRepository extends JpaRepository<DishCategory,Long> {
    boolean existsByNameAndRestaurant_Id(String name,Long accountId);
    List<DishCategory> findByAccount_Id(Long accountId);
    Optional<DishCategory> findByCodeAndRestaurant_Id(String code, Long restaurantId);
}
