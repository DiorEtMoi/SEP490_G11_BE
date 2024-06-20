package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.TableTypeRequest;
import com.restaurent.manager.dto.response.TableTypeResponse;

import java.util.List;

public interface ITableTypeService {
    TableTypeResponse createTableType(TableTypeRequest request);
    List<TableTypeResponse> getTableTypes();
}
