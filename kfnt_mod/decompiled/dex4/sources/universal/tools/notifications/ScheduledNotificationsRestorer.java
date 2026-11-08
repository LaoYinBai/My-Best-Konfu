package universal.tools.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class ScheduledNotificationsRestorer extends BroadcastReceiver {
    private static final String RESTORE_SCHEDULED_NOTIFICATIONS = "RESTORE_SCHEDULED_NOTIFICATIONS";
    private static final String SCHEDULED_NOTIFICATIONS_STORED_PREFIX = "SCHEDULED_NOTIFICATIONS_STORED_";

    static void setRestoreScheduledOnReboot(Context context, boolean z) {
        SharedPreferences.Editor editorEdit = context.getApplicationContext().getSharedPreferences(ScheduledNotificationsRestorer.class.getName(), 0).edit();
        editorEdit.putBoolean(RESTORE_SCHEDULED_NOTIFICATIONS, z);
        editorEdit.apply();
    }

    static boolean getRestoreScheduledOnReboot(Context context) {
        return context.getApplicationContext().getSharedPreferences(ScheduledNotificationsRestorer.class.getName(), 0).getBoolean(RESTORE_SCHEDULED_NOTIFICATIONS, false);
    }

    static void register(Context context, UTNotification uTNotification) {
        Context applicationContext = context.getApplicationContext();
        if (getRestoreScheduledOnReboot(applicationContext)) {
            String string = uTNotification.toString();
            SharedPreferences.Editor editorEdit = applicationContext.getSharedPreferences(ScheduledNotificationsRestorer.class.getName(), 0).edit();
            editorEdit.putString(SCHEDULED_NOTIFICATIONS_STORED_PREFIX + uTNotification.id, string);
            editorEdit.apply();
        }
    }

    static void cancel(Context context, int i) {
        SharedPreferences.Editor editorEdit = context.getApplicationContext().getSharedPreferences(ScheduledNotificationsRestorer.class.getName(), 0).edit();
        editorEdit.remove(SCHEDULED_NOTIFICATIONS_STORED_PREFIX + i);
        editorEdit.apply();
    }

    private static void restoreScheduledNotifications(Context context) {
        Context applicationContext;
        int[] storedScheduledNotificationIds;
        if (getRestoreScheduledOnReboot(context) && (storedScheduledNotificationIds = Manager.getStoredScheduledNotificationIds((applicationContext = context.getApplicationContext()))) != null && storedScheduledNotificationIds.length > 0) {
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(ScheduledNotificationsRestorer.class.getName(), 0);
            for (int i : storedScheduledNotificationIds) {
                String string = sharedPreferences.getString(SCHEDULED_NOTIFICATIONS_STORED_PREFIX + i, null);
                if (string != null && !string.isEmpty()) {
                    restoreScheduledNotification(applicationContext, string);
                }
            }
        }
    }

    private static void restoreScheduledNotification(Context context, String str) {
        try {
            Manager.scheduleNotificationCommon(context, new UTNotification(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            restoreScheduledNotifications(context);
        }
    }
}
