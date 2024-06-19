package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.UnitRequest;
import com.restaurent.manager.dto.response.UnitResponse;
import com.restaurent.manager.mapper.UnitMapper;
import com.restaurent.manager.repository.UnitRepository;
import com.restaurent.manager.service.IUnitService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UnitService implements IUnitService {

    UnitMapper unitMapper;
    UnitRepository unitRepository;
    @Override
    public UnitResponse createUnit(UnitRequest request) {
        return unitMapper.toUnitResponse(unitRepository.save(
                unitMapper.toUnit(request)
        ));
    }

    @Override
    public List<UnitResponse> getUnits() {
        return unitRepository.findAll().stream().map(unitMapper::toUnitResponse).toList();
    }
}
