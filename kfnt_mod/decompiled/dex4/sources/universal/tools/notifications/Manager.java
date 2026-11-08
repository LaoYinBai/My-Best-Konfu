package universal.tools.notifications;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.security.NetworkSecurityPolicy;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;
import com.unity3d.player.UnityPlayer;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import me.leolin.shortcutbadger.ShortcutBadger;
import org.android.agoo.common.AgooConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import universal.tools.notifications.UTNotification;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
public class Manager {
    private static final String ALERT = "alert";
    private static final String BADGE_FIELD_NAME = "BADGE_FIELD_NAME";
    private static final String BODY = "body";
    private static final String BUTTONS_FIELD_NAME = "BUTTONS_FIELD_NAME";
    private static final String ID_FIELD_NAME = "ID_FIELD_NAME";
    private static final String IMAGE_URL = "image_url";
    private static final String INCREMENTAL_ID = "INCREMENTAL_ID";
    static final String KEY_BADGE_NUMBER = "universal.tools.notifications.__badge_number";
    static final String KEY_BUTTON_INDEX = "universal.tools.notifications.__button_index";
    static final String KEY_ID = "universal.tools.notifications.__id";
    static final String KEY_NOTIFICATION = "universal.tools.notifications.__notification";
    static final String KEY_OPEN_URL = "universal.tools.notifications.__open_url";
    private static final String NOTIFICATIONS_ENABLED = "NOTIFICATIONS_ENABLED";
    private static final String NOTIFICATIONS_GROUPING_MODE = "NOTIFICATIONS_GROUPING_MODE";
    private static final String NOTIFICATION_PROFILE_FIELD_NAME = "NOTIFICATION_PROFILE_FIELD_NAME";
    private static final String OPEN_URL = "open_url";
    private static final String PUSH_NOTIFICATIONS_ENABLED = "PUSH_NOTIFICATIONS_ENABLED";
    private static final String RECEIVED_NOTIFICATIONS = "RECEIVED_NOTIFICATIONS";
    private static final String SCHEDULED_NOTIFICATION_IDS = "SCHEDULED_NOTIFICATION_IDS";
    private static final String SCHEDULE_EXACT = "SCHEDULE_EXACT";
    private static final String SCHEDULE_TIMER_TYPE = "SCHEDULE_TIMER_TYPE";
    private static final String SHOW_LATEST_NOTIFICATIONS_ONLY = "SHOW_LATEST_NOTIFICATIONS_ONLY";
    private static final String SHOW_NOTIFICATIONS_MODE = "SHOW_NOTIFICATIONS_MODE";
    private static final String START_ID = "START_ID";
    private static final String TEXT_FIELD_NAME = "TEXT_FIELD_NAME";
    private static final String TITLE_FIELD_NAME = "TITLE_FIELD_NAME";
    private static final String USER_DATA_FIELD_NAME = "USER_DATA_FIELD_NAME";
    private static final String WILL_HANDLE_RECEIVED_NOTIFICATIONS = "WILL_HANDLE_RECEIVED_NOTIFICATIONS";
    private static Intent intent = null;
    private static WeakReference<Activity> m_closedActivity = null;
    private static int m_nextPushNotificationId = -1;
    private static IPushNotificationsProvider m_provider;

    public static void initialize(boolean z, boolean z2, boolean z3, int i, boolean z4, int i2, boolean z5, int i3, int i4, boolean z6, boolean z7, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, boolean z8) throws JSONException {
        m_nextPushNotificationId = i;
        Context applicationContext = UnityPlayer.currentActivity.getApplicationContext();
        SharedPreferences.Editor editorEdit = applicationContext.getSharedPreferences(Manager.class.getName(), 0).edit();
        editorEdit.putBoolean(WILL_HANDLE_RECEIVED_NOTIFICATIONS, z3);
        editorEdit.putBoolean(INCREMENTAL_ID, z4);
        editorEdit.putInt(START_ID, i);
        editorEdit.putInt(SHOW_NOTIFICATIONS_MODE, i2);
        editorEdit.putInt(NOTIFICATIONS_GROUPING_MODE, i3);
        editorEdit.putInt(SCHEDULE_TIMER_TYPE, i4);
        editorEdit.putBoolean(SHOW_LATEST_NOTIFICATIONS_ONLY, z6);
        editorEdit.putBoolean(SCHEDULE_EXACT, z7);
        editorEdit.putString(TITLE_FIELD_NAME, str);
        editorEdit.putString(TEXT_FIELD_NAME, str2);
        editorEdit.putString(USER_DATA_FIELD_NAME, str3);
        editorEdit.putString(NOTIFICATION_PROFILE_FIELD_NAME, str4);
        editorEdit.putString(ID_FIELD_NAME, str5);
        editorEdit.putString(BADGE_FIELD_NAME, str6);
        editorEdit.putString(BUTTONS_FIELD_NAME, str7);
        registerProfile(applicationContext, editorEdit, str8);
        editorEdit.apply();
        ScheduledNotificationsRestorer.setRestoreScheduledOnReboot(applicationContext, z5);
        if (z) {
            try {
                if (fcmProviderAvailable(z8)) {
                    m_provider = new FCMProvider();
                }
            } catch (Throwable th) {
                if (pushNotificationsEnabled(applicationContext)) {
                    onPushRegistrationFailed(th.toString());
                    return;
                } else {
                    Log.e(Manager.class.getName(), "Failed to disable push notifications", th);
                    return;
                }
            }
        }
        if (z2 && admProviderAvailable()) {
            m_provider = new ADMProvider(applicationContext);
        }
        if (m_provider != null) {
            if (pushNotificationsEnabled(applicationContext)) {
                m_provider.enable();
                return;
            } else {
                m_provider.disable();
                return;
            }
        }
        if (z || z2) {
            onPushRegistrationFailed("None of the enabled push notifications APIs is available in the device or required libraries are missing in Assets/Plugins/Android.");
        }
    }

