package com.example.customer;

import com.example.customer.model.Customer;
import com.example.customer.model.CustomerNote;
import com.example.customer.model.CustomerStatus;
import com.example.customer.repository.CustomerRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import de.svenjacobs.loremipsum.LoremIpsum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@SpringBootApplication
public class CustomerApplication {

    private static final Logger log = LoggerFactory.getLogger(CustomerApplication.class);

    // for test data
    private static final LoremIpsum loremIpsum = new LoremIpsum();
    private static final Faker faker = new Faker();
    private static final Random random = new Random();
    private static final int noOfTestUsers = 95;

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    ApplicationRunner generateSampleCustomers(CustomerRepository customerRepository) {
        return args -> {
            IntStream.range(0, noOfTestUsers).forEach(n -> {
                Name name = faker.name();

                Date creationDate = getRandomDate();
                Date modifiedDate = creationDate;

                List<CustomerNote> customerNotes = getRandomNotes();

                String customerFirstname = name.firstName();
                String customerLastname = name.lastName();
                String customerId = (customerLastname + customerFirstname).toLowerCase();

                Customer c = new Customer()
                        .setId(customerId)
                        .setFirstName(customerFirstname)
                        .setLastName(customerLastname)
                        .setStatus(getRandomStatus())
                        .setCreationDate(creationDate)
                        .setModifiedDate(modifiedDate)
                        .setCustomerNotes(customerNotes);

                customerRepository.save(c);
            });
        };
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }

    private CustomerStatus getRandomStatus() {
        return CustomerStatus.values()[random.nextInt(3)];
    }

    private Date getRandomDate() {
        return new Date(getRandomTimeBetweenTwoDates());
    }

    private long getRandomTimeBetweenTwoDates() {
        long beginTime = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long endTime = Timestamp.valueOf("2018-01-01 00:00:00").getTime();

        long diff = endTime - beginTime + 1;
        return beginTime + (long) (Math.random() * diff);
    }

    private List<CustomerNote> getRandomNotes() {
        List<CustomerNote> customerNotes = new ArrayList<>();

        IntStream.range(0, random.nextInt(3)).forEach(i -> {
            customerNotes.add(new CustomerNote().setText(StringUtils.capitalize(loremIpsum.getWords(random.nextInt(10) + 10, random.nextInt(50))) + "."));
        });

        return customerNotes;
    }
}
