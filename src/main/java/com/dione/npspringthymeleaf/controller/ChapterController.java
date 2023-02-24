package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.model.Chapter;
import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.model.EventDate;
import com.dione.npspringthymeleaf.model.EventType;
import com.dione.npspringthymeleaf.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/chapter")
public class ChapterController {

    @Autowired
    private ChapterRepository repository;


    @GetMapping(value = "dashboard")
    public String dashboard(Model model) {
        model.addAttribute("chapters", repository.findAll());
        return "chapter/chapter-dashboard";
    }

    @GetMapping(value = "/all")
    public String showAll(Model model){
        List<Chapter> chapterList = repository.findAll(Sort.by("book"));
        model.addAttribute("chapters", chapterList);
        return "chapter/chapter-list";
    }

    @GetMapping(value = "/error")
    public String errorPage(Model model){
        Iterable<Chapter> list = repository.findAll();
        return "redirect:/index";
    }


    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        Chapter chapter = new Chapter();
        model.addAttribute("chapters", chapter);
        return "chapter/chapter-creation";
    }


    @PostMapping("/update/{id}")
    public String updateChapter(@PathVariable("id") long id, @Valid Chapter chapter, BindingResult result, Model model) {
        if (result.hasErrors()) {
            chapter.setId(id);
            System.out.println("something went wrong with update");
            return "error";
        }
        repository.save(chapter);
        model.addAttribute("chapter", repository.findChaptersById(id));
        return "chapter/chapter-profile";
    }


    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("chapter", repository.findChaptersById(id));
        return "chapter/chapter-edit";
    }

    @GetMapping("/{id}")
    public String chapterProfile(@PathVariable("id") long id, Model model) {
        Chapter chapter = repository.findChaptersById(id);
        model.addAttribute("chapter", chapter);
        model.addAttribute("all", repository.findAll());
        return "chapter/chapter-profile";
    }

    @GetMapping("/delete/{id}")
    public String deleteChapter(@PathVariable("id") long id, Model model) {
        Chapter chapter = repository.findChaptersById(id);
        repository.delete(chapter);
        return "redirect:/chapter/all";
    }

    @PostMapping(value = "/save") //persists
    public String saveChapter(@ModelAttribute Chapter form, Model model) {
        repository.save(form);
        model.addAttribute("chapter", repository.findChaptersById(form.getId()));
        return "redirect:/chapter/all";
    }

}
