package com.springboot.ecommerce.springbootecommerce.service;

import com.springboot.ecommerce.springbootecommerce.exception.ProductNotFoundException;
import com.springboot.ecommerce.springbootecommerce.model.Product;
import com.springboot.ecommerce.springbootecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public @NotNull List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product Not Found..."));
    }

    @Override
    public Product addProduct(@NotNull Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(@NotNull Long id) {
        productRepository.deleteById(id);
    }
}

