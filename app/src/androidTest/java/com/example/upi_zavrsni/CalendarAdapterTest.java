package com.example.upi_zavrsni;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import android.graphics.Color;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class CalendarAdapterTest {

    @Mock
    private CalendarViewHolder mockViewHolder;

    @Mock
    private CalendarAdapter.OnItemListener mockListener;

    private CalendarAdapter calendarAdapter;
    private ArrayList<LocalDate> days;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Kreiranje liste datuma za testiranje
        days = new ArrayList<>();
        days.add(LocalDate.of(2023, 6, 1));
        days.add(LocalDate.of(2023, 6, 2));
        days.add(LocalDate.of(2023, 6, 3));

        calendarAdapter = new CalendarAdapter(days, mockListener);
    }

    @Test
    public void onBindViewHolder_SelectedDate_HighlightBackground() {
        // Postavljanje odabranog datuma na isti datum kao jedan od datuma u adapteru
        CalendarUtils.selectedDate = LocalDate.of(2023, 6, 2);

        // Pozivanje metode onBindViewHolder za drugu poziciju
        calendarAdapter.onBindViewHolder(mockViewHolder, 1);

        // Provjera je li boja pozadine parentView elementa postavljena na svijetlo sivu
        verify(mockViewHolder.parentView).setBackgroundColor(Color.parseColor("#EEE"));
    }

    @Test
    public void onBindViewHolder_DifferentMonth_SetTextColorToLTGRAY() {
        // Postavljanje odabranog datuma na drugi mjesec od datuma u adapteru
        CalendarUtils.selectedDate = LocalDate.of(2023, 5, 20);

        // Pozivanje metode onBindViewHolder za prvu poziciju
        calendarAdapter.onBindViewHolder(mockViewHolder, 0);

        // Provjera je li boja teksta dayOfMonth elementa postavljena na sivu
        verify(mockViewHolder.dayOfMonth).setTextColor(Color.parseColor("#888"));
    }
}
