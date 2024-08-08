package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.dish.DishRequest;
import com.restaurent.manager.dto.request.dish.DishUpdateRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.DishResponse;
import com.restaurent.manager.service.IDishService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dish")
@FieldDefaults(makeFinal = true)
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class DishController {
    IDishService dishService;
    @PreAuthorize(value = "hasRole('MANAGER') and hasAuthority('DISH')")
    @PostMapping(value = "/create")
    public ApiResponse<DishResponse> createDish(@RequestBody DishRequest request){
        return ApiResponse.<DishResponse>builder()
                .result(dishService.createNewDish(request))
                .build();
    }
    @PreAuthorize(value = "hasAnyRole('MANAGER', 'WAITER','HOSTESS') and hasAuthority('DISH')")
    @GetMapping(value = "/account/{accountId}")
    public ApiResponse<List<DishResponse>> findDishesByAccountId(@PathVariable Long accountId){
        return ApiResponse.<List<DishResponse>>builder()
                .result(dishService.getDishesByAccountId(accountId))
                .build();
    }
    @PreAuthorize(value = "hasAnyRole('MANAGER', 'WAITER', 'HOSTESS') and hasAuthority('DISH')")
    @GetMapping(value = "/category/{categoryCode}")
    public ApiResponse<List<DishResponse>> findDishesByCategoryCode(@PathVariable String categoryCode){
        return ApiResponse.<List<DishResponse>>builder()
                .result(dishService.findDishesByCategoryCode(categoryCode))
                .build();
    }
    @PreAuthorize(value = "hasRole('MANAGER') and hasAuthority('DISH')")
    @PutMapping(value = "/{dishId}")
    public ApiResponse<DishResponse> updateDishById(@PathVariable Long dishId, @RequestBody DishUpdateRequest request){
        return ApiResponse.<DishResponse>builder()
                .result(dishService.updateDish(dishId,request))
                .build();
    }
    @PreAuthorize(value = "hasAnyRole('MANAGER', 'WAITER','HOSTESS') and hasAuthority('DISH')")
    @GetMapping(value = "/account/{accountId}/{status}")
    public ApiResponse<List<DishResponse>> findDishesByAccountIdAndStatus(@PathVariable Long accountId, @PathVariable boolean status, @RequestParam(value = "page", defaultValue = "1") int pageIndex, @RequestParam(value = "size",defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(pageIndex - 1,size);
        return ApiResponse.<List<DishResponse>>builder()
                .result(dishService.getDishesByAccountIdAndStatus(accountId,status,pageable))
                .build();
    }
}
