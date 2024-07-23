package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.BillRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.BillResponse;
import com.restaurent.manager.service.IBillService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(makeFinal = true)
@RequestMapping(value = "/api/bill")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class BillController {
    IBillService billService;
    @PostMapping(value = "/create/order/{orderId}")
    public ApiResponse<BillResponse> createBill(@PathVariable Long orderId, @RequestBody BillRequest request){
        return ApiResponse.<BillResponse>builder()
                .result(billService.createBill(orderId,request))
                .build();
    }
}
