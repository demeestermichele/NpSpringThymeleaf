package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.model.CalendarType;
import com.dione.npspringthymeleaf.model.Charac;
import com.dione.npspringthymeleaf.model.EventDate;
import com.dione.npspringthymeleaf.repository.CharacRepository;
import com.dione.npspringthymeleaf.repository.EventDateRepository;
import io.netty.util.concurrent.BlockingOperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.thymeleaf.util.StringUtils.capitalize;

@Controller
@RequestMapping("/date")
public class EventDateController {

    @Autowired
    private EventDateRepository repository;

    @Autowired
    private CharacRepository characRepository;


    @GetMapping(value = "/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("dates", repository.findAll());
        return "date/date-dashboard";
    }

    @GetMapping(value = "/timeline")
    public String timeline(Model model) {
        model.addAttribute("dates", repository.findAll());
        return "timeline/timeline";
    }

    /***
     * Gets all events sorted chronologically through date "shortForm"
     * @param model
     * @param year
     * @param page
     * @param size
     * @param sort
     * @return
     */
    @GetMapping(value = "/all")
    public String showAll(Model model, @RequestParam(required = false) Integer year,
                          @RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "6") int size,
                          @RequestParam(defaultValue = "id,asc") String[] sort) {
        List<EventDate> list = repository.findAll(Sort.by("shortForm"));
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
        EventDate eventId = repository.getDateById(id);
        if (eventId.getType() != null) {
            String strings = eventId.getType().toString().toLowerCase() + " of ";
            model.addAttribute("type", capitalize(strings));
        } else if (eventId.getType() == null) {
            String eventTypeNull = "";
            model.addAttribute("type", eventTypeNull);

        }
        model.addAttribute("date", eventId);
        model.addAttribute("characters", characRepository.findAll());
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


    @GetMapping("/year/{year}")
    public String eventYear(@PathVariable("year") Integer year, Model model, @RequestParam(defaultValue = "1") int page,
                            @RequestParam(defaultValue = "6") int size,
                            @RequestParam(defaultValue = "id,asc") String[] sort) {
        List<CalendarType> list = repository.findAllByYearsOrderByMonths(year);
        List<CalendarType> calendarType = repository.findEventDatesByYears(year);
        model.addAttribute("eventYear", calendarType.get(0));
        model.addAttribute("events", list);
        System.out.println(calendarType.get(0));
        return "calendar/calendar-year";
    }

    @GetMapping("/birthday/{month}")
    public String sharedBirthdays(@PathVariable("month") Integer month, Model model, @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "6") int size,
                                  @RequestParam(defaultValue = "days,asc") String[] sort) {

        model.addAttribute("month", month);

        List<Charac> characRepositoryAll = characRepository.findAll(Sort.by("birth.days").ascending());
        List<Charac> characList = characRepository.getAllByBirthMonths(month);

        //TODO get characters with same birthday
//        List<Charac> list = characRepository.getCharacsByBirthDays(i);



        Map<Charac, Integer> map = characList.stream()
                .collect(Collectors.
                        toMap(Function.identity(),
                                value -> 1, Integer::sum));


//        System.out.println(map);
        model.addAttribute("all", characRepositoryAll);
        return "calendar/shared-birthdays";
    }
}
