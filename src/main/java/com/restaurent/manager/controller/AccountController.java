package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.AccountRequest;
import com.restaurent.manager.dto.request.AuthenticationRequest;
import com.restaurent.manager.dto.request.VerifyAccount;
import com.restaurent.manager.dto.response.*;
import com.restaurent.manager.service.IAccountService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@AllArgsConstructor
@Slf4j
public class AccountController {
    IAccountService accountService;
    @PostMapping("/register")
    public ApiResponse<AccountResponse> register(@RequestBody @Valid AccountRequest req){
        return ApiResponse.<AccountResponse>builder()
                .result(accountService.register(req))
                .build();
    }
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping(value = "/manager")
    public ApiResponse<List<AccountResponse>> getAccountsByRoleId(){
        return ApiResponse.<List<AccountResponse>>
                builder()
                .result(accountService.getAccountsManager())
                .build();
    }
    @PostMapping("/verify")
    public ApiResponse<VerifyResponse> verifyAccount(@RequestBody @Valid VerifyAccount req){
        VerifyResponse verifyResponse = accountService.verifyAccount(req);
        if(verifyResponse.isStatus()){
           return ApiResponse.<VerifyResponse>builder()
                    .result(verifyResponse)
                    .message("Verify success")
                    .build();
        }
        return  ApiResponse.<VerifyResponse>builder()
                .result(verifyResponse)
                .message("Verify failed")
                .build();
    }
    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest req){
        return ApiResponse.<AuthenticationResponse>builder()
                .result(accountService.authenticated(req))
                .build();
    }
    @PostMapping("/regenerated-otp")
    public ApiResponse<String> regeneratedOtp(@RequestBody VerifyAccount email){
        return ApiResponse.<String>builder()
                .result(accountService.regenerateOtp(email))
                .build();
    }
    @GetMapping(value = "/myInfo")
    public String getMyInfo(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username : " + authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return "";
    }
    @GetMapping("/{accountId}")
    public ApiResponse<AccountResponse> getAccountById(@PathVariable Long accountId){
        return  ApiResponse.<AccountResponse>builder()
                .result(accountService.getAccountById(accountId))
                .build();
    }
}
