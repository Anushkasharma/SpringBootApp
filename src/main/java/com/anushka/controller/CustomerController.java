package com.anushka.controller;

import com.anushka.entity.Customer;
import com.anushka.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by rxd2095 on 4/30/17.
 */
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers/unordered")
    public ResponseEntity<?> getAllCustomersUnordered() {
        try {
            List<Customer> customerList = customerService.getAllCustomersUnordered();
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/customers/orderedByLastName")
    public ResponseEntity<?> getAllCustomersOrderedByLastName() {
        try {
            List<Customer> customerList = customerService.getAllCustomersOrderedByLastName();
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        } catch (Exception ex) {
            String errorMessage = ex.getMessage();
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }
    }

}
