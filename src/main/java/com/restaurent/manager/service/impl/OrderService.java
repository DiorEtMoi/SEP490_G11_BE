package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.order.OrderRequest;
import com.restaurent.manager.dto.response.order.OrderResponse;
import com.restaurent.manager.entity.Order;
import com.restaurent.manager.entity.TableRestaurant;
import com.restaurent.manager.mapper.OrderMapper;
import com.restaurent.manager.repository.OrderRepository;
import com.restaurent.manager.repository.TableRestaurantRepository;
import com.restaurent.manager.service.IEmployeeService;
import com.restaurent.manager.service.IOrderService;
import com.restaurent.manager.service.IRestaurantService;
import com.restaurent.manager.service.ITableRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    OrderRepository orderRepository;
    IEmployeeService employeeService;
    ITableRestaurantService tableRestaurantService;
    IRestaurantService restaurantService;
    TableRestaurantRepository tableRestaurantRepository;
    OrderMapper orderMapper;
    @Override
    public OrderResponse createOrder(OrderRequest request) {
        TableRestaurant tableRestaurant = tableRestaurantService.findById(request.getTableId());
        Order order = new Order();
        order.setRestaurant(restaurantService.getRestaurantById(request.getRestaurantId()));
        order.setTableRestaurant(tableRestaurant);
        order.setEmployee(employeeService.findEmployeeById(request.getEmployeeId()));
        order.setOrderDate(LocalDate.now());
        Order orderSaved = orderRepository.save(order);
        tableRestaurant.setOrderCurrent(orderSaved.getId());
        tableRestaurantRepository.save(tableRestaurant);
        return orderMapper.toOrderResponse(orderSaved);
    }
}
