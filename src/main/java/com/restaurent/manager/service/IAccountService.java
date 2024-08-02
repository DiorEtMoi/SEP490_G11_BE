package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.AccountRequest;
import com.restaurent.manager.dto.request.AuthenticationRequest;
import com.restaurent.manager.dto.request.VerifyAccount;
import com.restaurent.manager.dto.response.AccountResponse;
import com.restaurent.manager.dto.response.AuthenticationResponse;
import com.restaurent.manager.dto.response.VerifyResponse;
import com.restaurent.manager.entity.Account;

import java.util.List;

public interface IAccountService{
    AccountResponse register(AccountRequest req);
    List<AccountResponse> getAccountsManager();
    VerifyResponse verifyAccount(VerifyAccount req);
    String regenerateOtp(String email);
    Account findAccountByID(Long id);
    AccountResponse getAccountById(Long id);
    Account findAccountByPhoneNumber(String phoneNumber);
    AuthenticationResponse authenticated(AuthenticationRequest req);
    AuthenticationResponse verifyOtp(VerifyAccount req);

}
