package com.restaurent.manager.dto.request;

import com.restaurent.manager.dto.request.order.DishOrderRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleRequest {
    @NotNull(message = "INVALID")
    @NotBlank(message = "INVALID")
    String customerName;
    @NotNull(message = "INVALID")
    @NotBlank(message = "INVALID")
    String customerPhone;
    String note;
    @NotNull(message = "INVALID")
    LocalDate bookedDate;
    @NotNull(message = "INVALID")
    String time;
    @Min(value = 1,message = "GREATER_NUMBER")
    double deposit;
    @Min(value = 10,message = "GREATER_NUMBER")
    Long intendTimeMinutes;
    @Min(value = 1,message = "GREATER_NUMBER")
    int numbersOfCustomer;
    @NotNull(message = "INVALID")
    List<Long> tables;
    @NotNull(message = "INVALID")
    List<DishOrderRequest> scheduleDishes;
}
