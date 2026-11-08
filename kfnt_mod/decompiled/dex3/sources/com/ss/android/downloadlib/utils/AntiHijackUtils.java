package com.ss.android.downloadlib.utils;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.ss.android.c.c;
import com.ss.android.downloadlib.DownloadComponentManager;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.socialbase.appdownloader.util.RomUtils;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AntiHijackUtils {
    public static String computeFileTTMd5(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return c.a(new File(str));
    }

    public static String computeInstalledPkgTTMd5(String str) {
        return computeFileTTMd5(getApkFilePath(str));
    }

    public static int checkTTMd5(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return 5;
        }
        return c.a(str, new File(str2));
    }

    public static int checkFileTTMd5(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return 5;
        }
        return c.a(new File(str), new File(str2));
    }

    public static String getApkFilePath(String str) throws PackageManager.NameNotFoundException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = GlobalInfo.getContext().getPackageManager().getApplicationInfo(str, 0);
            if (applicationInfo != null) {
                return applicationInfo.sourceDir;
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public static void tryHookAms() {
        if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_HOOK, 0) != 1) {
            return;
        }
        DownloadComponentManager.getInstance().submitScheduledTask(new Runnable() { // from class: com.ss.android.downloadlib.utils.AntiHijackUtils.1
            @Override // java.lang.Runnable
            public void run() {
                RomUtils.getName();
                AntiHijackUtils.hookAms();
            }
        }, DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void hookAms() {
        Field declaredField;
        Class<?> cls;
        try {
            if (Build.VERSION.SDK_INT < 26) {
                declaredField = Class.forName("android.app.ActivityManagerNative").getDeclaredField("gDefault");
            } else {
                declaredField = Class.forName("android.app.ActivityManager").getDeclaredField("IActivityManagerSingleton");
            }
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            Field declaredField2 = Class.forName("android.util.Singleton").getDeclaredField("mInstance");
            declaredField2.setAccessible(true);
            Object obj2 = declaredField2.get(obj);
            if (obj2 == null || (cls = Class.forName("android.app.IActivityManager")) == null) {
                return;
            }
            declaredField2.set(obj, Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, new AmsInvocationHandler(obj2)));
        } catch (Throwable unused) {
        }
    }

    public static void appendArgsBeforeInstall(Object[] objArr) {
        if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_HOOK, 0) == 1 && (objArr[1] instanceof String) && (objArr[2] instanceof Intent)) {
            Intent intent = (Intent) objArr[2];
            if ("android.intent.action.VIEW".equals(intent.getAction()) && com.ss.android.socialbase.downloader.constants.DownloadConstants.MIME_APK.equals(intent.getType())) {
                if (RomUtils.isVivo()) {
                    String strOptString = GlobalInfo.getDownloadSettings().optString(DownloadSettingKeys.KEY_VIVO_ARG, "com.android.settings");
                    if ("null".equals(strOptString)) {
                        return;
                    }
                    objArr[1] = strOptString;
                    return;
                }
                if (RomUtils.isOppo()) {
                    String strOptString2 = GlobalInfo.getDownloadSettings().optString(DownloadSettingKeys.KEY_OPPO_ARG1, "com." + com.ss.android.socialbase.downloader.constants.DownloadConstants.LOWER_OPPO + ".market");
                    if (!"null".equals(strOptString2)) {
                        objArr[1] = strOptString2;
                    }
                    String strOptString3 = GlobalInfo.getDownloadSettings().optString(DownloadSettingKeys.KEY_OPPO_ARG2, "com.android.browser");
                    String strOptString4 = GlobalInfo.getDownloadSettings().optString(DownloadSettingKeys.KEY_OPPO_ARG3, "m.store." + com.ss.android.socialbase.downloader.constants.DownloadConstants.LOWER_OPPO + "mobile.com");
                    StringBuilder sb = new StringBuilder();
                    sb.append(com.ss.android.socialbase.downloader.constants.DownloadConstants.LOWER_OPPO);
                    sb.append("_extra_pkg_name");
                    intent.putExtra(sb.toString(), strOptString3);
                    intent.putExtra("refererHost", strOptString4);
                    if (GlobalInfo.getDownloadSettings().optInt(DownloadSettingKeys.KEY_OPPO_ARG4, 0) == 1) {
                        Intent intent2 = new Intent();
                        intent2.putExtra(com.ss.android.socialbase.downloader.constants.DownloadConstants.LOWER_OPPO + "_extra_pkg_name", strOptString3);
                        intent2.putExtra("refererHost", strOptString4);
                        intent.putExtra("android.intent.extra.INTENT", intent2);
                        return;
                    }
                    return;
                }
                if (RomUtils.isEmui()) {
                    String strOptString5 = GlobalInfo.getDownloadSettings().optString(DownloadSettingKeys.KEY_HUAWEI_ARG1, RomUtils.MARKET_PKG_NAME_EMUI);
                    if (!"null".equals(strOptString5)) {
                        objArr[1] = strOptString5;
                    }
                    intent.putExtra("caller_package", GlobalInfo.getDownloadSettings().optString(DownloadSettingKeys.KEY_HUAWEI_ARG2, RomUtils.MARKET_PKG_NAME_EMUI));
                }
            }
        }
    }

    private static class AmsInvocationHandler implements InvocationHandler {
        private Object iActivityManagerObject;

        private AmsInvocationHandler(Object obj) {
            this.iActivityManagerObject = obj;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
            try {
                if ("startActivity".contains(method.getName())) {
                    AntiHijackUtils.appendArgsBeforeInstall(objArr);
                }
            } catch (Throwable unused) {
            }
            return method.invoke(this.iActivityManagerObject, objArr);
        }
    }
}
