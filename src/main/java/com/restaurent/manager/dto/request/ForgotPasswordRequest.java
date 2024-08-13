package com.restaurent.manager.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class ForgotPasswordRequest {
    private String email;
    private String phoneNumber;
}
