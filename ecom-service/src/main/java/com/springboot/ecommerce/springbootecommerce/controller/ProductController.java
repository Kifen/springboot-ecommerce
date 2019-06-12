package com.springboot.ecommerce.springbootecommerce.controller;

import com.springboot.ecommerce.springbootecommerce.model.Product;
import com.springboot.ecommerce.springbootecommerce.service.ProductService;
import com.springboot.ecommerce.springbootecommerce.utility.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.net.URI;

@RestController
@RequestMapping(Mappings.API_PRODUCTS)
public class ProductController {

    @Autowired
    private ProductService productService;

    //GET request to fetch all products from database
    @GetMapping(value = {"","/"})
    public Iterable<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //GET request to fetch a product using Id from the database
    @GetMapping(Mappings.PRODUCT_ID)
    public Product getProduct(@PathVariable long id){
        return productService.getProductById(id);
    }

    //POST request to add new product to database
    @PostMapping(value = {"/",""})
    public ResponseEntity<Product> addNewProduct(@NotNull @RequestBody Product product){
        Product newProduct = productService.addProduct(product);
        URI location = getUriLoc(String.valueOf(newProduct.getId()), product);
        return ResponseEntity.accepted().build();
    }

    //DELETE request to delete a product from database
    @DeleteMapping(Mappings.PRODUCT_ID)
    public void deleteProduct(@PathVariable long id){
        productService.deleteProductById(id);
    }


    private URI getUriLoc(String id, Product product){
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(id)
                .buildAndExpand(product.getId()).toUri();
        return location;
    }

    @GetMapping(Mappings.QTY_STOCK)
    public int qtyInStock(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return product.getQuantity_in_stock();
    }
}
