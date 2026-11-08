package universal.tools.notifications;

import android.content.Context;
import com.amazon.device.messaging.ADM;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class ADMProvider implements IPushNotificationsProvider {
    static final String Name = "ADM";
    private final WeakReference<Context> context;

    @Override // universal.tools.notifications.IPushNotificationsProvider
    public void subscribeToTopic(String str) {
    }

    @Override // universal.tools.notifications.IPushNotificationsProvider
    public void unsubscribeFromTopic(String str) {
    }

    ADMProvider(Context context) {
        this.context = new WeakReference<>(context);
    }

    static boolean isAvailable() throws ClassNotFoundException {
        try {
            Class.forName("com.amazon.device.messaging.ADM");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @Override // universal.tools.notifications.IPushNotificationsProvider
    public void enable() {
        Context context = this.context.get();
        if (context != null) {
            try {
                ADM adm = new ADM(context);
                String registrationId = adm.getRegistrationId();
                if (registrationId == null) {
                    adm.startRegister();
                } else {
                    Manager.onPushRegistered(Name, registrationId);
                }
            } catch (SecurityException e) {
                Manager.onPushRegistrationFailed(e.toString());
            }
        }
    }

    @Override // universal.tools.notifications.IPushNotificationsProvider
    public void disable() {
        Context context = this.context.get();
        if (context != null) {
            new ADM(context).startUnregister();
        }
    }
}
