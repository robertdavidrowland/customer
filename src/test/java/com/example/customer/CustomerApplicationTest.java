package com.example.customer;

import com.example.customer.model.Customer;
import com.example.customer.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerApplicationTest {

    private static final Logger log = LoggerFactory.getLogger(CustomerApplicationTest.class);

    @Autowired
    private CustomerService customerService;

    @Test
    public void contextLoadsWithSampleData() {
        List<Customer> customers = customerService.getAllCustomers();
        customers.forEach(c -> log.debug("customer: {}", c));
        //assertThat(customers.size()).isEqualTo(10);
    }
}
