package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.dto.CharacterCreationDto;
import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.repository.CharacRepository;
import com.dione.npspringthymeleaf.service.CharacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/character")
public class CharacController {


    private CharacService characService;

    @Autowired
    private CharacRepository characRepository;

    @GetMapping(value = "/all")
    public String showAll(Model model) {
        model.addAttribute("characters", characRepository.findAll());

        return "character/character-list";
    }

    @GetMapping(value = "/create")
    public String showCreateForm( String firstName, String lastName, Model model) {
        Charac charac = new Charac();
        if(charac.getId() == null) {
            charac.setFirstName(firstName);
            charac.setLastName(lastName);
            characRepository.save(charac);
            model.addAttribute("characters", characRepository.findAll());
        }
        return "character/character-creation";
    }


    @GetMapping(value = "/edit")
    public String showEditForm(Model model) {
        List<Charac> characters = new ArrayList<>();
        characService.findAll()
                .iterator()
                .forEachRemaining(characters::add);

        model.addAttribute("form", new CharacterCreationDto(characters));

        return "character/character-edit";
    }

    @PostMapping(value = "/save")
    public String saveCharac(@ModelAttribute CharacterCreationDto form, Model model) {
        characRepository.saveAll(form.getCharacList());

        model.addAttribute("characters", characRepository.findAll());
        System.out.println("Character " + form.getFirstName() + " " + form.getLastName() + " was created.");

        return "redirect:/character/character-list";
    }
}


