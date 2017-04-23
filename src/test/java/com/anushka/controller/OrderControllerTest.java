package com.anushka.controller;

import com.anushka.configuration.AbstractAnushkaDataSetup;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
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
public class OrderControllerTest extends AbstractAnushkaDataSetup {

    @Test
    public void orderControllerConnectsToEndpoint() throws Exception {
        LocalDate orderDate = LocalDate.of(2017, Month.APRIL, 1);
        mockMvc.perform(get("/orders/subtotal?orderDate=" + orderDate))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void orderController_returnsJsonBasedOnEndpoint() throws Exception {
        LocalDate orderDate = LocalDate.of(2017, Month.APRIL, 1);
        double expectedValue = 16.0;
        mockMvc.perform(get("/orders/subtotal?orderDate=" + orderDate))
                .andDo(print())
                .andExpect(jsonPath("$", CoreMatchers.is(expectedValue)));
    }

}