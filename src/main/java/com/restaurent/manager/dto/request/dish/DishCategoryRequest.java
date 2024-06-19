package com.restaurent.manager.dto.request.dish;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishCategoryRequest {
    String name;
    String description;
    Long accountId;
}
