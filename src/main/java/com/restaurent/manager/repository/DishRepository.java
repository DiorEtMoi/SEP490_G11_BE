package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    // Các phương thức tùy chỉnh nếu cần
}