    public static boolean fcmProviderAvailable(boolean z) {
        return FCMProvider.isAvailable(UnityPlayer.currentActivity.getApplicationContext(), z);
    }

    public static boolean admProviderAvailable() {
        return ADMProvider.isAvailable();
    }

    public static void postNotification(String str, String str2, int i, String str3, String str4, int i2, String str5) throws JSONException {
        postNotification(UnityPlayer.currentActivity, new UTNotification(0L, 0, decodeBase64(str), decodeBase64(str2), i, decodeBase64(str3), str4, i2, decodeBase64(str5)));
    }

    public static void scheduleNotification(int i, String str, String str2, int i2, String str3, String str4, int i3, String str5) throws JSONException {
        scheduleNotificationCommon(UnityPlayer.currentActivity, new UTNotification(triggerInSecondsToTriggerAtSystemTimeMillis(i), 0, decodeBase64(str), decodeBase64(str2), i2, decodeBase64(str3), str4, i3, decodeBase64(str5)));
    }

    public static void scheduleNotificationRepeating(int i, int i2, String str, String str2, int i3, String str3, String str4, int i4, String str5) throws JSONException {
        scheduleNotificationCommon(UnityPlayer.currentActivity, new UTNotification(triggerInSecondsToTriggerAtSystemTimeMillis(i), i2, decodeBase64(str), decodeBase64(str2), i3, decodeBase64(str3), str4, i4, decodeBase64(str5)));
    }

    public static boolean notificationsEnabled() {
        return notificationsEnabled(UnityPlayer.currentActivity.getApplicationContext());
    }

    public static boolean notificationsAllowed() {
        return NotificationManagerCompat.from(UnityPlayer.currentActivity.getApplicationContext()).areNotificationsEnabled();
    }

    public static void setNotificationsEnabled(boolean z) {
        SharedPreferences.Editor editorEdit = UnityPlayer.currentActivity.getApplicationContext().getSharedPreferences(Manager.class.getName(), 0).edit();
        editorEdit.putBoolean(NOTIFICATIONS_ENABLED, z);
        editorEdit.apply();
    }

    public static boolean pushNotificationsEnabled() {
        Context applicationContext = UnityPlayer.currentActivity.getApplicationContext();
        return m_provider != null && notificationsEnabled(applicationContext) && pushNotificationsEnabled(applicationContext);
    }

    public static void setPushNotificationsEnabled(boolean z) {
        Context applicationContext = UnityPlayer.currentActivity.getApplicationContext();
        if (pushNotificationsEnabled(applicationContext) != z) {
            SharedPreferences.Editor editorEdit = applicationContext.getSharedPreferences(Manager.class.getName(), 0).edit();
            editorEdit.putBoolean(PUSH_NOTIFICATIONS_ENABLED, z);
            editorEdit.apply();
            if (m_provider != null) {
                if (z) {
                    m_provider.enable();
                } else {
                    m_provider.disable();
                }
            }
        }
    }

    public static void hideNotification(int i) {
        hideNotification(UnityPlayer.currentActivity, i);
    }

    public static void cancelNotification(int i) {
        hideNotification(i);
        Context applicationContext = UnityPlayer.currentActivity.getApplicationContext();
        ((AlarmManager) applicationContext.getSystemService("alarm")).cancel(buildPendingIntentForScheduledNotification(applicationContext, new UTNotification(i)));
        clearSheduledNotificationId(applicationContext, i);
    }

    public static void hideAllNotifications() {
        hideAllNotifications(UnityPlayer.currentActivity);
    }

    public static void cancelAllNotifications() {
        hideAllNotifications();
        for (int i : getStoredScheduledNotificationIds(UnityPlayer.currentActivity.getApplicationContext())) {
            cancelNotification(i);
        }
    }

    public static String getClickedNotificationPacked() throws JSONException {
        Intent intent2 = getIntent();
        if (intent2 == null) {
            return "";
        }
        String stringExtra = intent2.getStringExtra(KEY_NOTIFICATION);
        setIntent(null);
        if (stringExtra == null) {
            return "";
        }
        if (!intent2.hasExtra(KEY_BUTTON_INDEX)) {
            return stringExtra;
        }
        JSONObject jSONObject = new JSONObject(stringExtra);
        jSONObject.put("buttonIndex", intent2.getIntExtra(KEY_BUTTON_INDEX, -1));
        return jSONObject.toString();
    }

    public static String getReceivedNotificationsPacked() {
        Context applicationContext = UnityPlayer.currentActivity.getApplicationContext();
        String receivedNotificationsPacked = readReceivedNotificationsPacked(applicationContext);
        SharedPreferences.Editor editorEdit = applicationContext.getSharedPreferences(Manager.class.getName(), 0).edit();
        editorEdit.remove(RECEIVED_NOTIFICATIONS);
        editorEdit.apply();
        return "[" + receivedNotificationsPacked + "]";
    }

    public static int getBadge() {
        return getBadge(UnityPlayer.currentActivity.getApplicationContext());
    }

    public static void setBadge(int i) {
        setBadge(UnityPlayer.currentActivity.getApplicationContext(), i);
    }

    public static void subscribeToTopic(String str) {
        if (m_provider != null) {
            m_provider.subscribeToTopic(str);
        }
    }

