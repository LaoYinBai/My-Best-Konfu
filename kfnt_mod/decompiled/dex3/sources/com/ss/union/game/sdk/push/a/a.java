package com.ss.union.game.sdk.push.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.ss.union.game.sdk.common.util.MainThreadExecutor;
import com.ss.union.game.sdk.push.b.b;
import com.ss.union.game.sdk.push.b.c;
import com.ss.union.game.sdk.push.callback.OnNotificationClickListener;
import com.ss.union.game.sdk.push.callback.OnPushMessageArriveListener;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private OnNotificationClickListener a;
    private OnPushMessageArriveListener b;
    private b c;
    private c d;

    public void a(final Context context, final int i, final String str, final String str2, final String str3, final Uri uri, final long j) {
        if (this.a != null) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.push.a.a.1
                @Override // java.lang.Runnable
                public void run() {
                    a.this.a.onNotificationClick(context, i, str, str2, str3, uri, j);
                }
            });
            return;
        }
        com.ss.union.game.sdk.push.d.a.a("onNotificationClickListener is null...");
        this.c = new b(context, i, str, str2, str3, j, uri);
        a(context);
    }

    public void a(final OnNotificationClickListener onNotificationClickListener) {
        this.a = onNotificationClickListener;
        if (this.c != null) {
            com.ss.union.game.sdk.push.d.a.a("current notificationClickEntity = " + this.c.toString());
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.push.a.a.2
                @Override // java.lang.Runnable
                public void run() {
                    onNotificationClickListener.onNotificationClick(a.this.c.a, a.this.c.b, a.this.c.c, a.this.c.d, a.this.c.e, a.this.c.g, a.this.c.f);
                    a.this.c = null;
                }
            });
        }
    }

    public void a(final Context context, final int i, final String str, final String str2, final String str3, final String str4, final long j) {
        if (this.b != null) {
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.push.a.a.3
                @Override // java.lang.Runnable
                public void run() {
                    a.this.b.onPushArrive(context, i, str, str2, str3, str4, j);
                }
            });
        } else {
            com.ss.union.game.sdk.push.d.a.a("onPushMessageArriveListener is null , wait to process stickyEvent... ");
            this.d = new c(context, i, str, str2, str3, j, str4);
        }
    }

    public void a(final OnPushMessageArriveListener onPushMessageArriveListener) {
        this.b = onPushMessageArriveListener;
        if (this.d != null) {
            com.ss.union.game.sdk.push.d.a.a("current pushArriveEntity = " + this.d.toString());
            MainThreadExecutor.post(new Runnable() { // from class: com.ss.union.game.sdk.push.a.a.4
                @Override // java.lang.Runnable
                public void run() {
                    onPushMessageArriveListener.onPushArrive(a.this.d.a, a.this.d.b, a.this.d.c, a.this.d.d, a.this.d.e, a.this.d.g, a.this.d.f);
                    a.this.d = null;
                }
            });
        }
    }

    public void a(Context context) {
        try {
            context.startActivity(a(context, context.getPackageName()));
        } catch (Exception e) {
            com.ss.union.game.sdk.push.d.a.a("startLaunchActivity error : " + e);
        }
    }

    private Intent a(Context context, String str) {
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return null;
            }
            try {
                if (!launchIntentForPackage.hasCategory("android.intent.category.LAUNCHER")) {
                    launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
                }
                launchIntentForPackage.setPackage(null);
                launchIntentForPackage.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                return launchIntentForPackage;
            } catch (Exception unused) {
                return launchIntentForPackage;
            }
        } catch (Exception unused2) {
            return null;
        }
    }
}
