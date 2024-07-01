package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.BillRequest;
import com.restaurent.manager.dto.response.BillResponse;

public interface IBillService {
    BillResponse createBill(Long orderId,BillRequest request);
}
