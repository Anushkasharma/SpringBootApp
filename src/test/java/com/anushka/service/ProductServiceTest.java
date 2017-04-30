package com.anushka.service;

import com.anushka.entity.Product;
import com.anushka.entity.ProductType;
import com.anushka.utility.AbstractAnushkaTestDataSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rxd2095 on 4/15/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest extends AbstractAnushkaTestDataSetup {

    @Test
    public void productService_returnsAllProducts() {
        List<Product> products = productService.findAllProducts();
        assertEquals(4, products.size());
    }

    @Test
    public void productService_returnsAllProductsSortedByPriceAsc() {
        List<Product> productList = productService.findAllProductsOrderedByPriceAsc();
        ProductType expectedProductType = ProductType.SUNFLOWER;
        assertEquals(expectedProductType, productList.get(productList.size() - 1).getProductType());
    }

}