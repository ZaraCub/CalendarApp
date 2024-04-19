package com.example.upi_zavrsni;

import org.junit.Test;

import static org.junit.Assert.*;


import static org.junit.Assert.assertEquals;


import java.time.LocalDate;
import java.time.LocalTime;

public class CalendarUtilsTest {

    @Test
    public void testFormattedDate() {
        LocalDate date = LocalDate.of(2023, 6, 12);
        String formatted = CalendarUtils.formattedDate(date);
        assertEquals("12 June 2023", formatted);
    }

    @Test
    public void testFormattedTime() {
        LocalTime time = LocalTime.of(10, 15, 30);
        String formatted = CalendarUtils.formattedTime(time);
        assertEquals("10:15:30 AM", formatted);
    }

    @Test
    public void testDaysInMonthArray() {
        CalendarUtils.selectedDate = LocalDate.of(2023, 2, 15);
        assertEquals(42, CalendarUtils.daysInMonthArray().size());
    }
}
