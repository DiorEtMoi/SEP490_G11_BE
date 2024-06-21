package com.restaurent.manager.dto.response.Combo;

import com.restaurent.manager.dto.response.AccountResponse;
import com.restaurent.manager.dto.response.DishResponse;
import lombok.Data;

import java.util.Set;

@Data
public class ComboResponse {
    Long id;
    String comboName;
    double comboPrice;
    String description;
    boolean status;
    Set<DishResponse> dishes;
    AccountResponse account;
}