package com.anushka.repository;

import com.anushka.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by rxd2095 on 4/17/17.
 */
@Component
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query(value = "SELECT * FROM ORDERS O INNER JOIN ORDERS_PRODUCTS_ORDERS OPO ON O.ID = OPO.ORDERS_ID INNER JOIN PRODUCTS_ORDERS PO ON OPO.PRODUCTS_ORDERS_ID = PO.ID INNER JOIN CUSTOMER C ON O.CUSTOMER_ID = C.ID INNER JOIN PRODUCT P ON OPO.PRODUCTS_ORDERS_ID = P.ID WHERE C.FIRST_NAME = ?1", nativeQuery = true)
    List<Orders> findAllOrdersWithAllOtherInfoByCustomerFirstName(String firstName);

    @Query(value = "SELECT * FROM ORDERS O INNER JOIN CUSTOMER C ON O.CUSTOMER_ID = C.ID WHERE C.FIRST_NAME = ?1", nativeQuery = true)
    List<Orders> findAllOrdersByCustomerFirstName(String firstName);

    @Query(value = "", nativeQuery = true)
    double getOrderSubtotalByOrderDate(LocalDate orderDate);

}
