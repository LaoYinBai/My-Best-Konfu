package universal.tools.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.net.URLDecoder;
import java.util.List;
import universal.tools.notifications.UTNotification;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class AlarmBroadcastReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        try {
            String stringExtra = intent.getStringExtra("universal.tools.notifications.__notification");
            if (stringExtra != null) {
                Manager.postNotification(context, new UTNotification(stringExtra));
            } else {
                Bundle bundle = (Bundle) intent.getParcelableExtra("__UT_USER_DATA");
                Manager.postNotification(context, new UTNotification(0L, 0, URLDecoder.decode(bundle.getString("__UT_TITLE"), "UTF-8"), URLDecoder.decode(bundle.getString("__UT_TEXT"), "UTF-8"), intent.getIntExtra("__UT_ID", 1), Manager.bundleToMap(bundle), bundle.getString("__UT_NOTIFICATION_PROFILE"), bundle.getInt("__UT_BADGE_NUMBER", -1), (List<UTNotification.Button>) null));
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
