package com.restaurent.manager.dto.request.Customer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)

public class CustomerRequest {
     String phoneNumber;
     String name;
     String address;
     Long restaurantId;
}
