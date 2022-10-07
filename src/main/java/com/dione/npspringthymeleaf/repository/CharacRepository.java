package com.dione.npspringthymeleaf.repository;


import com.dione.npspringthymeleaf.model.Charac;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

public interface CharacRepository extends CrudRepository<Charac, Long> {
    public Charac findCharacById(Long id);
    public Charac findCharactersByFirstName(String firstName);
    public Charac findCharactersByLastName(String lastName);

}
