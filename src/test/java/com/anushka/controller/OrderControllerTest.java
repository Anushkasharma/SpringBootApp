package com.anushka.controller;

import com.anushka.utility.AbstractAnushkaTestDataSetup;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rxd2095 on 4/21/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class OrderControllerTest extends AbstractAnushkaTestDataSetup {

    @Test
    public void orderControllerConnectsToEndpoint() throws Exception {
        String sOrderDate = "2017-04-01";
        mockMvc.perform(get("/orders/subtotal?orderDate=" + sOrderDate))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void orderControllerDoesNotConnectToEndpoint() throws Exception {
        String sOrderDate = "BlahBlahBlah";
        mockMvc.perform(get("/orders/subtotal?orderDate=" + sOrderDate))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void orderController_returnsSubTotal_whenPassedOrderDateInTheCorrectFormat() throws Exception {
        LocalDate orderDate = LocalDate.of(2017, Month.APRIL, 1);
        double expectedValue = 16.0;
        mockMvc.perform(get("/orders/subtotal?orderDate=" + orderDate))
                .andDo(print())
                .andExpect(jsonPath("$", CoreMatchers.is(expectedValue)));
    }

    @Test
    public void orderController_returns400_whenPassedOrderDateInTheWrongFormat() throws Exception {
        String orderDate = "MyDogSnoresSoLoudly";
        mockMvc.perform(get("/orders/subtotal?orderDate=" + orderDate))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void orderController_returnsJsonForAllOrdersByCustomerId() throws Exception {
        Long customerId = customerRepository.findByFirstName("Mohan").getId();
        mockMvc.perform(get("/orders/getOrderDetailsForCustomerById?id=" + customerId.toString()))
                .andDo(print())
                // there are 3 separate products tied to the productorders for this customer
                .andExpect(jsonPath("$", hasSize(3)))
                // there are 3 customer names, but they are all the same, so we just want to make sure that our lovely "Anushka" appears in the collection.
                .andExpect(jsonPath("$..customer.firstName", hasItem("Mohan")))
                // these matchers assert that the json of the last order item from 4/3/2017 is what we expect.
                .andExpect(jsonPath("$.[2].id", equalTo(3)))
                .andExpect(jsonPath("$.[2].orderDate.[0]", equalTo(2017)))
                .andExpect(jsonPath("$.[2].orderDate.[1]", equalTo(4)))
                .andExpect(jsonPath("$.[2].orderDate.[2]", equalTo(3)))
                .andExpect(jsonPath("$.[2].productsOrders.[0].id", equalTo(4)))
                .andExpect(jsonPath("$.[2].productsOrders.[0].product.productType", equalTo("ORCHID")))
                .andExpect(jsonPath("$.[2].productsOrders.[0].product.productName", equalTo("Naked Man Orchid")))
                .andExpect(jsonPath("$.[2].productsOrders.[0].product.price", equalTo(10.0)))
                .andExpect(jsonPath("$.[2].productsOrders.[0].productQty", equalTo(10.0)))
                .andExpect(jsonPath("$.[2].productsOrders.[0].productSubTotal", equalTo(100.0)))
                .andExpect(jsonPath("$.[2].orderSubTotal", equalTo(100.0)))
                .andExpect(jsonPath("$.[2].orderTax", equalTo(6.0)))
                .andExpect(jsonPath("$.[2].orderTotal", equalTo(106.0)));
    }

    @Test
    public void orderController_returns400_forAllOrdersByCustomerId_whenPassedIdInTheWrongFormat() throws Exception {
        String customerId = "Hercules";
        mockMvc.perform(get("/orders/getOrderDetailsForCustomerById?id=" + customerId))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}