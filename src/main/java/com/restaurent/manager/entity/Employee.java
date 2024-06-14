package com.restaurent.manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    String employeeId;
    String username;
    String password;
    String employeeName;
    String email;
    String phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    Restaurant restaurant;
    @ManyToOne(fetch = FetchType.LAZY)
    Role role;
}
