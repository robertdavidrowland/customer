package com.example.customer.service;

import com.example.customer.model.CustomerNote;

import java.util.Optional;

public interface CustomerNoteService {

    Optional<CustomerNote> getCustomerNoteById(Long id);

    void delete(CustomerNote customerNote);

    CustomerNote create(CustomerNote customerNote);

    void update(Long id, CustomerNote customerNote);

    void save(CustomerNote customerNote);
}
