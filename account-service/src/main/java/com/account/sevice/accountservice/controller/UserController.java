package com.account.sevice.accountservice.controller;

import com.account.sevice.accountservice.model.User;
import com.account.sevice.accountservice.service.UserService;
import com.account.sevice.accountservice.utility.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Mappings.USER_API)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/",""})
    public Iterable<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username){
        return userService.findUser(username);
    }
}
