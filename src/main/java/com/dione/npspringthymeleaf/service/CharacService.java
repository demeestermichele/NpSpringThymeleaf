package com.dione.npspringthymeleaf.service;

import com.dione.npspringthymeleaf.model.Charac;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacService {
    List<Charac> findAll();

    void saveAll(List<Charac> characs);

}
