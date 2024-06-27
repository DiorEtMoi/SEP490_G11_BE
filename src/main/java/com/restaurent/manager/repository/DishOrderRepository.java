package com.restaurent.manager.repository;

import com.restaurent.manager.entity.DishOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishOrderRepository extends JpaRepository<DishOrder,Long> {
    DishOrder findDishOrderByOrder_IdAndDish_Id(Long orderId,Long dishId);
    List<DishOrder> findDishOrderByOrder_Id(Long orderId);
}
