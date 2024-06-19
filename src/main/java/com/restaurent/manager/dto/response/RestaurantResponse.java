package com.restaurent.manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantResponse {
    private Long id;
    private String restaurantName;
    private String address;
    private String province;
    private String district;
    private PackageResponse restaurantPackage;
    private LocalDateTime expiryDate;
    private String token;
}
