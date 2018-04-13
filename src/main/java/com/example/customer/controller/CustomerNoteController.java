package com.example.customer.controller;

import com.example.customer.model.Customer;
import com.example.customer.model.CustomerNote;
import com.example.customer.service.CustomerNoteService;
import com.example.customer.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer-note")
public class CustomerNoteController {

    private static final Logger log = LoggerFactory.getLogger(CustomerNoteController.class);

    @Autowired
    CustomerNoteService customerNoteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerNote> getCustomerNote(@PathVariable( "id" ) Long id) {
        Optional<CustomerNote> customerNote = customerNoteService.getCustomerNoteById(id);
        if (!customerNote.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerNote.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<CustomerNote> create(@RequestBody CustomerNote customerNote) {
        customerNoteService.create(customerNote);
        return new ResponseEntity<>(customerNote, HttpStatus.CREATED);
    }
  
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable( "id" ) Long id, @RequestBody CustomerNote customerNote) {
        customerNoteService.update(id, customerNote);
        return new ResponseEntity<>(HttpStatus.OK);
    }
  
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        customerNoteService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
