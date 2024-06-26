package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    // Các phương thức tùy chỉnh nếu cần
    List<Dish> findByAccount_Id(Long accountId);
    List<Dish> findByDishCategory_Id(Long categoryId);
    List<Dish> findByAccount_IdAndStatus(Long accountId, boolean status, Pageable pageable);
}
