package com.meizu.cloud.pushsdk.notification;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.meizu.cloud.pushinternal.DebugLogger;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import com.meizu.cloud.pushsdk.handler.MessageV3;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSetting;
import com.meizu.cloud.pushsdk.notification.model.AdvanceSettingEx;
import com.meizu.cloud.pushsdk.util.MinSdkChecker;
import com.meizu.cloud.pushsdk.util.MzSystemUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a implements c {
    protected final Context a;
    protected final PushNotificationBuilder b;
    private final NotificationManager c;
    private final Handler d;

    protected a(Context context, PushNotificationBuilder pushNotificationBuilder) {
        this.b = pushNotificationBuilder;
        this.a = context;
        this.d = new Handler(context.getMainLooper());
        this.c = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
    }

    private Notification a(MessageV3 messageV3, PendingIntent pendingIntent, PendingIntent pendingIntent2) throws IllegalAccessException, PackageManager.NameNotFoundException, IllegalArgumentException {
        Notification.Builder builder = new Notification.Builder(this.a);
        a(builder, messageV3, pendingIntent, pendingIntent2);
        c(builder, messageV3);
        b(builder, messageV3);
        a(builder, messageV3);
        d(builder, messageV3);
        Notification notificationBuild = MinSdkChecker.isSupportNotificationBuild() ? builder.build() : builder.getNotification();
        c(notificationBuild, messageV3);
        a(notificationBuild, messageV3);
        b(notificationBuild, messageV3);
        return notificationBuild;
    }

    private PendingIntent a(MessageV3 messageV3, String str) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE, messageV3);
        intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_PRIVATE);
        intent.setClassName(str, MzSystemUtils.findReceiver(this.a, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, str));
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        intent.setFlags(32);
        return PendingIntent.getBroadcast(this.a, 0, intent, 1073741824);
    }

    private void a(Notification.Builder builder, MessageV3 messageV3, PendingIntent pendingIntent, PendingIntent pendingIntent2) {
        int statusBarIcon;
        builder.setContentTitle(messageV3.getTitle());
        builder.setContentText(messageV3.getContent());
        builder.setTicker(messageV3.getTitle());
        builder.setAutoCancel(true);
        if (MinSdkChecker.isSupportSendNotification()) {
            builder.setVisibility(1);
        }
        if (!MinSdkChecker.isSupportSetDrawableSmallIcon()) {
            if (this.b != null && this.b.getStatusBarIcon() != 0) {
                statusBarIcon = this.b.getStatusBarIcon();
            }
            builder.setSmallIcon(statusBarIcon);
            builder.setContentIntent(pendingIntent);
            builder.setDeleteIntent(pendingIntent2);
        }
        Icon iconB = b(messageV3.getUploadDataPackageName());
        if (iconB != null) {
            builder.setSmallIcon(iconB);
            builder.setContentIntent(pendingIntent);
            builder.setDeleteIntent(pendingIntent2);
        } else {
            DebugLogger.e("AbstractPushNotification", "cannot get " + messageV3.getUploadDataPackageName() + " smallIcon");
        }
        statusBarIcon = com.meizu.cloud.pushsdk.notification.c.c.l(this.a);
        builder.setSmallIcon(statusBarIcon);
        builder.setContentIntent(pendingIntent);
        builder.setDeleteIntent(pendingIntent2);
    }

    @TargetApi(23)
    private Icon b(String str) {
        try {
            int identifier = this.a.getPackageManager().getResourcesForApplication(str).getIdentifier(PushConstants.MZ_PUSH_NOTIFICATION_SMALL_ICON, "drawable", str);
            if (identifier == 0) {
                return null;
            }
            DebugLogger.i("AbstractPushNotification", "get " + str + " smallIcon success resId " + identifier);
            return Icon.createWithResource(str, identifier);
        } catch (Exception e) {
            DebugLogger.e("AbstractPushNotification", "cannot load smallIcon form package " + str + " Error message " + e.getMessage());
            return null;
        }
    }

    private String b(Context context, String str) throws PackageManager.NameNotFoundException {
        CharSequence applicationLabel;
        try {
            PackageManager packageManager = context.getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(str, 0);
            if (applicationInfo == null || (applicationLabel = packageManager.getApplicationLabel(applicationInfo)) == null) {
                return null;
            }
            return (String) applicationLabel;
        } catch (PackageManager.NameNotFoundException unused) {
            DebugLogger.e("AbstractPushNotification", "can not find " + str + " application info");
            return null;
        }
    }

    private PendingIntent c(MessageV3 messageV3) {
        return a(messageV3, (!messageV3.getWhiteList() || MzSystemUtils.isExistReceiver(this.a, messageV3.getUploadDataPackageName(), PushConstants.MZ_PUSH_ON_MESSAGE_ACTION)) ? messageV3.getUploadDataPackageName() : messageV3.getPackageName());
    }

    private void c(Notification.Builder builder, MessageV3 messageV3) {
        boolean zIsSound;
        AdvanceSetting advanceSetting = messageV3.getAdvanceSetting();
        AdvanceSettingEx advanceSettingEx = messageV3.getAdvanceSettingEx();
        if (advanceSetting == null || advanceSettingEx == null) {
            return;
        }
        if (TextUtils.isEmpty(advanceSettingEx.getSoundTitle())) {
            zIsSound = advanceSetting.getNotifyType().isSound();
        } else {
            Uri uriB = com.meizu.cloud.pushsdk.notification.c.b.b(this.a, advanceSettingEx.getSoundTitle());
            if (uriB != null) {
                DebugLogger.e("AbstractPushNotification", "advance setting builder, sound:" + uriB);
                builder.setSound(uriB);
                zIsSound = false;
            } else {
                zIsSound = true;
            }
        }
        if (advanceSetting.getNotifyType() != null) {
            boolean zIsVibrate = advanceSetting.getNotifyType().isVibrate();
            boolean zIsLights = advanceSetting.getNotifyType().isLights();
            if (zIsVibrate || zIsLights || zIsSound) {
                int i = zIsVibrate ? 2 : 0;
                if (zIsLights) {
                    i |= 4;
                }
                if (zIsSound) {
                    i |= 1;
                }
                DebugLogger.e("AbstractPushNotification", "advance setting builder, defaults:" + i);
                builder.setDefaults(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("advance setting builder, ongoing:");
        sb.append(!advanceSetting.isClearNotification());
        DebugLogger.e("AbstractPushNotification", sb.toString());
        builder.setOngoing(!advanceSetting.isClearNotification());
        if (MinSdkChecker.isSupportNotificationBuild()) {
            DebugLogger.e("AbstractPushNotification", "advance setting builder, priority:" + advanceSettingEx.getPriorityDisplay());
            builder.setPriority(advanceSettingEx.getPriorityDisplay());
        }
    }

    @SuppressLint({"NewApi"})
    private void c(Notification notification, MessageV3 messageV3) throws IllegalAccessException, PackageManager.NameNotFoundException, IllegalArgumentException {
        String strB;
        Bundle bundle;
        String str;
        com.meizu.cloud.pushsdk.notification.c.b.a(notification, true);
        com.meizu.cloud.pushsdk.notification.c.b.a(notification, e(messageV3));
        notification.extras.putString(PushConstants.EXTRA_ORIGINAL_NOTIFICATION_PACKAGE_NAME, messageV3.getUploadDataPackageName());
        notification.extras.putString(PushConstants.EXTRA_FLYME_GREEN_NOTIFICATION_SETTING, a(messageV3));
        notification.extras.putString(PushConstants.NOTIFICATION_EXTRA_TASK_ID, messageV3.getTaskId());
        notification.extras.putString(PushConstants.NOTIFICATION_EXTRA_SEQ_ID, messageV3.getSeqId());
        notification.extras.putString(PushConstants.NOTIFICATION_EXTRA_DEVICE_ID, messageV3.getDeviceId());
        notification.extras.putString(PushConstants.NOTIFICATION_EXTRA_PUSH_TIMESTAMP, messageV3.getPushTimestamp());
        if (TextUtils.isEmpty(this.b.getAppLabel())) {
            strB = b(this.a, messageV3.getUploadDataPackageName());
            DebugLogger.e("AbstractPushNotification", "current package " + messageV3.getUploadDataPackageName() + " label is " + strB);
            if (TextUtils.isEmpty(strB)) {
                return;
            }
            bundle = notification.extras;
            str = PushConstants.EXTRA_SUBSTITUTE_APP_NAME;
        } else {
            DebugLogger.e("AbstractPushNotification", "set app label " + this.b.getAppLabel());
            bundle = notification.extras;
            str = PushConstants.EXTRA_SUBSTITUTE_APP_NAME;
            strB = this.b.getAppLabel();
        }
        bundle.putString(str, strB);
    }

    private PendingIntent d(MessageV3 messageV3) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra(PushConstants.MZ_PUSH_PRIVATE_MESSAGE, messageV3);
        intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_DELETE);
        intent.setClassName(messageV3.getPackageName(), MzSystemUtils.findReceiver(this.a, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, messageV3.getPackageName()));
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        return PendingIntent.getBroadcast(this.a, 0, intent, 1073741824);
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0109  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(android.app.Notification.Builder r11, com.meizu.cloud.pushsdk.handler.MessageV3 r12) {
        /*
            Method dump skipped, instructions count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.meizu.cloud.pushsdk.notification.a.d(android.app.Notification$Builder, com.meizu.cloud.pushsdk.handler.MessageV3):void");
    }

    private PendingIntent e(MessageV3 messageV3) {
        Intent intent = new Intent();
        intent.setData(Uri.parse("custom://" + System.currentTimeMillis()));
        intent.putExtra(PushConstants.MZ_PUSH_NOTIFICATION_STATE_MESSAGE, messageV3.getNotificationMessage());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_TASK_ID, messageV3.getTaskId());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_SEQ_ID, messageV3.getSeqId());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_DEVICE_ID, messageV3.getDeviceId());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_PUSH_TIMESTAMP, messageV3.getPushTimestamp());
        intent.putExtra(PushConstants.NOTIFICATION_EXTRA_SHOW_PACKAGE_NAME, messageV3.getUploadDataPackageName());
        intent.putExtra(PushConstants.MZ_PUSH_WHITE_LIST, messageV3.getWhiteList());
        intent.putExtra(PushConstants.MZ_PUSH_DELAYED_REPORT_MILLIS, messageV3.getDelayedReportMillis());
        intent.putExtra("method", PushConstants.MZ_PUSH_MESSAGE_METHOD_ACTION_NOTIFICATION_STATE);
        intent.setClassName(messageV3.getPackageName(), MzSystemUtils.findReceiver(this.a, PushConstants.MZ_PUSH_ON_MESSAGE_ACTION, messageV3.getPackageName()));
        intent.setAction(PushConstants.MZ_PUSH_ON_MESSAGE_ACTION);
        return PendingIntent.getBroadcast(this.a, 0, intent, 1073741824);
    }

    protected Bitmap a(Context context, String str) throws PackageManager.NameNotFoundException {
        BitmapDrawable bitmapDrawable;
        try {
            Drawable applicationIcon = context.getPackageManager().getApplicationIcon(str);
            Bitmap bitmap = null;
            if (Build.VERSION.SDK_INT < 26 || (applicationIcon instanceof BitmapDrawable)) {
                bitmapDrawable = (BitmapDrawable) applicationIcon;
            } else if (applicationIcon instanceof AdaptiveIconDrawable) {
                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(applicationIcon.getIntrinsicWidth(), applicationIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapCreateBitmap);
                applicationIcon.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                applicationIcon.draw(canvas);
                bitmapDrawable = null;
                bitmap = bitmapCreateBitmap;
            } else {
                bitmapDrawable = null;
            }
            return bitmap == null ? bitmapDrawable.getBitmap() : bitmap;
        } catch (Exception e) {
            DebugLogger.i("AbstractPushNotification", "get app icon error " + e.getMessage());
            return ((BitmapDrawable) context.getApplicationInfo().loadIcon(context.getPackageManager())).getBitmap();
        }
    }

    protected Bitmap a(String str) {
        com.meizu.cloud.pushsdk.b.a.c cVarB = com.meizu.cloud.pushsdk.b.a.a(str).a().b();
        if (!cVarB.b() || cVarB.a() == null) {
            DebugLogger.i("AbstractPushNotification", "ANRequest On other Thread down load largeIcon " + str + "image fail");
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ANRequest On other Thread down load largeIcon ");
        sb.append(str);
        sb.append("image ");
        sb.append(cVarB.a() != null ? FirebaseAnalytics.Param.SUCCESS : "fail");
        DebugLogger.i("AbstractPushNotification", sb.toString());
        return (Bitmap) cVarB.a();
    }

    protected String a(MessageV3 messageV3) throws JSONException {
        String string = null;
        try {
            if (!TextUtils.isEmpty(messageV3.getNotificationMessage())) {
                string = new JSONObject(messageV3.getNotificationMessage()).getJSONObject("data").getJSONObject(PushConstants.EXTRA).getString("fns");
            }
        } catch (Exception e) {
            DebugLogger.e("AbstractPushNotification", "parse flyme notification setting error " + e.getMessage());
        }
        DebugLogger.i("AbstractPushNotification", "current FlymeGreen notification setting is " + string);
        return string;
    }

    protected void a(Notification.Builder builder, MessageV3 messageV3) {
    }

    protected void a(Notification notification, MessageV3 messageV3) {
    }

    protected boolean a() {
        return Thread.currentThread() == this.a.getMainLooper().getThread();
    }

    protected void b(Notification.Builder builder, MessageV3 messageV3) {
    }

    protected void b(Notification notification, MessageV3 messageV3) {
    }

    @Override // com.meizu.cloud.pushsdk.notification.c
    @SuppressLint({"NewApi"})
    public void b(MessageV3 messageV3) throws IllegalAccessException, PackageManager.NameNotFoundException, IllegalArgumentException {
        Notification notificationA = a(messageV3, c(messageV3), d(messageV3));
        int iAbs = Math.abs((int) System.currentTimeMillis());
        com.meizu.cloud.pushsdk.notification.model.a aVarA = com.meizu.cloud.pushsdk.notification.model.a.a(messageV3);
        if (aVarA != null && aVarA.a() != 0) {
            iAbs = aVarA.a();
            DebugLogger.e("AbstractPushNotification", "server notify id " + iAbs);
            if (!TextUtils.isEmpty(aVarA.b())) {
                int iH = com.meizu.cloud.pushsdk.util.b.h(this.a, messageV3.getUploadDataPackageName(), aVarA.b());
                DebugLogger.e("AbstractPushNotification", "notifyKey " + aVarA.b() + " preference notifyId is " + iH);
                if (iH != 0) {
                    DebugLogger.e("AbstractPushNotification", "use preference notifyId " + iH + " and cancel it");
                    this.c.cancel(iH);
                }
                DebugLogger.e("AbstractPushNotification", "store new notifyId " + iAbs + " by notifyKey " + aVarA.b());
                com.meizu.cloud.pushsdk.util.b.b(this.a, messageV3.getUploadDataPackageName(), aVarA.b(), iAbs);
            }
        }
        DebugLogger.e("AbstractPushNotification", "current notify id " + iAbs);
        if (messageV3.isDiscard()) {
            if (com.meizu.cloud.pushsdk.util.b.c(this.a, messageV3.getPackageName()) == 0) {
                com.meizu.cloud.pushsdk.util.b.a(this.a, messageV3.getPackageName(), iAbs);
                DebugLogger.i("AbstractPushNotification", "no notification show so put notification id " + iAbs);
            }
            if (!TextUtils.isEmpty(messageV3.getTaskId())) {
                if (com.meizu.cloud.pushsdk.util.b.d(this.a, messageV3.getPackageName()) == 0) {
                    com.meizu.cloud.pushsdk.util.b.b(this.a, messageV3.getPackageName(), Integer.valueOf(messageV3.getTaskId()).intValue());
                } else {
                    if (Integer.valueOf(messageV3.getTaskId()).intValue() < com.meizu.cloud.pushsdk.util.b.d(this.a, messageV3.getPackageName())) {
                        DebugLogger.i("AbstractPushNotification", "current package " + messageV3.getPackageName() + " task id " + messageV3.getTaskId() + " don't show notification");
                        return;
                    }
                    com.meizu.cloud.pushsdk.util.b.b(this.a, messageV3.getPackageName(), Integer.valueOf(messageV3.getTaskId()).intValue());
                    iAbs = com.meizu.cloud.pushsdk.util.b.c(this.a, messageV3.getPackageName());
                }
            }
            DebugLogger.i("AbstractPushNotification", "current package " + messageV3.getPackageName() + " notificationId=" + iAbs + " taskId=" + messageV3.getTaskId());
        }
        this.c.notify(iAbs, notificationA);
    }
}
