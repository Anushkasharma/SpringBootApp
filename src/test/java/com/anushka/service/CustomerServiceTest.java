package com.anushka.service;

import com.anushka.entity.Customer;
import com.anushka.utility.AbstractAnushkaTestDataSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by rxd2095 on 4/29/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest extends AbstractAnushkaTestDataSetup {

    @Test
    public void customerService_usingCriteriaWithJPAToReturnOrderedListOfCustomers() {
        String expectedLastName = "";
        String actualLastName = "";

        List<Customer> allCustomersUnordered = customerService.getAllCustomersUnordered_usingCriteria();
        expectedLastName = "Lal";
        actualLastName = allCustomersUnordered.get(0).getLastName();
        assertEquals(expectedLastName, actualLastName);

        List<Customer> allCustomersOrdered = customerService.getAllCustomersOrderedByLastName_usingCriteria();
        expectedLastName = "Irani";
        actualLastName = allCustomersOrdered.get(0).getLastName();
        assertEquals(expectedLastName, actualLastName);
        assertNotEquals(allCustomersUnordered.get(0), allCustomersOrdered.get(0));
    }

}