    public static void unsubscribeFromTopic(String str) {
        if (m_provider != null) {
            m_provider.unsubscribeFromTopic(str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0070 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:79:? A[RETURN, SYNTHETIC] */
    @android.annotation.SuppressLint({"HardwareIds", "MissingPermission"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getDeviceId() throws java.lang.Throwable {
        /*
            android.app.Activity r0 = com.unity3d.player.UnityPlayer.currentActivity
            java.lang.String r1 = ""
            if (r0 == 0) goto L72
            java.lang.String r2 = "phone"
            java.lang.Object r2 = r0.getSystemService(r2)     // Catch: java.lang.Throwable -> L15
            android.telephony.TelephonyManager r2 = (android.telephony.TelephonyManager) r2     // Catch: java.lang.Throwable -> L15
            if (r2 == 0) goto L15
            java.lang.String r2 = r2.getDeviceId()     // Catch: java.lang.Throwable -> L15
            r1 = r2
        L15:
            if (r1 == 0) goto L1d
            boolean r2 = r1.isEmpty()
            if (r2 == 0) goto L28
        L1d:
            android.content.ContentResolver r0 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L28
            java.lang.String r2 = "android_id"
            java.lang.String r0 = android.provider.Settings.Secure.getString(r0, r2)     // Catch: java.lang.Throwable -> L28
            goto L29
        L28:
            r0 = r1
        L29:
            if (r0 == 0) goto L31
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L6d
        L31:
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L62
            java.lang.String r3 = "/sys/class/net/wlan0/address"
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L55 java.io.IOException -> L62
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L63
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L63
            r4.<init>(r2)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L63
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L53 java.io.IOException -> L63
            java.lang.String r1 = r3.readLine()     // Catch: java.lang.Throwable -> L4e java.io.IOException -> L51
            r3.close()     // Catch: java.io.IOException -> L4a
        L4a:
            r2.close()     // Catch: java.io.IOException -> L6e
            goto L6e
        L4e:
            r0 = move-exception
            r1 = r3
            goto L57
        L51:
            r1 = r3
            goto L63
        L53:
            r0 = move-exception
            goto L57
        L55:
            r0 = move-exception
            r2 = r1
        L57:
            if (r1 == 0) goto L5c
            r1.close()     // Catch: java.io.IOException -> L5c
        L5c:
            if (r2 == 0) goto L61
            r2.close()     // Catch: java.io.IOException -> L61
        L61:
            throw r0
        L62:
            r2 = r1
        L63:
            if (r1 == 0) goto L68
            r1.close()     // Catch: java.io.IOException -> L68
        L68:
            if (r2 == 0) goto L6d
            r2.close()     // Catch: java.io.IOException -> L6d
        L6d:
            r1 = r0
        L6e:
            if (r1 != 0) goto L72
            java.lang.String r1 = ""
        L72:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: universal.tools.notifications.Manager.getDeviceId():java.lang.String");
    }

    private static boolean isCleartextPermitted(String str) {
        if (Build.VERSION.SDK_INT < 23) {
            return true;
        }
        NetworkSecurityPolicy networkSecurityPolicy = NetworkSecurityPolicy.getInstance();
        if (Build.VERSION.SDK_INT >= 24) {
            return networkSecurityPolicy.isCleartextTrafficPermitted(str);
        }
        return networkSecurityPolicy.isCleartextTrafficPermitted();
    }

    static String getTitleKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(TITLE_FIELD_NAME, "title");
    }

    static String getTextKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(TEXT_FIELD_NAME, "text");
    }

    static String getIdKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(ID_FIELD_NAME, AgooConstants.MESSAGE_ID);
    }

    static String getUserDataKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(USER_DATA_FIELD_NAME, "");
    }

    static String getNotificationProfileKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(NOTIFICATION_PROFILE_FIELD_NAME, "notification_profile");
    }

    static String getBadgeNumberKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(BADGE_FIELD_NAME, "badge_number");
    }

    static String getButtonsKey(SharedPreferences sharedPreferences) {
        return sharedPreferences.getString(BUTTONS_FIELD_NAME, "buttons");
    }

    static void postPushNotification(Context context, String str, Bundle bundle) {
        ArrayList arrayList;
        String string;
        Context applicationContext = context.getApplicationContext();
        if (pushNotificationsEnabled(applicationContext)) {
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(Manager.class.getName(), 0);
            String titleKey = getTitleKey(sharedPreferences);
            String textKey = getTextKey(sharedPreferences);
            String userDataKey = getUserDataKey(sharedPreferences);
            String notificationProfileKey = getNotificationProfileKey(sharedPreferences);
            String idKey = getIdKey(sharedPreferences);
            String badgeNumberKey = getBadgeNumberKey(sharedPreferences);
            String buttonsKey = getButtonsKey(sharedPreferences);
            String strExtractStringFromBundle = extractStringFromBundle(bundle, titleKey);
            String strExtractStringFromBundle2 = extractStringFromBundle(bundle, textKey);
            if (strExtractStringFromBundle2 == null) {
                strExtractStringFromBundle2 = bundle.getString(ALERT);
                bundle.remove(ALERT);
            }
            if (strExtractStringFromBundle2 == null) {
                strExtractStringFromBundle2 = bundle.getString("body");
                bundle.remove("body");
            }
            if (strExtractStringFromBundle == null && strExtractStringFromBundle2 == null) {
                Log.e(Manager.class.getName(), "Push notification message is received but malformed: \"data/" + titleKey + " & \"data/" + textKey + " not present!\nIt can be just a service message, then you can ignore it. Otherwise configure Advanced->Push Payload Format in the UTNotifications settings");
                return;
            }
            if (strExtractStringFromBundle == null) {
                strExtractStringFromBundle = "\"data/" + titleKey + "\" not present! Configure Advanced->Push Payload Format in the UTNotifications settings";
            }
            String str2 = strExtractStringFromBundle;
            if (strExtractStringFromBundle2 == null) {
                strExtractStringFromBundle2 = "\"data/" + textKey + "\" not present! Configure Advanced->Push Payload Format in the UTNotifications settings";
            }
            String str3 = strExtractStringFromBundle2;
            String strExtractStringFromBundle3 = extractStringFromBundle(bundle, notificationProfileKey);
            int iExtractIntFromBundle = extractIntFromBundle(bundle, badgeNumberKey, -1);
            int iExtractIntFromBundle2 = extractIntFromBundle(bundle, idKey, -1);
            String strExtractStringFromBundle4 = extractStringFromBundle(bundle, buttonsKey);
            if (strExtractStringFromBundle4 == null) {
                strExtractStringFromBundle4 = extractStringFromBundle(bundle, "o");
            }
            if (strExtractStringFromBundle4 != null) {
                try {
                    JSONArray jSONArray = new JSONArray(strExtractStringFromBundle4);
                    ArrayList arrayList2 = new ArrayList(jSONArray.length());
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject.has("title")) {
                            string = jSONObject.getString("title");
                            jSONObject.remove("title");
                        } else if (jSONObject.has("n")) {
                            string = jSONObject.getString("n");
                            jSONObject.remove("n");
                        } else {
                            Log.e(Manager.class.getName(), "Skipping button without a title: " + jSONObject);
                        }
                        arrayList2.add(new UTNotification.Button(string, jSONObject));
                    }
                    arrayList = arrayList2;
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            } else {
                arrayList = null;
            }
            Bundle subBundle = getSubBundle(bundle, userDataKey.split("\\/"), false);
            if (subBundle == null) {
                subBundle = bundle;
            }
            postNotification(applicationContext, new UTNotification(str, 0L, 0, str2, str3, iExtractIntFromBundle2 != -1 ? iExtractIntFromBundle2 : getNextPushNotificationId(applicationContext), bundleToMap(subBundle), strExtractStringFromBundle3, iExtractIntFromBundle, arrayList));
        }
    }

    static void postNotification(Context context, final UTNotification uTNotification) {
        final Context applicationContext = context.getApplicationContext();
        if (uTNotification.userData != null && uTNotification.userData.containsKey(IMAGE_URL)) {
            new AsyncTask<Void, Void, Bitmap>() { // from class: universal.tools.notifications.Manager.1
                /*  JADX ERROR: Types fix failed
                    java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
                    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:183)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:242)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
                    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
                    */
                /* JADX INFO: Access modifiers changed from: protected */
                /* JADX WARN: Failed to calculate best type for var: r5v1 ??
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
                 */
                /* JADX WARN: Failed to calculate best type for var: r5v1 ??
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:466)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:188)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
                 */
                /* JADX WARN: Failed to calculate best type for var: r5v2 ??
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:156)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:133)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:238)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:221)
                	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:91)
                 */
                /* JADX WARN: Failed to calculate best type for var: r5v2 ??
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.allSameListener(TypeUpdate.java:473)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
                 */
                /* JADX WARN: Failed to calculate best type for var: r5v5 ??
                java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "changeArg" is null
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:439)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.moveListener(TypeUpdate.java:447)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.runListeners(TypeUpdate.java:232)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:212)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeForSsaVar(TypeUpdate.java:183)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.updateTypeChecked(TypeUpdate.java:112)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:83)
                	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:56)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:145)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:123)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:101)
                	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:101)
                	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
                 */
                /* JADX WARN: Not initialized variable reg: 0, insn: 0x0045: MOVE (r3 I:??[OBJECT, ARRAY]) = (r0 I:??[OBJECT, ARRAY]), block:B:25:0x0045 */
                @Override // android.os.AsyncTask
                public android.graphics.Bitmap doInBackground(java.lang.Void... r5) {
                    /*
                        r4 = this;
                        r5 = 0
                        java.net.URL r0 = new java.net.URL     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L34
                        universal.tools.notifications.UTNotification r1 = r1     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L34
                        java.util.Map<java.lang.String, java.lang.String> r1 = r1.userData     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L34
                        java.lang.String r2 = "image_url"
                        java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L34
                        java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L34
                        r0.<init>(r1)     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L34
                        java.net.URLConnection r0 = r0.openConnection()     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L34
                        r1 = 1
                        r0.setDoInput(r1)     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L34
                        r0.connect()     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L34
                        java.io.InputStream r0 = r0.getInputStream()     // Catch: java.lang.Throwable -> L32 java.lang.Throwable -> L34
                        android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r0)     // Catch: java.lang.Throwable -> L30 java.lang.Throwable -> L44
                        if (r0 == 0) goto L2f
                        r0.close()     // Catch: java.lang.Throwable -> L2b
                        goto L2f
                    L2b:
                        r5 = move-exception
                        r5.printStackTrace()
                    L2f:
                        return r1
                    L30:
                        r1 = move-exception
                        goto L36
                    L32:
                        r0 = move-exception
                        goto L48
                    L34:
                        r1 = move-exception
                        r0 = r5
                    L36:
                        r1.printStackTrace()     // Catch: java.lang.Throwable -> L44
                        if (r0 == 0) goto L43
                        r0.close()     // Catch: java.lang.Throwable -> L3f
                        goto L43
                    L3f:
                        r0 = move-exception
                        r0.printStackTrace()
                    L43:
                        return r5
                    L44:
                        r5 = move-exception
                        r3 = r0
                        r0 = r5
                        r5 = r3
                    L48:
                        if (r5 == 0) goto L52
                        r5.close()     // Catch: java.lang.Throwable -> L4e
                        goto L52
                    L4e:
                        r5 = move-exception
                        r5.printStackTrace()
                    L52:
                        throw r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: universal.tools.notifications.Manager.AnonymousClass1.doInBackground(java.lang.Void[]):android.graphics.Bitmap");
                }

                /* JADX INFO: Access modifiers changed from: protected */
                @Override // android.os.AsyncTask
                public void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute((AnonymousClass1) bitmap);
                    Manager.showNotification(applicationContext, uTNotification, bitmap);
                }
            }.execute(new Void[0]);
        } else {
            showNotification(applicationContext, uTNotification, null);
        }
    }

    @SuppressLint({"NewApi"})
    public static void hideNotification(Context context, int i) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            String group = null;
            boolean z = false;
            if (Build.VERSION.SDK_INT >= 24) {
                StatusBarNotification[] activeNotifications = notificationManager.getActiveNotifications();
                int length = activeNotifications.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    }
                    StatusBarNotification statusBarNotification = activeNotifications[i2];
                    if (statusBarNotification.getId() == i && statusBarNotification.isGroup()) {
                        group = statusBarNotification.getNotification().getGroup();
                        break;
                    }
                    i2++;
                }
            }
            notificationManager.cancel(i);
            if (group != null) {
                int groupNotificationId = getGroupNotificationId(group);
                StatusBarNotification[] activeNotifications2 = notificationManager.getActiveNotifications();
                int length2 = activeNotifications2.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length2) {
                        z = true;
                        break;
                    }
                    StatusBarNotification statusBarNotification2 = activeNotifications2[i3];
                    if (statusBarNotification2.getId() != i && statusBarNotification2.getId() != groupNotificationId && statusBarNotification2.isGroup() && group.equals(statusBarNotification2.getNotification().getGroup())) {
                        break;
                    } else {
                        i3++;
                    }
                }
                if (z) {
                    notificationManager.cancel(groupNotificationId);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void onApplicationQuit() {
        Activity activity = UnityPlayer.currentActivity;
        if (activity != null) {
            m_closedActivity = new WeakReference<>(activity);
        } else {
            m_closedActivity = null;
        }
    }

    public static String getStoredScheduledNotificationIdsJson() {
        Activity activity = UnityPlayer.currentActivity;
        if (activity == null) {
            return "[]";
        }
        String storedScheduledNotificationIdsString = getStoredScheduledNotificationIdsString(activity.getApplicationContext());
        if (TextUtils.isEmpty(storedScheduledNotificationIdsString)) {
            return "[]";
        }
        return "[" + storedScheduledNotificationIdsString + "]";
    }

    static void onPushRegistered(String str, String str2) {
        if (UnityPlayer.currentActivity != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONArray.put(str2);
            UnityPlayer.UnitySendMessage("UTNotificationsManager", "_OnAndroidIdReceived", jSONArray.toString());
        }
    }

    static void onPushRegistrationFailed(String str) {
        if (UnityPlayer.currentActivity != null) {
            UnityPlayer.UnitySendMessage("UTNotificationsManager", "_OnAndroidPushRegistrationFailed", str);
        }
    }

    static boolean notificationsEnabled(Context context) {
        return context.getSharedPreferences(Manager.class.getName(), 0).getBoolean(NOTIFICATIONS_ENABLED, true);
    }

    static boolean pushNotificationsEnabled(Context context) {
        return context.getSharedPreferences(Manager.class.getName(), 0).getBoolean(PUSH_NOTIFICATIONS_ENABLED, true);
    }

    static int getNextPushNotificationId(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (m_nextPushNotificationId == -1) {
            m_nextPushNotificationId = applicationContext.getSharedPreferences(Manager.class.getName(), 0).getInt(START_ID, 0);
        }
        if (applicationContext.getSharedPreferences(Manager.class.getName(), 0).getBoolean(INCREMENTAL_ID, false)) {
            int i = m_nextPushNotificationId;
            m_nextPushNotificationId = i + 1;
            return i;
        }
        return m_nextPushNotificationId;
    }

    static int[] getStoredScheduledNotificationIds(Context context) {
        String storedScheduledNotificationIdsString = getStoredScheduledNotificationIdsString(context);
        if (storedScheduledNotificationIdsString == null || storedScheduledNotificationIdsString.isEmpty()) {
            return new int[0];
        }
        String[] strArrSplit = storedScheduledNotificationIdsString.split(",");
        int[] iArr = new int[strArrSplit.length];
        for (int i = 0; i < strArrSplit.length; i++) {
            iArr[i] = Integer.parseInt(strArrSplit[i]);
        }
        return iArr;
    }

    static void scheduleNotificationCommon(Context context, UTNotification uTNotification) {
        Context applicationContext = context.getApplicationContext();
        PendingIntent pendingIntentBuildPendingIntentForScheduledNotification = buildPendingIntentForScheduledNotification(applicationContext, uTNotification);
        long j = uTNotification.triggerAtSystemTimeMillis;
        long jCurrentTimeMillis = System.currentTimeMillis();
        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService("alarm");
        int i = applicationContext.getSharedPreferences(Manager.class.getName(), 0).getInt(SCHEDULE_TIMER_TYPE, 2);
        if (uTNotification.isRepeated()) {
            if (j < jCurrentTimeMillis) {
                j = ((j - jCurrentTimeMillis) % uTNotification.intervalSeconds) + jCurrentTimeMillis;
            }
            alarmManager.setInexactRepeating(i, triggerAtSystemTimeMillisToTimerTime(i, j, jCurrentTimeMillis), uTNotification.intervalSeconds * 1000, pendingIntentBuildPendingIntentForScheduledNotification);
        } else {
            if (j < jCurrentTimeMillis) {
                j = jCurrentTimeMillis + 1000;
            }
            long jTriggerAtSystemTimeMillisToTimerTime = triggerAtSystemTimeMillisToTimerTime(i, j, jCurrentTimeMillis);
            if (Build.VERSION.SDK_INT >= 19 && applicationContext.getSharedPreferences(Manager.class.getName(), 0).getBoolean(SCHEDULE_EXACT, false)) {
                alarmManager.setExact(i, jTriggerAtSystemTimeMillisToTimerTime, pendingIntentBuildPendingIntentForScheduledNotification);
            } else {
                alarmManager.set(i, jTriggerAtSystemTimeMillisToTimerTime, pendingIntentBuildPendingIntentForScheduledNotification);
            }
        }
        storeScheduledNotificationId(applicationContext, uTNotification);
    }

    private static Intent getIntent() {
        return intent;
    }

    static void setIntent(Intent intent2) {
        intent = intent2;
    }

    static HashMap<String, String> bundleToMap(Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return null;
        }
        HashMap<String, String> map = new HashMap<>();
        for (String str : bundle.keySet()) {
            try {
                map.put(str, bundle.get(str).toString());
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return map;
    }

    private static void hideAllNotifications(Context context) {
        try {
            ((NotificationManager) context.getSystemService("notification")).cancelAll();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void showNotification(Context context, UTNotification uTNotification, Bitmap bitmap) {
        try {
            Context applicationContext = context.getApplicationContext();
            if (notificationsEnabled(applicationContext)) {
                if (checkShowMode(applicationContext)) {
                    Notification notificationBuildNotification = buildNotification(applicationContext, uTNotification, bitmap);
                    boolean z = applicationContext.getSharedPreferences(Manager.class.getName(), 0).getBoolean(SHOW_LATEST_NOTIFICATIONS_ONLY, false);
                    if (z) {
                        hideAllNotifications(applicationContext);
                    }
                    if (uTNotification.badgeNumber >= 0) {
                        ShortcutBadger.applyNotification(applicationContext, notificationBuildNotification, uTNotification.badgeNumber);
                        setBadge(applicationContext, uTNotification.badgeNumber);
                    }
                    NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService("notification");
                    notificationManager.notify(uTNotification.id, notificationBuildNotification);
                    if (!z && Build.VERSION.SDK_INT >= 24 && !TextUtils.isEmpty(notificationBuildNotification.getGroup())) {
                        notificationManager.notify(getGroupNotificationId(notificationBuildNotification.getGroup()), buildGroupNotification(applicationContext, notificationBuildNotification));
                    }
                }
                notificationReceived(applicationContext, uTNotification);
            }
            if (isRepeatedOrNotScheduled(uTNotification)) {
                return;
            }
            clearSheduledNotificationId(applicationContext, uTNotification.id);
        } catch (Throwable th) {
            Log.e(Manager.class.getName(), th.toString());
            th.printStackTrace();
        }
    }

    private static int getGroupNotificationId(String str) {
        return -Math.abs(str.hashCode());
    }

    private static PendingIntent buildPendingIntentForScheduledNotification(Context context, UTNotification uTNotification) {
        Intent intent2 = new Intent(context, (Class<?>) AlarmBroadcastReceiver.class);
        intent2.setData(Uri.parse("custom://ut." + uTNotification.id));
        intent2.putExtra(KEY_NOTIFICATION, uTNotification.toString());
        return PendingIntent.getBroadcast(context, uTNotification.id, intent2, 134217728);
    }

    private static int setSmallIcon(Context context, Resources resources, String str, Notification.Builder builder, Profile profile, Profile profile2) {
        int smallIcon = profile.getSmallIcon(context, resources, str);
        if (smallIcon == 0 && profile != profile2) {
            smallIcon = profile2.getSmallIcon(context, resources, str);
        }
        builder.setSmallIcon(smallIcon);
        return smallIcon;
    }

    private static void setColor(Notification.Builder builder, Profile profile) {
        if (Build.VERSION.SDK_INT < 21 || profile.color == null) {
            return;
        }
        builder.setColor(profile.color.intValue());
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setColorized(true);
        }
    }

    private static int getLargeIcon(Resources resources, String str, Profile profile, Profile profile2) {
        int largeIcon = profile.getLargeIcon(resources, str);
        return (largeIcon != 0 || profile == profile2) ? largeIcon : profile2.getLargeIcon(resources, str);
    }

    private static String getSoundUri(Resources resources, String str, Profile profile, Profile profile2) {
        int soundId = profile.getSoundId(resources, str);
        if (soundId == 0 && profile != profile2) {
            soundId = profile2.getSoundId(resources, str);
            profile = profile2;
        }
        return Profile.getSoundUri(str, profile.id, soundId);
    }

    private static PendingIntent buildPendingIntent(Context context, UTNotification uTNotification, int i) {
        int i2;
        String str;
        Intent intent2 = new Intent(context, (Class<?>) NotificationIntentService.class);
        intent2.putExtra(KEY_NOTIFICATION, uTNotification.toString());
        intent2.putExtra(KEY_ID, uTNotification.id);
        Map<String, String> map = (i < 0 || i >= uTNotification.buttons.size()) ? uTNotification.userData : uTNotification.buttons.get(i).userData;
        if (map != null && map.containsKey(OPEN_URL) && (str = map.get(OPEN_URL)) != null) {
            intent2.putExtra(KEY_OPEN_URL, str);
        }
        if (i >= 0) {
            intent2.putExtra(KEY_BUTTON_INDEX, i);
            i2 = (((uTNotification.id % 104729) * 64) - 2139095040) + i;
        } else {
            i2 = uTNotification.id;
        }
        return PendingIntent.getService(context, i2, intent2, 134217728);
    }

    @SuppressLint({"NewApi"})
    private static Notification buildNotification(Context context, UTNotification uTNotification, Bitmap bitmap) {
        Resources resources = context.getResources();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Manager.class.getName(), 0);
        Profile profile = new Profile("__default_profile", sharedPreferences);
        Profile profile2 = (uTNotification.notificationProfile == null || "__default_profile".equals(uTNotification.notificationProfile)) ? profile : new Profile(uTNotification.notificationProfile, sharedPreferences);
        String packageName = context.getPackageName();
        boolean z = Build.VERSION.SDK_INT >= 26;
        int largeIcon = getLargeIcon(resources, packageName, profile2, profile);
        String soundUri = z ? null : getSoundUri(resources, packageName, profile2, profile);
        PendingIntent pendingIntentBuildPendingIntent = buildPendingIntent(context, uTNotification, -1);
        Notification.Builder builder = z ? new Notification.Builder(context, profile2.id) : new Notification.Builder(context);
        builder.setContentTitle(uTNotification.title).setContentText(uTNotification.text).setContentIntent(pendingIntentBuildPendingIntent).setAutoCancel(true);
        if (!z) {
            builder.setDefaults(soundUri == null ? -1 : 6);
        }
        int smallIcon = setSmallIcon(context, resources, packageName, builder, profile2, profile);
        setColor(builder, profile2);
        if (uTNotification.buttons != null && !uTNotification.buttons.isEmpty() && Build.VERSION.SDK_INT > 19) {
            for (int i = 0; i < uTNotification.buttons.size(); i++) {
                builder.addAction(new Notification.Action.Builder(0, uTNotification.buttons.get(i).title, buildPendingIntent(context, uTNotification, i)).build());
            }
        }
        if (!z && profile2.highPriority) {
            builder.setPriority(1);
        }
        if (Build.VERSION.SDK_INT < 21) {
            if (largeIcon == 0) {
                largeIcon = smallIcon;
            }
            builder.setLargeIcon(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, largeIcon), resources.getDimensionPixelSize(android.R.dimen.notification_large_icon_width), resources.getDimensionPixelSize(android.R.dimen.notification_large_icon_height), false));
        } else if (largeIcon != 0) {
            builder.setLargeIcon(BitmapFactory.decodeResource(resources, largeIcon));
        }
        if (soundUri != null) {
            builder.setSound(Uri.parse(soundUri));
        }
        if (bitmap != null) {
            builder.setStyle(new Notification.BigPictureStyle().bigPicture(bitmap).setBigContentTitle(uTNotification.title).setSummaryText(uTNotification.text));
        } else {
            builder.setStyle(new Notification.BigTextStyle().bigText(uTNotification.text));
        }
        if (Build.VERSION.SDK_INT >= 24) {
            switch (context.getSharedPreferences(Manager.class.getName(), 0).getInt(NOTIFICATIONS_GROUPING_MODE, 0)) {
                case 1:
                    builder.setGroup(profile2.id);
                    break;
                case 2:
                    if (uTNotification.userData != null && uTNotification.userData.containsKey("notification_group")) {
                        builder.setGroup(uTNotification.userData.get("notification_group"));
                        break;
                    }
                    break;
                case 3:
                    builder.setGroup("__ALL");
                    break;
            }
        }
        return builder.build();
    }

