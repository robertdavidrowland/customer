package com.example.customer.controller;

import com.example.customer.model.CustomerNote;
import com.example.customer.service.CustomerNoteService;
import com.example.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer-note")
public class CustomerNoteController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerNoteService customerNoteService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CustomerNote> getCustomerNote(@PathVariable("id") Long id) {
        Optional<CustomerNote> customerNote = customerNoteService.getCustomerNoteById(id);
        if (!customerNote.isPresent()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerNote.get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{customerId}", method = RequestMethod.POST)
    public ResponseEntity<CustomerNote> create(@PathVariable("customerId") String customerId, @RequestBody CustomerNote customerNote) {
        customerNoteService.create(customerNote, customerId);
        return new ResponseEntity<>(customerNote, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody CustomerNote customerNote) {
        customerNoteService.update(id, customerNote);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/{customerId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id, @PathVariable("customerId") String customerId) {
        customerNoteService.deleteById(id, customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
