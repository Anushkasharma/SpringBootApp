package com.anushka.controller;

import com.anushka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Created by rxd2095 on 4/21/17.
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    // http://localhost:8080/orders/subtotal?orderDate=2017-04-02
    @GetMapping("/orders/subtotal")
    public double getOrderSubtotalByOrderDate(@RequestParam String orderDate) {
        LocalDate oDate = LocalDate.parse(orderDate);
        return orderService.getOrderSubtotalByOrderDateAnnotated(oDate);
    }

}
