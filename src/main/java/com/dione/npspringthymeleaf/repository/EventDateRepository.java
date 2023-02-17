package com.dione.npspringthymeleaf.repository;

import com.dione.npspringthymeleaf.model.EventDate;
import com.dione.npspringthymeleaf.model.EventType;
import jdk.jfr.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EventDateRepository extends JpaRepository<EventDate, Long> {

    EventDate getDateById(Long id);
    EventDate getEventDatesByType(EventType type);
}
