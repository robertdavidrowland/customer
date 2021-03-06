package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.model.CustomerNote;
import com.example.customer.repository.CustomerNoteRepository;
import com.example.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerNoteServiceImpl implements CustomerNoteService {

    private CustomerNoteRepository customerNoteRepository;

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerNoteServiceImpl(
            CustomerNoteRepository customerNoteRepository,
            CustomerRepository customerRepository) {

        this.customerNoteRepository = customerNoteRepository;

        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<CustomerNote> getCustomerNoteById(Long id) {
        return customerNoteRepository.findById(id);
    }

    @Override
    public CustomerNote create(CustomerNote customerNote, String customerId) {
        customerNote = customerNoteRepository.save(customerNote);

        Customer customer = customerRepository.getOne(customerId);
        customer.addCustomerNote(customerNote);
        customerRepository.save(customer);

        return customerNote;
    }

    @Override
    public void update(Long id, CustomerNote customerNote) {
        customerNote.setId(id);
        customerNoteRepository.save(customerNote);
    }

    @Override
    public void deleteById(Long id, String customerId) {
        CustomerNote customerNote = customerNoteRepository.getOne(id);

        Customer customer = customerRepository.getOne(customerId);
        customer.removeCustomerNote(customerNote);
        customerRepository.save(customer);

        customerNoteRepository.delete(customerNoteRepository.getOne(id));
    }
}
