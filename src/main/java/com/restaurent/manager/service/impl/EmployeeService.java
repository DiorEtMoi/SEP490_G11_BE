package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.employee.EmployeeRequest;
import com.restaurent.manager.dto.request.employee.EmployeeUpdateRequest;
import com.restaurent.manager.dto.response.EmployeeResponse;
import com.restaurent.manager.entity.Employee;
import com.restaurent.manager.entity.Restaurant;
import com.restaurent.manager.entity.Role;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.EmployeeMapper;
import com.restaurent.manager.repository.EmployeeRepository;
import com.restaurent.manager.repository.RestaurantRepository;
import com.restaurent.manager.repository.RoleRepository;
import com.restaurent.manager.service.IEmployeeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmployeeService implements IEmployeeService {

    RestaurantRepository restaurantRepository;
    RoleRepository roleRepository;
    EmployeeMapper employeeMapper;
    EmployeeRepository employeeRepository;
    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Restaurant restaurant = restaurantRepository.findByAccount_Id(request.getAccountId());
        if(employeeRepository.existsByUsernameAndRestaurant_Id(request.getUsername(),restaurant.getId())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        Employee employee = employeeMapper.toEmployee(request);
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED)
        );
        restaurant.addEmployee(employee);
        role.assginEmployee(employee);
        employeeRepository.save(employee);
        return employeeMapper.toEmployeeResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeUpdateRequest request) {
        Employee employee = findEmployeeById(request.getId());
        employeeMapper.updateRestaurant(employee,request);
        return employeeMapper.toEmployeeResponse(employeeRepository.save(employee));
    }

    @Override
    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
    }

    @Override
    public EmployeeResponse findEmployeeByIdConvertDTO(Long id) {
        return employeeMapper.toEmployeeResponse(findEmployeeById(id));
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeById(id);
        employeeRepository.delete(employee);
    }

    @Override
    public List<EmployeeResponse> findEmployeesByAccountId(Long accountId) {
        Restaurant restaurant = restaurantRepository.findByAccount_Id(accountId);
        if(restaurant == null){
            throw new AppException(ErrorCode.NOT_EXIST);
        }
        return employeeRepository.findByRestaurant_Id(restaurant.getId()).stream().map(employeeMapper::toEmployeeResponse).toList();
    }
}
