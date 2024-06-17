package com.restaurent.manager.mapper;

import com.restaurent.manager.dto.request.AccountRequest;
import com.restaurent.manager.dto.request.EmployeeRequest;
import com.restaurent.manager.dto.response.AccountResponse;
import com.restaurent.manager.dto.response.EmployeeResponse;
import com.restaurent.manager.entity.Account;
import com.restaurent.manager.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeRequest request);
    EmployeeResponse toEmployeeResponse(Employee employee);
}
