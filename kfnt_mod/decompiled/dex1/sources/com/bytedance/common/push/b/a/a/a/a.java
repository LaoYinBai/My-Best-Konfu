package com.bytedance.common.push.b.a.a.a;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.bytedance.common.push.utility.i;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public static String f3820a = ":push";

    /* renamed from: b, reason: collision with root package name */
    public static boolean f3821b = false;

    /* renamed from: c, reason: collision with root package name */
    public static boolean f3822c = false;
    private static String d = null;
    private static String e = null;
    private static boolean f = true;

    public static Intent a(Context context, String str) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return launchIntentForPackage;
        }
        if (!launchIntentForPackage.hasCategory("android.intent.category.LAUNCHER")) {
            launchIntentForPackage.addCategory("android.intent.category.LAUNCHER");
            com.bytedance.common.push.utility.a.b("ToolUtils", "add category LAUNCHER in launch intent");
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(2097152);
        launchIntentForPackage.addFlags(268435456);
        return launchIntentForPackage;
    }

    public static boolean a() {
        if (!f3822c) {
            try {
                if (Class.forName("miui.os.Build") != null) {
                    f3821b = true;
                    f3822c = true;
                    return f3821b;
                }
            } catch (Exception unused) {
            }
            f3822c = true;
        }
        return f3821b;
    }

    public static boolean a(Context context) {
        String strC = c(context);
        return strC != null && strC.endsWith(f3820a);
    }

    public static boolean b(Context context) {
        String strC = c(context);
        return (strC == null || !strC.contains(":")) && strC != null && strC.equals(context.getPackageName());
    }

    public static String c(Context context) {
        String str = e;
        if (!i.a(str)) {
            return str;
        }
        try {
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    if (com.bytedance.common.push.utility.a.b()) {
                        com.bytedance.common.push.utility.a.b("Process", "processName = " + runningAppProcessInfo.processName);
                    }
                    e = runningAppProcessInfo.processName;
                    return e;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        e = b();
        return e;
    }

    private static String b() throws Throwable {
        BufferedReader bufferedReader;
        BufferedReader bufferedReader2 = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
            try {
                StringBuilder sb = new StringBuilder();
                while (true) {
                    int i = bufferedReader.read();
                    if (i <= 0) {
                        break;
                    }
                    sb.append((char) i);
                }
                if (com.bytedance.common.push.utility.a.b()) {
                    com.bytedance.common.push.utility.a.b("Process", "get processName = " + sb.toString());
                }
                String string = sb.toString();
                try {
                    bufferedReader.close();
                } catch (Exception unused) {
                }
                return string;
            } catch (Throwable unused2) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception unused3) {
                    }
                }
                return null;
            }
        } catch (Throwable th) {
            th = th;
        }
    }
}
