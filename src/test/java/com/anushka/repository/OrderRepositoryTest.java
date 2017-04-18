package com.anushka.repository;

import com.anushka.entity.Orders;
import com.anushka.entity.Product;
import com.anushka.entity.ProductType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rxd2095 on 4/18/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Before
    public void setUp() {
        Product product1 = new Product(ProductType.DAISY, "Polka-dotted Daisy", 9.99);
        Product product2 = new Product(ProductType.DAISY, "Red-striped Daisy", 9.98);

        Orders orders1 = new Orders();

        List<Product> productList1 = Arrays.asList(product1, product2);

        orders1.setOrderDate(LocalDate.of(2017, Month.APRIL, 1));
        orders1.setOrderQuantity(1);
        orders1.setOrderSubtotal(24.00);
        orders1.setProducts(productList1);

        List<Orders> ordersList = new ArrayList<>();
        ordersList.add(orders1);

        ordersList.forEach(orderRepository::save);
    }

    @After
    public void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    public void usingQueryAnnotation_returnsAllRecords() {
        List<Orders> orders = orderRepository.findAllProductsAndAllOrders();
        assertEquals(orders.size(), 1);
    }

}