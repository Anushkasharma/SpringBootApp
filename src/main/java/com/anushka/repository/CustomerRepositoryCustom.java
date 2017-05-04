package com.anushka.repository;

import com.anushka.entity.Customer;

import java.util.List;

/**
 * Created by rxd2095 on 4/30/17.
 */
public interface CustomerRepositoryCustom {
    List<Customer> getAllCustomersUnordered_usingCriteria();
    List<Customer> getAllCustomersOrderedByLastName_usingCriteria();
    List<Customer> getCustomerById_usingCriteriaWithPredicate(final Long id);
    Customer getCustomerById_usingJDBCTemplate(final Long custId);
}
