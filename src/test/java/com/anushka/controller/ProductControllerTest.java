package com.anushka.controller;

import com.anushka.entity.Product;
import com.anushka.entity.ProductType;
import com.anushka.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rxd2095 on 4/15/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProductControllerTest {

    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductController productController;

    @Before
    public void setUp() {
        List<Product> productList = Arrays.asList(
                new Product(ProductType.LILLY, "White Lilly", 1.00),
                new Product(ProductType.CHRYSANTHYMUM, "Polka-dotted Chrysanthymum", 2.00)
        );
        productList.forEach(productRepository::save);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @After
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void productController_correctlyMapsToEndpoint() throws Exception {
        mockMvc.perform(get("/products/findAll"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}