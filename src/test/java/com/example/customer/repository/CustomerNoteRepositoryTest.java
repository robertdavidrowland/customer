package com.example.customer.repository;

import com.example.customer.model.CustomerNote;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerNoteRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerNoteRepositoryTest.class);

    private final CustomerNote note = new CustomerNote().setText("Lorem ipsum dollar");

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CustomerNoteRepository customerNoteRepository;

    @Before
    public void setUp() {
        entityManager.persist(note);
        entityManager.flush();
    }

    @Test
    public void findByIdReturnsCustomerNote() {
        log.debug("findByIdReturnsCustomerNote");

        Optional<CustomerNote> found = customerNoteRepository.findById(note.getId());

        assertThat(found.isPresent()).isEqualTo(true);
        assertThat(found.get()).isEqualTo(note);
    }
}
