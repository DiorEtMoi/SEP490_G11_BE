package com.restaurent.manager.mapper;

import com.restaurent.manager.dto.request.TableTypeRequest;
import com.restaurent.manager.dto.response.TableTypeResponse;
import com.restaurent.manager.entity.TableType;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableTypeMapper {
    TableType toTableType(TableTypeRequest request);
    TableTypeResponse toTableTypeResponse(TableType tableType);
}
