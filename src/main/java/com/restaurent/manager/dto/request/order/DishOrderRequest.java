package com.restaurent.manager.dto.request.order;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class DishOrderRequest {
    Long dishId;
    Long comboId;
    int quantity;
}
