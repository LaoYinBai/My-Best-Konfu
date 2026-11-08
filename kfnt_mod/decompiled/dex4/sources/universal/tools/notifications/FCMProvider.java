package universal.tools.notifications;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.unity3d.player.UnityPlayer;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class FCMProvider implements IPushNotificationsProvider {
    static final String Name = "FCM";

    static boolean isAvailable(Context context, boolean z) {
        try {
            GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
            int iIsGooglePlayServicesAvailable = googleApiAvailability.isGooglePlayServicesAvailable(context);
            if (iIsGooglePlayServicesAvailable == 0 || !z) {
                return true;
            }
            if (googleApiAvailability.isUserResolvableError(iIsGooglePlayServicesAvailable) && iIsGooglePlayServicesAvailable != 1 && iIsGooglePlayServicesAvailable != 9) {
                googleApiAvailability.getErrorDialog(UnityPlayer.currentActivity, iIsGooglePlayServicesAvailable, 0).show();
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // universal.tools.notifications.IPushNotificationsProvider
    public void enable() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        Task instanceId = FirebaseInstanceId.getInstance().getInstanceId();
        instanceId.addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() { // from class: universal.tools.notifications.FCMProvider.1
            public void onSuccess(InstanceIdResult instanceIdResult) {
                Manager.onPushRegistered(FCMProvider.Name, instanceIdResult.getToken());
            }
        });
        instanceId.addOnFailureListener(new OnFailureListener() { // from class: universal.tools.notifications.FCMProvider.2
            public void onFailure(Exception exc) {
                Manager.onPushRegistrationFailed(exc.toString());
            }
        });
    }

    @Override // universal.tools.notifications.IPushNotificationsProvider
    public void disable() {
        FirebaseMessaging.getInstance().setAutoInitEnabled(false);
        new Thread(new Runnable() { // from class: universal.tools.notifications.FCMProvider.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    FirebaseInstanceId.getInstance().deleteInstanceId();
                } catch (IOException e) {
                    Log.e(FCMProvider.class.getName(), "Failed to disable", e);
                }
            }
        }).start();
    }

    @Override // universal.tools.notifications.IPushNotificationsProvider
    public void subscribeToTopic(String str) {
        FirebaseMessaging.getInstance().subscribeToTopic(str);
    }

    @Override // universal.tools.notifications.IPushNotificationsProvider
    public void unsubscribeFromTopic(String str) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(str);
    }
}
