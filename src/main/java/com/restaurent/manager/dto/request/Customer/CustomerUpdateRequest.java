package com.restaurent.manager.dto.request.Customer;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.AccessLevel;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerUpdateRequest {
    Long id;
    String phoneNumber;
    String name;
    String address;
    Long restaurantId;
}
