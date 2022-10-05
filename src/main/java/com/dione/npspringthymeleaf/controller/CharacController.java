package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.dto.CharacterCreationDto;
import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.repository.CharacRepository;
import com.dione.npspringthymeleaf.service.CharacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/character")
public class CharacController {

    private final CharacService characService = new CharacService() {
        @Override
        public List<Charac> findAll() {
            return (List<Charac>) characRepository.findAll();
        }

        @Override
        public void saveAll(List<Charac> characs) {
            characRepository.saveAll(characs);
        }
    };

    @Autowired
    private CharacRepository characRepository;

    @GetMapping(value = "/all")
    public String showAll(Model model) {
        model.addAttribute("characters", characService.findAll());

        return "character/character-list";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        CharacterCreationDto characterCreationDto = new CharacterCreationDto();
        characterCreationDto.addCharac(new Charac());
        model.addAttribute("characters", characService.findAll());
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
        characService.saveAll(form.getCharacList());

        model.addAttribute("characters", characService.findAll());
        System.out.println("Character " + form.toString() + "was created.");

        return "redirect:/character/all";
    }
}


