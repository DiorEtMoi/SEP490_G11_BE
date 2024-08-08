package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.Customer.CustomerRequest;
import com.restaurent.manager.dto.request.Customer.CustomerUpdateRequest;
import com.restaurent.manager.dto.response.CustomerResponse;
import com.restaurent.manager.entity.Customer;

import java.util.List;

public interface ICustomerService {
    CustomerResponse createCustomer(CustomerRequest request);

    CustomerResponse updateCustomer(CustomerUpdateRequest request);

    CustomerResponse getCustomerById(Long id); // New method declaration

    List<CustomerResponse> getCustomersOrderByTotalPoint(Long id);
    CustomerResponse findCustomerResponseByPhoneNumber(String phoneNumber);
    Customer findCustomerByPhoneNumber(String phoneNumber);
    boolean existCustomerByPhoneNumber(String phone);
}