    @RequiresApi(api = 24)
    @SuppressLint({"NewApi"})
    private static Notification buildGroupNotification(Context context, Notification notification) {
        int resId;
        Notification.Builder builder = Build.VERSION.SDK_INT >= 26 ? new Notification.Builder(context, notification.getChannelId()) : new Notification.Builder(context);
        try {
            resId = notification.getSmallIcon().getResId();
        } catch (Throwable unused) {
            resId = android.R.drawable.btn_plus;
        }
        return builder.setSmallIcon(resId).setAutoCancel(true).setGroup(notification.getGroup()).setGroupSummary(true).build();
    }

    private static void notificationReceived(Context context, UTNotification uTNotification) throws JSONException {
        if (context.getSharedPreferences(Manager.class.getName(), 0).getBoolean(WILL_HANDLE_RECEIVED_NOTIFICATIONS, false)) {
            String receivedNotificationsPacked = readReceivedNotificationsPacked(context);
            String string = uTNotification.toString();
            if (TextUtils.isEmpty(string)) {
                return;
            }
            if (!TextUtils.isEmpty(receivedNotificationsPacked)) {
                string = receivedNotificationsPacked + ',' + string;
            }
            SharedPreferences.Editor editorEdit = context.getSharedPreferences(Manager.class.getName(), 0).edit();
            editorEdit.putString(RECEIVED_NOTIFICATIONS, string);
            editorEdit.apply();
        }
    }

