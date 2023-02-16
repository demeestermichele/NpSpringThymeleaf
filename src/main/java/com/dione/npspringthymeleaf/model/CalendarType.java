package com.dione.npspringthymeleaf.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class CalendarType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;
    private Integer years;
    private Integer months;

    @Column(nullable = true)
    private Integer weeks;
    private Integer days;

    @Column(nullable = true)
    private Integer hours;

    @Column(nullable = true)
    private Integer minutes;

    @Column(nullable = true)
    private Integer seconds;

    public CalendarType() {
    }

    public CalendarType(Integer years, Integer months, Integer days) {
        this.years = years;
        this.months = months;
        this.days = days;
    }

    public CalendarType(Integer years, Integer months, Integer days, Integer hours) {
        this.years = years;
        this.months = months;
        this.days = days;
        this.hours = hours;
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

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getWeeks() {
        return weeks;
    }

    public void setWeeks(Integer weeks) {
        this.weeks = weeks;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public Integer getHours() {
        return hours;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "CalendarType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", years=" + years +
                ", months=" + months +
                ", weeks=" + weeks +
                ", days=" + days +
                ", hours=" + hours +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                '}';
    }
}
