package com.restaurent.manager.service;

import com.nimbusds.jose.JOSEException;
import com.restaurent.manager.dto.request.AccountRequest;
import com.restaurent.manager.dto.request.AuthenticationRequest;
import com.restaurent.manager.dto.request.IntrospectRequest;
import com.restaurent.manager.dto.request.VerifyAccount;
import com.restaurent.manager.dto.response.AccountResponse;
import com.restaurent.manager.dto.response.AuthenticationResponse;
import com.restaurent.manager.dto.response.IntrospectResponse;
import com.restaurent.manager.dto.response.VerifyResponse;

import java.text.ParseException;
import java.util.List;

public interface IAuthenticationService {
IntrospectResponse introspect(IntrospectRequest req) throws JOSEException, ParseException;
void logout(IntrospectRequest request) throws ParseException, JOSEException;
}
