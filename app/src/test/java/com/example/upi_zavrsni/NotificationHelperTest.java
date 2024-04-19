package com.example.upi_zavrsni;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.os.Build;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class NotificationHelperTest {

    @Mock
    private Context mockContext;

    @Mock
    private NotificationManager mockNotificationManager;

    private NotificationHelper notificationHelper;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockContext.getSystemService(Context.NOTIFICATION_SERVICE)).thenReturn(mockNotificationManager);
        notificationHelper = new NotificationHelper(mockContext);
    }

    @Test
    public void showNotification_createsChannelForOreoAndAbove() {
        when(mockContext.getApplicationInfo()).thenReturn(new ApplicationInfo());
        when(mockContext.getApplicationInfo().targetSdkVersion).thenReturn(Build.VERSION_CODES.O);

        notificationHelper.showNotification("Test Title", "Test Message");

        verify(mockNotificationManager).createNotificationChannel(any(NotificationChannel.class));
    }
}
