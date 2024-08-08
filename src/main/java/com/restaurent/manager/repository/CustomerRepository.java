package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByPhoneNumberAndRestaurantId(String phoneNumber, Long restaurantId);


    @Query("SELECT c FROM Customer c WHERE c.restaurant.id = :restaurantId ORDER BY c.totalPoint DESC")
    List<Customer> findAllOrderByTotalPointDesc(Long restaurantId);
    Optional<Customer> findByPhoneNumber(String phoneNumber);
    @Query("select c from Customer  c where c.restaurant.id = :restaurantId and DATE (c.dateCreated) = CURRENT_DATE")
    List<Customer> findCustomerByRestaurant_IdInToday(Long restaurantId);
    @Query("select c from Customer c where c.restaurant.id = :restaurantId and DATE (c.dateCreated) = :date")
    List<Customer> findCustomerByRestaurant_IdInYesterday(Long restaurantId, Date date);
    @Query("select c from Customer c where c.restaurant.id = :restaurantId and c.dateCreated between :startDate and :endDate")
    List<Customer> findCustomerByRestaurantIdInStartDateAndEndDate(@Param("restaurantId") Long restaurantId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    boolean existsByPhoneNumber(String phoneNumber);
}
