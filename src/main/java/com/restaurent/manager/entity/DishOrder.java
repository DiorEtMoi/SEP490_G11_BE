package com.restaurent.manager.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    Dish dish;
    @ManyToOne(fetch = FetchType.LAZY)
    Order order;
    int quantity;
    boolean status;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DishOrder )) return false;
        return id != null && id.equals(((DishOrder) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
