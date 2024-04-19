package com.example.upi_zavrsni;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;

class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder> {
    private final ArrayList<LocalDate> days;
    private final OnItemListener onItemListener;

    public CalendarAdapter(ArrayList<LocalDate> days, OnItemListener onItemListener) {
        this.days = days;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (days.size() > 15) { // Prikaz za mjesec
            layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        } else { // Prikaz za tjedan
            layoutParams.height = (int) parent.getHeight();
        }

        // Stvaranje i vraćanje novog CalendarViewHolder-a s inflatiranim izgledom reda
        return new CalendarViewHolder(view, onItemListener, days);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        // Dohvaćanje trenutnog datuma iz liste
        final LocalDate date = days.get(position);

        // Postavljanje teksta dana u mjesecu
        holder.dayOfMonth.setText(String.valueOf(date.getDayOfMonth()));

        // Provjera je li trenutni datum označen kao odabrani datum i postavljanje boje pozadine
        if (date.equals(CalendarUtils.selectedDate)) {
            holder.parentView.setBackgroundColor(Color.LTGRAY);
        }

        // Provjera pripada li trenutni datum istom mjesecu kao i odabrani datum i postavljanje boje teksta
        if (date.getMonth().equals(CalendarUtils.selectedDate.getMonth())) {
            holder.dayOfMonth.setTextColor(Color.BLACK);
        } else {
            holder.dayOfMonth.setTextColor(Color.LTGRAY);
        }
    }

    @Override
    public int getItemCount() {
        // Vraćanje ukupnog broja dana u mjesecu
        return days.size();
    }

    public interface OnItemListener {
        void onItemClick(int position, LocalDate date);
    }
}
