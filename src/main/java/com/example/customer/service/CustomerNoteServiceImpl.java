package com.example.customer.service;

import com.example.customer.model.CustomerNote;
import com.example.customer.repository.CustomerNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerNoteServiceImpl implements CustomerNoteService {

    @Autowired
    private CustomerNoteRepository customerNoteRepository;

    @Override
    public Optional<CustomerNote> getCustomerNoteById(Long id) {
        return customerNoteRepository.findById(id);
    }

    public void delete(CustomerNote customerNote) {
        customerNoteRepository.delete(customerNote);
    }

    public CustomerNote create(CustomerNote customerNote) {
        return customerNoteRepository.save(customerNote);
    }

    public void update(Long id, CustomerNote customerNote) {
        customerNote.setId(id);
        customerNoteRepository.save(customerNote);
    }

    public void save(CustomerNote customerNote) {
        customerNoteRepository.save(customerNote);
    }
}
