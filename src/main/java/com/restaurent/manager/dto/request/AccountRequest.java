package com.restaurent.manager.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @Valid
    @NotNull(message = "Username is require")
    @NotBlank(message = "Username is require")
    private String username;
    @NotBlank(message = "phoneNumber is require")
    @NotNull(message = "phoneNumber is require")
    private String phoneNumber;
    @NotBlank(message = "password is require")
    @NotNull(message = "password is require")
    private String password;
    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
}
