package com.restaurent.manager.dto.request.dish;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishRequest {
    String name;
    float weight;
    boolean status;
    String description;
    double price;
    Long dishCategoryId;
    String imageUrl;
    Long unitId;
    Long accountId;
}
