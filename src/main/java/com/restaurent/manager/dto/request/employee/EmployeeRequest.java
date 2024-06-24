package com.restaurent.manager.dto.request.employee;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {
    String username;
    String password;
    String phoneNumber;
    String employeeName;
    Long accountId;
    Long roleId;
}
