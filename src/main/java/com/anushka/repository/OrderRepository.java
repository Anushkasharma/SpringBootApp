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
public interface OrderRepository extends JpaRepository<Orders, Long>, OrderRepositoryCustom {

    @Query(value = "SELECT * FROM ORDERS O INNER JOIN ORDERS_PRODUCTS_ORDERS OPO ON O.ID = OPO.ORDERS_ID INNER JOIN PRODUCTS_ORDERS PO ON OPO.PRODUCTS_ORDERS_ID = PO.ID INNER JOIN CUSTOMER C ON O.CUSTOMER_ID = C.ID INNER JOIN PRODUCT P ON OPO.PRODUCTS_ORDERS_ID = P.ID WHERE C.FIRST_NAME = ?1", nativeQuery = true)
    List<Orders> findAllOrdersWithAllOtherInfoByCustomerFirstName(String firstName);

    @Query(value = "SELECT * FROM ORDERS O INNER JOIN ORDERS_PRODUCTS_ORDERS OPO ON O.ID = OPO.ORDERS_ID INNER JOIN PRODUCTS_ORDERS PO ON OPO.PRODUCTS_ORDERS_ID = PO.ID INNER JOIN CUSTOMER C ON O.CUSTOMER_ID = C.ID INNER JOIN PRODUCT P ON OPO.PRODUCTS_ORDERS_ID = P.ID WHERE O.CUSTOMER_ID = ?1", nativeQuery = true)
    List<Orders> findAllOrdersWithAllOtherInfoByCustomerId(Long id);

    @Query(value = "SELECT * FROM ORDERS O INNER JOIN CUSTOMER C ON O.CUSTOMER_ID = C.ID WHERE C.FIRST_NAME = ?1", nativeQuery = true)
    List<Orders> findAllOrdersByCustomerFirstNameAnnotated(String firstName);

    List<Orders> findAllOrdersByCustomerFirstName(String firstName);

    @Query(value = "SELECT SUM(PRODUCT_SUB_TOTAL) FROM PRODUCTS_ORDERS PO INNER JOIN ORDERS_PRODUCTS_ORDERS OPO ON PO.ID = OPO.PRODUCTS_ORDERS_ID INNER JOIN ORDERS O ON OPO.ORDERS_ID = O.ID WHERE O.ORDER_DATE = ?1", nativeQuery = true)
    Double getOrderSubtotalByOrderDateAnnotated(LocalDate orderDate);

    List<Orders> getOrderSubtotalByOrderDate(LocalDate orderDate);
}
