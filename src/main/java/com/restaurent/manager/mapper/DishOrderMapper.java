package com.restaurent.manager.mapper;

import com.restaurent.manager.dto.request.order.DishOrderRequest;
import com.restaurent.manager.dto.response.order.DishOrderResponse;
import com.restaurent.manager.entity.DishOrder;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DishOrderMapper {
    DishOrder toDishOrder(DishOrderRequest req);
    DishOrderResponse toDishOrderResponse(DishOrder dishOrder);
}
