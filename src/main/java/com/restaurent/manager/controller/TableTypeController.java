package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.TableTypeRequest;
import com.restaurent.manager.dto.request.TableTypeUpdateRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.TableTypeResponse;
import com.restaurent.manager.service.ITableTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequestMapping(value = "/api/table-type")
@SecurityRequirement(name = "bearerAuth")
public class TableTypeController {
    ITableTypeService tableTypeService;
    @GetMapping
    public ApiResponse<List<TableTypeResponse>> getTableTypes(){
        return ApiResponse.<List<TableTypeResponse>>builder()
                .result(tableTypeService.getTableTypes())
                .build();
    }
    @PostMapping
    public ApiResponse<TableTypeResponse> createTableType(@RequestBody @Valid TableTypeRequest request){
        return ApiResponse.<TableTypeResponse>builder()
                .result(tableTypeService.createTableType(request))
                .build();
    }
    @PutMapping(value = "/update")
    public ApiResponse<TableTypeResponse> updateTableTypeById(@RequestBody TableTypeUpdateRequest request){
        return ApiResponse.<TableTypeResponse>builder()
                .result(tableTypeService.updateTableType(request))
                .build();
    }
    @DeleteMapping(value = "/{tableTypeId}")
    public ApiResponse<Void> deleteTableType(@PathVariable Long tableTypeId){
        tableTypeService.deleteTableType(tableTypeId);
        return ApiResponse.<Void>builder()
                .message("Delete success")
                .build();
    }
}
