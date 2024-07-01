package com.restaurent.manager.dto.request.employee;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeLoginRequest {
    String phoneNumberOfRestaurant;
    String username;
    String password;
}
