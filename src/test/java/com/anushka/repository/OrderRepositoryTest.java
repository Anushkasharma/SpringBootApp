package com.anushka.repository;

import com.anushka.configuration.AbstractAnushkaDataSetup;
import com.anushka.entity.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void usingQueryAnnotationToFindAllOrdersByCustomerFirstName() {
        String fName = "Anushka";
        List<Orders> orderList = orderRepository.findAllOrdersByCustomerFirstName(fName);
        assertEquals(2, orderList.size());

        fName = "Chad";
        orderList = orderRepository.findAllOrdersByCustomerFirstName(fName);
        assertEquals(1, orderList.size());
    }

}