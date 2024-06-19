package com.restaurent.manager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String foodName;
    float weight;
    String description;
    double money;
    @ManyToOne(fetch = FetchType.LAZY)
    DishCategory dishCategory;
    String imageUrl;
    boolean status;
    @ManyToOne(fetch = FetchType.LAZY)
    Unit unit;
    @OneToMany(mappedBy = "dish")
    Set<DishOrder> dishOrders;
    @ManyToOne(fetch = FetchType.LAZY)
    Account account;
}
