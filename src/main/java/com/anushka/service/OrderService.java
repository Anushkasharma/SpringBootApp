package com.anushka.service;

import com.anushka.entity.Orders;
import com.anushka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by rxd2095 on 4/20/17.
 */
@Component
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public double getOrderSubtotalByOrderDateAnnotated(LocalDate orderDate) {
        return orderRepository.getOrderSubtotalByOrderDateAnnotated(orderDate);
    }

    public List<Orders> getOrderSubtotalByOrderDate(LocalDate orderDate) {
        return orderRepository.getOrderSubtotalByOrderDate(orderDate);
    }


}
