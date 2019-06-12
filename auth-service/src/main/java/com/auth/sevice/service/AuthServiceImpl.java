package com.auth.sevice.service;

import com.auth.sevice.model.User;
import com.auth.sevice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository repository;


    @Override
    public void createUser(User user) {
        repository.save(user);
    }
}
