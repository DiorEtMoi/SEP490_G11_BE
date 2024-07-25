package com.restaurent.manager.repository;

import com.restaurent.manager.entity.RestaurantPackagePaymentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantPackagePaymentHistoryRepository extends JpaRepository<RestaurantPackagePaymentHistory,Long> {
}
