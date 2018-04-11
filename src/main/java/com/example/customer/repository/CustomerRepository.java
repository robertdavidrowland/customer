package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, String> {

    List<Customer> findByFirstName(String firstName);

    List<Customer> findByLastName(String lastName);
}
