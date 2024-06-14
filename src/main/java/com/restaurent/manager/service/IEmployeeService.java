package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.EmployeeRequest;
import com.restaurent.manager.dto.response.EmployeeResponse;

public interface IEmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);
}
