package com.restaurent.manager.dto.request.dish;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishUpdateRequest {
    @NotBlank(message = "NOT_EMPTY")
    String name;
    @NotNull(message = "NOT_EMPTY")
    @Min(value = 0, message = "GREATER_NUMBER")
    float weight;
    @NotNull(message = "NOT_EMPTY")
    boolean status;
    @NotNull(message = "NOT_EMPTY")
    String description;
    @Min(value = 0, message = "GREATER_NUMBER")
    double price;
    Long dishCategoryId;
    @NotNull(message = "NOT_EMPTY")
    String imageUrl;
    Long unitId;
}
