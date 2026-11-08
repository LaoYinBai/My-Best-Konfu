package universal.tools.notifications;

import android.app.Activity;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.AndroidRuntimeException;
import com.unity3d.player.UnityPlayer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class NotificationIntentService extends IntentService {
    static final /* synthetic */ boolean $assertionsDisabled = false;

    public NotificationIntentService() {
        super("NotificationIntentService");
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            final Context applicationContext = getApplicationContext();
            Manager.setIntent(intent);
            if (intent.hasExtra("universal.tools.notifications.__id") && intent.hasExtra("universal.tools.notifications.__button_index")) {
                final int intExtra = intent.getIntExtra("universal.tools.notifications.__id", 0);
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: universal.tools.notifications.NotificationIntentService.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Manager.hideNotification(applicationContext, intExtra);
                    }
                });
            }
            if (intent.getExtras() != null && intent.getExtras().containsKey("universal.tools.notifications.__open_url")) {
                try {
                    String string = intent.getExtras().getString("universal.tools.notifications.__open_url");
                    if (!string.startsWith("http://") && !string.startsWith("https://")) {
                        string = "http://" + string;
                    }
                    Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(string));
                    intent2.setFlags(268435456);
                    startActivity(intent2);
                    return;
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            Activity activity = UnityPlayer.currentActivity;
            if (activity != null) {
                Intent intent3 = new Intent(activity, activity.getClass());
                intent3.setFlags(131072);
                try {
                    startActivity(intent3);
                    return;
                } catch (AndroidRuntimeException unused) {
                    intent3.addFlags(268435456);
                    startActivity(intent3);
                    return;
                }
            }
            Intent launchIntentForPackage = applicationContext.getPackageManager().getLaunchIntentForPackage(applicationContext.getPackageName());
            launchIntentForPackage.setFlags(337641472);
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
            startActivity(launchIntentForPackage);
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
    }
}
