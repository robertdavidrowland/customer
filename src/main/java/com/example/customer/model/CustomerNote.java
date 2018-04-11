package com.example.customer.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CustomerNote {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    private Customer customer;

    private String text;

    public String getText() {
        return text;
    }

    public CustomerNote setText(String text) {
        this.text = text;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerNote)) return false;
        CustomerNote that = (CustomerNote) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(getText(), that.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getText());
    }

    @Override
    public String toString() {
        return "CustomerNote{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
