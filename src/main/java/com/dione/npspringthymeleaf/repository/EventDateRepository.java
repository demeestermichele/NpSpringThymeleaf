package com.dione.npspringthymeleaf.repository;

import com.dione.npspringthymeleaf.model.EventDate;
import org.springframework.data.repository.CrudRepository;

public interface EventDateRepository extends CrudRepository<EventDate, Long> {

    EventDate getDateById(Long id);
}
