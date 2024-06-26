package com.restaurent.manager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "order_restaurant")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    Employee employee;
    @ManyToOne(fetch = FetchType.LAZY)
    Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    TableRestaurant tableRestaurant;
    LocalDate orderDate;
    @OneToOne(mappedBy = "order")
    Bill bill;
    @OneToMany(mappedBy = "order",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    Set<DishOrder> dishOrders;
    @ManyToOne(fetch = FetchType.LAZY)
    Restaurant restaurant;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        return id != null && id.equals(((Order) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
