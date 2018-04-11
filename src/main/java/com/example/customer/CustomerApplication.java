package com.example.customer;

import com.example.customer.model.Customer;
import com.example.customer.model.CustomerNote;
import com.example.customer.model.CustomerStatus;
import com.example.customer.repository.CustomerRepository;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import de.svenjacobs.loremipsum.LoremIpsum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.IntStream;

@SpringBootApplication
public class CustomerApplication {

    private static final Logger log = LoggerFactory.getLogger(CustomerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    ApplicationRunner generateSampleCustomers(CustomerRepository customerRepository) {
        return args -> {
            Faker faker = new Faker();

            IntStream.range(0, 10).forEach(n -> {
                Name name = faker.name();

                Date creationDate = getRandomDate();
                Date modifiedDate = creationDate;

                List<CustomerNote> notes = getRandomNotes();

                Customer c = new Customer()
                        .setId(name.username())
                        .setFirstName(name.firstName())
                        .setLastName(name.lastName())
                        .setStatus(getRandomStatus())
                        .setCreationDate(creationDate)
                        .setModifiedDate(modifiedDate)
                        .setNotes(notes);

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
        Random random = new Random();
        return CustomerStatus.values()[random.nextInt(3)];
    }

    private Date getRandomDate() {
        return new Date(getRandomTimeBetweenTwoDates());
    }

    private long getRandomTimeBetweenTwoDates () {
        long beginTime = Timestamp.valueOf("2017-01-01 00:00:00").getTime();
        long endTime = Timestamp.valueOf("2018-01-01 00:00:00").getTime();

        long diff = endTime - beginTime + 1;
        return beginTime + (long) (Math.random() * diff);
    }

    private List<CustomerNote> getRandomNotes() {
        LoremIpsum loremIpsum = new LoremIpsum();

        List<CustomerNote> notes = new ArrayList<>();

        IntStream.range(0, 2).forEach(i ->{
            notes.add(new CustomerNote().setText(loremIpsum.getWords(10)));
        });

        return notes;
    }
}
