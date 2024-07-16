package com.restaurent.manager.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableRestaurantResponse {
    Long id;
    String name;
    TableTypeResponse tableType;
    float positionX;
    float positionY;
    Long orderCurrent;
}
