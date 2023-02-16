package com.dione.npspringthymeleaf.model.conversions;

import com.dione.npspringthymeleaf.model.CalendarType;
import com.dione.npspringthymeleaf.model.EventDate;

public class Conversion extends EventDate {

    private double processToShortDate;
    private EventDate processToLongDate;


    public double processToShortDate(EventDate eventDate, CalendarType calendarType) {
        double processedDate = 0.0;
        double years = Double.valueOf(eventDate.getYears());
        double months = Double.valueOf(eventDate.getMonths());
        double days = Double.valueOf(eventDate.getDays());

        if (years != 0) {
            processedDate = years + processedDate;

        } else {
            years = 0.0;
            processedDate = processedDate + years;
        }

        if (months != 0) {
            months = ((double)(calendarType.getMonths()) /  //10
                    ((double)calendarType.getDays() * 2))    //1000 = 500*2
                    *
                    ((months - 1) * calendarType.getMonths()); // x-1 * 10
            processedDate = processedDate + months;

        } else {
            months = 0.0;
            processedDate = processedDate + months;
        }


        if (days != 0) {
            double daysCalc = (double)calendarType.getYears() /       // 0.002
                    ((double)calendarType.getDays());

            processedDate = (daysCalc * (days - 1)) + processedDate;
        } else {
            days = 0.0;
            processedDate = processedDate + days;
        }

        return processedDate;
    }


    public EventDate processToLongDate(double dateToConvert, CalendarType calendarType) {
        // Let's say 2081.392 which should be 47.04.2081
        //        dateToConvert = 2081.392;
        EventDate eventDate = new EventDate();

        double year = Math.floor(dateToConvert); //2081.0
        eventDate.setYears((int) year);

        double minYear = (dateToConvert - year);
        double a = (dateToConvert - year) * calendarType.getMonths();
        double month = Math.floor(a) + 1;
        eventDate.setMonths((int) month);

        double c = Math.floor(a) / calendarType.getMonths();
        double d = (minYear - c) * calendarType.getDays();
        double day = Math.ceil(d);
        eventDate.setDays((int) day);

        return eventDate;
    }


}
