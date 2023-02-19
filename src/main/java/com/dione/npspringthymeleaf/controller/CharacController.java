package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.model.EventDate;
import com.dione.npspringthymeleaf.model.EventType;
import com.dione.npspringthymeleaf.repository.CharacRepository;
import com.dione.npspringthymeleaf.repository.EventDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/character")
public class CharacController {


    @Autowired
    private CharacRepository characRepository;
    @Autowired
    private EventDateRepository dateRepository;

    @GetMapping(value = "/all")
    public String showAll(Model model) {
        List<Charac> characList = characRepository.findAll(Sort.by("id"));

        dateRepository.getEventDateByTypeBirth(characRepository.findBy("eventDate"));
        model.addAttribute("characters", characList);
        model.addAttribute("birth", birthDate);
        return "character/character-list";
    }

    @GetMapping(value = "/error")
    public String errorPage(Model model) {
        Iterable<Charac> list = characRepository.findAll();

        model.addAttribute("characters", list);

        return "redirect:/index";
    }


    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        Charac charac = new Charac();
        System.out.println("CREATE CHARACTER");
        model.addAttribute("characters", charac);
        return "character/character-creation";
    }


    @PostMapping("/update/{id}") //this works
    public String updateCharacter(@PathVariable("id") long id, @Valid Charac character, BindingResult result, Model model) {
        if (result.hasErrors()) {
            character.setId(id);
            System.out.println("something went wrong with update");
            return "error";
        }

        characRepository.save(character);
        model.addAttribute("character", characRepository.findCharacById(id));
        return "character/character-profile";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("character", characRepository.findCharacById(id));
        return "character/character-edit";
    }

    @GetMapping("/{id}")
    public String characterProfile(@PathVariable("id") long id, Model model) {
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
        model.addAttribute("character", characRepository.findCharacById(form.getId()));

        System.out.println(characRepository.count() + " characters in table.");
        System.out.println("The character called " + form.getFirstName() + " " + form.getLastName() + " has been added.");
        return "redirect:/character/all";
    }


}


