package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.EmployeeRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.EmployeeResponse;
import com.restaurent.manager.service.IEmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(value = "/api/employee")
public class EmployeeController {
    IEmployeeService employeeService;

    @PostMapping("/create")
    public ApiResponse<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest req){
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.createEmployee(req))
                .build();
    }
}
