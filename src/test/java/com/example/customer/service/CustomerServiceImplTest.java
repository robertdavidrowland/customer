package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.model.CustomerStatus;
import com.example.customer.repository.CustomerRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CustomerServiceImplTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerRepositoryTest.class);

    private final Customer rob = new Customer().setId("rowlro01").setFirstName("Rob").setLastName("Rowland").setStatus(CustomerStatus.PROSPECTIVE);
    private final Customer andy = new Customer().setId("rowlan01").setFirstName("Andy").setLastName("Rowland").setStatus(CustomerStatus.CURRENT);
    private final Customer andy2 = new Customer().setId("imrian01").setFirstName("Andy").setLastName("Imrie").setStatus(CustomerStatus.NONACTIVE);

    @TestConfiguration
    static class CustomerServiceImplTestContextConfiguration {

        @Bean
        public CustomerService customerService() {
            return new CustomerServiceImpl();
        }
    }

    @Autowired
    private CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
    }

    @Test
    public void getCustomerByIdReturnsCustomer() {
        log.debug("getCustomerByIdReturnsCustomer");

        Mockito.when(customerRepository.findById(rob.getId())).thenReturn(Optional.of(rob));

        Optional<Customer> found = customerService.getCustomerById(rob.getId());

        assertThat(found.isPresent()).isEqualTo(true);
        assertThat(found.get()).isEqualTo(rob);
    }

    @Test
    public void getCustomersByFirstNameReturnsCustomer() {
        log.debug("getCustomersByFirstNameReturnsCustomer");

        List<Customer> customers = new ArrayList<>();
        customers.add(andy);
        customers.add(andy2);
        Mockito.when(customerRepository.findByFirstName(andy.getFirstName())).thenReturn(customers);

        List<Customer> found = customerService.getCustomersByFirstName(andy.getFirstName());

        assertThat(found.size()).isEqualTo(2);
        assertThat(found.get(0)).isEqualTo(andy);
        assertThat(found.get(1)).isEqualTo(andy2);
    }

    @Test
    public void getCustomersByLastNameReturnsCustomer() {
        log.debug("getCustomersByLastNameReturnsCustomer");

        List<Customer> customers = new ArrayList<>();
        customers.add(rob);
        customers.add(andy);
        Mockito.when(customerRepository.findByLastName(rob.getLastName())).thenReturn(customers);

        List<Customer> found = customerService.getCustomersByLastName(rob.getLastName());

        assertThat(found.size()).isEqualTo(2);
        assertThat(found.get(0)).isEqualTo(rob);
        assertThat(found.get(1)).isEqualTo(andy);
    }
}
