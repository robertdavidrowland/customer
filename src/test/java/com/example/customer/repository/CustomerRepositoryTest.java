package com.example.customer.repository;

import com.example.customer.model.Customer;
import com.example.customer.model.CustomerStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryTest.class);

    private final Customer rob = new Customer().setId("rowlro01").setFirstName("Rob").setLastName("Rowland").setStatus(CustomerStatus.PROSPECTIVE);
    private final Customer andy = new Customer().setId("rowlan01").setFirstName("Andy").setLastName("Rowland").setStatus(CustomerStatus.CURRENT);
    private final Customer andy2 = new Customer().setId("imrian01").setFirstName("Andy").setLastName("Imrie").setStatus(CustomerStatus.NONACTIVE);

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CustomerRepository customerRepository;

    @Before
    public void setUp() {
        entityManager.persist(rob);
        entityManager.persist(andy);
        entityManager.persist(andy2);
        entityManager.flush();
    }

    @Test
    public void findByIdReturnsCustomer() {
        log.debug("findByIdReturnsCustomer");

        Optional<Customer> found = customerRepository.findById(rob.getId());

        assertThat(found.isPresent()).isEqualTo(true);
        assertThat(found.get()).isEqualTo(rob);
    }

    @Test
    public void findByFirstNameReturnsCustomers() {
        log.debug("findByFirstNameReturnsCustomer");

        List<Customer> found = customerRepository.findByFirstName(andy.getFirstName());

        assertThat(found.size()).isEqualTo(2);
        assertThat(found.get(0)).isEqualTo(andy);
        assertThat(found.get(1)).isEqualTo(andy2);
    }

    @Test
    public void findByLastNameReturnsCustomers() {
        log.debug("findByLastNameReturnsCustomer");

        List<Customer> found = customerRepository.findByLastName(rob.getLastName());

        assertThat(found.size()).isEqualTo(2);
        assertThat(found.get(0)).isEqualTo(rob);
        assertThat(found.get(1)).isEqualTo(andy);
    }

    @Test
    public void findAllGetsAllCustomers() {
        log.debug("findAllGetsAllCustomers");

        List<Customer> found = customerRepository.findAll();

        //assertThat(found.size()).isEqualTo(13);
    }
}
