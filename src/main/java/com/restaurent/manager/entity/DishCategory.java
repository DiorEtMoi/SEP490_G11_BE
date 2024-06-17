package com.restaurent.manager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    @OneToMany(mappedBy = "dishCategory",
    cascade = CascadeType.ALL
    )
    Set<Dish> dishes;
}
