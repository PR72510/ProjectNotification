package com.example.projectnotification;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class NotificationChannels extends Application {

    public static final String CHANNEL_1 = "Channel 1";
    public static final String CHANNEL_2 = "Channel 2";

    @Override
    public void onCreate() {
        super.onCreate();
        createChannels();           // calling function to create channels
    }

    private void createChannels() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {           // checking if current SDK version greater than OREO
            NotificationChannel channel1 = new NotificationChannel(     // creating channel
                    CHANNEL_1,                                          // ID of the channel
                    "Channel 1",                                  // user visible name of channel
                    NotificationManager.IMPORTANCE_HIGH                 // shows everywhere, make noise and peek
            );

            channel1.setDescription("This is High Priority Channel 1");


            NotificationChannel channel2 = new NotificationChannel(
                    CHANNEL_2,
                    "Channel 2",
                    NotificationManager.IMPORTANCE_LOW
            );

            channel2.setDescription("This is High Priority Channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);
        }
    }
}
