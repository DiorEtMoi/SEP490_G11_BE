package com.restaurent.manager.dto.request;

import com.restaurent.manager.enums.MethodPayment;
import lombok.Data;

@Data
public class BillRequest {
    double total;
    MethodPayment methodPayment;
}
