package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.UnitRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.UnitResponse;
import com.restaurent.manager.service.IUnitService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@RequestMapping(value = "/api/unit")
@SecurityRequirement(name = "bearerAuth")
public class UnitController {

    IUnitService unitService;
    @GetMapping(value = "/account/{accountId}")
    public ApiResponse<List<UnitResponse>> getUnitsByAccountId(@PathVariable Long accountId){
        return ApiResponse.<List<UnitResponse>>builder()
                .result(unitService.getUnitsByAccountId(accountId))
                .build();
    }
    @PostMapping(value = "/create")
    public ApiResponse<UnitResponse> createUnit(@RequestBody UnitRequest request){
        return ApiResponse.<UnitResponse>builder()
                .result(unitService.createUnit(request))
                .build();
    }
    @PutMapping(value = "/{unitId}")
    public ApiResponse<UnitResponse> updateUnit(@PathVariable Long unitId,@RequestBody UnitRequest request){
        return ApiResponse.<UnitResponse>builder()
                .result(unitService.updateUnit(unitId,request))
                .build();
    }
    @DeleteMapping(value = "/{unitId}")
    public ApiResponse<Void> deleteUnitById(@PathVariable Long unitId){
        unitService.deleteUnitById(unitId);
        return ApiResponse.<Void>builder()
                .message("Delete success")
                .build();
    }
}
