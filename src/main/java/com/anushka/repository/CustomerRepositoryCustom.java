package com.anushka.repository;

import com.anushka.entity.Customer;
import com.anushka.entity.Orders;

import java.util.List;
import java.util.Map;

/**
 * Created by rxd2095 on 4/30/17.
 */
public interface CustomerRepositoryCustom {
    List<Customer> getAllCustomersUnordered();
    List<Customer> getAllCustomersOrderedByLastName();
    List<Customer> getCustomerById(final Long id);
}
