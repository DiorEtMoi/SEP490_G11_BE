package com.restaurent.manager.mapper;

import com.restaurent.manager.dto.request.UnitRequest;
import com.restaurent.manager.dto.response.UnitResponse;
import com.restaurent.manager.entity.Unit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitMapper {
    Unit toUnit(UnitRequest request);
    UnitResponse toUnitResponse(Unit unit);
}
