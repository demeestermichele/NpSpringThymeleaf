package com.dione.npspringthymeleaf.repository;


import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.model.EventDate;
import com.dione.npspringthymeleaf.model.EventType;
import jdk.jfr.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CharacRepository extends JpaRepository<Charac, Long> {
    public Charac findCharacById(Long id);

    public Charac findCharactersByFirstName(String firstName);

    public Charac findCharactersByLastName(String lastName);

    List<Charac> getCharacsByEventType(EventType eventType);
    List<Charac> getAllByBirthMonths(Integer month);


    List<Charac> getCharacsByBirthDays(Integer days);


}
