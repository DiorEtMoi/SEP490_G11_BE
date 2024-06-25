package com.restaurent.manager.dto.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TableRestaurantResponse {
    Long id;
    String name;
    int numberChairs;
    TableTypeResponse tableType;
    float positionX;
    float positionY;
}
