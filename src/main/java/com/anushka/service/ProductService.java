package com.anushka.service;

import com.anushka.entity.Product;
import com.anushka.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rxd2095 on 4/15/17.
 */
@Component
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

}
