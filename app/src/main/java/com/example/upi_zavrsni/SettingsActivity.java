package com.example.upi_zavrsni;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

public class SettingsActivity extends AppCompatActivity {

    SwitchCompat notificationSwitch, soundSwitch, vibrationSwitch;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notificationSwitch = findViewById(R.id.notificationSwitch);
        soundSwitch = findViewById(R.id.soundSwitch);
        vibrationSwitch = findViewById(R.id.vibrationSwitch);
        backButton = findViewById(R.id.backButton); // Initialize the back button

        // Handle back button click
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Close the SettingsActivity when the back button is clicked
            }
        });


        // Initialize switches from shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        notificationSwitch.setChecked(sharedPreferences.getBoolean("Notifications", true));
        soundSwitch.setChecked(sharedPreferences.getBoolean("Sound", true));
        vibrationSwitch.setChecked(sharedPreferences.getBoolean("Vibration", true));

        // Handle switch changes
        notificationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("Notifications", isChecked);
            editor.apply();
        });

        soundSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("Sound", isChecked);
            editor.apply();
        });

        vibrationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("Vibration", isChecked);
            editor.apply();
        });
    }
}
