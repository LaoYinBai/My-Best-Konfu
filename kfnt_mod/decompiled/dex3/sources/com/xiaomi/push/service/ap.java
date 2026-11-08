package com.xiaomi.push.service;

import android.app.Notification;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ap {

    /* renamed from: a, reason: collision with other field name */
    private static final String[] f889a = {"com.mi.globalbrowser", "com.android.browser"};
    private static String a = null;

    public static int a(ContentResolver contentResolver) {
        if (Build.VERSION.SDK_INT >= 17) {
            try {
                return Settings.Global.getInt(contentResolver, "user_aggregate", 0);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.m22a("get user aggregate failed, " + e);
            }
        }
        return 0;
    }

    static int a(Context context, String str) {
        return com.xiaomi.push.g.b(context, str);
    }

    public static String a(Notification notification) {
        Object objA;
        String string = null;
        try {
            if (Build.VERSION.SDK_INT >= 19 && notification.extras != null) {
                string = notification.extras.getString("target_package");
            }
            if (TextUtils.isEmpty(string) && (objA = com.xiaomi.push.bh.a(notification, "extraNotification")) != null) {
                return (String) com.xiaomi.push.bh.a(objA, "getTargetPkg", new Object[0]);
            }
        } catch (Exception unused) {
        }
        return string;
    }

    static void a(Notification notification, String str) {
        try {
            if (Build.VERSION.SDK_INT >= 19 && notification.extras != null) {
                notification.extras.putString("target_package", str);
            }
            Object objA = com.xiaomi.push.bh.a(notification, "extraNotification");
            if (objA != null) {
                com.xiaomi.push.bh.a(objA, "setTargetPkg", str);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static void a(android.content.Context r4, android.content.Intent r5) {
        /*
            r0 = -1
        L1:
            if (r0 >= 0) goto L6
            java.lang.String r1 = com.xiaomi.push.service.ap.a
            goto La
        L6:
            java.lang.String[] r1 = com.xiaomi.push.service.ap.f889a
            r1 = r1[r0]
        La:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L35
            r5.setPackage(r1)
            android.content.pm.PackageManager r2 = r4.getPackageManager()     // Catch: java.lang.Exception -> L20
            r3 = 65536(0x10000, float:9.1835E-41)
            android.content.pm.ResolveInfo r2 = r2.resolveActivity(r5, r3)     // Catch: java.lang.Exception -> L20
            if (r2 == 0) goto L35
            goto L3d
        L20:
            r1 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "not found xm browser:"
            r2.append(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.xiaomi.channel.commonutils.logger.b.m22a(r1)
        L35:
            int r0 = r0 + 1
            java.lang.String[] r1 = com.xiaomi.push.service.ap.f889a
            int r1 = r1.length
            if (r0 < r1) goto L1
            r1 = 0
        L3d:
            r5.setPackage(r1)
            com.xiaomi.push.service.ap.a = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.ap.a(android.content.Context, android.content.Intent):void");
    }

    public static boolean a(Notification.Builder builder, boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setGroupAlertBehavior(z ? 2 : 1);
            return true;
        }
        com.xiaomi.channel.commonutils.logger.b.b("not support setGroupAlertBehavior");
        return false;
    }

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m602a(ContentResolver contentResolver) {
        int iA = a(contentResolver);
        return iA == 1 || iA == 2;
    }
}
