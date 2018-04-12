package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> getCustomersByFirstName(String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    @Override
    public List<Customer> getCustomersByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void delete(Customer customer) {
        customerRepository.delete(customer);
    }

    @Override
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void update(String id, Customer customer) {
        customer.setId(id);
        customerRepository.save(customer);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}
