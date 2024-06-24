package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.PackageRequest;
import com.restaurent.manager.dto.response.Pack.PackUpgradeResponse;
import com.restaurent.manager.dto.response.Pack.PackageResponse;
import com.restaurent.manager.entity.Package;
import com.restaurent.manager.entity.Permission;
import com.restaurent.manager.entity.Restaurant;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.PackageMapper;
import com.restaurent.manager.repository.PackageRepository;
import com.restaurent.manager.repository.PermissionRepository;
import com.restaurent.manager.service.IPackageService;
import com.restaurent.manager.service.IRestaurantService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    IRestaurantService restaurantService;
    @Override
    public PackageResponse create(PackageRequest request) {
        Package pack = packageMapper.toPackage(request);
        List<Permission> permissions = permissionRepository.findAllById(request.getPermissions());
        if(!permissions.isEmpty()){
            pack.setPermissions(new HashSet<>(permissions));
            permissions.forEach(permission -> permission.getPackages().add(pack));
        }
        return packageMapper.toPackResponse(packageRepository.save(pack));
    }

    @Override
    public List<PackageResponse> getPacks() {
        return packageRepository.findAll().stream().map(packageMapper::toPackResponse).toList();
    }

    @Override
    public PackageResponse addPermission(Long permissionID,Long packId) {
        Permission permission = permissionRepository.findById(permissionID).orElseThrow(() ->
                new AppException(ErrorCode.INVALID_KEY));
        Package pack = packageRepository.findById(packId).orElseThrow(() ->
                new AppException(ErrorCode.INVALID_KEY));
        permission.addPermissionToPackage(pack);
        return packageMapper.toPackResponse(packageRepository.save(pack));
    }

    @Override
    public PackageResponse updatePackage(Long id, PackageRequest packageRequest) {
        Package pack = findPackById(id);
        packageMapper.updatePackage(pack,packageRequest);
        List<Permission> permissions = permissionRepository.findAllById(packageRequest.getPermissions());
        if(!permissions.isEmpty()){
            pack.setPermissions(new HashSet<>(permissions));
            permissions.forEach(permission -> permission.getPackages().add(pack));
        }
        return packageMapper.toPackResponse(pack);
    }

    @Override
    public Package findPackById(Long id) {
        return packageRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        );
    }

    @Override
    public PackUpgradeResponse findPacksToUpgradeForRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantService.getRestaurantById(restaurantId);
        Package pack = findPackById(restaurant.getRestaurantPackage().getId());
        List<PackageResponse> packages = packageRepository.findByPricePerMonthGreaterThan(pack.getPricePerMonth()).stream()
                .map(packageMapper::toPackResponse).toList();
        double moneyPerDay = pack.getPricePerMonth() / LocalDate.MAX.getDayOfMonth();
        LocalDate expiryDate = restaurant.getExpiryDate().toLocalDate();
        LocalDate currentDate = LocalDate.now();
        long daysLeft = ChronoUnit.DAYS.between(currentDate, expiryDate);
        log.info("Max day in month :" + LocalDate.MAX.getDayOfMonth());
        log.info("Price per month :" + pack.getPricePerMonth());
        log.info("moneyPerDay : " + moneyPerDay);
        double deposit = 0;
        if(daysLeft > 0){
            deposit = moneyPerDay * daysLeft;
        }
        return PackUpgradeResponse.builder()
                .deposit(Math.ceil(deposit))
                .packages(packages)
                .build();
    }
}
