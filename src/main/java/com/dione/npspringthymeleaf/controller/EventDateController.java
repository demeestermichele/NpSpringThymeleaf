package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.model.EventDate;
import com.dione.npspringthymeleaf.repository.CalendarTypeRepository;
import com.dione.npspringthymeleaf.repository.EventDateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;

@Controller
@RequestMapping("/date")
public class EventDateController {

    @Autowired
    private EventDateRepository repository;

    @Autowired
    private CalendarTypeRepository calendarTypeRepository;

    @GetMapping(value = "/dashboard")
    public String dashboard(Model model){
        model.addAttribute("dates", repository.findAll());
        return "date/date-dashboard";
    }

    @GetMapping(value = "/all")
    public String showAll(Model model){
        Iterable<EventDate> list = repository.findAll();
//        Comparator<EventDate> compareId = (EventDate c1, EventDate c2) -> c1.getId().compareTo(c2.getId());
//        ((ArrayList<EventDate>) list).sort(compareId);
        model.addAttribute("dates", list);
        return "date/date-list";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        EventDate eventDate = new EventDate();
        System.out.println("CREATE EVENT");
        model.addAttribute("date", eventDate);
        return "date/date-creation";
    }

    @PostMapping("/update/{id}")
    public String updateEvent(@PathVariable("id") Long id, @Valid EventDate eventDate, Model model, BindingResult result) {
        if (result.hasErrors()) {
            eventDate.setId(id);
            System.err.println("update error");
            return "error";
        }
        repository.save(eventDate);
        model.addAttribute("date", repository.getDateById(id));
        return "date/date-profile";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("date", repository.getDateById(id));
        return "date/date-edit";
    }

    @GetMapping("/{id}")
    public String eventProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("date", repository.getDateById(id));
        return "date/date-profile";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        EventDate date = repository.getDateById(id);
        repository.delete(date);
        System.out.println("date " + date.getName() + " has been deleted.");
        return "redirect:/date/all";
    }

    @PostMapping(value = "/save")
    public String saveEvent(@ModelAttribute EventDate form, Model model) {
        repository.save(form);
        model.addAttribute("date", repository.findAll());
        System.out.println("date " + form.getName() + " has been saved.");
        return "redirect:/date/all";

    }

}
