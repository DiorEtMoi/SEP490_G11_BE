package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.PackageRequest;
import com.restaurent.manager.dto.request.RoleRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.PackageResponse;
import com.restaurent.manager.dto.response.RoleResponse;
import com.restaurent.manager.service.IPackageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/package")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
public class PackageController {
    IPackageService packageService;
    @PostMapping
    public ApiResponse<PackageResponse> create(@RequestBody PackageRequest req){
        return ApiResponse.<PackageResponse>builder()
                .result(packageService.create(req))
                .build();
    }
    @GetMapping
    public ApiResponse<List<PackageResponse>> getRoles(){
        return ApiResponse.<List<PackageResponse>>
                builder()
                .result(packageService.getPacks())
                .build();
    }
    @PutMapping("/{packId}/permission/{permissionId}")
    public ApiResponse<PackageResponse> addPermission(@PathVariable String packId, @PathVariable String permissionId ){
            int pId = Integer.parseInt(packId);
            Long perId = Long.parseLong(permissionId);
            return ApiResponse.<PackageResponse>builder()
                    .result(packageService.addPermission(perId,pId))
                    .build();
    }
}
