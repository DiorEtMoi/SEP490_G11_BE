package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.PackageRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.Pack.PackUpgradeResponse;
import com.restaurent.manager.dto.response.Pack.PackageResponse;
import com.restaurent.manager.mapper.PackageMapper;
import com.restaurent.manager.service.IPackageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/package")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PackageController {
    IPackageService packageService;
    PackageMapper packageMapper;
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping
    public ApiResponse<PackageResponse> create(@RequestBody @Valid PackageRequest req){
        return ApiResponse.<PackageResponse>builder()
                .result(packageService.create(req))
                .build();
    }
    @GetMapping(value = "/view")
    public ApiResponse<List<PackageResponse>> getPacksView(){
        return ApiResponse.<List<PackageResponse>>builder()
                .result(packageService.getPacksView())
                .build();
    }
    @GetMapping
    public ApiResponse<List<PackageResponse>> getRoles(){
        return ApiResponse.<List<PackageResponse>>
                builder()
                .result(packageService.getPacks())
                .build();
    }
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/{packId}/permission/{permissionId}")
    public ApiResponse<PackageResponse> addPermission(@PathVariable String packId, @PathVariable String permissionId ){
            Long pId = Long.parseLong(packId);
            Long perId = Long.parseLong(permissionId);
            return ApiResponse.<PackageResponse>builder()
                    .result(packageService.addPermission(perId,pId))
                    .build();
    }
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PutMapping("/{packId}")
    public ApiResponse<PackageResponse> updatePackage(@PathVariable Long packId,@RequestBody @Valid PackageRequest request){
        return ApiResponse.<PackageResponse>builder()
                .result(packageService.updatePackage(packId,request))
                .build();
    }
    @PreAuthorize(value = "hasAnyRole('ADMIN','MANAGER')")
    @GetMapping(value = "/restaurant/{restaurantId}")
    public ApiResponse<PackUpgradeResponse> findPacksUpgradeForRestaurant(@PathVariable Long restaurantId){
        return ApiResponse.<PackUpgradeResponse>builder()
                .result(packageService.findPacksToUpgradeForRestaurant(restaurantId))
                .build();
    }
    @GetMapping(value = "/{packId}")
    public ApiResponse<PackageResponse> findPackById(@PathVariable Long packId){
        return ApiResponse.<PackageResponse>builder()
                .result(packageMapper.toPackResponse(packageService.findPackById(packId)))
                .build();
    }

}
