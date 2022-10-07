package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.model.CalendarType;
import com.dione.npspringthymeleaf.repository.CalendarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/calendar")
public class CalenderTypeController {

    @Autowired
    private CalendarTypeRepository repository;

    @GetMapping(value = "all")
    public String showAll(Model model){
        model.addAttribute("calendar", repository.findAll());
        return "calendar/calendar-list";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(CalendarType calendar, Model model){
        repository.save(calendar);
        model.addAttribute("calendar", repository.findAll());
        return "calendar/calendar-creation";
    }

    @PostMapping("/update/{id}")
public String updateCalendar(@PathVariable("id") Long id, @Valid CalendarType calendar, Model model, BindingResult result){
        if (result.hasErrors()){
            calendar.setId(id);
            return "error";
        }
        repository.save(calendar);
        model.addAttribute("calendar", repository.findAll());
        return "/calendar/calendar-profile";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("calendar", repository.findById(id));
        return "calendar/calendar-edit";
    }

    @GetMapping("/{id}")
    public String calendarProfile(@PathVariable("id") Long id, Model model){
        model.addAttribute("calendar", repository.findById(id));
        return "calendar/calendar-profile";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model){
        CalendarType calendarType = repository.findCalendarTypeById(id);
        repository.delete(calendarType);
        return "redirect:/calendar/all";
    }

    @PostMapping(value="/save")
    public String saveCalendar(@ModelAttribute CalendarType form, Model model){
        repository.save(form);
        model.addAttribute("form", repository.findAll());
        System.out.println("Calendar " + form.getName() + " has been deleted.");
        return "redirect:/calendar/all";
    }
}
