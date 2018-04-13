package com.example.customer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CustomerNote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    private Customer customer;

    private String text;

    public Long getId() {
        return id;
    }

    public CustomerNote setId(Long id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public CustomerNote setText(String text) {
        this.text = text;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CustomerNote setCustomer(Customer customer) {
        this.customer = customer;
        return this;
    }

    @Override
    public String toString() {
        return "CustomerNote{" +
                "id=" + id +
                ", customer=" + customer.getId() +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerNote)) return false;
        CustomerNote that = (CustomerNote) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }
}
