package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.repository.CharacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/character")
public class CharacController {


    @Autowired
    private CharacRepository characRepository;

    @GetMapping(value = "/index")
    public String showCharacterList(Model model) {
        model.addAttribute("characters", characRepository.findAll());
        return "index";
    }

    @GetMapping(value = "/all")
    public String showAll(Model model) {
        model.addAttribute("characters", characRepository.findAll());

        return "character/character-list";
    }

/*    @GetMapping(value = "/create")
    public String showCreateForm(Charac charac, Model model) {
        characRepository.save(charac);
        model.addAttribute("characters", characRepository.findAll());
        return "character/character-creation";
    }*/

    @GetMapping(value = "/create") //works
    public String showCreateForm(String firstName, String lastName, Model model) {
        Charac charac = new Charac();
        charac.setFirstName(firstName);
        charac.setLastName(lastName);
        characRepository.save(charac);

        model.addAttribute("form", characRepository.findAll());

        return "character/character-creation";
    }


    @GetMapping(value = "/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Charac charac = characRepository.findCharacById(id);
        model.addAttribute("character", charac);

        return "character/character-edit";
    }

    @PostMapping("/update/{id}")
    public String updateCharacter(@PathVariable("id") long id, Charac charac, Model model) {
        characRepository.save(charac);
        return "redirect:/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id, Model model) {
        Charac charac = characRepository.findCharacById(id);
        characRepository.delete(charac);
        return "redirect:/index";
    }

    @PostMapping(value = "/save") //persists
    public String saveCharac(@ModelAttribute Charac form, Model model) {
        characRepository.save(form);
        model.addAttribute("characters", characRepository.findAll());

        System.out.println("The character called " + form.getFirstName() + " " + form.getLastName() + " has been added.");
        return "redirect:/character/all";
    }


}


