package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @ModelAttribute("allCustomers")
    public List<Customer> populateCustomers() {
        return customerService.getAllCustomers();
    }

    @RequestMapping("/")
    public String customers() {
        return "customers";
    }
}
