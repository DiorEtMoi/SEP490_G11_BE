package com.restaurent.manager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Combo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String comboName;
    double comboPrice;
    String description;
    boolean status;
    @ManyToMany
    Set<Dish> dishes;
}
