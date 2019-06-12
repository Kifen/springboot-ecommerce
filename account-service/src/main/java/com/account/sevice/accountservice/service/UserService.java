package com.account.sevice.accountservice.service;

import com.account.sevice.accountservice.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface UserService{

    User findUser(@NotNull String email);
    List<User> getAllUsers();
}
