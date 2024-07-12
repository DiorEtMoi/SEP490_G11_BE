package com.restaurent.manager.repository;

import com.restaurent.manager.entity.DishOrder;
import com.restaurent.manager.entity.Order;
import com.restaurent.manager.enums.DISH_ORDER_STATE;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishOrderRepository extends JpaRepository<DishOrder,Long> {
    DishOrder findDishOrderByOrder_IdAndDish_Id(Long orderId,Long dishId);
    List<DishOrder> findDishOrderByOrder_Id(Long orderId);
    List<DishOrder> findDishOrderByOrder_IdAndStatus(Long orderId, DISH_ORDER_STATE state);

}
