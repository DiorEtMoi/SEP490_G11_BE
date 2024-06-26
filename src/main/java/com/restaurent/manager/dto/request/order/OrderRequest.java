package com.restaurent.manager.dto.request.order;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    Long tableId;
    Long employeeId;
    Long restaurantId;
}
