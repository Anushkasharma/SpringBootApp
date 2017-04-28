package com.anushka.controller;

import com.anushka.utility.AbstractAnushkaTestDataSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by rxd2095 on 4/15/17.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ProductControllerTest extends AbstractAnushkaTestDataSetup {

    @Test
    public void productController_correctlyMapsToEndpoint() throws Exception {
        productMockMvc.perform(get("/products/findAll"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}