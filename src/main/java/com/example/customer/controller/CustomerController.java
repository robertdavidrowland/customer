package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @RequestMapping(value = "/customers/", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> listAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        customers.stream().forEach(c -> log.debug(c.toString()));


        return new ResponseEntity<>(customers, HttpStatus.OK);
    }
}
