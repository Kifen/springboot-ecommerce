package com.account.sevice.accountservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "cart-service", url="localhost:8084")
public interface CartServiceClient {

    @PostMapping(value = "/api/carts/create/{email}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void createCart(@PathVariable String email);
}
