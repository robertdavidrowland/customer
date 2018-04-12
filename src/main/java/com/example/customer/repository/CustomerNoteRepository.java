package com.example.customer.repository;

import com.example.customer.model.CustomerNote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerNoteRepository extends JpaRepository<CustomerNote, Long> {
}
