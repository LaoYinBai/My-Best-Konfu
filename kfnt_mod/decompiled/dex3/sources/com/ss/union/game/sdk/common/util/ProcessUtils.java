package com.ss.union.game.sdk.common.util;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ProcessUtils {
    private static String a = "";

    public static boolean isMainProcess(Context context) {
        return TextUtils.equals(context.getPackageName(), getCurrentProcessName());
    }

    public static String getCurrentProcessName() {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        a = a();
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        a = b();
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        a = c();
        return a;
    }

    private static String a() {
        if (Build.VERSION.SDK_INT >= 28) {
            return Application.getProcessName();
        }
        return null;
    }

    private static String b() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            if (objInvoke instanceof String) {
                return (String) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String c() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        Context context = GlobalApplicationUtils.getContext();
        if (context == null) {
            return "";
        }
        int iMyPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null) {
            return "";
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    public static boolean appIsForeground() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        Context context = GlobalApplicationUtils.getContext();
        if (context == null || (runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) == null) {
            return false;
        }
        String packageName = context.getPackageName();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.importance == 100 && runningAppProcessInfo.processName.equals(packageName)) {
                return true;
            }
        }
        return false;
    }
}
