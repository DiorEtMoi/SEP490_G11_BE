package com.restaurent.manager.dto.request.order;

import com.restaurent.manager.dto.response.CustomerResponse;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequest {
    Long tableId;
    Long employeeId;
    CustomerResponse customerResponse;
    Long restaurantId;
}
