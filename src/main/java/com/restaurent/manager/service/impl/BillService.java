package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.BillRequest;
import com.restaurent.manager.dto.response.BillResponse;
import com.restaurent.manager.entity.Bill;
import com.restaurent.manager.entity.Order;
import com.restaurent.manager.entity.TableRestaurant;
import com.restaurent.manager.mapper.BillMapper;
import com.restaurent.manager.repository.BillRepository;
import com.restaurent.manager.repository.TableRestaurantRepository;
import com.restaurent.manager.service.IBillService;
import com.restaurent.manager.service.IOrderService;
import com.restaurent.manager.service.ITableRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class BillService implements IBillService {
    IOrderService orderService;
    BillMapper billMapper;
    BillRepository billRepository;
    ITableRestaurantService tableRestaurantService;
    TableRestaurantRepository tableRestaurantRepository;
    @Override
    public BillResponse createBill(Long orderId, BillRequest request) {
        Order order = orderService.findOrderById(orderId);
        TableRestaurant tableRestaurant = tableRestaurantService.findById(order.getTableRestaurant().getId());
        tableRestaurant.setOrderCurrent(null);
        tableRestaurantRepository.save(tableRestaurant);
        Bill bill = billMapper.toBill(request);
        bill.setOrder(order);
        bill.setDateCreated(LocalDateTime.now());
        return billMapper.toBillResponse(billRepository.save(bill));
    }
}
