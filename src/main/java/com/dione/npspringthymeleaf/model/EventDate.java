package com.dione.npspringthymeleaf.model;

import com.dione.npspringthymeleaf.model.conversions.Conversion;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class EventDate extends CalendarType {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String eventLocation;
    private String name;
    @Column(nullable = true)
    private String description;

    @Column(nullable = true)
    private EventType type;

    @Column(name = "short_form", nullable = true)
    private double shortForm;


    @OneToOne
    @JoinColumn(name = "duration_id")
    private CalendarType duration;

    public CalendarType getDuration() {
        return duration;
    }

    public void setDuration(CalendarType duration) {
        this.duration = duration;
    }


    //constructors

    public EventDate() {
    }

    public EventDate(Integer years, Integer months, Integer days){
        this.setYears(years);
        this.setMonths(months);
        this.setDays(days);
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public double getShortForm() {
        return shortForm;
    }

    public void setShortForm(double shortForm) {
        Conversion conversion = new Conversion();
        shortForm = conversion.processToShortDate(
                  new EventDate(this.getYears(), this.getMonths(), this.getDays()),
                  new CalendarType(super.getYears(), super.getMonths(), super.getDays()
                  ));
    }

    @Override
    public String toString() {
//        DecimalFormat days = new DecimalFormat("##");
//        DecimalFormat months = new DecimalFormat("##");
//        DecimalFormat years = new DecimalFormat("####");
        return "Event long date: " +
                Math.round(super.getDays()) + "." +
                Math.round(super.getMonths()) + "." +
                Math.round(super.getYears());
    }
}
