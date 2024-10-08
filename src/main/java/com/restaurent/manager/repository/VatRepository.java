package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Vat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VatRepository extends JpaRepository<Vat,Long> {
    Optional<Vat> findByRestaurantId(Long restaurantId);
}
