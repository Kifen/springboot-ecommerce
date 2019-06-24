package com.service.cart.controller;

import com.service.cart.exception.ApiResponse;
import com.service.cart.model.Cart;
import com.service.cart.model.CartItem;
import com.service.cart.repository.CartRepository;
import com.service.cart.service.CartService;
import com.service.cart.utility.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(Mappings.CART_API)
public class CartController {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @GetMapping(value = {"","/"})
    public List<Cart> getAllCarts(){
        return cartService.getAllCarts();
    }

    @PostMapping(Mappings.CREATE_CART+"/{email}")
    public ResponseEntity<?> createUserCart(@PathVariable String email){
        if(cartRepository.existsByEmail(email)){
            return new ResponseEntity<>(new ApiResponse
                    (false, "Cart With Email [" +email+ "] Already Exists..."), HttpStatus.BAD_REQUEST);
        }
        Cart result = cartService.createCart(email);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path(Mappings.CART_API+"/{email}")
                .buildAndExpand(result.getEmail()).toUri();
        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Cart for user ["+ email+ "] successfully created"));

    }

    @GetMapping(Mappings.GET_CART)
    public Cart findCart(@PathVariable String email){
        return cartService.getCart(email);
    }

    @PostMapping(Mappings.ADD_TO_CART)
    public void addToCart(@RequestBody CartItem cartItem, @PathVariable String email){
        cartService.addToCart(email, cartItem);
    }

    @PutMapping(Mappings.EMPTY_CART)
    public void emptyCart(@PathVariable String email){
        cartService.emptyCart(email);
    }

    @GetMapping(Mappings.IS_CART_EMPTY)
    public Boolean cartIsEmpty(@PathVariable String email){
        return cartService.isCartEmpty(email);
    }

    @PutMapping(Mappings.REMOVE_ITEM)
    public void removeItemFromCart(@PathVariable String email, @RequestParam Long productId){
        cartService.removeItem(email, productId);
    }

    @GetMapping(Mappings.SIZE)
    public int sizeOfCart(@PathVariable String email){
        return cartService.numberOfItemsInCart(email);
    }

    @GetMapping(Mappings.CART_ITEMS)
    public List<CartItem> getCartItems(@PathVariable String email){
        return cartService.getCartItems(email);
    }
}
