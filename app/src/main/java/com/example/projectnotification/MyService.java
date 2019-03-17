package com.example.projectnotification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    private static final String TAG = "MyService";
    MainActivity activity;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        activity = MainActivity.getInstance();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(TAG, "onStart: ");
        activity.sendNotification1();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
