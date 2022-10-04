package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.dto.CharacterCreationDto;
import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.repository.CharacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/character")
public class CharacController {


    @Autowired
    private CharacRepository characRepository;

    @GetMapping(value = "/all")
    public String showAll(Model model) {
        model.addAttribute("characters", characRepository.findAll());

        return "character/character-list";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(String firstName, String lastName, Model model) {
        Charac charac = new Charac();
        charac.setFirstName(firstName);
        charac.setLastName(lastName);
        characRepository.save(charac);

        model.addAttribute("form", characRepository.findAll());

        return "character/character-creation";
    }


    @GetMapping(value = "/edit")
    public String showEditForm(Model model) {
        List<Charac> characters = new ArrayList<>();
        characRepository.findAll()
                .iterator()
                .forEachRemaining(characters::add);

        model.addAttribute("form", characRepository.saveAll(characters));

        return "character/character-edit";
    }

    @PostMapping(value = "/save")
    public String saveCharac(@ModelAttribute Charac form, Model model) {
        characRepository.save(form);

        model.addAttribute("characters", characRepository.findAll());

        return "redirect:/character/all";
    }
}


