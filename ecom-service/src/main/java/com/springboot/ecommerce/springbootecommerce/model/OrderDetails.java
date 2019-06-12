package com.springboot.ecommerce.springbootecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class OrderDetails{

    private Long orderId;
    private List<Product> orderProducts = new ArrayList<>();
    private Double orderValue;
}
