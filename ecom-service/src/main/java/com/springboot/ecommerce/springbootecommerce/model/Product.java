package com.springboot.ecommerce.springbootecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor()
@AllArgsConstructor
//@NotNull(message = "product field cannot be null")
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull private String product_name;
    private String category;
    private Double weight;
    @NotNull private Double price;
    private String imageUrl;

    @Column(name = "quantity_in_stock")
    @NotNull
    @JsonIgnore
    private Integer quantity_in_stock;

    @Transient
    public void updateQuantity(int qty){
        this.setQuantity_in_stock(this.getQuantity_in_stock()-qty);
    }
}
