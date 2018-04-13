package com.example.customer.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
public class Customer {

    @Id
    @NotNull
    private String id;

    private String firstName;

    private String lastName;

    private CustomerStatus status = CustomerStatus.PROSPECTIVE;

    private Date creationDate = new Date();

    private Date modifiedDate = new Date();

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JsonManagedReference
    private List<CustomerNote> customerNotes = new ArrayList<>();

    public String getId() {
        return id;
    }

    public Customer setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Customer setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public Customer setStatus(CustomerStatus status) {
        this.status = status;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Customer setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public Customer setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
        return this;
    }

    public List<CustomerNote> getCustomerNotes() {
        return customerNotes;
    }

    public Customer setCustomerNotes(List<CustomerNote> customerNotes) {
        this.customerNotes = customerNotes;
        customerNotes.stream().forEach(cn -> cn.setCustomer(this));
        return this;
    }

    public Customer addCustomerNote(CustomerNote customerNote) {
        this.customerNotes.add(customerNote);
        customerNote.setCustomer(this);
        return this;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", modifiedDate=" + modifiedDate +
                ", customerNotes=" + customerNotes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getId(), customer.getId()) &&
                Objects.equals(getFirstName(), customer.getFirstName()) &&
                Objects.equals(getLastName(), customer.getLastName()) &&
                getStatus() == customer.getStatus() &&
                Objects.equals(getCreationDate(), customer.getCreationDate()) &&
                Objects.equals(getModifiedDate(), customer.getModifiedDate());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getFirstName(), getLastName(), getStatus(), getCreationDate(), getModifiedDate());
    }
}
