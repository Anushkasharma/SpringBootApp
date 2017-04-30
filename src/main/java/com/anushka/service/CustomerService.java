package com.anushka.service;

import com.anushka.entity.Customer;
import com.anushka.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by rxd2095 on 4/29/17.
 */
@Component
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getAllCustomersUnordered() {

        return customerRepository.getAllCustomersUnordered();

    }

    public List<Customer> getAllCustomersOrderedByLastName() {

        return customerRepository.getAllCustomersOrderedByLastName();
    }

}
