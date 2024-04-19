package com.example.upi_zavrsni;

import static com.example.upi_zavrsni.CalendarUtils.daysInMonthArray;
import static com.example.upi_zavrsni.CalendarUtils.monthYearFromDate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {

    public static final String NOTIFICATION_CHANNEL_ID = "com.example.upi_zavrsni.notificationChannel";
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now();
        setMonthView();


    }

    // Inicijalizacija widgeta iz XML layouta
    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    // Prikazivanje kalendara za odabrani mjesec i godinu
    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray();

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    // Akcija koja se izvršava prilikom pritiska na gumb za prethodni mjesec
    public void previousMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    // Akcija koja se izvršava prilikom pritiska na gumb za sljedeći mjesec
    public void nextMonthAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    // Metoda koja se poziva prilikom klika na određeni dan u kalendaru
    @Override
    public void onItemClick(int position, LocalDate date) {
        if (date != null) {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }

    // Akcija koja se izvršava prilikom pritiska na gumb za tjedni prikaz
    public void weeklyAction(View view) {
        startActivity(new Intent(this, WeekViewActivity.class));
    }


    public void dailyAction(View view) {
        startActivity(new Intent(this, DailyCalendarActivity.class));
    }


    // This method opens the SettingsActivity when the settings button is clicked
    public void openSettings(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }




}
