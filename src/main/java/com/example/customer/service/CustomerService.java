package com.example.customer.service;

import com.example.customer.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    Optional<Customer> getCustomerById(String id);

    List<Customer> getCustomersByFirstName(String name);

    List<Customer> getCustomersByLastName(String name);

    List<Customer> getAllCustomers();

    Customer create(Customer customer);

    void update(String id, Customer customer);

    void deleteById(String id);
}
