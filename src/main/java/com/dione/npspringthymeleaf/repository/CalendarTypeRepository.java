package com.dione.npspringthymeleaf.repository;

import com.dione.npspringthymeleaf.model.CalendarType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CalendarTypeRepository extends JpaRepository<CalendarType, Long> {

    //findById in CrudRepo gives Optional
    CalendarType findCalendarTypeById(Long id);
    List<CalendarType> findAllByYears(Integer years);
    Page<CalendarType> findCalendarTypesByYears(Integer years, Pageable pageable);
    Iterable<CalendarType> findCalendarTypesByYears(Integer years);
    List<CalendarType> findAllByYearsOrderByMonths(Integer years);
}
