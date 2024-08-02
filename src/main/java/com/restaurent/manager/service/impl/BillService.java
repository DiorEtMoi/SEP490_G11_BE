package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.BillRequest;
import com.restaurent.manager.dto.response.BillResponse;
import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.entity.Bill;
import com.restaurent.manager.entity.Order;
import com.restaurent.manager.entity.Restaurant;
import com.restaurent.manager.entity.TableRestaurant;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.BillMapper;
import com.restaurent.manager.repository.BillRepository;
import com.restaurent.manager.repository.TableRestaurantRepository;
import com.restaurent.manager.service.IBillService;
import com.restaurent.manager.service.IOrderService;
import com.restaurent.manager.service.IRestaurantService;
import com.restaurent.manager.service.ITableRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    IRestaurantService restaurantService;
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

    @Override
    public List<BillResponse> getBillsByRestaurantId(Long restaurantId,Pageable pageable) {
        return billRepository.findByOrder_Restaurant_Id(restaurantId,pageable).stream().map(
                billMapper::toBillResponse
        ).toList();
    }

    @Override
    public List<DishOrderResponse> getDetailBillByBillId(Long billId,Pageable pageable) {
        Bill bill = findBillById(billId);
        return orderService.findDishByOrderId(bill.getOrder().getId(),pageable);
    }

    @Override
    public Bill findBillById(Long billId) {
        return billRepository.findById(billId).orElseThrow(() -> new AppException(ErrorCode.NOT_EXIST));
    }

    @Override
    public double getProfitRestaurantByIdAndDate(Long resId,LocalDateTime date) {
        Date sqlDate = Date.valueOf(date.toLocalDate());
        List<Bill> bills = billRepository.findByDateCreated(resId,sqlDate);
        double results = 0;
        if(!bills.isEmpty()){
            for (Bill bill : bills){
                results += bill.getTotal();
            }
            return results;
        }
        return 0;
    }

    @Override
    public double getProfitRestaurantByIdAndDateBetween(Long resId, LocalDateTime start, LocalDateTime end) {
        List<Bill> bills = billRepository.findByDateCreatedBetween(resId, start, end);
        double results = 0;
        if(!bills.isEmpty()){
            for (Bill bill : bills){
                results += bill.getTotal();
            }
            return results;
        }
        return 0;
    }

    @Override
    public double getVatValueForRestaurant(Long resId, LocalDateTime start, LocalDateTime end) {
        List<Bill> bills = billRepository.findByDateCreatedBetween(resId, start, end);
        Restaurant restaurant = restaurantService.getRestaurantById(resId);
        double res = 0;
        if(!bills.isEmpty() && restaurant.getVat().getTaxValue() != 0){
            for (Bill bill : bills){
                res += bill.getTotal() * (restaurant.getVat().getTaxValue() / 110);
            }
            return res;
        }
        return 0;
    }
}
