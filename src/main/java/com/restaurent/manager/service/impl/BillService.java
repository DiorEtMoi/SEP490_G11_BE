package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.BillRequest;
import com.restaurent.manager.dto.response.BillResponse;
import com.restaurent.manager.entity.Bill;
import com.restaurent.manager.entity.Order;
import com.restaurent.manager.mapper.BillMapper;
import com.restaurent.manager.repository.BillRepository;
import com.restaurent.manager.service.IBillService;
import com.restaurent.manager.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class BillService implements IBillService {
    IOrderService orderService;
    BillMapper billMapper;
    BillRepository billRepository;
    @Override
    public BillResponse createBill(Long orderId, BillRequest request) {
        Order order = orderService.findOrderById(orderId);
        Bill bill = billMapper.toBill(request);
        bill.setOrder(order);
        return billMapper.toBillResponse(billRepository.save(bill));
    }
}
