package com.example.projectnotification;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

import static com.example.projectnotification.NotificationChannels.CHANNEL_1;
import static com.example.projectnotification.NotificationChannels.CHANNEL_2;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private NotificationManagerCompat notificationManager;
    Button btnChannelFirst, btnChannelSec, btnStrtSchedule, btnStpSchedule, btnPerformWork;
    static MainActivity activity;
    Timer t1 = new Timer();         // Timer executes TimerTask
    Demo demo = new Demo();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG, "onCreate: ");
        activity = this;
        final OneTimeWorkRequest request = new OneTimeWorkRequest.Builder(MyWorker.class).build();      // we have to request for work once only

        notificationManager = NotificationManagerCompat.from(this);            // getting object of NotificationManagerCompat
        btnChannelFirst = findViewById(R.id.btn_channel_first);
        btnChannelSec = findViewById(R.id.btn_channel_second);
        btnStrtSchedule = findViewById(R.id.btn_schedule_strt);
        btnStpSchedule = findViewById(R.id.btn_schedule_stp);
        btnPerformWork = findViewById(R.id.btn_wrk_mgr);

        btnPerformWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkManager.getInstance().enqueue(request);

            }
        });

        btnChannelFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification1();
            }
        });

        btnChannelSec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendNotification2();
            }
        });

        btnStrtSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schedule();
                Log.i(TAG, "onClick: schedule");
            }
        });

        btnStpSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopSchedule();
            }
        });

    }


    public void sendNotification1() {

        Intent notificationIntent = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this.getApplicationContext(), 0, notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1)     // constructing notification
                .setSmallIcon(R.drawable.icon_1)
                .setContentTitle("Channel 1")
                .setContentText("This is high priority notification")
                .setPriority(NotificationCompat.PRIORITY_HIGH)            // priority should be same as importance of the channel
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .addAction(R.drawable.img_1, "Jump", contentIntent)
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setLights(Color.RED, 3000, 3000)
                .build();

//        notification.contentIntent = contentIntent;

        Log.i(TAG, "sendNotification1: ");

        notificationManager.notify(1, notification);            // id should be different if we want to send more than 1 notifications
    }

    public void sendNotification2() {
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_2)
                .setSmallIcon(R.drawable.icon_2)
                .setContentTitle("Channel 2")
                .setContentText("This is low priority notification")
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .build();
        Log.i(TAG, "sendNotification2: ");

        notificationManager.notify(2, notification);
    }


    private void schedule() {

        Log.i(TAG, "schedule: ");
        t1.schedule(new Demo(), 0, 10000);

    }

    public static MainActivity getInstance() {
        return activity;
    }

    private void stopSchedule() {           // Called when stop btn isclicked
        demo.cancel();                      // TimerTask.cancel() cancels a particular task, current one
        t1.cancel();                        // Timer`s cancel() cancels all tasks scheduled to Timer


    }
}

