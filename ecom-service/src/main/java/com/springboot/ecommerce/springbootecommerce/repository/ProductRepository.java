package com.springboot.ecommerce.springbootecommerce.repository;

import com.springboot.ecommerce.springbootecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
