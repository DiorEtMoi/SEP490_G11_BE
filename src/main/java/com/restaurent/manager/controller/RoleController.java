package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.RoleRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.RoleResponse;
import com.restaurent.manager.service.IRoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class RoleController {
    IRoleService roleService;
    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest req){
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.createRole(req))
                .build();
    }
    @GetMapping
    public ApiResponse<List<RoleResponse>> getRolesInRestaurant(){
        return ApiResponse.<List<RoleResponse>>
                builder()
                .result(roleService.getRolesInRestaurant())
                .build();
    }
}
