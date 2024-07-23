package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Bill;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> findByOrder_Restaurant_Id(Long restaurantId, Pageable pageable);
}
