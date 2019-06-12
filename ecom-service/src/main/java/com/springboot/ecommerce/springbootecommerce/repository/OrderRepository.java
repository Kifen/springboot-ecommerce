package com.springboot.ecommerce.springbootecommerce.repository;

import com.springboot.ecommerce.springbootecommerce.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
