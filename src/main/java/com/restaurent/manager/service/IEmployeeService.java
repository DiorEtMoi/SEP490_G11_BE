package com.restaurent.manager.service;

import com.nimbusds.openid.connect.sdk.AuthenticationRequest;
import com.restaurent.manager.dto.request.employee.EmployeeLoginRequest;
import com.restaurent.manager.dto.request.employee.EmployeeRequest;
import com.restaurent.manager.dto.request.employee.EmployeeUpdateInformationRequest;
import com.restaurent.manager.dto.request.employee.EmployeeUpdateRequest;
import com.restaurent.manager.dto.response.AuthenticationResponse;
import com.restaurent.manager.dto.response.EmployeeResponse;
import com.restaurent.manager.entity.Employee;

import java.util.List;

public interface IEmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);
    EmployeeResponse updateEmployee(Long employeeId, EmployeeUpdateInformationRequest request);
    Employee findEmployeeById(Long id);
    EmployeeResponse findEmployeeByIdConvertDTO(Long id);
    void deleteEmployee(Long id);
    List<EmployeeResponse> findEmployeesByAccountId(Long accountId);
    AuthenticationResponse authenticated(EmployeeLoginRequest request);
    void updateEmployeePassword(Long employeeId, String newPassword);
}
