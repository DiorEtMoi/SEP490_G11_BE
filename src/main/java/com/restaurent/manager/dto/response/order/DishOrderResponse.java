package com.restaurent.manager.dto.response.order;

import com.restaurent.manager.dto.response.DishResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishOrderResponse {
    DishResponse dish;
    int quantity;
    String status;
}
