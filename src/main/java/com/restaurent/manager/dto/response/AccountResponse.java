package com.restaurent.manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private Long id;
    private String username;
    private String phoneNumber;
    private String password;
    private String email;
    private boolean status;
    private RoleResponse role;
}
