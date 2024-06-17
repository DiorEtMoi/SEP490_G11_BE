package com.restaurent.manager.dto.request.employee;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeUpdateRequest {
    Long id;
    String password;
    String employeeName;
    Long roleId;
}
