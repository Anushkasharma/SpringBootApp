package com.anushka.controller;

import com.anushka.entity.Product;
import com.anushka.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rxd2095 on 4/15/17.
 */
//@RestController
public class ProductController {

//    @Autowired
    ProductService productService;

//    @GetMapping("/products/findAll")
    public List<Product> findAllProducts() {
        return productService.findAllProducts();
    }

}
