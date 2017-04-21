package com.anushka.service;

import com.anushka.entity.Product;
import com.anushka.repository.ProductRepository;

import java.util.List;

/**
 * Created by rxd2095 on 4/15/17.
 */
//@Component
public class ProductService {

//    @Autowired
    ProductRepository productRepository;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

}
