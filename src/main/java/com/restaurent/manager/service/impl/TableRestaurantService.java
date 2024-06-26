package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.Table.TableRestaurantRequest;
import com.restaurent.manager.dto.request.Table.TableRestaurantUpdateRequest;
import com.restaurent.manager.dto.response.TableRestaurantResponse;
import com.restaurent.manager.entity.TableRestaurant;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.TableRestaurantMapper;
import com.restaurent.manager.repository.AreaRepository;
import com.restaurent.manager.repository.TableRestaurantRepository;
import com.restaurent.manager.repository.TableTypeRepository;
import com.restaurent.manager.service.ITableRestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TableRestaurantService implements ITableRestaurantService {
    TableRestaurantMapper tableRestaurantMapper;
    AreaRepository areaRepository;
    TableTypeRepository tableTypeRepository;
    TableRestaurantRepository tableRestaurantRepository;
    @Override
    public TableRestaurantResponse createTable(TableRestaurantRequest request) {
        if(tableRestaurantRepository.existsByNameAndArea_Id(request.getName(),request.getAreaId())){
            throw new AppException(ErrorCode.TABLE_NAME_EXISTED);
        }
        TableRestaurant tableRestaurant = tableRestaurantMapper.toTableRestaurant(request);
        tableRestaurant.setTableType(tableTypeRepository.findById(request.getTableTypeId()).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        ));
        tableRestaurant.setArea(areaRepository.findById(request.getAreaId()).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        ));
        return tableRestaurantMapper.toTableRestaurantResponse(tableRestaurantRepository.save(tableRestaurant));
    }

    @Override
    public List<TableRestaurantResponse> getTableByAreaId(Long areaId) {
        return tableRestaurantRepository.findByArea_Id(areaId).stream().map(tableRestaurantMapper::toTableRestaurantResponse).toList();
    }

    @Override
    public List<TableRestaurantResponse> createManyTable(int numbers, TableRestaurantRequest request) {
        List<TableRestaurantResponse> tableRestaurantResponses = new ArrayList<>();
        TableRestaurant tableRestaurant = tableRestaurantRepository.findTopByNameStartingWithOrderByNameDesc(request.getName());
        if(tableRestaurant != null){
            String[] originalName = tableRestaurant.getName().split("-");
            int tableNumber = Integer.parseInt(originalName[1]);
            log.info(originalName[1]);
            for (int i = 1; i <= numbers; i++) {
                tableNumber++;
                request.setName(request.getName() + "-" + tableNumber);
                tableRestaurantResponses.add(createTable(request));
                request.setName(originalName[0]);
            }
        }else{
            String originalName = request.getName();
            for (int i = 1; i <= numbers; i++) {
                request.setName(request.getName() + "-" + i);
                tableRestaurantResponses.add(createTable(request));
                request.setName(originalName);
            }
        }
        return tableRestaurantResponses;
    }

    @Override
    public TableRestaurantResponse updateTableByTableId(Long tableId, TableRestaurantRequest request) {
        if(tableRestaurantRepository.existsByNameAndArea_Id(request.getName(),request.getAreaId())){
            throw new AppException(ErrorCode.TABLE_NAME_EXISTED);
        }
        TableRestaurant tableRestaurant = findById(tableId);
        tableRestaurantMapper.updateTable(tableRestaurant,request);
        return tableRestaurantMapper.toTableRestaurantResponse(tableRestaurantRepository.save(tableRestaurant));
    }

    @Override
    public TableRestaurant findById(Long id) {
        return tableRestaurantRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.NOT_EXIST)
        );
    }

    @Override
    public void deleteTableById(Long tableId) {
        TableRestaurant tableRestaurant = findById(tableId);
        tableRestaurantRepository.delete(tableRestaurant);
    }

    @Override
    public void updateTables(List<TableRestaurantUpdateRequest> tables) {
        for (TableRestaurantUpdateRequest tableRestaurant : tables){
            updateTableByTableId(tableRestaurant.getId(),tableRestaurantMapper.toTableRestaurantRequest(tableRestaurant));
        }
    }
}
