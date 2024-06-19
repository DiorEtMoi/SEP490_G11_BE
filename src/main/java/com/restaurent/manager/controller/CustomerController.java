package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.Customer.CustomerRequest;
import com.restaurent.manager.dto.request.Customer.CustomerUpdateRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.CustomerResponse;
import com.restaurent.manager.service.ICustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping("create")
    public ApiResponse<CustomerResponse> createCustomer(@RequestBody CustomerRequest customerRequest) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.createCustomer(customerRequest))
                .build();
    }

    @PutMapping("update")
    public ApiResponse<CustomerResponse> updateCustomer(@RequestBody CustomerUpdateRequest customerUpdateRequest) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.updateCustomer(customerUpdateRequest))
                .build();
    }

    @GetMapping("getDetail")
    public ApiResponse<CustomerResponse> getCustomerDetail(@RequestParam long id) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.getCustomerById(id))
                .build();
    }

    @GetMapping("/rankingCustomer")
    public List<CustomerResponse> getCustomersOrderByTotalPoint() {
        return customerService.getCustomersOrderByTotalPoint();
    }


}
