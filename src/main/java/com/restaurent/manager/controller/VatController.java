package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.VatRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.entity.Vat;
import com.restaurent.manager.service.IVatService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/vat")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@FieldDefaults(makeFinal = true)
public class VatController {
    IVatService vatService;
    @PostMapping(value = "/restaurant/{restaurantId}/create")
    public ApiResponse<Vat> createVatForRestaurant(@PathVariable Long restaurantId, @RequestBody @Valid VatRequest request){
        return ApiResponse.<Vat>builder()
                .result(vatService.createVat(restaurantId,request))
                .build();
    }
    @PutMapping(value = "/{vatId}")
    public ApiResponse<Vat> updateVat(@PathVariable Long vatId, @RequestBody @Valid VatRequest request){
        return ApiResponse.<Vat>builder()
                .result(vatService.updateVatInformation(vatId,request))
                .build();
    }

}