    private static String readReceivedNotificationsPacked(Context context) {
        return context.getSharedPreferences(Manager.class.getName(), 0).getString(RECEIVED_NOTIFICATIONS, "");
    }

    private static String getStoredScheduledNotificationIdsString(Context context) {
        return context.getSharedPreferences(Manager.class.getName(), 0).getString(SCHEDULED_NOTIFICATION_IDS, null);
    }

    private static void storeScheduledNotificationId(Context context, UTNotification uTNotification) {
        ScheduledNotificationsRestorer.register(context, uTNotification);
        for (int i : getStoredScheduledNotificationIds(context)) {
            if (i == uTNotification.id) {
                return;
            }
        }
        String storedScheduledNotificationIdsString = getStoredScheduledNotificationIdsString(context);
        String str = (storedScheduledNotificationIdsString == null || storedScheduledNotificationIdsString.isEmpty()) ? "" + uTNotification.id : storedScheduledNotificationIdsString + "," + uTNotification.id;
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(Manager.class.getName(), 0).edit();
        editorEdit.putString(SCHEDULED_NOTIFICATION_IDS, str);
        editorEdit.apply();
    }

    private static void clearSheduledNotificationId(Context context, int i) {
        String string = Integer.toString(i);
        String storedScheduledNotificationIdsString = getStoredScheduledNotificationIdsString(context);
        if (storedScheduledNotificationIdsString != null && !storedScheduledNotificationIdsString.isEmpty()) {
            boolean z = true;
            if (storedScheduledNotificationIdsString.equals(string)) {
                storedScheduledNotificationIdsString = "";
            } else {
                if (storedScheduledNotificationIdsString.contains("," + string + ",")) {
                    storedScheduledNotificationIdsString = storedScheduledNotificationIdsString.replace("," + string + ",", ",");
                } else {
                    if (storedScheduledNotificationIdsString.startsWith(string + ",")) {
                        storedScheduledNotificationIdsString = storedScheduledNotificationIdsString.substring(string.length() + 1);
                    } else {
                        if (storedScheduledNotificationIdsString.endsWith("," + string)) {
                            storedScheduledNotificationIdsString = storedScheduledNotificationIdsString.substring(0, storedScheduledNotificationIdsString.length() - (string.length() + 1));
                        } else {
                            z = false;
                        }
                    }
                }
            }
            if (z) {
                SharedPreferences.Editor editorEdit = context.getSharedPreferences(Manager.class.getName(), 0).edit();
                editorEdit.putString(SCHEDULED_NOTIFICATION_IDS, storedScheduledNotificationIdsString);
                editorEdit.apply();
            }
        }
        ScheduledNotificationsRestorer.cancel(context, i);
    }

