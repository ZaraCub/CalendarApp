package com.example.upi_zavrsni;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.content.SharedPreferences;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;


@RunWith(RobolectricTestRunner.class)
@Config(manifest=Config.NONE)
public class SettingsActivityTest {

    private SettingsActivity settingsActivity;
    private SharedPreferences sharedPreferences;

    @Before
    public void setUp() {
        settingsActivity = Robolectric.buildActivity(SettingsActivity.class).create().resume().get();
        sharedPreferences = ApplicationProvider.getApplicationContext().getSharedPreferences("Settings", 0);
    }

    @Test
    public void onCreate_initializesNotificationSwitchFromSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("Notifications", true).commit();

        boolean isNotificationSwitchChecked = settingsActivity.notificationSwitch.isChecked();

        assertTrue(isNotificationSwitchChecked);
    }

    @Test
    public void onNotificationSwitchChange_savesValueInSharedPreferences() {
        settingsActivity.notificationSwitch.performClick();

        boolean isSavedValue = sharedPreferences.getBoolean("Notifications", false);

        assertEquals(settingsActivity.notificationSwitch.isChecked(), isSavedValue);
    }
}
