package com.auth.sevice.client;

import com.auth.sevice.payload.SignupRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "account-service", url="localhost:8081")
public interface AccountServiceClient {

    @PostMapping(value = "/api/accounts/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createUser(SignupRequest signUpRequest);
}
