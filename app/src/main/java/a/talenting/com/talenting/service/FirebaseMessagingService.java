package a.talenting.com.talenting.common;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

import a.talenting.com.talenting.R;

/**
 * Created by daeho on 2017. 12. 14..
 */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {
    private static final String TAG = "FirebaseMsgService";

    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
    }

    private void sendPushNotification(String title, String message) {
//        System.out.println("received message : " + message);
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_country).setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri).setLights(000000255,500,2000);
                //.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakelock = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");
        wakelock.acquire(5000);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    public void showNotification(String title, String message) {
//        NotificationManager notificationManager= (NotificationManager)MainActivity.this.getSystemService(MainActivity.this.NOTIFICATION_SERVICE);
//        Intent intent1 = new Intent(MainActivity.this.getApplicationContext(),MainActivity.class); //인텐트 생성.



//        Notification.Builder builder = new Notification.Builder(getApplicationContext());
//        intent1.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP| Intent.FLAG_ACTIVITY_CLEAR_TOP);//현재 액티비티를 최상으로 올리고, 최상의 액티비티를 제외한 모든 액티비티를
//
//        없앤다.

//                PendingIntent pendingNotificationIntent = PendingIntent.getActivity( MainActivity.this,0, intent1,PendingIntent.FLAG_UPDATE_CURRENT);
//        //PendingIntent는 일회용 인텐트 같은 개념입니다.
//        FLAG_UPDATE_CURRENT - > 만일 이미 생성된 PendingIntent가 존재 한다면, 해당 Intent의 내용을 변경함.
//
//                FLAG_CANCEL_CURRENT - .이전에 생성한 PendingIntent를 취소하고 새롭게 하나 만든다.
//
//                FLAG_NO_CREATE -> 현재 생성된 PendingIntent를 반환합니다.
//
//        FLAG_ONE_SHOT - >이 플래그를 사용해 생성된 PendingIntent는 단 한번밖에 사용할 수 없습니다
        Notification.Builder builder = new Notification.Builder(getApplicationContext());
        builder.setSmallIcon(R.drawable.ic_country).setTicker("HETT").setWhen(System.currentTimeMillis())
                .setNumber(1).setContentTitle(title).setContentText(message)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .setContentIntent(null).setAutoCancel(true).setOngoing(true);
        //해당 부분은 API 4.1버전부터 작동합니다.

//setSmallIcon - > 작은 아이콘 이미지

//setTicker - > 알람이 출력될 때 상단에 나오는 문구.

//setWhen -> 알림 출력 시간.

//setContentTitle-> 알림 제목

//setConentText->푸쉬내용
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build()); // Notification send
    }
}
