package com.anushka.service;

import com.anushka.configuration.AbstractAnushkaDataSetup;
import com.anushka.entity.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rxd2095 on 4/20/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest extends AbstractAnushkaDataSetup {

    @Test
    public void orderServiceCalculatesOrderSubtotal() {
        Orders fakeOrder = new Orders();
        List<ProductsOrders> fakeOrderList = new ArrayList<>();
        Product orangeRose = new Product(ProductType.ROSE, "Orange Rose", 99.99);
        Product brownRose = new Product(ProductType.ROSE, "Brown Rose", 4.00);
        Customer anushka = new Customer();
        anushka.setFirstName("Anushka");
        anushka.setLastName("Sharma");
        ProductsOrders fakePO1 = new ProductsOrders(orangeRose, 12, calculateProductSubTotal(orangeRose, 12));
        ProductsOrders fakePO2 = new ProductsOrders(brownRose, 6, calculateProductSubTotal(brownRose, 6));
        fakeOrderList.add(fakePO1);
        fakeOrderList.add(fakePO2);
        fakeOrder.setId(100L);
        fakeOrder.setProductsOrders(fakeOrderList);
        fakeOrder.setOrderDate(LocalDate.of(2017, Month.OCTOBER, 31));
        fakeOrder.setCustomer(anushka);
        orderRepository.save(fakeOrder);

        assertEquals(1.00, orderRepository.getOne(fakeOrder.getId()).getOrderSubTotal());
    }

    @Test
    public void orderServiceCalculatesOrderTax() {

    }

    @Test
    public void orderServiceCalculatesOrderTotal() {

    }

}