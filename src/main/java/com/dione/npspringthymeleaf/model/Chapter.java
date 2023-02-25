package com.dione.npspringthymeleaf.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Chapter")
public class Chapter implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Book book;

    private String name;

    private Integer number;

    private float version;

    private String description;

    public Chapter(Book book, String name, String description) {
        this.book = book;
        this.name = name;
        this.description = description;
    }

    public Chapter() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public float getVersion() {
        return version;
    }

    public void setVersion(float version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "book=" + book +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", version=" + version +
                ", description='" + description + '\'' +
                '}';
    }
}
