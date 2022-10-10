package com.dione.npspringthymeleaf.model;

import javax.persistence.*;

@Entity
@Table(name = "events")
public class EventDate extends CalendarType {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "event_id", nullable = false)
    private Long id;

    private String name;

    private String description;

    private EventType type;

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

    @Override
    public Long getId() {
        return id;
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
}
