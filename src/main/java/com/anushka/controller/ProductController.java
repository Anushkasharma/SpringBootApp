package com.anushka.controller;

import com.anushka.entity.Product;
import com.anushka.service.ProductService;

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
