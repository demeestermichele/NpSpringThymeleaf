package com.dione.npspringthymeleaf.repository;

import com.dione.npspringthymeleaf.model.CalendarType;
import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.model.EventDate;
import com.dione.npspringthymeleaf.model.EventType;
import jdk.jfr.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventDateRepository extends JpaRepository<EventDate, Long> {

    EventDate getDateById(Long id);
    List<EventDate> getEventDatesByType(EventType type);
    List<CalendarType> findAllByYearsOrderByMonths(Integer years);
    List<CalendarType> findEventDatesByYears(Integer years);
    EventDate findEventDateByYears(Integer years);
}
