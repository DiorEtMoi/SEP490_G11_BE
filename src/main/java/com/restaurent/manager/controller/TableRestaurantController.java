package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.Table.TableRestaurantRequest;
import com.restaurent.manager.dto.request.Table.TableRestaurantUpdateRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.TableRestaurantResponse;
import com.restaurent.manager.service.ITableRestaurantService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/table")
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TableRestaurantController {
    ITableRestaurantService tableRestaurantService;
    @PostMapping(value = "/create")
    public ApiResponse<TableRestaurantResponse> createTable(@RequestBody @Valid TableRestaurantRequest request){
        return ApiResponse.<TableRestaurantResponse>builder()
                .result(tableRestaurantService.createTable(request))
                .build();
    }
    @GetMapping(value = "/area/{areaId}")
    public ApiResponse<List<TableRestaurantResponse>> getTableByAreaId(@PathVariable Long areaId){
        return ApiResponse.<List<TableRestaurantResponse>>builder()
                .result(tableRestaurantService.getTableByAreaId(areaId))
                .build();
    }
    @PostMapping(value = "/create/{numbers}")
    public ApiResponse<List<TableRestaurantResponse>> createTables(@PathVariable String numbers,@RequestBody @Valid TableRestaurantRequest request){
        return ApiResponse.<List<TableRestaurantResponse>>builder()
                .result(tableRestaurantService.createManyTable(Integer.parseInt(numbers),request))
                .build();
    }
    @PutMapping(value = "/{tableRestaurantId}")
    public ApiResponse<TableRestaurantResponse> updateTable(@PathVariable Long tableRestaurantId,@RequestBody @Valid TableRestaurantRequest request){
        return ApiResponse.<TableRestaurantResponse>builder()
                .result(tableRestaurantService.updateTableByTableId(tableRestaurantId,request))
                .build();
    }
    @DeleteMapping(value = "/{tableRestaurantId}")
    public ApiResponse<Void> deleteTableById(@PathVariable Long tableRestaurantId){
        tableRestaurantService.deleteTableById(tableRestaurantId);
        return ApiResponse.<Void>builder()
                .message("Delete success")
                .build();
    }
    @PutMapping(value = "/update")
    public ApiResponse<Void> updateTables(@RequestBody List<TableRestaurantResponse> tableRestaurantResponses){
        tableRestaurantService.updateTables(tableRestaurantResponses);
        return ApiResponse.<Void>builder()
                .message("Update success")
                .build();
    }
}