    private static boolean checkShowMode(Context context) {
        switch (context.getApplicationContext().getSharedPreferences(Manager.class.getName(), 0).getInt(SHOW_NOTIFICATIONS_MODE, 0)) {
            case 0:
                if (UnityPlayer.currentActivity == null || !UnityPlayer.currentActivity.hasWindowFocus()) {
                }
                break;
            case 1:
                Activity activity = UnityPlayer.currentActivity;
                if (activity != null && activity.getWindow() != null) {
                    if (m_closedActivity == null || !activity.equals(m_closedActivity.get())) {
                    }
                }
                break;
        }
        return true;
    }

    private static void setBadge(Context context, int i) {
        if (i != 0) {
            ShortcutBadger.applyCount(context, i);
        } else {
            ShortcutBadger.removeCount(context);
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(Manager.class.getName(), 0).edit();
        editorEdit.putInt(KEY_BADGE_NUMBER, i);
        editorEdit.apply();
    }

    private static int getBadge(Context context) {
        return context.getSharedPreferences(Manager.class.getName(), 0).getInt(KEY_BADGE_NUMBER, 0);
    }

    private static Bundle getSubBundle(Bundle bundle, String[] strArr, boolean z) {
        Bundle bundleJsonToBundle;
        if (strArr != null && strArr.length != 0) {
            if (strArr.length != 1 || (strArr[0] != null && !strArr[0].isEmpty())) {
                int length = z ? strArr.length - 1 : strArr.length;
                if (length <= 0) {
                    return bundle;
                }
                Object obj = bundle.get(strArr[0]);
                if ((obj instanceof String) && (bundleJsonToBundle = jsonToBundle((String) obj)) != null) {
                    bundle.putBundle(strArr[0], bundleJsonToBundle);
                }
                for (int i = 0; i < length; i++) {
                    try {
                        bundle = bundle.getBundle(strArr[i]);
                        if (bundle == null) {
                            return null;
                        }
                    } catch (Throwable unused) {
                        return null;
                    }
                }
                return bundle;
            }
        }
        return bundle;
    }

    private static String extractStringFromBundle(Bundle bundle, String str) {
        String[] strArrSplit = str.split("/");
        Bundle subBundle = getSubBundle(bundle, strArrSplit, true);
        if (subBundle == null) {
            return null;
        }
        try {
            String str2 = strArrSplit[strArrSplit.length - 1];
            String string = subBundle.getString(str2);
            subBundle.remove(str2);
            return string;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static int extractIntFromBundle(Bundle bundle, String str, int i) {
        int i2;
        String[] strArrSplit = str.split("/");
        Bundle subBundle = getSubBundle(bundle, strArrSplit, true);
        if (subBundle != null) {
            String str2 = strArrSplit[strArrSplit.length - 1];
            try {
                i2 = subBundle.getInt(str2, i);
            } catch (Throwable unused) {
                i2 = i;
            }
            if (i2 == i) {
                try {
                    i = Integer.parseInt(subBundle.getString(str2));
                } catch (Throwable unused2) {
                }
                subBundle.remove(str2);
            } else {
                i = i2;
                subBundle.remove(str2);
            }
        }
        return i;
    }

    private static Bundle jsonToBundle(String str) {
        try {
            return jsonToBundle(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bundle jsonToBundle(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object obj = jSONObject.get(next);
            if (obj instanceof String) {
                bundle.putString(next, (String) obj);
            } else if (obj instanceof Integer) {
                bundle.putInt(next, ((Integer) obj).intValue());
            } else if (obj instanceof Long) {
                bundle.putInt(next, (int) ((Long) obj).longValue());
            } else if (obj instanceof JSONObject) {
                bundle.putBundle(next, jsonToBundle((JSONObject) obj));
            } else {
                bundle.putString(next, obj.toString());
            }
        }
        return bundle;
    }

    private static void registerProfile(Context context, SharedPreferences.Editor editor, String str) throws JSONException {
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                new Profile(jSONObject.getString(AgooConstants.MESSAGE_ID), jSONObject.getString("name"), jSONObject.getString("description"), jSONObject.getBoolean("high_priority"), jSONObject.has("color") ? Integer.valueOf(jSONObject.getInt("color")) : null).saveInSharedPreferences(editor).registerChannel(context);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static boolean isRepeatedOrNotScheduled(UTNotification uTNotification) {
        return !uTNotification.isScheduled() || uTNotification.isRepeated();
    }

    private static long triggerInSecondsToTriggerAtSystemTimeMillis(int i) {
        if (i == 0) {
            return 0L;
        }
        return System.currentTimeMillis() + (i * 1000);
    }

    private static long triggerAtSystemTimeMillisToElapsedRealtime(long j, long j2) {
        return (SystemClock.elapsedRealtime() + j) - j2;
    }

    private static long triggerAtSystemTimeMillisToTimerTime(int i, long j, long j2) {
        switch (i) {
            case 0:
            case 1:
                return j;
            case 2:
            case 3:
                return triggerAtSystemTimeMillisToElapsedRealtime(j, j2);
            default:
                Log.e(Manager.class.getName(), "Unexpected timer type: " + i);
                return j;
        }
    }

    private static String decodeBase64(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        try {
            return new String(Base64.decode(str, 0));
        } catch (Throwable th) {
            th.printStackTrace();
            return str;
        }
    }
}
