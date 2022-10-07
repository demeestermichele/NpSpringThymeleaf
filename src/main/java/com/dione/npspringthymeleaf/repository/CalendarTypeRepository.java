package com.dione.npspringthymeleaf.repository;

import com.dione.npspringthymeleaf.model.CalendarType;
import org.springframework.data.repository.CrudRepository;

public interface CalendarTypeRepository extends CrudRepository<CalendarType, Long> {

    //findById in CrudRepo gives Optional
    CalendarType findCalendarTypeById(Long id);
}
