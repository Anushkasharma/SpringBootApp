package com.anushka.repository;

import com.anushka.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by rxd2095 on 4/17/17.
 */
@Component
public interface OrderRepository extends JpaRepository<Orders, Long> {

    @Query(value = "SELECT * FROM PRODUCT P INNER JOIN ORDERS_PRODUCTS OP ON P.ID = OP.PRODUCTS_ID INNER JOIN ORDERS O ON O.ID = OP.ORDERS_ID", nativeQuery = true)
    List<Orders> findAllProductsAndAllOrders();

}
