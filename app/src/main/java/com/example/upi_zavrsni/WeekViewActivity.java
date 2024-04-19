package com.example.upi_zavrsni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.upi_zavrsni.CalendarUtils.daysInMonthArray;
import static com.example.upi_zavrsni.CalendarUtils.daysInWeekArray;
import static com.example.upi_zavrsni.CalendarUtils.monthYearFromDate;

public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener {
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        initWidgets();
        setWeekView();
    }

    // Inicijalizacija widgeta iz XML layouta
    private void initWidgets() {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
    }

    // Postavljanje tjednog prikaza kalendara
    private void setWeekView() {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();
    }

    // Akcija koja se izvršava prilikom pritiska na gumb za prethodni tjedan
    public void previousWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    // Akcija koja se izvršava prilikom pritiska na gumb za sljedeći tjedan
    public void nextWeekAction(View view) {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    // Metoda koja se poziva prilikom klika na određeni dan u kalendaru
    @Override
    public void onItemClick(int position, LocalDate date) {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    // Osvježavanje prikaza događaja prilikom povratka na aktivnost iz EventEditActivity
    @Override
    protected void onResume() {
        super.onResume();
        setEventAdapter();
    }

    // Postavljanje adaptera za prikaz događaja
    private void setEventAdapter() {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    // Akcija koja se izvršava prilikom pritiska na gumb za dodavanje novog događaja
    public void newEventAction(View view) {
        startActivity(new Intent(this, EventEditActivity.class));
    }

    // Akcija koja se izvršava prilikom pritiska na gumb za prijelaz na dnevni prikaz
    public void dailyAction(View view) {
        startActivity(new Intent(this, DailyCalendarActivity.class));
    }

    // Add this function inside your WeekViewActivity class
    public void monthlyAction(View view) {
        startActivity(new Intent(this, MainActivity.class));

    }

    // Opens SettingsActivity when the settings btn is clicked
    public void openSettings(View view) {
        Intent intent = new Intent(WeekViewActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

}
