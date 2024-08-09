package com.restaurent.manager.dto.request.dish;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishCategoryRequest {
    @NotNull(message = "NOT_EMPTY")
    @NotBlank(message = "NOT_EMPTY")
    String name;
    @NotNull(message = "NOT_EMPTY")
    @NotBlank(message = "NOT_EMPTY")
    String description;
    @NotNull(message = "NOT_NULL")
    Long accountId;
}
