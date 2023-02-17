package com.dione.npspringthymeleaf.controller;

import com.dione.npspringthymeleaf.model.CalendarType;
import com.dione.npspringthymeleaf.repository.CalendarTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/calendar")
public class CalendarTypeController {

    @Autowired
    private CalendarTypeRepository repository;

    @GetMapping(value = "dashboard")
    public String dashboard(Model model) {
        model.addAttribute("calendars", repository.findAll());
        return "calendar/calendar-dashboard";
    }

    @GetMapping(value = "all")
    public String showAll(Model model, @RequestParam(required = false) Integer year,
                          @RequestParam(defaultValue = "1") int page,
                          @RequestParam(defaultValue = "6") int size,
                          @RequestParam(defaultValue = "id,asc") String[] sort) {
        List<CalendarType> list = repository.findAll(Sort.by("years"));
        model.addAttribute("calendars", list);

/*        try {
            List<CalendarType> types = new ArrayList<CalendarType>();

            String sortField = sort[0];
            String sortDirection = sort[1];

            Sort.Direction direction = sortDirection.equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort.Order order = new Sort.Order(direction, sortField);

            Pageable pageable = PageRequest.of(page - 1, size, Sort.by(order));

            Page<CalendarType> calendarTypePage;

            if (year == null) {
                calendarTypePage = repository.findAll(pageable);

            } else {
                calendarTypePage = repository.findCalendarTypesByYears(year, pageable);
                model.addAttribute("years", year);

            }
types = calendarTypePage.getContent();


            Comparator<CalendarType> compareId = (CalendarType c1, CalendarType c2) -> c1.getId().compareTo(c2.getId());
            ((ArrayList<CalendarType>) list).sort(compareId);
            model.addAttribute("calendars", list);
            System.out.println();
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }*/
        return "calendar/calendar-list";
    }

    @GetMapping(value = "/create")
    public String showCreateForm(Model model) {
        CalendarType calendar = new CalendarType();
        System.out.println("CREATE CALENDAR");
        model.addAttribute("calendars", calendar);
        return "calendar/calendar-creation";
    }

    @PostMapping("/update/{id}")
    public String updateCalendar(@PathVariable("id") Long id, @Valid CalendarType calendar, Model model, BindingResult result) {
        if (result.hasErrors()) {
            calendar.setId(id);
            System.err.println("update error");
            return "error";
        }
        repository.save(calendar);
        model.addAttribute("calendar", repository.findCalendarTypeById(id));
        return "calendar/calendar-profile";
    }

    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("calendar", repository.findCalendarTypeById(id));
        return "calendar/calendar-edit";
    }

    @GetMapping("/{id}")
    public String calendarProfile(@PathVariable("id") Long id, Model model) {
        model.addAttribute("calendar", repository.findCalendarTypeById(id));
        return "calendar/calendar-profile";
    }

    @GetMapping("/year/{year}")
    public String calendarProfile(@PathVariable("year") Integer year, Model model, @RequestParam(defaultValue = "1") int page,
                                  @RequestParam(defaultValue = "6") int size,
                                  @RequestParam(defaultValue = "id,asc") String[] sort) {
        List<CalendarType> list = repository.findAllByYearsOrderByMonths(year);
        model.addAttribute("events", list);
        return "calendar/calendar-year";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        CalendarType calendarType = repository.findCalendarTypeById(id);
        repository.delete(calendarType);
        System.out.println("Calendar " + calendarType.getName() + " has been deleted.");
        return "redirect:/calendar/all";
    }

    @PostMapping(value = "/save")
    public String saveCalendar(@ModelAttribute CalendarType form, Model model) {
        repository.save(form);
        model.addAttribute("calendars", repository.findAll());
        System.out.println("Calendar " + form.getName() + " has been saved.");
        return "redirect:/calendar/all";

    }
}
