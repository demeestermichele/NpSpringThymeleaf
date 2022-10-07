package com.dione.npspringthymeleaf.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Character")
public class Charac implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;


    /**
     * Constructors
     **/
    public Charac() {
    }

    public Charac(Long id) {
        this.id = id;
    }

    public Charac(String firstName) {
        this.firstName = firstName;
    }

    public Charac(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public Charac(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * Getters and setters
     **/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * ToString
     **/
    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
