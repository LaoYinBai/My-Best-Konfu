package com.kwad.sdk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kuaishou.weapon.adsdk.DeviceInfo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class InstalledAppInfoManager {

    public static class AppPackageInfo implements Serializable {
        private static final long serialVersionUID = -324393456884895874L;
        public String appName;
        public long firstInstallTime;
        public boolean isSystemApp;
        public long lastUpdateTime;
        public String packageName;
        public int reportMethod;
        public String versionName;
    }

    public static AppPackageInfo a(@NonNull PackageInfo packageInfo, @Nullable PackageManager packageManager) {
        AppPackageInfo appPackageInfo = new AppPackageInfo();
        appPackageInfo.packageName = packageInfo.packageName;
        if (packageInfo.applicationInfo != null) {
            appPackageInfo.isSystemApp = a(packageInfo.applicationInfo) || b(packageInfo.applicationInfo);
        }
        appPackageInfo.versionName = packageInfo.versionName;
        appPackageInfo.firstInstallTime = packageInfo.firstInstallTime;
        appPackageInfo.lastUpdateTime = packageInfo.lastUpdateTime;
        if (packageManager != null && packageInfo.applicationInfo != null) {
            try {
                appPackageInfo.appName = packageManager.getApplicationLabel(packageInfo.applicationInfo).toString();
            } catch (Throwable th) {
                com.kwad.sdk.core.d.a.b(th);
            }
        }
        return appPackageInfo;
    }

    @NonNull
    public static JSONArray a(Context context) {
        return a(b(context));
    }

    @NonNull
    private static JSONArray a(@NonNull Map<String, AppPackageInfo> map) {
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                AppPackageInfo appPackageInfo = map.get(it.next());
                if (appPackageInfo != null && !TextUtils.isEmpty(appPackageInfo.packageName)) {
                    t.a(jSONArray, a(appPackageInfo));
                }
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.b(e);
        }
        return jSONArray;
    }

    public static JSONObject a(AppPackageInfo appPackageInfo) {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "pkgName", appPackageInfo.packageName);
        t.a(jSONObject, "system_app", appPackageInfo.isSystemApp ? 1 : 0);
        t.a(jSONObject, "appVersion", appPackageInfo.versionName);
        t.a(jSONObject, "firstInstallTime", appPackageInfo.firstInstallTime);
        t.a(jSONObject, "lastUpdateTime", appPackageInfo.lastUpdateTime);
        t.a(jSONObject, "reportMethod", appPackageInfo.reportMethod);
        t.a(jSONObject, "appName", appPackageInfo.appName);
        return jSONObject;
    }

    private static boolean a(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 1) != 0;
    }

    @NonNull
    public static JSONArray[] a(Context context, List<String> list) {
        JSONArray[] jSONArrayArr = new JSONArray[2];
        if (context != null && list != null && !list.isEmpty()) {
            ArrayList<String> arrayList = new ArrayList();
            if (!ao.m()) {
                if (com.kwad.sdk.core.config.b.a(16L)) {
                    return jSONArrayArr;
                }
                arrayList.addAll(list);
            } else if (ao.n() != null) {
                HashSet hashSet = new HashSet(list);
                HashSet hashSet2 = new HashSet(ao.n());
                HashSet hashSet3 = new HashSet(hashSet);
                hashSet3.retainAll(hashSet2);
                arrayList.addAll(hashSet3);
            }
            HashMap map = new HashMap();
            HashMap map2 = new HashMap();
            for (String str : arrayList) {
                try {
                    PackageManager packageManager = context.getPackageManager();
                    PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                    if (packageInfo != null) {
                        AppPackageInfo appPackageInfoA = a(packageInfo, packageManager);
                        map.put(appPackageInfoA.packageName, appPackageInfoA);
                    } else {
                        AppPackageInfo appPackageInfo = new AppPackageInfo();
                        appPackageInfo.packageName = str;
                        map2.put(appPackageInfo.packageName, appPackageInfo);
                    }
                } catch (Exception unused) {
                    AppPackageInfo appPackageInfo2 = new AppPackageInfo();
                    appPackageInfo2.packageName = str;
                    map2.put(appPackageInfo2.packageName, appPackageInfo2);
                }
            }
            jSONArrayArr[0] = a(map);
            jSONArrayArr[1] = a(map2);
        }
        return jSONArrayArr;
    }

    @NonNull
    public static Map<String, AppPackageInfo> b(Context context) throws PackageManager.NameNotFoundException {
        HashMap map = new HashMap();
        if (context == null) {
            return map;
        }
        PackageManager packageManager = context.getPackageManager();
        if (ao.m()) {
            if (ao.n() != null) {
                Iterator<String> it = ao.n().iterator();
                while (it.hasNext()) {
                    try {
                        PackageInfo packageInfo = packageManager.getPackageInfo(it.next(), 0);
                        if (packageInfo != null) {
                            AppPackageInfo appPackageInfoA = a(packageInfo, packageManager);
                            appPackageInfoA.reportMethod = 3;
                            map.put(appPackageInfoA.packageName, appPackageInfoA);
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
            return map;
        }
        if (com.kwad.sdk.core.config.b.a(16L)) {
            return map;
        }
        try {
            List<String> listB = h.b(context);
            if (com.kwad.sdk.b.a.booleanValue()) {
                for (String str : DeviceInfo.queryInstalledAppList(context)) {
                    if (listB != null && !listB.isEmpty()) {
                        listB.remove(str);
                    }
                    PackageInfo packageInfo2 = packageManager.getPackageInfo(str, 0);
                    if (packageInfo2 != null) {
                        AppPackageInfo appPackageInfoA2 = a(packageInfo2, packageManager);
                        appPackageInfoA2.reportMethod = 1;
                        map.put(appPackageInfoA2.packageName, appPackageInfoA2);
                    }
                }
            } else {
                Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
                intent.addCategory("android.intent.category.LAUNCHER");
                for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentActivities(intent, 32)) {
                    if (resolveInfo != null && resolveInfo.activityInfo != null && !TextUtils.isEmpty(resolveInfo.activityInfo.packageName)) {
                        String str2 = resolveInfo.activityInfo.packageName;
                        if (listB != null && !listB.isEmpty()) {
                            listB.remove(str2);
                        }
                        PackageInfo packageInfo3 = packageManager.getPackageInfo(str2, 0);
                        if (packageInfo3 != null) {
                            AppPackageInfo appPackageInfoA3 = a(packageInfo3, packageManager);
                            appPackageInfoA3.reportMethod = 1;
                            map.put(appPackageInfoA3.packageName, appPackageInfoA3);
                        }
                    }
                }
            }
            if (listB != null && !listB.isEmpty()) {
                Iterator<String> it2 = listB.iterator();
                while (it2.hasNext()) {
                    try {
                        PackageInfo packageInfo4 = packageManager.getPackageInfo(it2.next(), 0);
                        if (packageInfo4 != null) {
                            AppPackageInfo appPackageInfoA4 = a(packageInfo4, packageManager);
                            appPackageInfoA4.reportMethod = 2;
                            map.put(appPackageInfoA4.packageName, appPackageInfoA4);
                        }
                    } catch (Throwable unused2) {
                    }
                }
            }
        } catch (Exception unused3) {
        }
        map.putAll(b(context, com.kwad.sdk.core.config.b.f()));
        return map;
    }

    @NonNull
    private static Map<String, AppPackageInfo> b(Context context, List<String> list) throws PackageManager.NameNotFoundException {
        HashMap map = new HashMap();
        if (context != null && list != null) {
            for (String str : list) {
                try {
                    PackageManager packageManager = context.getPackageManager();
                    PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
                    if (packageInfo != null) {
                        AppPackageInfo appPackageInfoA = a(packageInfo, packageManager);
                        map.put(appPackageInfoA.packageName, appPackageInfoA);
                    }
                } catch (Exception unused) {
                }
            }
        }
        return map;
    }

    private static boolean b(ApplicationInfo applicationInfo) {
        return (applicationInfo.flags & 128) != 0;
    }
}
