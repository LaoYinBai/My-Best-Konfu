package com.kwad.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.api.core.fragment.FileProvider;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.File;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ag {
    public static String a(String str) {
        PackageInfo packageArchiveInfo;
        if (!new File(str).exists()) {
            com.kwad.sdk.core.d.a.d("PackageUtil", "cannot save package, download apk is not exists.");
            return null;
        }
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null || (packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1)) == null) {
            return null;
        }
        return packageArchiveInfo.applicationInfo.packageName;
    }

    public static void a(String str, String str2) {
        String str3;
        String str4;
        com.kwad.sdk.core.d.a.d("PackageUtil", "saveDownloadFile " + str2);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            str3 = "PackageUtil";
            str4 = "cannot save package, has no download apk info.";
        } else {
            File file = new File(str);
            if (file.exists()) {
                Context context = KsAdSDKImpl.get().getContext();
                if (context == null) {
                    return;
                }
                as.b(context, str2, file.length());
                try {
                    as.a(context, str2, c.b(file));
                    return;
                } catch (Exception e) {
                    com.kwad.sdk.core.d.a.a(e);
                    return;
                }
            }
            str3 = "PackageUtil";
            str4 = "cannot save package, download apk is not exists.";
        }
        com.kwad.sdk.core.d.a.d(str3, str4);
    }

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static int b(@Nullable Context context, String str) {
        if (context == null || str == null) {
            return -1;
        }
        if ((context.getApplicationInfo().targetSdkVersion >= 29 && Build.VERSION.SDK_INT >= 29 && !Environment.isExternalStorageLegacy()) || ContextCompat.checkSelfPermission(context, "android.permission.READ_EXTERNAL_STORAGE") != 0) {
            return -1;
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + str);
        return (file.exists() && file.isDirectory()) ? 1 : 0;
    }

    public static int b(String str, String str2) throws PackageManager.NameNotFoundException {
        String str3;
        String str4;
        com.kwad.sdk.core.d.a.d("PackageUtil", "isPackageChanged " + str + " packageName " + str2);
        Context context = KsAdSDKImpl.get().getContext();
        if (context == null) {
            return 0;
        }
        long jB = as.b(context, str);
        String strC = as.c(context, str);
        if (TextUtils.isEmpty(strC) || jB <= 0) {
            str3 = "PackageUtil";
            str4 = "cannot judge package, has no download apk info.";
        } else {
            try {
                PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(str2, 0);
                if (TextUtils.isEmpty(str2) || packageInfo == null || packageInfo.applicationInfo == null || TextUtils.isEmpty(packageInfo.applicationInfo.publicSourceDir)) {
                    str3 = "PackageUtil";
                    str4 = "cannot judge package, cannot get installed apk info.";
                } else {
                    File file = new File(packageInfo.applicationInfo.publicSourceDir);
                    if (!file.exists()) {
                        str3 = "PackageUtil";
                        str4 = "cannot judge package, insgtalled apk is not exists.";
                    } else {
                        if (jB != file.length()) {
                            return 1;
                        }
                        if (TextUtils.isEmpty(strC)) {
                            str3 = "PackageUtil";
                            str4 = "cannot judge package, cannot calculate md5 of download file.";
                        } else {
                            String strB = c.b(file);
                            if (!TextUtils.isEmpty(strB)) {
                                return strC.equalsIgnoreCase(strB) ? 2 : 1;
                            }
                            str3 = "PackageUtil";
                            str4 = "cannot judge package, cannot calculate md5 of installed file.";
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                com.kwad.sdk.core.d.a.a(e);
                return 0;
            }
        }
        com.kwad.sdk.core.d.a.d(str3, str4);
        return 0;
    }

    public static boolean c(@Nullable Context context, @Nullable String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return false;
            }
            launchIntentForPackage.setFlags(337641472);
            context.startActivity(launchIntentForPackage);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void d(Context context, String str) {
        Uri uriFromFile;
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            File file = new File(str);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(268435456);
            intent.addFlags(3);
            if (Build.VERSION.SDK_INT >= 24) {
                uriFromFile = FileProvider.getUriForFile(context, context.getPackageName() + ".adFileProvider", file);
            } else {
                uriFromFile = Uri.fromFile(file);
            }
            intent.setDataAndType(uriFromFile, "application/vnd.android.package-archive");
            Iterator<ResolveInfo> it = context.getPackageManager().queryIntentActivities(intent, C.DEFAULT_BUFFER_SEGMENT_SIZE).iterator();
            while (it.hasNext()) {
                context.grantUriPermission(it.next().activityInfo.packageName, uriFromFile, 3);
            }
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }
}
