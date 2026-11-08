package com.ss.android.common.applog;

import android.os.Build;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RootUtils {
    private static final String TAG = "com.ss.android.common.applog.RootUtils";

    public static boolean checkRootMethod1() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    public static boolean checkRootMethod2() {
        try {
            if (new File("/system/bin/su").exists()) {
                return true;
            }
            return new File("/system/xbin/su").exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean checkRootMethod3() {
        try {
            return new File("/system/app/Superuser.apk").exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3();
    }
}
