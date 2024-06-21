package com.restaurent.manager.dto.response;

import lombok.Data;

@Data
public class DishResponse {
    Long id;
    String foodName;
    float weight;
    String description;
    double money;
    String imageUrl;
    boolean status;
}
