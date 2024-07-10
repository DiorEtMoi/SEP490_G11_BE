package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.Customer.CustomerRequest;
import com.restaurent.manager.dto.request.Customer.CustomerUpdateRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.CustomerResponse;
import com.restaurent.manager.service.ICustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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
    public ApiResponse<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.createCustomer(customerRequest))
                .build();
    }

    @PutMapping("update")
    public ApiResponse<CustomerResponse> updateCustomer(@RequestBody @Valid CustomerUpdateRequest customerUpdateRequest) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.updateCustomer(customerUpdateRequest))
                .build();
    }

    @GetMapping("getDetail{id}")
    public ApiResponse<CustomerResponse> getCustomerDetail(@RequestParam long id) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.getCustomerById(id))
                .build();
    }

    @GetMapping("/rankingCustomer{restaurantID}")
    public List<CustomerResponse> getCustomersOrderByTotalPoint(@RequestParam long id) {
        return customerService.getCustomersOrderByTotalPoint(id);
    }
    @GetMapping(value = "/{phoneNumber}")
    public ApiResponse<CustomerResponse> findCustomerByPhoneNumber(@PathVariable String phoneNumber){
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.findCustomerByPhoneNumber(phoneNumber))
                .build();
    }


}
