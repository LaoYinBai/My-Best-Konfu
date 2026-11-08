package com.kwad.sdk.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.meizu.cloud.pushsdk.constants.PushConstants;
import java.lang.reflect.Method;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class al {
    private static String a = "";

    public static String a() {
        return Build.VERSION.SDK_INT >= 28 ? Application.getProcessName() : "";
    }

    public static String a(@NonNull Context context) {
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
        a = b(context);
        return a;
    }

    public static String b() {
        try {
            Method declaredMethod = Class.forName("android.app.ActivityThread", false, Application.class.getClassLoader()).getDeclaredMethod("currentProcessName", new Class[0]);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, new Object[0]);
            return objInvoke instanceof String ? (String) objInvoke : "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String b(@NonNull Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (context == null) {
            return "";
        }
        int iMyPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(PushConstants.INTENT_ACTIVITY_NAME);
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
}
