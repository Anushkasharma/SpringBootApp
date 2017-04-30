package com.anushka.controller;

import com.anushka.utility.AbstractAnushkaTestDataSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.Assert.*;

/**
 * Created by rxd2095 on 4/30/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerControllerTest extends AbstractAnushkaTestDataSetup {

    @Test
    public void customerControllerConnectsToEndpoint() throws Exception {
        customerMockMvc.perform(get("/customers/unordered"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void customerController_returnsAllCustomersUnsorted() throws Exception {
        customerMockMvc.perform(get("/customers/unordered"))
                .andDo(print())
                // there are 2 customers
                .andExpect(jsonPath("$", hasSize(2)))
                // the customers should be ordered in the same way they were added to the repo
                .andExpect(jsonPath("$.[0].id", equalTo(1)))
                .andExpect(jsonPath("$.[0].firstName", equalTo("Mohan")))
                .andExpect(jsonPath("$.[0].lastName", equalTo("Lal")))
                .andExpect(jsonPath("$.[1].id", equalTo(2)))
                .andExpect(jsonPath("$.[1].firstName", equalTo("Sanaya")))
                .andExpect(jsonPath("$.[1].lastName", equalTo("Irani")));
    }

    @Test
    public void customerController_returnsAllCustomersSortedByLastName() throws Exception {
        customerMockMvc.perform(get("/customers/orderedByLastName"))
                .andDo(print())
                // there are 2 customers
                .andExpect(jsonPath("$", hasSize(2)))
                // the customers should be ordered in the same way they were added to the repo
                .andExpect(jsonPath("$.[0].id", equalTo(2)))
                .andExpect(jsonPath("$.[0].firstName", equalTo("Sanaya")))
                .andExpect(jsonPath("$.[0].lastName", equalTo("Irani")))
                .andExpect(jsonPath("$.[1].id", equalTo(1)))
                .andExpect(jsonPath("$.[1].firstName", equalTo("Mohan")))
                .andExpect(jsonPath("$.[1].lastName", equalTo("Lal")));

    }

}