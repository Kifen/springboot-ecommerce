package com.service.cart.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false)
    private String email;

    //@Column(name = "cart_items")
    //@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //private List<CartItem> cartItemList = new ArrayList<>();

    //@Transient
    //public void addToCartItem(CartItem cartItem){
        //this.cartItemList.add(cartItem);
    //}
}
