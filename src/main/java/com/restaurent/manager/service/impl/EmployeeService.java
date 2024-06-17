package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.EmployeeRequest;
import com.restaurent.manager.dto.response.EmployeeResponse;
import com.restaurent.manager.entity.Employee;
import com.restaurent.manager.entity.Restaurant;
import com.restaurent.manager.entity.Role;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.EmployeeMapper;
import com.restaurent.manager.repository.RestaurantRepository;
import com.restaurent.manager.repository.RoleRepository;
import com.restaurent.manager.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmployeeService implements IEmployeeService {

    RestaurantRepository restaurantRepository;
    RoleRepository roleRepository;
    EmployeeMapper employeeMapper;
    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Employee employee = employeeMapper.toEmployee(request);
        Restaurant restaurant = restaurantRepository.findByAccount_Id(request.getAccountId());
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED)
        );
        restaurant.addEmployee(employee);
        role.assginEmployee(employee);
        restaurantRepository.save(restaurant);
        roleRepository.save(role);
        return employeeMapper.toEmployeeResponse(employee);
    }
}
