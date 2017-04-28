package com.anushka.controller;

import com.anushka.entity.Orders;
import com.anushka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by rxd2095 on 4/21/17.
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    // http://localhost:8080/orders/subtotal?orderDate=2017-04-02
    @GetMapping("/orders/subtotal")
    public ResponseEntity<?> getOrderSubtotalByOrderDate(@RequestParam String orderDate) {
        try {
            LocalDate oDate = LocalDate.parse(orderDate);
            Double subTotal = orderService.getOrderSubtotalByOrderDateAnnotated(oDate);
            return new ResponseEntity<>(subTotal, HttpStatus.OK);
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    // http://localhost:8080//orders/getOrderDetailsForCustomerByFirstName?firstName=Anushka
    @GetMapping("/orders/getOrderDetailsForCustomerByFirstName")
    public ResponseEntity<?> getOrderDetailsForCustomerByFirstName(@RequestParam String firstName) {
        try {
            List<Orders> ordersList = orderService.findAllOrdersWithAllOtherInfoByCustomerFirstName(firstName);
            return new ResponseEntity<>(ordersList, HttpStatus.OK);
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    // http://localhost:8080//orders/getOrderDetailsForCustomerById?id=1
    @GetMapping("/orders/getOrderDetailsForCustomerById")
    public ResponseEntity<?> getOrderDetailsForCustomerById(@RequestParam Long id) {
        try {
            List<Orders> ordersList = orderService.findAllOrdersWithAllOtherInfoByCustomerId(id);
            return new ResponseEntity<>(ordersList, HttpStatus.OK);
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

}
