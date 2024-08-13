package com.restaurent.manager.dto.request.dish;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishRequest {
    @NotNull(message = "NOT_EMPTY")
    @NotBlank(message = "NOT_EMPTY")
    String name;
    @NotNull(message = "NOT_NULL")
    @Min(value = 1, message = "GREATER_NUMBER")
    float weight;
    @NotNull(message = "NOT_EMPTY")
    @NotBlank(message = "NOT_EMPTY")
    String description;
    @NotNull(message = "NOT_NULL")
    @Min(value = 1, message = "GREATER_NUMBER")
    double price;
    @NotNull(message = "NOT_NULL")
    Long dishCategoryId;
    @NotNull(message = "NOT_EMPTY")
    @NotBlank(message = "NOT_EMPTY")
    String imageUrl;
    @NotNull(message = "NOT_NULL")
    Long unitId;
    @NotNull(message = "NOT_NULL")
    Long accountId;
}
