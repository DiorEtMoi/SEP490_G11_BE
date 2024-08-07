package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.Customer.CustomerRequest;
import com.restaurent.manager.dto.request.Customer.CustomerUpdateRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.CustomerResponse;
import com.restaurent.manager.service.ICustomerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @PreAuthorize(value = "hasAnyRole('MANAGER', 'WAITER') and hasAuthority('CUSTOMER')")
    @PostMapping("create")
    public ApiResponse<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.createCustomer(customerRequest))
                .build();
    }
    @PreAuthorize(value = "hasAnyRole('MANAGER', 'WAITER') and hasAuthority('CUSTOMER')")
    @PutMapping("update")
    public ApiResponse<CustomerResponse> updateCustomer(@RequestBody @Valid CustomerUpdateRequest customerUpdateRequest) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.updateCustomer(customerUpdateRequest))
                .build();
    }
    @PreAuthorize(value = "hasAnyRole('MANAGER', 'WAITER', 'HOSTESS') and hasAuthority('CUSTOMER')")

    @GetMapping("getDetail/{id}")
    public ApiResponse<CustomerResponse> getCustomerDetail(@PathVariable long id) {
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.getCustomerById(id))
                .build();
    }
    @PreAuthorize(value = "hasAnyRole('MANAGER', 'WAITER') and hasAuthority('CUSTOMER')")
    @GetMapping("/rankingCustomer/{restaurantID}")
    public List<CustomerResponse> getCustomersOrderByTotalPoint(@PathVariable long restaurantID) {
        return customerService.getCustomersOrderByTotalPoint(restaurantID);
    }
    @PreAuthorize(value = "hasAnyRole('MANAGER', 'WAITER') and hasAuthority('CUSTOMER')")
    @GetMapping(value = "/{phoneNumber}")
    public ApiResponse<CustomerResponse> findCustomerByPhoneNumber(@PathVariable String phoneNumber){
        return ApiResponse.<CustomerResponse>builder()
                .result(customerService.findCustomerResponseByPhoneNumber(phoneNumber))
                .build();
    }


}
