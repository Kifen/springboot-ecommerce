package com.auth.sevice.controller;

import com.auth.sevice.client.AccountServiceClient;
import com.auth.sevice.model.Role;
import com.auth.sevice.model.RoleName;
import com.auth.sevice.model.User;
import com.auth.sevice.payload.ApiResponse;
import com.auth.sevice.payload.JwtAuthenticationResponse;
import com.auth.sevice.payload.LoginRequest;
import com.auth.sevice.payload.SignupRequest;
import com.auth.sevice.repository.UserRepository;
import com.auth.sevice.security.JwtTokenProvider;
import com.auth.sevice.service.AuthService;
import com.auth.sevice.utility.Mappings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping(Mappings.AUTH_API)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AccountServiceClient accountServiceClient;

    @PostMapping(Mappings.SIGNIN)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        log.info("BEFORE anthenticateUser() METHOD...");

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        log.info("AFTER anthenticateUser() METHOD...");

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping(Mappings.SIGNUP)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest){
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            return new ResponseEntity(new ApiResponse
                    (false, "Account With Email[" +signUpRequest.getEmail()+ "]Already Exists..."),
                    HttpStatus.BAD_REQUEST);
        }


        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
                signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = new Role(RoleName.USER);
        //authService.addUserRole(role, signUpRequest.getEmail());
        user.addRole(role);

        accountServiceClient.createUser(signUpRequest);
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path(Mappings.USERS_API+"/{email}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "User [" +signUpRequest.getEmail() +"] registered successfully"));
    }
}
