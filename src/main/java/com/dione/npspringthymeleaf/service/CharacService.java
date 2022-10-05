package com.dione.npspringthymeleaf.service;

import com.dione.npspringthymeleaf.model.Charac;

import java.util.List;

public interface CharacService {
    List<Charac> findAll();

    void saveAll(List<Charac> characs);

}
