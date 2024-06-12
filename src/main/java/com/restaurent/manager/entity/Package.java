package com.restaurent.manager.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String packName;
    @OneToMany(mappedBy = "restaurantPackage",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    Set<Restaurant> restaurants;
    public void addRestaurant(Restaurant restaurant){
        restaurant.setRestaurantPackage(this);
        this.restaurants.add(restaurant);
    }
}
