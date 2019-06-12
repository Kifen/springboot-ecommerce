package com.account.sevice.accountservice.controller;

import com.account.sevice.accountservice.model.Account;
import com.account.sevice.accountservice.model.SignupRequest;
import com.account.sevice.accountservice.service.AccountService;
import com.account.sevice.accountservice.utility.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(Mappings.ACCOUNT_API)
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(Mappings.CREATE_ACCOUNT)
    public ResponseEntity<?> createNewAccount(@RequestBody SignupRequest signupRequest){

        log.info("SIGNUPREQUEST = {}", signupRequest.toString());
        accountService.create(signupRequest);
        log.info("AFTER -- accountService.create(signUpRequest) -- METHOD");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = {"/",""})
    public Iterable<Account> getAllAccounts(){
        return accountService.getAllAccounts();
    }
}
