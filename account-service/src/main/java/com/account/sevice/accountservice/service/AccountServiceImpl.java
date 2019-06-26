package com.account.sevice.accountservice.service;


import com.account.sevice.accountservice.client.CartServiceClient;
import com.account.sevice.accountservice.model.Account;
import com.account.sevice.accountservice.model.SignupRequest;
import com.account.sevice.accountservice.model.User;
import com.account.sevice.accountservice.repository.AccountRepository;
import com.account.sevice.accountservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService{


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartServiceClient cartServiceClient;

    @Override
    public Account create(@NotNull SignupRequest signUpRequest) {
        Account existing = accountRepository.findByEmail(signUpRequest.getEmail());
        Assert.isNull(existing, "Account already exists: "+signUpRequest.getEmail());

        Account account = new Account();
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());

        log.info("USER: "+user.getEmail()+" CREATED...");
        log.info("User: "+user.getEmail()+" SAVED TO DB...");
        log.info("AFTER authServiceClient.createUser METHOD");
        account.setName(user.getUsername());
        account.setEmail(signUpRequest.getEmail());
        account.setLastLogIn(LocalDateTime.now());
        user.setAccount(account);
        account.setUser(user);

        cartServiceClient.createCart(signUpRequest.getEmail());
        log.info("Cart created for new user");userRepository.save(user);

        log.info("New account created: "+account.getEmail());
        return account;
    }

    @Override
    public Account findAccount(String email) {
        return accountRepository.findByEmail(email);
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
