package com.example.upi_zavrsni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event> {
    public EventAdapter(@NonNull Context context, List<Event> events) {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Event event = getItem(position);

        // Provjera je li već inflatiran pogled
        if (convertView == null) {
            // Inflatiranje izgleda retka
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }

        // Dohvaćanje TextView elementa izgleda retka
        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

        // Postavljanje teksta za prikaz naslova događaja i formatiranog vremena
        String eventTitle = event.getName() + " " + CalendarUtils.formattedTime(event.getTime());
        eventCellTV.setText(eventTitle);

        return convertView;
    }
}
