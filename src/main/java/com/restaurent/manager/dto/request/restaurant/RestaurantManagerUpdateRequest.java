package com.restaurent.manager.dto.request.restaurant;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantManagerUpdateRequest {
    private String restaurantName;
    private String address;
    private String province;
    private String district;
}
