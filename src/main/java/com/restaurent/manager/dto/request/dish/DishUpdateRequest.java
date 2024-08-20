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
    @NotBlank(message = "NAME_EMPTY")
    String name;
    @NotNull(message = "NOT_EMPTY")
    boolean status;
    @NotNull(message = "NOT_EMPTY")
    String description;
    @Min(value = 0, message = "GREATER_NUMBER")
    double price;
    @NotNull(message = "NOT_NULL")
    Long dishCategoryId;
    @NotNull(message = "NOT_EMPTY")
    String imageUrl;
    @NotNull(message = "NOT_NULL")
    Long unitId;
}
