package com.springboot.ecommerce.springbootecommerce;

import com.springboot.ecommerce.springbootecommerce.model.Product;
import com.springboot.ecommerce.springbootecommerce.service.OrderService;
import com.springboot.ecommerce.springbootecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CLR implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;


    @Override
    public void run(String... args) throws Exception {
        productService.addProduct(new Product(1L, "TV Set", "Electronics", 150.0, 450.00, "http://placehold.it/200x100", 12));
        productService.addProduct(new Product(2L, "Game console", "Electronics", 12.0, 240.00, "http://placehold.it/200x100", 10));
        productService.addProduct(new Product(3L, "Sofa", "Furniture", 48.0, 60.00, "http://placehold.it/200x100",6));
        productService.addProduct(new Product(4L, "Yeezy", "Shoe", 9.0, 22.00, "http://placehold.it/200x100", 7));
        productService.addProduct(new Product(5L, "Bag", "Fashion", 10.0, 8.00, "http://placehold.it/200x100", 25));
        productService.addProduct(new Product(6L, "Nivea Body Spray", "Fashion", 5.0, 3.00, "http://placehold.it/200x100", 22));
        productService.addProduct(new Product(7L, "Beats speakers", "Electronics", 12.0, 25.00, "http://placehold.it/200x100", 5));
    }

}
