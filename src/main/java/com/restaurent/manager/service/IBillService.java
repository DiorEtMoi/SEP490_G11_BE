package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.BillRequest;
import com.restaurent.manager.dto.response.BillResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBillService {
    BillResponse createBill(Long orderId,BillRequest request);
    List<BillResponse> getBillsByRestaurantId(Long restaurantId,Pageable pageable);
}
