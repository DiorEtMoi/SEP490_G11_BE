package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.entity.DishOrder;
import com.restaurent.manager.enums.DISH_ORDER_STATE;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.DishOrderMapper;
import com.restaurent.manager.repository.DishOrderRepository;
import com.restaurent.manager.service.IDishOrderService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
public class DishOrderService implements IDishOrderService {
    DishOrderRepository dishOrderRepository;
    DishOrderMapper dishOrderMapper;
    @Override
    public DishOrderResponse changeStatusDishOrderById(Long id, DISH_ORDER_STATE status) {
        DishOrder dishOrder = findById(id);
        dishOrder.setStatus(status);
        dishOrderRepository.save(dishOrder);
        return dishOrderMapper.toDishOrderResponse(dishOrder);
    }

    @Override
    public DishOrder findById(Long dishOrderId) {
        return dishOrderRepository.findById(dishOrderId).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        );
    }
}
