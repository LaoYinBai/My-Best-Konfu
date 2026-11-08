package com.sigmob.sdk.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.sigmob.sdk.common.utils.m;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j {
    private static void a(Context context, Intent intent, String str) throws com.sigmob.sdk.common.c.b {
        m.a.a(context);
        m.a.a(intent);
        try {
            c(context, intent);
        } catch (Throwable th) {
            throw new com.sigmob.sdk.common.c.b(str + "\n" + th.getMessage());
        }
    }

    public static void a(Context context, Uri uri) throws com.sigmob.sdk.common.c.b {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        m.a.a(context);
        m.a.a(uri);
        if (a(context, intent)) {
            b(context, intent);
            return;
        }
        throw new com.sigmob.sdk.common.c.b("Could not handle application specific action: " + uri + "\n\tYou may be running in the emulator or another device which does not have the required application.");
    }

    public static boolean a(Context context, Intent intent) {
        try {
            return !context.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
        } catch (NullPointerException unused) {
            return false;
        }
    }

    public static void b(Context context, Intent intent) throws com.sigmob.sdk.common.c.b {
        m.a.a(context);
        m.a.a(intent);
        if (a(context, intent)) {
            intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            a(context, intent, "Unable to open intent: " + intent);
        }
    }

    private static void c(Context context, Intent intent) throws com.sigmob.sdk.common.c.b {
        m.a.a(context);
        m.a.a(intent);
        if (!(context instanceof Activity)) {
            intent.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        }
        try {
            context.startActivity(intent);
        } catch (Throwable th) {
            throw new com.sigmob.sdk.common.c.b(th);
        }
    }
}
