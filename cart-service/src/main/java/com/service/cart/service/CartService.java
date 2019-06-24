package com.service.cart.service;

import com.service.cart.model.Cart;
import com.service.cart.model.CartItem;

import java.util.List;

public interface CartService {

    Cart createCart(String email);
    Cart getCart(String email);
    List<Cart> getAllCarts();
    CartItem addToCart(String email, CartItem cartItem);
    void emptyCart(String email);
    boolean isCartEmpty(String email);
    int numberOfItemsInCart(String email);
    void removeItem(String email, Long id);
    List<CartItem> getCartItems(String email);
}
