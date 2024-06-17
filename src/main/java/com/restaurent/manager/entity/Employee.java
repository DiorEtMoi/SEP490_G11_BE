package com.restaurent.manager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    String employeeName;
    String email;
    String phoneNumber;
    @ManyToOne(fetch = FetchType.LAZY)
    Restaurant restaurant;
    @ManyToOne(fetch = FetchType.LAZY)
    Role role;
    @OneToMany(mappedBy = "employee",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    Set<Order> orders;
}
