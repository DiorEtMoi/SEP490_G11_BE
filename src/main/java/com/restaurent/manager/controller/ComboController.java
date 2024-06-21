package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.Combo.ComboRequest;
import com.restaurent.manager.dto.request.Combo.ComboUpdateRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.Combo.ComboResponse;
import com.restaurent.manager.dto.response.CustomerResponse;
import com.restaurent.manager.service.IComboService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/combos")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class ComboController {
    private final IComboService comboService;

    @PostMapping
    public ComboResponse createCombo(@RequestBody @Valid ComboRequest comboRequest) {
        return comboService.createCombo(comboRequest);
    }

    @GetMapping("/getAllCombos")
    public List<ComboResponse> getAllCombos() {
        return comboService.getAllCombos();
    }

    @PutMapping("update")
    public ApiResponse<ComboResponse> updateCombo(@RequestBody @Valid ComboUpdateRequest comboUpdateRequest){
        return ApiResponse.<ComboResponse>builder()
                .result(comboService.updateCombo(comboUpdateRequest))
                .build();
    }

    @GetMapping("/getDetail")
    public ApiResponse<ComboResponse> getComboDetail(@RequestParam long id){
        return ApiResponse.<ComboResponse>builder()
                .result(comboService.getComboById(id))
                .build();
    }
}