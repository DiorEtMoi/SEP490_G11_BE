package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.PermissionRequest;
import com.restaurent.manager.dto.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    PermissionResponse createPermission(PermissionRequest request);
    List<PermissionResponse> getPermissions();
}
