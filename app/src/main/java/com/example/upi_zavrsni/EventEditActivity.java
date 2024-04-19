package com.example.upi_zavrsni;

import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventEditActivity extends AppCompatActivity {
    private EditText eventNameET;
    private TextView eventDateTV;
    private TimePicker eventTimePicker;

    private LocalDate date;
    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check for VIBRATE permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.VIBRATE)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission not granted, request it from user
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.VIBRATE}, 0);
                return;
            }
        }

        setContentView(R.layout.activity_event_edit);
        initWidgets();
        date = CalendarUtils.selectedDate;
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(date));
        time = LocalTime.now();
        setEventTime();
    }


    private void initWidgets() {
        eventNameET = findViewById(R.id.eventNameET);
        eventDateTV = findViewById(R.id.eventDateTV);
        eventTimePicker = findViewById(R.id.eventTimePicker);
        eventTimePicker.setIs24HourView(true);
    }

    // Metoda koja se poziva prilikom pritiska na gumb za spremanje događaja
    public void saveEventAction(View view) {
        // Dohvaćanje unesenog naziva događaja
        String eventName = eventNameET.getText().toString();
        // Dohvaćanje odabranog sata i minute iz TimePicker-a
        int hour = eventTimePicker.getHour();
        int minute = eventTimePicker.getMinute();
        time = LocalTime.of(hour, minute);
        // Stvaranje novog događaja s unesenim podacima
        Event newEvent = new Event(eventName, date, time);
        // Dodavanje novog događaja u listu svih događaja
        Event.eventsList.add(newEvent);

        // Show notification for the new event
        NotificationHelper notificationHelper = new NotificationHelper(this);
        notificationHelper.showNotification("New Event Created", eventName);

        // Završetak aktivnosti
        finish();
    }

    private void setEventTime() {
        // Postavljanje odabrane vrijednosti sata i minute u TimePicker
        int hour = time.getHour();
        int minute = time.getMinute();
        eventTimePicker.setHour(hour);
        eventTimePicker.setMinute(minute);
    }



}
