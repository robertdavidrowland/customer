package com.example.customer.service;

import com.example.customer.model.CustomerNote;
import com.example.customer.repository.CustomerNoteRepository;
import com.example.customer.repository.CustomerRepositoryTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CustomerNoteServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryTest.class);

    private final CustomerNote note = new CustomerNote().setText("Lorem ipsum dollar");

    @TestConfiguration
    static class CustomerServiceImplTestContextConfiguration {

        @Bean
        public CustomerNoteService customerNoteService() {
            return new CustomerNoteServiceImpl();
        }
    }

    @Autowired
    private CustomerNoteService customerNoteService;

    @MockBean
    private CustomerNoteRepository customerNoteRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void getCustomerNoteByIdReturnsCustomerNote() {
        log.debug("getCustomerNoteByIdReturnsCustomerNote");

        Mockito.when(customerNoteRepository.findById(note.getId())).thenReturn(Optional.of(note));

        Optional<CustomerNote> found = customerNoteService.getCustomerNoteById(note.getId());

        assertThat(found.isPresent()).isEqualTo(true);
        assertThat(found.get()).isEqualTo(note);
    }
}
