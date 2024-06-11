package com.restaurent.manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String restaurantName;
    private String address;
    private String province;
    private String district;
    @ManyToOne(fetch = FetchType.LAZY)
    private Package restaurantPackage;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Restaurant )) return false;
        return id != null && id.equals(((Restaurant) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
