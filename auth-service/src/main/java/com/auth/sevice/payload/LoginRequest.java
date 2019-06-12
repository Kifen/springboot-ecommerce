package com.auth.sevice.payload;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
public class LoginRequest {


    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    private String password;
}
