package com.service.cart.service;

import com.service.cart.exception.ResourceNotFoundException;
import com.service.cart.model.Cart;
import com.service.cart.model.CartItem;
import com.service.cart.repository.CartItemRepository;
import com.service.cart.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.ListIterator;

@Service
@Transactional
@Slf4j
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;


    @Override
    public Cart createCart(String email) {
        Cart cart = new Cart();
        cart.setEmail(email);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Cart getCart(String email) {
        return cartRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Cart with email [" + email + "] not found"));
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public CartItem addToCart(String email, CartItem cartItem) {
        return cartRepository.findByEmail(email).map(cart -> {
            cartItem.setCart(cart);
            return cartItemRepository.save(cartItem);
        }).orElseThrow(()-> new ResourceNotFoundException("Cart with email [" + email + "] not found"));
    }


    @Override
    public void emptyCart(String email) {
        Long id = getCart(email).getId();
        ListIterator<CartItem> iterator = cartItemRepository.findByCartId(id).listIterator();
        while (iterator.hasNext()){
            CartItem cartItem = iterator.next();
            cartItemRepository.delete(cartItem);
        }
    }

    @Override
    public boolean isCartEmpty(String email) {
        Long id = getCart(email).getId();
        if(cartItemRepository.findByCartId(id).isEmpty()) return true;
        return false;
    }

    @Override
    public int numberOfItemsInCart(String email) {
        int count = 0;
        ListIterator<CartItem> iterator = cartItemRepository.findAll().listIterator();
        while (iterator.hasNext()){
            count++;
        }
        return count;
    }

    @Override
    public void removeItem(String email, Long productId) {
       Cart cart = getCart(email);
       long cartId = cart.getId();
       List<CartItem> cartItems = cartItemRepository.
               findByProductIdAndCartId(productId, cartId);
       cartItemRepository.delete(cartItems.get(0));
    }

    @Override
    public List<CartItem> getCartItems(String email) {
        Long id = getCart(email).getId();
        return cartItemRepository.findByCartId(id);
    }
}
