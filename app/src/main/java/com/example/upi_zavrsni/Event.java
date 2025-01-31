package com.example.upi_zavrsni;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {
    // Lista svih događaja
    public static ArrayList<Event> eventsList = new ArrayList<>();

    // Metoda koja vraća listu događaja za određeni datum
    public static ArrayList<Event> eventsForDate(LocalDate date) {
        ArrayList<Event> events = new ArrayList<>();

        for (Event event : eventsList) {
            if (event.getDate().equals(date)) {
                events.add(event);
            }
        }

        return events;
    }

    // Metoda koja vraća listu događaja za određeni datum i vrijeme
    public static ArrayList<Event> eventsForDateAndTime(LocalDate date, LocalTime time) {
        ArrayList<Event> events = new ArrayList<>();

        for (Event event : eventsList) {
            int eventHour = event.getTime().getHour();
            int cellHour = time.getHour();
            if (event.getDate().equals(date) && eventHour == cellHour) {
                events.add(event);
            }
        }

        return events;
    }

    private String name;
    private LocalDate date;
    private LocalTime time;

    public Event(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
