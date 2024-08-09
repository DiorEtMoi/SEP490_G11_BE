package com.restaurent.manager.dto.request;

import com.restaurent.manager.enums.MethodPayment;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BillRequest {
    @Min(value = 0, message = "GREATER_NUMBER")
    double total;
    @NotNull(message = "NOT_EMPTY")
    @NotBlank(message = "NOT_EMPTY")
    MethodPayment methodPayment;
}
