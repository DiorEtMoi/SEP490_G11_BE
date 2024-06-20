package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.TableTypeRequest;
import com.restaurent.manager.dto.response.TableTypeResponse;
import com.restaurent.manager.entity.TableType;
import com.restaurent.manager.mapper.TableTypeMapper;
import com.restaurent.manager.repository.TableTypeRepository;
import com.restaurent.manager.service.ITableTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class TableTypeService implements ITableTypeService {
    TableTypeRepository tableTypeRepository;
    TableTypeMapper tableTypeMapper;
    @Override
    public TableTypeResponse createTableType(TableTypeRequest request) {
        TableType tableType = tableTypeMapper.toTableType(request);
        return tableTypeMapper.toTableTypeResponse(tableTypeRepository.save(tableType));
    }

    @Override
    public List<TableTypeResponse> getTableTypes() {
        return tableTypeRepository.findAll().stream().map(tableTypeMapper::toTableTypeResponse).toList();
    }
}
