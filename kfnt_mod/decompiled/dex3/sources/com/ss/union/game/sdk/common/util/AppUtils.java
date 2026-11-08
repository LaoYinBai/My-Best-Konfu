package com.ss.union.game.sdk.common.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppUtils {
    private static volatile Boolean a;

    private AppUtils() {
    }

    public static boolean isInstalledApp(String str) {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void openSystemBrowser(String str) {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openAppSetting() {
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return;
        }
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            intent.setFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
            context.startActivity(intent);
        } catch (Throwable unused) {
        }
    }

    public static boolean isDebug(Context context) {
        if (a == null) {
            synchronized (AppUtils.class) {
                if (a == null) {
                    try {
                        a = Boolean.valueOf((context.getApplicationInfo().flags & 2) != 0);
                    } catch (Exception unused) {
                        a = false;
                    }
                }
            }
        }
        return a.booleanValue();
    }

    public static Drawable getAppIcon(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationIcon(str);
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public static String getAppName(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            return String.valueOf(packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 0)));
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static Intent getLaunchIntentForPackage(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return null;
        }
        if (!launchIntentForPackage.hasCategory("android.intent.category.LAUNCHER")) {
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(2097152);
        launchIntentForPackage.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
        return launchIntentForPackage;
    }
}
