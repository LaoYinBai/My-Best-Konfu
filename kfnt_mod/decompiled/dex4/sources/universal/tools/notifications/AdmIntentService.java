package universal.tools.notifications;

import android.content.Intent;
import com.amazon.device.messaging.ADMMessageHandlerBase;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class AdmIntentService extends ADMMessageHandlerBase {
    protected void onUnregistered(String str) {
    }

    public AdmIntentService() {
        super("AdmIntentService");
    }

    protected void onRegistered(String str) {
        Manager.onPushRegistered("ADM", str);
    }

    protected void onRegistrationError(String str) {
        Manager.onPushRegistrationFailed(str);
    }

    protected void onMessage(Intent intent) {
        Manager.postPushNotification(getApplicationContext(), "ADM", intent.getExtras());
    }
}
