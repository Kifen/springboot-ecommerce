package com.springboot.ecommerce.springbootecommerce.service;

import com.springboot.ecommerce.springbootecommerce.model.OrderProduct;
import com.springboot.ecommerce.springbootecommerce.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Service
@Transactional
public class OrderProductServiceImpl implements OrderProductService {

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Override
    public void create(@NotNull @Valid OrderProduct orderProduct) {
        orderProductRepository.save(orderProduct);
    }
}
