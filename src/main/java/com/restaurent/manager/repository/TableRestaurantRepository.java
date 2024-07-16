package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Order;
import com.restaurent.manager.entity.TableRestaurant;
import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TableRestaurantRepository extends JpaRepository<TableRestaurant,Long> {
    List<TableRestaurant> findByArea_Id(Long areaId);
    boolean existsByNameAndArea_Id(String name,Long areaId);
    TableRestaurant findTopByNameStartingWithOrderByNameDesc(String name);
}
