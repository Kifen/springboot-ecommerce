package com.account.sevice.accountservice.repository;

import com.account.sevice.accountservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(@NotNull String email);

}
