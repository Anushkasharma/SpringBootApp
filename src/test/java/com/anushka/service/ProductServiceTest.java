package com.anushka.service;

import com.anushka.configuration.AbstractAnushkaDataSetup;
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

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rxd2095 on 4/15/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest extends AbstractAnushkaDataSetup {

    @Test
    public void productService_returnsAllProducts() {
        List<Product> products = productService.findAllProducts();
        assertEquals(4, products.size());
    }

}