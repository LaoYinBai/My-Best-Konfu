package com.huawei.hms.support.api.push.b.c;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.hms.android.HwBuildEx;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import java.util.Date;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    private static int a;

    @TargetApi(26)
    public static synchronized void a(Context context, com.huawei.hms.support.api.push.b.b.a aVar, String str) {
        int iHashCode;
        int i;
        int i2;
        int i3;
        if (context == null || aVar == null) {
            return;
        }
        HMSLog.d("PushSelfShowLog", " showNotification , the msg id = " + aVar.a());
        if (a == 0) {
            a = (context.getPackageName() + new Date().toString()).hashCode();
        }
        if (TextUtils.isEmpty(aVar.e())) {
            iHashCode = a + 1;
            a = iHashCode;
            i = a + 1;
            a = i;
            i2 = a + 1;
            a = i2;
            i3 = a + 1;
            a = i3;
        } else {
            iHashCode = (aVar.i() + aVar.e()).hashCode();
            i = a + 1;
            a = i;
            i2 = a + 1;
            a = i2;
            i3 = a + 1;
            a = i3;
        }
        int i4 = i;
        int i5 = i2;
        HMSLog.d("PushSelfShowLog", "notifyId:" + iHashCode + ",openNotifyId:" + i4 + ",delNotifyId:" + i5 + ",alarmNotifyId:" + i3);
        Notification notificationA = com.huawei.hms.support.api.push.b.d.a.a() ? a(context, aVar, str, iHashCode, i4, i5) : null;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(RemoteMessageConst.NOTIFICATION);
        if (notificationManager != null && notificationA != null) {
            if (Build.VERSION.SDK_INT >= 26) {
                notificationManager.createNotificationChannel(new NotificationChannel("HwPushChannelID", context.getString(ResourceLoaderUtil.getStringId("hms_push_channel")), 3));
            }
            notificationManager.notify(iHashCode, notificationA);
            if (aVar.f() > 0) {
                Intent intent = new Intent("com.huawei.intent.action.PUSH_DELAY_NOTIFY");
                intent.putExtra("selfshow_info", aVar.c()).putExtra("selfshow_token", aVar.d()).putExtra("selfshow_event_id", "-1").putExtra("extra_encrypt_data", str).putExtra("selfshow_notify_id", iHashCode).setPackage(context.getPackageName()).setFlags(32);
                a(context, intent, aVar.f(), i3);
                HMSLog.d("PushSelfShowLog", "setDelayAlarm alarmNotityId" + i3 + " and intent is " + intent.toURI());
            }
        }
    }

    private static PendingIntent a(Context context, com.huawei.hms.support.api.push.b.b.a aVar, String str, int i, int i2) {
        Intent intent = new Intent("com.huawei.intent.action.PUSH_DELAY_NOTIFY");
        intent.putExtra("selfshow_info", aVar.c()).putExtra("selfshow_token", aVar.d()).putExtra("selfshow_event_id", "1").putExtra("extra_encrypt_data", str).putExtra("selfshow_notify_id", i).setPackage(context.getPackageName()).setFlags(268435456);
        return PendingIntent.getBroadcast(context, i2, intent, 134217728);
    }

    private static PendingIntent b(Context context, com.huawei.hms.support.api.push.b.b.a aVar, String str, int i, int i2) {
        Intent intent = new Intent("com.huawei.intent.action.PUSH_DELAY_NOTIFY");
        intent.putExtra("selfshow_info", aVar.c()).putExtra("selfshow_token", aVar.d()).putExtra("selfshow_event_id", "2").putExtra("selfshow_notify_id", i).setPackage(context.getPackageName()).putExtra("extra_encrypt_data", str).setFlags(268435456);
        return PendingIntent.getBroadcast(context, i2, intent, 134217728);
    }

    @TargetApi(26)
    public static Notification a(Context context, com.huawei.hms.support.api.push.b.b.a aVar, String str, int i, int i2, int i3) {
        Notification.Builder builder = new Notification.Builder(context);
        a.a(context, builder, aVar);
        int i4 = context.getApplicationInfo().labelRes;
        builder.setTicker(aVar.k());
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(true);
        builder.setDefaults(1);
        if (aVar.m() == null || "".equals(aVar.m())) {
            builder.setContentTitle(context.getResources().getString(i4));
        } else {
            builder.setContentTitle(aVar.m());
        }
        builder.setContentText(aVar.k());
        builder.setContentIntent(a(context, aVar, str, i, i2));
        builder.setDeleteIntent(b(context, aVar, str, i, i3));
        Bitmap bitmapA = a.a(context, aVar);
        if (bitmapA != null) {
            builder.setLargeIcon(bitmapA);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId("HwPushChannelID");
        }
        a(context, builder, aVar);
        b(context, builder, aVar);
        return builder.getNotification();
    }

    @SuppressLint({"NewApi"})
    private static void a(Context context, Notification.Builder builder, com.huawei.hms.support.api.push.b.b.a aVar) {
        if ("com.huawei.android.pushagent".equals(context.getPackageName())) {
            Bundle bundle = new Bundle();
            String strI = aVar.i();
            if (TextUtils.isEmpty(strI)) {
                return;
            }
            bundle.putString("hw_origin_sender_package_name", strI);
            builder.setExtras(bundle);
        }
    }

    @SuppressLint({"NewApi"})
    private static void b(Context context, Notification.Builder builder, com.huawei.hms.support.api.push.b.b.a aVar) {
        if (HwBuildEx.VERSION.EMUI_SDK_INT < 11 || !com.huawei.hms.support.api.push.b.d.a.b(context)) {
            return;
        }
        Bundle bundle = new Bundle();
        String strI = aVar.i();
        HMSLog.i("PushSelfShowLog", "the package name of notification is:" + strI);
        if (!TextUtils.isEmpty(strI)) {
            String strA = com.huawei.hms.support.api.push.b.d.a.a(context, strI);
            HMSLog.i("PushSelfShowLog", "the app name is:" + strA);
            if (strA != null) {
                bundle.putCharSequence("android.extraAppName", strA);
            }
        }
        builder.setExtras(bundle);
    }

    public static void a(Context context, Intent intent, long j, int i) {
        try {
            HMSLog.d("PushSelfShowLog", "enter setDelayAlarm(intent:" + intent.toURI() + " interval:" + j + "ms, context:" + context);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
            if (alarmManager != null) {
                alarmManager.set(0, System.currentTimeMillis() + j, PendingIntent.getBroadcast(context, i, intent, 134217728));
            }
        } catch (Exception e) {
            HMSLog.w("PushSelfShowLog", "set DelayAlarm error" + e.toString());
        }
    }
}
