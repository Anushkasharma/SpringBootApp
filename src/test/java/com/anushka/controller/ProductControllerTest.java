package com.anushka.controller;

import com.anushka.configuration.AbstractAnushkaDataSetup;
import com.anushka.entity.Product;
import com.anushka.entity.ProductType;
import com.anushka.repository.ProductRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rxd2095 on 4/15/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProductControllerTest extends AbstractAnushkaDataSetup {

    @Test
    public void productController_correctlyMapsToEndpoint() throws Exception {
        productMockMvc.perform(get("/products/findAll"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}