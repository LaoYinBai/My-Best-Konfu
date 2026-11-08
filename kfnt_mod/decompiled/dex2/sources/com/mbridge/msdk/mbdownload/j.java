package com.mbridge.msdk.mbdownload;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.shell.MBService;

/* compiled from: NotificationUtils.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class j {
    public static String a(int i, String str) {
        if (i == 0) {
            return null;
        }
        return (i + "") + ":" + str;
    }

    public static PendingIntent a(Context context, String str) {
        Intent intent = new Intent(context, (Class<?>) MBService.class);
        intent.putExtra("com.mbridge.msdk.broadcast.download.msg", str);
        return PendingIntent.getService(context, str.hashCode(), intent, 134217728);
    }

    public static void a() {
        Context contextJ = com.mbridge.msdk.foundation.controller.a.f().j();
        if (contextJ == null || o.v(contextJ) < 26 || Build.VERSION.SDK_INT < 26) {
            return;
        }
        ((NotificationManager) contextJ.getSystemService(RemoteMessageConst.NOTIFICATION)).createNotificationChannel(new NotificationChannel("download", "下载进度", 2));
    }
}
