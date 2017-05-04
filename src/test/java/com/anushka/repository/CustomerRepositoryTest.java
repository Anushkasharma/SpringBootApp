package com.anushka.repository;

import com.anushka.entity.Customer;
import com.anushka.utility.AbstractAnushkaTestDataSetup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by rxd2095 on 4/30/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerRepositoryTest extends AbstractAnushkaTestDataSetup {

    @Test
    public void customerRepository_usingCriteriaWithJPAToReturnOrderedListOfCustomers() {
        List<Customer> allCustomersUnordered = customerRepository.getAllCustomersUnordered_usingCriteria();
        List<Customer> allCustomersOrdered = customerRepository.getAllCustomersOrderedByLastName_usingCriteria();
        assertEquals("Unordered List did not contain the right element in the first position", allCustomersUnordered.get(0).getLastName(), "Lal");
        assertEquals("Ordered List did not properly order the list of Customers in the proper order", allCustomersOrdered.get(0).getLastName(), "Irani");
        assertNotEquals(allCustomersUnordered.get(0), allCustomersOrdered.get(0));
    }

    @Test
    public void customerRepository_getCustomersById_usingCriteriaWithPredicates() {
        // Let's first get customer Mohan Lal
        Long id = 0L;
        int expectedCustomers = 0;
        int actualCustomers = 0;
        String expectedFirstName = "";
        String actualFirstName = "";
        List<Customer> customerList = new ArrayList<>();

        id = 1L;
        expectedCustomers = 1;
        expectedFirstName = "Mohan";
        customerList = customerRepository.getCustomerById_usingCriteriaWithPredicate(id);
        // There should only be one customer for this Id (duh!)
        actualCustomers = customerList.size();
        assertEquals(expectedCustomers, actualCustomers);
        // His name should be Mohan Lal
        actualFirstName = customerList.get(0).getFirstName();
        assertEquals(expectedFirstName, actualFirstName);

        // Now let's get customer Sanaya Irani
        id = 2L;
        expectedCustomers = 1;
        expectedFirstName = "Sanaya";
        customerList = customerRepository.getCustomerById_usingCriteriaWithPredicate(id);
        // There should only be one customer (again) for this Id
        actualCustomers = customerList.size();
        assertEquals(expectedCustomers, actualCustomers);
        // Her name should be Sanaya
        actualFirstName = customerList.get(0).getFirstName();
        assertEquals(expectedFirstName, actualFirstName);
    }

    @Test
    public void customerRepository_getCustomerById_usingJDBCTemplate() {
        Long id = 0L;
        String expectedFirstName = "";
        String actualFirstName = "";

        id = 1L;
        Customer customer1 = customerRepository.getCustomerById_usingJDBCTemplate(id);
        expectedFirstName = "Mohan";
        actualFirstName = customer1.getFirstName();
        assertEquals(expectedFirstName, actualFirstName);

        id = 2L;
        Customer customer2 = customerRepository.getCustomerById_usingJDBCTemplate(id);
        expectedFirstName = "Sanaya";
        actualFirstName = customer2.getFirstName();
        assertEquals(expectedFirstName, actualFirstName);
    }

}
