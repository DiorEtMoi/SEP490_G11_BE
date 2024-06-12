package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsByEmail(String email);
    Optional<Account> findByEmailAndStatus(String email,boolean status);
    Optional<Account> findByEmail(String userName);
}
