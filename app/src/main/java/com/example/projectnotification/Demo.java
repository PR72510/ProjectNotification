package com.example.projectnotification;

import java.util.TimerTask;

public class Demo extends TimerTask {           // TimerTask implements Runnable task
    MainActivity activity = MainActivity.getInstance();
    int counter = 1;

    @Override
    public void run() {
        if (counter == 1) {
            activity.sendNotification1();
            counter = 0;
        } else if (counter == 0) {
            activity.sendNotification2();
            counter = 1;
        }
    }
}
