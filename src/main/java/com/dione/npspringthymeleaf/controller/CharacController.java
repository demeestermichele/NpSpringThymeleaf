package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.repository.CharacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;

@Controller
@RequestMapping("/character")
public class CharacController {


    @Autowired
    private CharacRepository characRepository;

    @GetMapping(value = "/all") //sorted descending
    public String showAll(Model model) {
        Iterable<Charac> list = characRepository.findAll();
        Comparator<Charac> compareId = (Charac c1, Charac c2) -> c1.getId().compareTo(c2.getId());
        ((ArrayList<Charac>) list).sort(compareId);
        model.addAttribute("characters", list);

        return "character/character-list";
    }

    @GetMapping(value = "/error")
    public String errorPage(Model model) {
        Iterable<Charac> list = characRepository.findAll();

        model.addAttribute("characters", list);

        return "redirect:/index";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(Charac charac, Model model) {
        characRepository.save(charac);
        model.addAttribute("characters", characRepository.findAll());
        return "character/character-creation";
    }

    @PostMapping("/update/{id}") //this works
    public String updateCharacter(@PathVariable("id") Long id, @Valid Charac character, BindingResult result, Model model) {
        if (result.hasErrors()) {
            character.setId(id);
            return "error";
        }

        characRepository.save(character);
        model.addAttribute("character", characRepository.findAll());
        return "/character/character-profile";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("character", characRepository.findCharacById(id));
        return "character/character-edit";
    }

    @GetMapping("/{id}")
    public String characterProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("character", characRepository.findCharacById(id));
        return "character/character-profile";
    }


    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Charac charac = characRepository.findCharacById(id);
        characRepository.delete(charac);
        return "redirect:/character/all";
    }

    @PostMapping(value = "/save") //persists
    public String saveCharac(@ModelAttribute Charac form, Model model) {
        characRepository.save(form);
        model.addAttribute("characters", characRepository.findAll());

        System.out.println("The character called " + form.getFirstName() + " " + form.getLastName() + " has been added.");
        return "redirect:/character/all";
    }


}


