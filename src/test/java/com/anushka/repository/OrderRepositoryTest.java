package com.anushka.repository;

import com.anushka.configuration.AbstractAnushkaDataSetup;
import com.anushka.entity.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rxd2095 on 4/18/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class OrderRepositoryTest extends AbstractAnushkaDataSetup {

    @Test
    public void findAllOrdersByCustomerFirstName_usingQueryAnnotation() {
        String fName = "Anushka";
        List<Orders> orderList = orderRepository.findAllOrdersByCustomerFirstName(fName);
        assertEquals(2, orderList.size());

        fName = "Chad";
        orderList = orderRepository.findAllOrdersByCustomerFirstName(fName);
        assertEquals(1, orderList.size());
    }

    @Test
    public void getOrderSubTotalByDate_usingQueryAnnotation() {
        LocalDate orderDate = LocalDate.of(2017, Month.APRIL, 1);
        double subTotal = orderRepository.getOrderSubtotalByOrderDate(orderDate);
        assertEquals(16.00, subTotal, 0.00);
    }

}