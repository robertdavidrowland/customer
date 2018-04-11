package com.example.customer.service;

import com.example.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getCustomerById(String id);

    List<Customer> getCustomerByFirstName(String name);

    List<Customer> getCustomerByLastName(String name);

    List<Customer> getAllCustomers();
}
