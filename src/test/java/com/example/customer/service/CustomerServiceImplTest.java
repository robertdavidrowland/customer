package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.model.CustomerStatus;
import com.example.customer.repository.CustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CustomerServiceImplTest {

    private final Customer rob = new Customer().setId("rowlro01").setFirstName("Rob").setLastName("Rowland").setStatus(CustomerStatus.PROSPECTIVE);
    private final Customer andy = new Customer().setId("rowlan01").setFirstName("Andy").setLastName("Rowland").setStatus(CustomerStatus.CURRENT);
    private final Customer andy2 = new Customer().setId("imrian01").setFirstName("Andy").setLastName("Imrie").setStatus(CustomerStatus.NONACTIVE);

    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void getCustomerByIdReturnsCustomer() {
        Mockito.when(customerRepository.findById(rob.getId())).thenReturn(Optional.of(rob));

        Optional<Customer> found = customerService.getCustomerById(rob.getId());

        assertThat(found.isPresent()).isEqualTo(true);
        assertThat(found.get()).isEqualTo(rob);
    }

    @Test
    public void getCustomersByFirstNameReturnsCustomer() {
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
