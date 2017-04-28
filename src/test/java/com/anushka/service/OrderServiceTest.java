package com.anushka.service;

import com.anushka.entity.Orders;
import com.anushka.utility.AbstractAnushkaTestDataSetup;
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
 * Created by rxd2095 on 4/20/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class OrderServiceTest extends AbstractAnushkaTestDataSetup {

    @Test
    public void getOrderSubtotalByOrderDate_returnsOrdersByOrderDate_usingAnnotation() {
        LocalDate orderDate = LocalDate.of(2017, Month.APRIL, 1);
        double orderSubTotal = orderService.getOrderSubtotalByOrderDateAnnotated(orderDate);
        assertEquals(16.0, orderSubTotal, 0.0);
    }

    @Test
    public void getOrderSubtotalByOrderDate_returnsOrdersByOrderDate_usingJPA() {
        LocalDate orderDate = LocalDate.of(2017, Month.APRIL, 1);
        List<Orders> ordersList = orderService.getOrderSubtotalByOrderDate(orderDate);
        double expectedOrderSubTotal = ordersList.get(0).getOrderSubTotal();
        assertEquals(16.0, expectedOrderSubTotal, 0.0);
    }

    @Test
    public void findAllOrdersWithAllOtherInfoByCustomerId_returnsOrdersByCustomerId() {
        Long customerId = customerRepository.findByFirstName("Anushka").getId();
        List<Orders> ordersList = orderService.findAllOrdersWithAllOtherInfoByCustomerId(customerId);
        assertEquals(3, ordersList.size());
    }

}