package com.springboot.ecommerce.springbootecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class OrderForm{

    private List<OrderItem> orderList = new ArrayList<>();

}
