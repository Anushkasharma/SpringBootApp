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
        // This test uses the @Query annotation in OrdersRepository
        String fName = "Anushka";
        List<Orders> orderList = orderRepository.findAllOrdersByCustomerFirstNameAnnotated(fName);
        assertEquals(2, orderList.size());

        fName = "Chad";
        orderList = orderRepository.findAllOrdersByCustomerFirstNameAnnotated(fName);
        assertEquals(1, orderList.size());
    }

    @Test
    public void findAllOrdersByCustomerFirstName_usingJPAFunctionNaming() {
        // This test uses JPA's naming convention standards of the Repository functions to return results
        String fName = "Anushka";
        List<Orders> orderList = orderRepository.findAllOrdersByCustomerFirstName(fName);
        assertEquals(2, orderList.size());

        fName = "Chad";
        orderList = orderRepository.findAllOrdersByCustomerFirstName(fName);
        assertEquals(1, orderList.size());
    }

    @Test
    public void getOrderSubTotalByDate_usingQueryAnnotation() {
        // This test uses the @Query annotation in OrdersRepository
        LocalDate orderDate = LocalDate.of(2017, Month.APRIL, 1);
        // Notice that we can get any of our data elements using a custom query; however, if we need to
        // return a collection of data elements (a subset of object's properties), we will have to create
        // a custom POJO to contain those, then return them from the @Query-annotated repository function.
        double subTotal = orderRepository.getOrderSubtotalByOrderDateAnnotated(orderDate);
        assertEquals(16.00, subTotal, 0.00);
    }

    @Test
    public void getOrderSubTotalByDate_usingJPAFunctionNaming() {
        // This test uses JPA's naming convention standards of the Repository functions to return results
        LocalDate orderDate = LocalDate.of(2017, Month.APRIL, 1);
        // Notice that JPA tries to return a List of Orders, so we are responsible for traversing the collection
        // to retrieve the property(ies) that we need (something we don't have to do when we use @Query, above)
        List<Orders> ordersList = orderRepository.getOrderSubtotalByOrderDate(orderDate);
        double orderSubTotal = ordersList.get(0).getOrderSubTotal();
        assertEquals(16.00, orderSubTotal, 0.00);
    }

}