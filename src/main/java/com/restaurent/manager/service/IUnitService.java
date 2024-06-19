package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.UnitRequest;
import com.restaurent.manager.dto.response.UnitResponse;

import java.util.List;

public interface IUnitService {
    UnitResponse createUnit(UnitRequest request);
    List<UnitResponse> getUnits();
}
