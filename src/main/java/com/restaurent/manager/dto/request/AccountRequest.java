package com.restaurent.manager.dto.request;

import com.restaurent.manager.custom.ValidEmail;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^(0[3|5|7|8|9])[0-9]{8}$", message = "Invalid phone number")
    private String phoneNumber;

    @NotBlank(message = "password is require")
    @NotNull(message = "password is require")
    private String password;

    @NotNull(message = "Email is mandatory")
    @ValidEmail
    private String email;
}
