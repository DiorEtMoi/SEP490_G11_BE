package com.restaurent.manager.repository;

import com.restaurent.manager.entity.DishCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DishCategoryRepository extends JpaRepository<DishCategory,Long> {
    boolean existsByNameAndAccount_Id(String name,Long accountId);
    List<DishCategory> findByAccount_Id(Long accountId);
    Optional<DishCategory> findByCode(String code);
}
