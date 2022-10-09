package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.model.CalendarType;
import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.repository.CalendarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;

@Controller
@RequestMapping("/calendar")
public class CalenderTypeController {

    @Autowired
    private CalendarTypeRepository repository;

    @GetMapping(value = "dashboard")
    public String dashboard(Model model){
        model.addAttribute("calendars", repository.findAll());
        return "calendar/calendar-dashboard";
    }

    @GetMapping(value = "all")
    public String showAll(Model model){
        Iterable<CalendarType> list = repository.findAll();
        Comparator<CalendarType> compareId = (CalendarType c1, CalendarType c2) -> c1.getId().compareTo(c2.getId());
        ((ArrayList<CalendarType>) list).sort(compareId);
        model.addAttribute("calendars", list);
        return "calendar/calendar-list";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(CalendarType calendar, Model model){
        System.out.println("trigger create");
        repository.save(calendar);
        model.addAttribute("calendars", repository.findAll());
        return "calendar/calendar-creation";
    }

    @PostMapping("/update/{id}")
public String updateCalendar(@PathVariable("id") Long id, @Valid CalendarType calendar, Model model, BindingResult result){
        if (result.hasErrors()){
            calendar.setId(id);
            return "error";
        }
        repository.save(calendar);
        model.addAttribute("calendar", repository.findCalendarTypeById(id));
        return "/calendar/calendar-profile";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("calendar", repository.findCalendarTypeById(id));
        return "calendar/calendar-edit";
    }

    @GetMapping("/{id}")
    public String calendarProfile(@PathVariable("id") Long id, Model model){
        model.addAttribute("calendar", repository.findCalendarTypeById(id));
        return "calendar/calendar-profile";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model){
        CalendarType calendarType = repository.findCalendarTypeById(id);
        repository.delete(calendarType);
        System.out.println("Calendar " + calendarType.getName() + " has been deleted.");
        return "redirect:/calendar/all";
    }

    @PostMapping(value="/save")
    public String saveCalendar(@ModelAttribute CalendarType form, Model model){
        repository.save(form);
        model.addAttribute("calendars", repository.findAll());
        System.out.println("Calendar " + form.getName() + " has been saved.");
        return "redirect:/calendar/all";
    }
}
