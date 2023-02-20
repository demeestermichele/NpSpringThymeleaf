package com.dione.npspringthymeleaf.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Character")
public class Charac implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String firstName;
    private String lastName;

    private Role role;

    private Sex sex;

    @ManyToOne
    @JoinColumn(name = "EVENT_ID")
    private EventDate event;

    @ManyToOne
    @JoinColumn(name = "birth")
    private EventDate birth;

    private String nickname;

    @ManyToOne
    @JoinColumn(name = "mother", nullable = true)
    private Charac mother;

    @ManyToOne
    @JoinColumn(name = "father", nullable = true)
    private Charac father;


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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public EventDate getEvent() {
        return event;
    }

    public void setEvent(EventDate event) {
        this.event = event;
    }

    public EventDate getBirth() {
        return birth;
    }

    public void setBirth(EventDate birth) {
        this.birth = birth;
    }

    public Charac getFather() {
        return father;
    }

    public void setFather(Charac father) {
        this.father = father;
    }

    public Charac getMother() {
        return mother;
    }

    public void setMother(Charac mother) {
        this.mother = mother;
    }

    /**
     * ToString
     **/

    @Override
    public String toString() {
        return "Charac{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", sex=" + sex +
                '}';
    }
}
