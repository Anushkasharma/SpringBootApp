package com.anushka.service;

import com.anushka.entity.Customer;
import com.anushka.utility.AbstractAnushkaTestDataSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by rxd2095 on 4/29/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerServiceTest extends AbstractAnushkaTestDataSetup {

    @Test
    public void customerService_usingCriteriaWithJPAToReturnOrderedListOfCustomers() {
        List<Customer> allCustomersUnordered = customerService.getAllCustomersUnordered();
        List<Customer> allCustomersOrdered = customerService.getAllCustomersOrderedByLastName();
        assertEquals("Unordered List did not contain the right element in the first position", allCustomersUnordered.get(0).getLastName(), "Sharma");
        assertEquals("Ordered List did not properly order the list of Customers in the proper order", allCustomersOrdered.get(0).getLastName(), "Davis");
        assertNotEquals(allCustomersUnordered.get(0), allCustomersOrdered.get(0));
    }

}