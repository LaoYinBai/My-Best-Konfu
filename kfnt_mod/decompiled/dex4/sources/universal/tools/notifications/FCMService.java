package universal.tools.notifications;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class FCMService extends FirebaseMessagingService {
    /* JADX WARN: Multi-variable type inference failed */
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Bundle bundle = new Bundle();
        RemoteMessage.Notification notification = remoteMessage.getNotification();
        if (notification != null) {
            Log.w(FCMService.class.getName(), "\"notification\"-FCM message received. Please use \"data\"-only FCM messages with UTNotifications to make sure it works correctly.");
            SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(Manager.class.getName(), 0);
            if (notification.getTitle() != null) {
                bundle.putString(Manager.getTitleKey(sharedPreferences), notification.getTitle());
            }
            if (notification.getBody() != null) {
                bundle.putString(Manager.getTextKey(sharedPreferences), notification.getBody());
            }
        }
        Map data = remoteMessage.getData();
        if (data != null) {
            for (Map.Entry entry : data.entrySet()) {
                bundle.putString((String) entry.getKey(), (String) entry.getValue());
            }
        }
        Manager.postPushNotification(this, "FCM", bundle);
    }
}
