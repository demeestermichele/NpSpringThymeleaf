package com.dione.npspringthymeleaf.model;

import javax.persistence.*;
import java.io.Serializable;
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

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<EventDate> events = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "birth")
    private EventDate birth;

    private String alias;

    @ManyToOne
    @JoinColumn(name = "mother", nullable = true)
    private Charac mother;

    @ManyToOne
    @JoinColumn(name = "father", nullable = true)
    private Charac father;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Chapter> chapters= new LinkedHashSet<>();

    //TODO brothers and sisters (better in frontend)
    //TODO status or titles
    //TODO which books are these characters present in
    //TODO which world/race do they belong to
    //TODO what languages do they speak
    //TODO what are their physical characteristics/ skin, eye and hair color
    //TODO what is their personality like
    //TODO


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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Set<EventDate> getEvents() {
        return events;
    }

    public void setEvents(Set<EventDate> events) {
        this.events = events;
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

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
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
                ", months=" + birth.getMonths() +
                ", days=" + birth.getDays() +
                '}';
    }
}
