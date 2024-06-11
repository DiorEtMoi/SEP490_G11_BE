package com.restaurent.manager.repository;

import com.restaurent.manager.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    boolean existsByPhoneNumber(String phoneNumber);
    Optional<Account> findByPhoneNumberAndStatus(String  phoneNumber,boolean status);
    Optional<Account> findByUsername(String userName);
}
