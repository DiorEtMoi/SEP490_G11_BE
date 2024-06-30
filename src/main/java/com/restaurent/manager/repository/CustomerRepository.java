package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneNumberAndRestaurantId(String phoneNumber, Long restaurantId);

    @Query("SELECT c FROM Customer c WHERE c.restaurant.id = :restaurantId ORDER BY c.totalPoint DESC")
    List<Customer> findAllOrderByTotalPointDesc(Long restaurantId);
}
