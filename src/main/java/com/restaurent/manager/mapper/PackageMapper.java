package com.restaurent.manager.mapper;

import com.restaurent.manager.dto.request.PackageRequest;
import com.restaurent.manager.dto.response.PackageResponse;
import com.restaurent.manager.entity.Package;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PackageMapper {
    @Mapping(target = "permissions", ignore = true)
    Package toPackage(PackageRequest request);
    PackageResponse toPackResponse(Package pack);
}
