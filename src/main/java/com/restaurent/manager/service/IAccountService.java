package com.restaurent.manager.service;

import com.restaurent.manager.dto.request.AccountRequest;
import com.restaurent.manager.dto.request.AuthenticationRequest;
import com.restaurent.manager.dto.request.VerifyAccount;
import com.restaurent.manager.dto.response.AccountResponse;
import com.restaurent.manager.dto.response.AuthenticationResponse;
import com.restaurent.manager.dto.response.VerifyResponse;
import com.restaurent.manager.entity.Account;

import java.util.List;

public interface IAccountService {
    AccountResponse register(AccountRequest req);
    List<AccountResponse> getAccountsManager();
    VerifyResponse verifyAccount(VerifyAccount req);
    AuthenticationResponse authenticated(AuthenticationRequest req);
    String regenerateOtp(VerifyAccount req);
    Account findAccountByID(Long id);

}
