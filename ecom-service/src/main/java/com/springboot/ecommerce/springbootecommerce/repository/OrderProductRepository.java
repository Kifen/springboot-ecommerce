package com.springboot.ecommerce.springbootecommerce.repository;

import com.springboot.ecommerce.springbootecommerce.model.OrderProduct;
import com.springboot.ecommerce.springbootecommerce.model.OrderProductPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPk> {
}
