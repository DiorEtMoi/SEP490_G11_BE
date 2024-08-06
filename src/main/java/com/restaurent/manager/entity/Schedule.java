package com.restaurent.manager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String customerName;
    String customerPhone;
    String note;
    LocalDate bookedDate;
    LocalTime time;
    double deposit;
    LocalTime intendTime;
    int numbersOfCustomer;
    @ManyToMany
    Set<TableRestaurant> tableRestaurants;
    @ManyToOne
    Restaurant restaurant;
}
