package com.restaurent.manager.dto.request.order;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DishOrderAddRequest {
   List<DishOrderRequest> dishOrderRequests;
   Long orderId;
}
