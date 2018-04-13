package com.example.customer.service;

import com.example.customer.model.CustomerNote;
import com.example.customer.repository.CustomerNoteRepository;
import com.example.customer.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CustomerNoteServiceImplTest {

    private final CustomerNote note = new CustomerNote().setText("Lorem ipsum dollar");

    private CustomerNoteService customerNoteService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerNoteRepository customerNoteRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerNoteService = new CustomerNoteServiceImpl(customerNoteRepository, customerRepository);
    }

    @Test
    public void getCustomerNoteByIdReturnsCustomerNote() {
        Mockito.when(customerNoteRepository.findById(note.getId())).thenReturn(Optional.of(note));

        Optional<CustomerNote> found = customerNoteService.getCustomerNoteById(note.getId());

        assertThat(found.isPresent()).isEqualTo(true);
        assertThat(found.get()).isEqualTo(note);
    }
}
