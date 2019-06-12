package com.account.sevice.accountservice.service;

import com.account.sevice.accountservice.model.Account;
import com.account.sevice.accountservice.model.SignupRequest;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface AccountService {

    Account create(@NotNull SignupRequest signUpRequest);
    Account findAccount(String email);
    List<Account> getAllAccounts();
}
