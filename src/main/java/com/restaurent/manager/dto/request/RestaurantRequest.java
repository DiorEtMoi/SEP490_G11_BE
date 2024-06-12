package com.restaurent.manager.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {
    private String restaurantName;
    private Long accountId;
    private String address;
    private String province;
    private String district;
}
