package com.restaurent.manager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    int numberChairs;
    Long orderCurrent;
    @ManyToOne(fetch = FetchType.LAZY)
    TableType tableType;
    @OneToMany(mappedBy = "tableRestaurant",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    Set<Order> orders;
    @ManyToOne(fetch = FetchType.LAZY)
    Area area;
    float positionX;
    float positionY;
}
