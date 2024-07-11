package com.restaurent.manager.entity;

import com.restaurent.manager.enums.DISH_ORDER_STATE;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    Dish dish;
    @ManyToOne(fetch = FetchType.LAZY)
    Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    Combo combo;
    int quantity;
    @Enumerated(EnumType.STRING)
    DISH_ORDER_STATE status;
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
