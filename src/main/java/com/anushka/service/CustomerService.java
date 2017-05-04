package com.anushka.service;

import com.anushka.entity.Customer;
import com.anushka.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rxd2095 on 4/29/17.
 */
@Component
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomersUnordered_usingCriteria() {
        return customerRepository.getAllCustomersUnordered_usingCriteria();
    }

    public List<Customer> getAllCustomersOrderedByLastName_usingCriteria() {
        return customerRepository.getAllCustomersOrderedByLastName_usingCriteria();
    }

}
