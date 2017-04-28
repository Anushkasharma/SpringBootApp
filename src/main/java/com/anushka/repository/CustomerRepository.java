package com.anushka.repository;

import com.anushka.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rxd2095 on 4/19/17.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstName(String firstName);
}
