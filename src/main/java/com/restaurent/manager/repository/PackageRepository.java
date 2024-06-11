package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Package;
import com.restaurent.manager.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package,Integer> {

}
