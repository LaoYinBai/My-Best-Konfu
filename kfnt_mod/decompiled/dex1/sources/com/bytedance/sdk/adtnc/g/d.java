package com.bytedance.sdk.adtnc.g;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static String f5117a;

    private static String a() throws Throwable {
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

    public static boolean a(Context context) {
        String strB = b(context);
        return (strB == null || !strB.contains(":")) && strB != null && strB.equals(context.getPackageName());
    }

    public static String b(Context context) {
        String str = f5117a;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    f5117a = runningAppProcessInfo.processName;
                    return f5117a;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        f5117a = a();
        return f5117a;
    }

    public static boolean c(Context context) {
        NetworkInfo[] allNetworkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            if (connectivityManager != null && (allNetworkInfo = connectivityManager.getAllNetworkInfo()) != null) {
                for (NetworkInfo networkInfo : allNetworkInfo) {
                    if (networkInfo.getState() == NetworkInfo.State.CONNECTED || networkInfo.getState() == NetworkInfo.State.CONNECTING) {
                        return true;
                    }
                }
            }
        } catch (Throwable th) {
            b.a("TNCUtil", "checkWifiAndGPRS", (Object) " check net connection exception ", th);
        }
        return false;
    }

    public static ProviderInfo[] d(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers;
        } catch (Throwable unused) {
            return null;
        }
    }
}
