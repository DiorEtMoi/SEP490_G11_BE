package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.PackageRequest;
import com.restaurent.manager.dto.response.PackageResponse;
import com.restaurent.manager.entity.Package;

import java.util.List;

public interface IPackageService {
    PackageResponse create(PackageRequest request);
    List<PackageResponse> getPacks();
    PackageResponse addPermission(Long permissionID,Long packId);
    PackageResponse updatePackage(Long id, PackageRequest packageRequest);
    Package findPackById(Long id);
}
