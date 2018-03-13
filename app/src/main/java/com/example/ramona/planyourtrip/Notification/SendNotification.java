package com.example.ramona.planyourtrip.Notification;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ramona.planyourtrip.R;

/**
 * Created by Ramona on 2/13/2018.
 */

public class SendNotification extends AppCompatActivity {


    public void sendNotification() {

        //Get an instance of NotificationManager//

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.home)
                        .setContentTitle("Plan Your Trip")
                        .setContentText("Buna, tocmai avem o oferta noua pentru tine!")
                        .setColor(Color.RED);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
    }
}
