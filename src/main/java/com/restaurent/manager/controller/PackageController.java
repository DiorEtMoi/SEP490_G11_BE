package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.PackageRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.PackageResponse;
import com.restaurent.manager.service.IPackageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/package")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
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
            Long pId = Long.parseLong(packId);
            Long perId = Long.parseLong(permissionId);
            return ApiResponse.<PackageResponse>builder()
                    .result(packageService.addPermission(perId,pId))
                    .build();
    }
    @PutMapping("/{packId}")
    public ApiResponse<PackageResponse> updatePackage(@PathVariable String packId,@RequestBody PackageRequest request){
        return ApiResponse.<PackageResponse>builder()
                .result(packageService.updatePackage(Long.parseLong(packId),request))
                .build();
    }
}
