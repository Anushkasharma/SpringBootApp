package com.anushka.repository;

import com.anushka.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by rxd2095 on 4/19/17.
 */
@Component
public interface CustomerRepository extends JpaRepository<Customer, Long>, CustomerRepositoryCustom {
    Customer findByFirstName(String firstName);
}
