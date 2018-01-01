package a.talenting.com.talenting.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import a.talenting.com.talenting.R;
import a.talenting.com.talenting.common.NotificationHelper;
import a.talenting.com.talenting.controller.MainActivity;

/**
 * Created by daeho on 2017. 12. 14..
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";
    private NotificationHelper notificationHelper;

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        sendPushNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }

    private void sendPushNotification(String title, String message) {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                    PendingIntent.FLAG_ONE_SHOT);

            Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "talenting")
                    .setSmallIcon(R.drawable.message_on).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher) )
                    .setContentTitle(title)
                    .setContentText(message)
                    .setAutoCancel(true)
                    .setSound(defaultSoundUri).setLights(000000255,500,2000)
                    .setContentIntent(pendingIntent);



            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
            PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
            wakelock.acquire(5000);

            notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
        }
        else {
            if (notificationHelper == null) notificationHelper = new NotificationHelper(this);
            notificationHelper.notify(1, notificationHelper.getNotification1(title, message));
        }
    }

}
