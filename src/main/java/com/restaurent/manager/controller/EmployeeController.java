package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.employee.EmployeeRequest;
import com.restaurent.manager.dto.request.employee.EmployeeUpdateRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.EmployeeResponse;
import com.restaurent.manager.service.IEmployeeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(value = "/api/employee")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {
    IEmployeeService employeeService;

    @PostMapping("/create")
    public ApiResponse<EmployeeResponse> createEmployee(@RequestBody @Valid EmployeeRequest req){
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.createEmployee(req))
                .build();
    }
    @PutMapping("/{employeeId}")
    public ApiResponse<EmployeeResponse> updateEmployee(@PathVariable Long employeeId,@RequestBody @Valid EmployeeUpdateRequest req){
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.updateEmployee(employeeId,req))
                .build();
    }
    @DeleteMapping("/delete/{employeeId}")
    public ApiResponse<Void> deleteEmployee(@PathVariable Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ApiResponse.<Void>builder()
                .message("Delete success")
                .build();
    }
    @GetMapping("/{employeeId}")
    public ApiResponse<EmployeeResponse> getEmployeeById(@PathVariable Long employeeId){
        return ApiResponse.<EmployeeResponse>builder()
                .result(employeeService.findEmployeeByIdConvertDTO(employeeId))
                .build();
    }
    @GetMapping("/restaurant/{accountId}")
    public ApiResponse<List<EmployeeResponse>> getEmployeesInRestaurantByAccountId(@PathVariable Long accountId){
        return  ApiResponse.<List<EmployeeResponse>>builder()
                .result(employeeService.findEmployeesByAccountId(accountId))
                .build();
    }
}
