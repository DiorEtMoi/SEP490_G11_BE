package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Bill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findByOrder_Restaurant_Id(Long restaurantId, Pageable pageable);
    @Query("select b from Order o join o.bill b where o.restaurant.id = :restaurantId and DATE(b.dateCreated) = :date")
    List<Bill> findByDateCreated(@Param("restaurantId") Long restaurantId,@Param("date") Date date);
    @Query("select b from Order o join o.bill b where o.restaurant.id = :restaurantId and b.dateCreated between :startDate and :endDate")
    List<Bill> findByDateCreatedBetween(@Param("restaurantId") Long restaurantId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
