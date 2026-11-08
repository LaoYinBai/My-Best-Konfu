package com.vivo.push.util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.umeng.message.MsgConstant;
import java.security.PublicKey;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class y {
    private static Boolean c;
    private static String[] a = {"com.vivo.push.sdk.RegistrationReceiver", "com.vivo.push.sdk.service.PushService", "com.vivo.push.sdk.service.CommonJobService"};
    private static String[] b = {MsgConstant.PERMISSION_INTERNET, MsgConstant.PERMISSION_READ_PHONE_STATE, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, "android.permission.WRITE_SETTINGS", "android.permission.VIBRATE", MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, MsgConstant.PERMISSION_ACCESS_WIFI_STATE, MsgConstant.PERMISSION_WAKE_LOCK, "android.permission.GET_ACCOUNTS", "com.bbk.account.permission.READ_ACCOUNTINFO", "android.permission.AUTHENTICATE_ACCOUNTS", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", MsgConstant.PERMISSION_GET_TASKS};
    private static String[] d = {"com.vivo.push.sdk.service.CommandService", "com.vivo.push.sdk.service.CommonJobService"};
    private static String[] e = {"com.vivo.push.sdk.RegistrationReceiver"};
    private static String[] f = new String[0];

    public static boolean a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        if (c != null) {
            return c.booleanValue();
        }
        if (context == null) {
            n.d("Utility", "isPushProcess context is null");
            return false;
        }
        String strB = r.b(context);
        if (context != null && context.getPackageName() != null && context.getPackageName().equals(strB)) {
            Boolean bool = true;
            c = bool;
            return bool.booleanValue();
        }
        int iMyPid = Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        String str = null;
        if (activityManager != null && (runningAppProcesses = activityManager.getRunningAppProcesses()) != null && runningAppProcesses.size() != 0) {
            Iterator<ActivityManager.RunningAppProcessInfo> it = runningAppProcesses.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ActivityManager.RunningAppProcessInfo next = it.next();
                if (next.pid == iMyPid) {
                    str = next.processName;
                    break;
                }
            }
        }
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Boolean boolValueOf = Boolean.valueOf(str.contains(":pushservice"));
        c = boolValueOf;
        return boolValueOf.booleanValue();
    }

    public static long b(Context context) {
        String strB = r.b(context);
        if (TextUtils.isEmpty(strB)) {
            n.a("Utility", "systemPushPkgName is null");
            return -1L;
        }
        return a(context, strB);
    }

    public static long a(Context context, String str) throws PackageManager.NameNotFoundException {
        Object objB = b(context, str, "com.vivo.push.sdk_version");
        if (objB == null) {
            objB = b(context, str, "sdk_version");
        }
        if (objB != null) {
            try {
                return Long.parseLong(objB.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
                n.a("Utility", "getSdkVersionCode error ", e2);
                return -1L;
            }
        }
        n.a("Utility", "getSdkVersionCode sdk version is null");
        return -1L;
    }

    public static String b(Context context, String str) throws PackageManager.NameNotFoundException {
        Object objB = b(context, str, "com.vivo.push.app_id");
        if (objB != null) {
            return objB.toString();
        }
        Object objB2 = b(context, str, "app_id");
        return objB2 != null ? objB2.toString() : "";
    }

    private static Object b(Context context, String str, String str2) throws PackageManager.NameNotFoundException {
        if (context == null || TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            Bundle bundle = applicationInfo != null ? applicationInfo.metaData : null;
            if (bundle != null) {
                return bundle.get(str2);
            }
            return null;
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static Object a(String str, String str2) throws Exception {
        Class<?> cls = Class.forName(str);
        return cls.getField(str2).get(cls);
    }

    public static void c(Context context) throws PackageManager.NameNotFoundException, VivoPushException {
        String string;
        n.d("Utility", "check PushService AndroidManifest declearation !");
        String strB = r.b(context);
        boolean zA = r.a(context, context.getPackageName(), "com.vivo.pushservice.action.RECEIVE");
        boolean zA2 = r.a(context, context.getPackageName(), "com.vivo.pushservice.action.METHOD");
        boolean zA3 = r.a(context, context.getPackageName(), "com.vivo.pushclient.action.RECEIVE");
        if (zA2) {
            a = new String[]{"com.vivo.push.sdk.RegistrationReceiver", "com.vivo.push.sdk.service.PushService", "com.vivo.push.sdk.service.CommonJobService"};
            b = new String[]{MsgConstant.PERMISSION_INTERNET, MsgConstant.PERMISSION_READ_PHONE_STATE, MsgConstant.PERMISSION_ACCESS_NETWORK_STATE, "android.permission.WRITE_SETTINGS", "android.permission.VIBRATE", MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE, MsgConstant.PERMISSION_ACCESS_WIFI_STATE, MsgConstant.PERMISSION_WAKE_LOCK, "android.permission.GET_ACCOUNTS", "com.bbk.account.permission.READ_ACCOUNTINFO", "android.permission.AUTHENTICATE_ACCOUNTS", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", MsgConstant.PERMISSION_GET_TASKS};
            d = new String[]{"com.vivo.push.sdk.service.CommandService", "com.vivo.push.sdk.service.CommonJobService"};
            e = new String[]{"com.vivo.push.sdk.RegistrationReceiver"};
        } else {
            if (!zA3 && !zA) {
                throw new VivoPushException("AndroidManifest.xml中receiver配置项错误，详见接入文档");
            }
            if (zA3) {
                d = new String[]{"com.vivo.push.sdk.service.CommandClientService"};
            } else {
                d = new String[]{"com.vivo.push.sdk.service.CommandService"};
            }
            e = new String[0];
            a = new String[0];
            if (zA) {
                b = new String[]{MsgConstant.PERMISSION_INTERNET, "android.permission.WRITE_SETTINGS"};
            } else {
                b = new String[]{MsgConstant.PERMISSION_INTERNET};
            }
        }
        if (zA || zA2) {
            long jA = a(context, context.getPackageName());
            long j = context.getPackageName().equals(strB) ? 1305L : 305L;
            if (jA == -1) {
                throw new VivoPushException("AndroidManifest.xml中未配置sdk_version");
            }
            if (jA != j) {
                throw new VivoPushException("AndroidManifest.xml中sdk_version配置项错误，请配置当前sdk_version版本为:" + j);
            }
        }
        f(context);
        e(context, strB);
        c(context, strB);
        d(context, strB);
        String packageName = context.getPackageName();
        Object objB = b(context, packageName, "com.vivo.push.api_key");
        if (objB != null) {
            string = objB.toString();
        } else {
            Object objB2 = b(context, packageName, "api_key");
            string = objB2 != null ? objB2.toString() : "";
        }
        if (TextUtils.isEmpty(string)) {
            throw new VivoPushException("com.vivo.push.api_key is null");
        }
        if (TextUtils.isEmpty(b(context, context.getPackageName()))) {
            throw new VivoPushException("com.vivo.push.app_id is null");
        }
        if ((zA || zA2) && a(context, context.getPackageName()) == -1) {
            throw new VivoPushException("sdkversion is null");
        }
        if (zA2) {
            a(context, "com.vivo.pushservice.action.METHOD", "com.vivo.push.sdk.RegistrationReceiver", true);
            a(context, "com.vivo.pushservice.action.PUSH_SERVICE", "com.vivo.push.sdk.service.PushService", false);
        }
    }

    private static void c(Context context, String str) throws VivoPushException {
        try {
            if (context.getPackageManager() == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            ServiceInfo[] serviceInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4).services;
            if (serviceInfoArr == null) {
                throw new VivoPushException("serviceInfos is null");
            }
            for (String str2 : d) {
                a(str2, serviceInfoArr, str);
            }
        } catch (Exception e2) {
            throw new VivoPushException("error " + e2.getMessage());
        }
    }

    private static void d(Context context, String str) throws VivoPushException {
        if (f.length <= 0) {
            return;
        }
        try {
            if (context.getPackageManager() == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
            if (activityInfoArr == null) {
                throw new VivoPushException("activityInfos is null");
            }
            for (String str2 : f) {
                a(str2, activityInfoArr, str);
            }
        } catch (Exception e2) {
            throw new VivoPushException("error " + e2.getMessage());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x002c, code lost:
    
        r3 = r3 + 1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void f(android.content.Context r8) throws com.vivo.push.util.VivoPushException {
        /*
            android.content.pm.PackageManager r0 = r8.getPackageManager()     // Catch: java.lang.Exception -> L60
            if (r0 == 0) goto L58
            java.lang.String r8 = r8.getPackageName()     // Catch: java.lang.Exception -> L60
            r1 = 4096(0x1000, float:5.74E-42)
            android.content.pm.PackageInfo r8 = r0.getPackageInfo(r8, r1)     // Catch: java.lang.Exception -> L60
            java.lang.String[] r8 = r8.requestedPermissions     // Catch: java.lang.Exception -> L60
            if (r8 == 0) goto L50
            java.lang.String[] r0 = com.vivo.push.util.y.b     // Catch: java.lang.Exception -> L60
            int r1 = r0.length     // Catch: java.lang.Exception -> L60
            r2 = 0
            r3 = 0
        L19:
            if (r3 >= r1) goto L4f
            r4 = r0[r3]     // Catch: java.lang.Exception -> L60
            int r5 = r8.length     // Catch: java.lang.Exception -> L60
            r6 = 0
        L1f:
            if (r6 >= r5) goto L2f
            r7 = r8[r6]     // Catch: java.lang.Exception -> L60
            boolean r7 = r4.equals(r7)     // Catch: java.lang.Exception -> L60
            if (r7 != 0) goto L2c
            int r6 = r6 + 1
            goto L1f
        L2c:
            int r3 = r3 + 1
            goto L19
        L2f:
            com.vivo.push.util.VivoPushException r0 = new com.vivo.push.util.VivoPushException     // Catch: java.lang.Exception -> L60
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L60
            java.lang.String r2 = "permission : "
            r1.<init>(r2)     // Catch: java.lang.Exception -> L60
            r1.append(r4)     // Catch: java.lang.Exception -> L60
            java.lang.String r2 = "  check fail : "
            r1.append(r2)     // Catch: java.lang.Exception -> L60
            java.lang.String r8 = java.util.Arrays.toString(r8)     // Catch: java.lang.Exception -> L60
            r1.append(r8)     // Catch: java.lang.Exception -> L60
            java.lang.String r8 = r1.toString()     // Catch: java.lang.Exception -> L60
            r0.<init>(r8)     // Catch: java.lang.Exception -> L60
            throw r0     // Catch: java.lang.Exception -> L60
        L4f:
            return
        L50:
            com.vivo.push.util.VivoPushException r8 = new com.vivo.push.util.VivoPushException     // Catch: java.lang.Exception -> L60
            java.lang.String r0 = "Permissions is null!"
            r8.<init>(r0)     // Catch: java.lang.Exception -> L60
            throw r8     // Catch: java.lang.Exception -> L60
        L58:
            com.vivo.push.util.VivoPushException r8 = new com.vivo.push.util.VivoPushException     // Catch: java.lang.Exception -> L60
            java.lang.String r0 = "localPackageManager is null"
            r8.<init>(r0)     // Catch: java.lang.Exception -> L60
            throw r8     // Catch: java.lang.Exception -> L60
        L60:
            r8 = move-exception
            com.vivo.push.util.VivoPushException r0 = new com.vivo.push.util.VivoPushException
            java.lang.String r8 = r8.getMessage()
            r0.<init>(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.y.f(android.content.Context):void");
    }

    private static void a(String str, ComponentInfo[] componentInfoArr, String str2) throws VivoPushException {
        for (ComponentInfo componentInfo : componentInfoArr) {
            if (str.equals(componentInfo.name)) {
                if (!componentInfo.enabled) {
                    throw new VivoPushException(componentInfo.name + " module Push-SDK need is illegitmacy !");
                }
                a(componentInfo, str2);
                return;
            }
        }
        throw new VivoPushException(str + " module Push-SDK need is not exist");
    }

    private static void a(ComponentInfo componentInfo, String str) throws VivoPushException {
        if (componentInfo.applicationInfo.packageName.equals(str)) {
            return;
        }
        for (String str2 : a) {
            if (str2.equals(componentInfo.name) && !componentInfo.processName.contains(":pushservice")) {
                throw new VivoPushException("module : " + componentInfo.name + " process :" + componentInfo.processName + "  check process fail");
            }
        }
    }

    private static void e(Context context, String str) throws VivoPushException {
        try {
            if (context.getPackageManager() == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 2).receivers;
            if (activityInfoArr == null) {
                throw new VivoPushException("receivers is null");
            }
            for (String str2 : e) {
                a(str2, activityInfoArr, str);
            }
        } catch (Exception e2) {
            throw new VivoPushException(e2.getMessage());
        }
    }

    private static void a(Context context, String str, String str2, boolean z) throws VivoPushException {
        Intent intent = new Intent(str);
        intent.setPackage(context.getPackageName());
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                throw new VivoPushException("localPackageManager is null");
            }
            if (z) {
                List<ResolveInfo> listQueryBroadcastReceivers = packageManager.queryBroadcastReceivers(intent, 576);
                if (listQueryBroadcastReceivers == null || listQueryBroadcastReceivers.size() <= 0) {
                    throw new VivoPushException("checkModule " + intent + " has no receivers");
                }
                Iterator<ResolveInfo> it = listQueryBroadcastReceivers.iterator();
                while (it.hasNext()) {
                    if (str2.equals(it.next().activityInfo.name)) {
                        return;
                    }
                }
                throw new VivoPushException(str2 + " is missing");
            }
            List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 576);
            if (listQueryIntentServices == null || listQueryIntentServices.size() <= 0) {
                throw new VivoPushException("checkModule " + intent + " has no services");
            }
            for (ResolveInfo resolveInfo : listQueryIntentServices) {
                if (str2.equals(resolveInfo.serviceInfo.name)) {
                    if (resolveInfo.serviceInfo.exported) {
                        return;
                    }
                    throw new VivoPushException(resolveInfo.serviceInfo.name + " exported is false");
                }
            }
            throw new VivoPushException(str2 + " is missing");
        } catch (Exception e2) {
            n.a("Utility", "error  " + e2.getMessage());
            throw new VivoPushException("checkModule error" + e2.getMessage());
        }
    }

    public static String b(String str, String str2) {
        String str3;
        try {
            str3 = (String) Class.forName("android.os.SystemProperties").getMethod(MonitorConstants.CONNECT_TYPE_GET, String.class).invoke(null, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            str3 = str2;
        }
        return (str3 == null || str3.length() == 0) ? str2 : str3;
    }

    public static PublicKey d(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(com.vivo.push.h.a, null, null, null, null);
        if (cursorQuery == null) {
            return null;
        }
        while (cursorQuery.moveToNext()) {
            try {
                try {
                    if ("pushkey".equals(cursorQuery.getString(cursorQuery.getColumnIndex("name")))) {
                        String string = cursorQuery.getString(cursorQuery.getColumnIndex("value"));
                        n.d("Utility", "result key : " + string);
                        return s.a(string);
                    }
                } finally {
                    try {
                        cursorQuery.close();
                    } catch (Exception unused) {
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            cursorQuery.close();
        } catch (Exception unused2) {
        }
        return null;
    }

    public static void a(Context context, Intent intent) {
        String strB = r.b(context);
        String stringExtra = intent.getStringExtra("client_pkgname");
        if (TextUtils.isEmpty(strB)) {
            n.a("Utility", "illegality abe adapter : push pkg is null");
            return;
        }
        if (TextUtils.isEmpty(stringExtra)) {
            n.a("Utility", "illegality abe adapter : src pkg is null");
            return;
        }
        if (strB.equals(context.getPackageName())) {
            n.a("Utility", "illegality abe adapter : abe is not pushservice");
            return;
        }
        if (!strB.equals(stringExtra)) {
            n.d("Utility", "proxy to core : intent pkg : " + intent.getPackage() + " ; src pkg : " + stringExtra + " ; push pkg : " + strB);
            intent.setPackage(strB);
            intent.setClassName(strB, "com.vivo.push.sdk.service.PushService");
            context.startService(intent);
            return;
        }
        n.a("Utility", "illegality abe adapter : pushPkg = " + strB + " ; srcPkg = " + stringExtra);
    }

    public static boolean e(Context context) throws Throwable {
        Cursor cursor = null;
        try {
            try {
                try {
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e3) {
            n.a("Utility", "close", e3);
        }
        if (context == null) {
            n.a("Utility", "context is null");
            return false;
        }
        String packageName = context.getPackageName();
        Cursor cursorQuery = context.getContentResolver().query(com.vivo.push.h.b, null, "pushVersion = ? and appPkgName = ? and appCode = ? ", new String[]{"305", packageName, String.valueOf(context.getPackageManager().getPackageInfo(packageName, 0).versionCode)}, null);
        try {
        } catch (Exception e4) {
            e = e4;
            cursor = cursorQuery;
            n.a("Utility", "isSupport", e);
            if (cursor != null) {
                cursor.close();
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            cursor = cursorQuery;
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e5) {
                    n.a("Utility", "close", e5);
                }
            }
            throw th;
        }
        if (cursorQuery == null) {
            n.a("Utility", "cursor is null");
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Exception e6) {
                    n.a("Utility", "close", e6);
                }
            }
            return false;
        }
        if (!cursorQuery.moveToFirst() || (cursorQuery.getInt(cursorQuery.getColumnIndex(PointCategory.PERMISSION)) & 1) == 0) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return false;
        }
        if (cursorQuery != null) {
            try {
                cursorQuery.close();
            } catch (Exception e7) {
                n.a("Utility", "close", e7);
            }
        }
        return true;
    }

    public static boolean a(Context context, String str, String str2) throws Throwable {
        Cursor cursor = null;
        try {
            try {
                try {
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e3) {
            n.a("Utility", "close", e3);
        }
        if (context == null) {
            n.a("Utility", "context is null");
            return false;
        }
        Cursor cursorQuery = context.getContentResolver().query(com.vivo.push.h.c, null, "appPkgName = ? and regId = ? sdkVersion = ? ", new String[]{str, str2, "305"}, null);
        try {
        } catch (Exception e4) {
            cursor = cursorQuery;
            e = e4;
            n.a("Utility", "isOverdue", e);
            if (cursor != null) {
                cursor.close();
            }
            return false;
        } catch (Throwable th2) {
            cursor = cursorQuery;
            th = th2;
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e5) {
                    n.a("Utility", "close", e5);
                }
            }
            throw th;
        }
        if (cursorQuery == null) {
            n.a("Utility", "cursor is null");
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Exception e6) {
                    n.a("Utility", "close", e6);
                }
            }
            return false;
        }
        if (!cursorQuery.moveToFirst()) {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return false;
        }
        boolean z = Boolean.parseBoolean(cursorQuery.getString(cursorQuery.getColumnIndex("clientState")));
        if (cursorQuery != null) {
            try {
                cursorQuery.close();
            } catch (Exception e7) {
                n.a("Utility", "close", e7);
            }
        }
        return z;
    }
}
