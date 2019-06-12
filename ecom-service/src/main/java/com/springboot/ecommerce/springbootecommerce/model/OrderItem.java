package com.springboot.ecommerce.springbootecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class OrderItem{

    private Long itemId;
    private Integer quantity;
}
