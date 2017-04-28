package com.anushka.controller;

import com.anushka.entity.Product;
import com.anushka.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rxd2095 on 4/15/17.
 */
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products/findAll")
    public ResponseEntity<?> findAllProducts() {
        try {
            List<Product> productList = productService.findAllProducts();
            return new ResponseEntity<>(productList, HttpStatus.OK);
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

}
