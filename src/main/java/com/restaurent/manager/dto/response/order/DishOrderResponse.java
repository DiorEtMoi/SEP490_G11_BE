package com.restaurent.manager.dto.response.order;

import com.restaurent.manager.dto.response.DishResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishOrderResponse {
    Long id;
    DishResponse dish;
    OrderResponse order;
    int quantity;
    String status;
}
