package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.PackageRequest;
import com.restaurent.manager.dto.response.PackageResponse;
import com.restaurent.manager.entity.Package;
import com.restaurent.manager.entity.Permission;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.PackageMapper;
import com.restaurent.manager.repository.PackageRepository;
import com.restaurent.manager.repository.PermissionRepository;
import com.restaurent.manager.service.IPackageService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
public class PackageService implements IPackageService {
    PackageRepository packageRepository;
    PackageMapper packageMapper;
    PermissionRepository permissionRepository;
    @Override
    public PackageResponse create(PackageRequest request) {
        Package pack = packageMapper.toPackage(request);
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        permissions.forEach(permission -> permission.addPermissionToPackage(pack));
        pack.setPermissions(new HashSet<>(permissions));
        return packageMapper.toPackResponse(packageRepository.save(pack));
    }

    @Override
    public List<PackageResponse> getPacks() {
        return packageRepository.findAll().stream().map(packageMapper::toPackResponse).toList();
    }

    @Override
    public PackageResponse addPermission(Long permissionID,int packId) {
        Permission permission = permissionRepository.findById(permissionID).orElseThrow(() ->
                new AppException(ErrorCode.INVALID_KEY));
        Package pack = packageRepository.findById(packId).orElseThrow(() ->
                new AppException(ErrorCode.INVALID_KEY));
        permission.addPermissionToPackage(pack);
        return packageMapper.toPackResponse(packageRepository.save(pack));
    }
}
