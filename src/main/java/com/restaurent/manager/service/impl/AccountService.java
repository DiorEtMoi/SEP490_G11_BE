package com.restaurent.manager.service.impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.restaurent.manager.dto.request.AccountRequest;
import com.restaurent.manager.dto.request.AuthenticationRequest;
import com.restaurent.manager.dto.request.VerifyAccount;
import com.restaurent.manager.dto.response.AccountResponse;
import com.restaurent.manager.dto.response.AuthenticationResponse;
import com.restaurent.manager.dto.response.VerifyResponse;
import com.restaurent.manager.entity.Account;
import com.restaurent.manager.entity.Role;
import com.restaurent.manager.enums.RoleSystem;
import com.restaurent.manager.exception.AppException;
import com.restaurent.manager.exception.ErrorCode;
import com.restaurent.manager.mapper.AccountMapper;
import com.restaurent.manager.repository.AccountRepository;
import com.restaurent.manager.repository.RoleRepository;
import com.restaurent.manager.service.IAccountService;
import com.restaurent.manager.service.IEmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class AccountService implements IAccountService {
    @NonFinal
    @Value("${jwt.signerKey}")
    private String signerKey;

    AccountRepository accountRepository;
    AccountMapper accountMapper;
    RoleRepository roleRepository;
    IEmailService emailService;

    @Override
    public AccountResponse register(AccountRequest req) {
        if(accountRepository.existsByEmailAndStatus(req.getEmail(), true)){
            throw new AppException(ErrorCode.EMAIL_EXIST);
        }
        if(accountRepository.existsByPhoneNumberAndStatus(req.getPhoneNumber(), true)){
            throw new AppException(ErrorCode.PHONENUMBER_EXIST);
        }
        if(accountRepository.existsByEmailAndStatus(req.getEmail(),false)){
            accountRepository.delete(accountRepository.findByEmail(req.getEmail()).orElseThrow());
        }
        Account account = accountMapper.toAccount(req);
        String otp = emailService.generateCode(6);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setStatus(false);
        account.setOtp(otp);
        account.setOtpGeneratedTime(LocalDateTime.now());
        Role role = roleRepository.findByName(RoleSystem.MANAGER.name()).orElseThrow(() -> new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION)
        );
        role.assignAccount(account);
        String body = "Your OTP is : " + otp;
        emailService.sendEmail(account.getEmail(),body,"Verify account ");
        return accountMapper.toAccountResponse(accountRepository.save(account));
    }

    @Override
    public List<AccountResponse> getAccountsManager() {
        return accountRepository.findByRole_Id(5L).stream().map(accountMapper::toAccountResponse).toList();
    }


    @Override
    public VerifyResponse verifyAccount(VerifyAccount req) {
        Account account = accountRepository.findByEmail(req.getEmail()).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        if(account.isStatus()){
            throw new AppException(ErrorCode.USER_EXISTED);
        }
        if(account.getOtp().equals(req.getOtp()) && Duration.between(LocalDateTime.now(), account.getOtpGeneratedTime()).getSeconds() < 60){
            account.setStatus(true);
            return VerifyResponse.builder()
                    .status(accountRepository.save(account).isStatus())
                    .email(account.getEmail())
                    .build();
        }
        return VerifyResponse.builder()
                .email(account.getEmail())
                .status(false)
                .build();
    }

    @Override
    public AuthenticationResponse authenticated(AuthenticationRequest req) {
        Account account = accountRepository.findByEmailAndStatus(req.getEmail(),true).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated =  passwordEncoder.matches(req.getPassword(), account.getPassword());
        if(!authenticated){
            throw new AppException(ErrorCode.PASSWORD_INCORRECT);
        }
        String token = generateToken(account);
        return AuthenticationResponse.builder()
                .authenticated(true)
                .token(token)
                .build();
    }

    @Override
    public String regenerateOtp(VerifyAccount req) {
        Account account = accountRepository.findByEmailAndStatus(req.getEmail(), false).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        String otp = emailService.generateCode(6);
        account.setOtp(otp);
        account.setOtpGeneratedTime(LocalDateTime.now());
        accountRepository.save(account);
        String body = "Your OTP is : " + otp;
        emailService.sendEmail(account.getEmail(),body,"Verify account ");
        return "New otp are generated !";
    }

    @Override
    public Account findAccountByID(Long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED)
        );
    }

    public String generateToken(Account user){
        String restaurantId = "";
        if(user.getRestaurant() != null){
            restaurantId = user.getRestaurant().getId().toString();
        }
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .issuer(user.getUsername())
                .subject(user.getUsername())
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope",buildScope(user))
                .claim("email",user.getEmail())
                .claim("restaurantId",restaurantId)
                .claim("accountId",user.getId())
                .jwtID(UUID.randomUUID().toString())
                .build();
        Payload payload = new Payload(claims.toJSONObject());
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWSObject token = new JWSObject(header,payload);
        try {
            token.sign(new MACSigner(signerKey.getBytes()));
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        return token.serialize();
    }
    public String buildScope(Account user){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(user.getRole() != null){
            stringJoiner.add(user.getRole().getName());
            if(user.getRestaurant() != null){
                user.getRestaurant().getRestaurantPackage().getPermissions().forEach(permission -> stringJoiner.add(permission.getName()));
            }
        }
        return stringJoiner.toString();
    }

}
