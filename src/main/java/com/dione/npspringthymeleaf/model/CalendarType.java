package com.dione.npspringthymeleaf.model;

import javax.persistence.*;

@Entity
public class CalendarType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;
    private Double years;
    private Double months;
    private Double weeks;
    private Double days;
    private Double hours;
    private Double minutes;
    private Double seconds;

    public CalendarType() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getYears() {
        return years;
    }

    public void setYears(Double years) {
        this.years = years;
    }

    public Double getMonths() {
        return months;
    }

    public void setMonths(Double months) {
        this.months = months;
    }

    public Double getWeeks() {
        return weeks;
    }

    public void setWeeks(Double weeks) {
        this.weeks = weeks;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public Double getHours() {
        return hours;
    }

    public void setHours(Double hours) {
        this.hours = hours;
    }

    public Double getMinutes() {
        return minutes;
    }

    public void setMinutes(Double minutes) {
        this.minutes = minutes;
    }

    public Double getSeconds() {
        return seconds;
    }

    public void setSeconds(Double seconds) {
        this.seconds = seconds;
    }
}
