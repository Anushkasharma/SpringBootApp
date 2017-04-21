package com.anushka.service;

import com.anushka.entity.Orders;
import com.anushka.entity.ProductsOrders;
import com.anushka.entity.TaxConstants;
import com.anushka.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rxd2095 on 4/20/17.
 */
@Component
public class OrderService {

    @Autowired
    OrderRepository orderRepository;


}
