package com.anushka.repository;

import com.anushka.entity.Orders;

import java.util.List;

/**
 * Created by rxd2095 on 5/3/17.
 */
public interface OrderRepositoryCustom {
    List<Orders> getAllOrdersByCustomer_usingCriteriaAndPredicatesWithJoin(Long id);
    double getCumulativeOrderAmountByCustomer_usingCriteriaAndPredicatesWithJoin(Long id);
    List<Orders> getAllOrdersByCustomer_usingJDBCTemplate(Long id);
}
