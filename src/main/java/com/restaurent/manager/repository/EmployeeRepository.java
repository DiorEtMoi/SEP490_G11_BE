package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findByRestaurant_Id(Long restaurantId);
    boolean existsByUsername(String userName);
}
