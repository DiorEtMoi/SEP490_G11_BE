package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.RestaurantPackagePaymentHistoryRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.service.IRestaurantPackagePaymentHistoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/package-history")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
public class PackageHistoryController {
    IRestaurantPackagePaymentHistoryService service;
    @PostMapping(value = "/create")
    public ApiResponse<Void> create(@RequestBody RestaurantPackagePaymentHistoryRequest request){
        service.createRestaurantPackagePaymentHistory(request);
        return ApiResponse.<Void>builder()
                .message("create success")
                .build();
    }
    @GetMapping(value = "/new-id")
    public ApiResponse<Long> getNewID(){
        return ApiResponse.<Long>builder()
                .result(service.getNewId())
                .build();
    }

}
