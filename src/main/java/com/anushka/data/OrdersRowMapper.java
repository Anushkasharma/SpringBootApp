package com.anushka.data;

import com.anushka.entity.Customer;
import com.anushka.entity.Orders;
import com.anushka.entity.ProductsOrders;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by rxd2095 on 5/3/17.
 */
public class OrdersRowMapper implements RowMapper<Orders> {
    @Override
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
        // customer entity
        Long custId = rs.getLong("CUSTOMER_ID");
        String firstName = rs.getString("FIRST_NAME");
        String lastName = rs.getString("LAST_NAME");
        LocalDate birthDay = rs.getDate("BIRTH_DAY").toLocalDate();
        Customer customer = new Customer(custId, firstName, lastName, birthDay);

        // orders entity
        Long orderId = rs.getLong("ID");
        LocalDate orderDate = rs.getDate("ORDER_DATE").toLocalDate();
        double orderSubTotal = rs.getDouble("ORDER_SUB_TOTAL");
        double orderTax = rs.getDouble("ORDER_TAX");
        double orderTotal = rs.getDouble("ORDER_TOTAL");
        Orders order = new Orders(orderId, orderDate, orderSubTotal, orderTax, orderTotal);
        order.setCustomer(customer);

        // TODO: ADD ORDERS_PRODUCTS_ORDERS, PRODUCTS_ORDERS, PRODUCT TO IMPL QUERY
        // SO THAT WE CAN POPULATE THE LIST OF ProductsOrders IN THE ORDERS OBJECT.
        return order;
    }
}
