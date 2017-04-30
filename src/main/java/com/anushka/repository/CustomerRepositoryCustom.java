package com.anushka.repository;

import com.anushka.entity.Customer;
import java.util.List;

/**
 * Created by rxd2095 on 4/30/17.
 */
public interface CustomerRepositoryCustom {
    List<Customer> getAllCustomersUnordered();
    List<Customer> getAllCustomersOrderedByLastName();
}
