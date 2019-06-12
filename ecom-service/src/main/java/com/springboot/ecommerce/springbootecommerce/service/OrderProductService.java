package com.springboot.ecommerce.springbootecommerce.service;

import com.springboot.ecommerce.springbootecommerce.model.OrderProduct;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface OrderProductService {

    void create(@NotNull @Valid OrderProduct orderProduct);
}
