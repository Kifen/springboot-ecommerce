package com.service.cart.repository;

import com.service.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByEmail(@NotNull String email);
    Boolean existsByEmail(@NotNull String email);
}
