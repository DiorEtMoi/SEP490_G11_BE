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
public class RestaurantUpdateRequest {
    @Valid
    @NotNull(message = "id restaurant is require to update")
    private Long id;
    private String restaurantName;
    private String address;
    private String province;
    private String district;
    @NotNull(message = "id pack is require to update")
    private Long packId;
    @NotNull(message = "months is require for extend package")
    @Min(value = 1, message = "extend package months must be equal or greater than 1")
    private int months;
}
