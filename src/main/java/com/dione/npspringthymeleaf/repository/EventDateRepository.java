package com.dione.npspringthymeleaf.repository;

import com.dione.npspringthymeleaf.model.CalendarType;
import com.dione.npspringthymeleaf.model.EventDate;
import com.dione.npspringthymeleaf.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventDateRepository extends JpaRepository<EventDate, Long> {

    EventDate getDateById(Long id);

    List<EventDate> getEventDatesByType(EventType type);

    List<CalendarType> findAllByYearsOrderByMonths(Integer years);

    List<CalendarType> findEventDatesByMonthsOrderByDays(Integer month);
    List<CalendarType> findAllByMonthsOrderByDays(Integer month);
    List<CalendarType> findEventDatesByYears(Integer years);

    EventDate getEventDateByType(EventType type);
}
