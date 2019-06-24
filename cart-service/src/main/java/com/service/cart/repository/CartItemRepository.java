package com.service.cart.repository;

import com.service.cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    //Page<CartItem> findByCartId(Long cartId, Pageable pageable);
    List<CartItem> findByCartId(Long cartId);
    List<CartItem> findByProductIdAndCartId(Long productId, Long cartId);
}
