package com.springboot.ecommerce.springbootecommerce.service;

import com.springboot.ecommerce.springbootecommerce.model.Product;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface ProductService {

    @NotNull List<Product> getAllProducts();
    Product getProductById(Long id);
    Product addProduct(@NotNull @Valid Product product);
    void deleteProductById(@NotNull Long id);
}
