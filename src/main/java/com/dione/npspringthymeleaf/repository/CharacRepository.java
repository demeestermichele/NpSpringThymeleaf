package com.dione.npspringthymeleaf.repository;


import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.model.EventDate;
import com.dione.npspringthymeleaf.model.EventType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface CharacRepository extends JpaRepository<Charac, Long> {
    public Charac findCharacById(Long id);

    public Charac findCharactersByFirstName(String firstName);

    public Charac findCharactersByLastName(String lastName);

    Charac getByEvent_Type(EventType eventType);



}
