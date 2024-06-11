package com.restaurent.manager.dto.request;

import com.restaurent.manager.dto.response.RoleResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    private String username;
    private String phoneNumber;
    private String password;
    private String email;
}
