package com.anushka.repository;

import com.anushka.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by rxd2095 on 4/17/17.
 */
@Component
public interface OrderRepository extends JpaRepository<Orders, Long> {
}
