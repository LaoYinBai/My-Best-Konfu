package com.ss.android.message.a;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.common.push.utility.g;
import com.bytedance.common.push.utility.i;
import com.ss.android.account.model2.BDAccountPlatformEntity;
import com.ss.android.downloadlib.utils.ToolUtils;
import com.umeng.message.entity.UMessage;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static String a = ":push";
    public static String b = ":pushservice";
    public static boolean c;
    public static boolean d;
    private static String e;
    private static Boolean f;

    public static String a(Context context) {
        String str = e;
        if (!i.a(str)) {
            return str;
        }
        try {
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
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
        e = h();
        return e;
    }

    private static String h() throws Throwable {
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

    public static String b(Context context) {
        if (context == null) {
            return null;
        }
        Object systemService = context.getSystemService("user");
        if (systemService == null) {
            com.bytedance.common.push.utility.a.e(ToolUtils.TAG, "userManager not exsit !!!");
            return null;
        }
        try {
            Object objInvoke = Process.class.getMethod("myUserHandle", (Class[]) null).invoke(Process.class, (Object[]) null);
            return String.valueOf(((Long) systemService.getClass().getMethod("getSerialNumberForUser", objInvoke.getClass()).invoke(systemService, objInvoke)).longValue());
        } catch (IllegalAccessException e2) {
            com.bytedance.common.push.utility.a.e(ToolUtils.TAG, "", e2);
            return null;
        } catch (IllegalArgumentException e3) {
            com.bytedance.common.push.utility.a.e(ToolUtils.TAG, "", e3);
            return null;
        } catch (NoSuchMethodException e4) {
            com.bytedance.common.push.utility.a.e(ToolUtils.TAG, "", e4);
            return null;
        } catch (InvocationTargetException e5) {
            com.bytedance.common.push.utility.a.e(ToolUtils.TAG, "", e5);
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static boolean a(Context context, String str) {
        if (context == null || i.a(str)) {
            return false;
        }
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean c(Context context) {
        if (f != null) {
            return f.booleanValue();
        }
        String strA = a(context);
        boolean z = false;
        if (strA != null && strA.contains(Constants.COLON_SEPARATOR)) {
            return false;
        }
        if (strA != null && strA.equals(context.getPackageName())) {
            z = true;
        }
        f = Boolean.valueOf(z);
        return f.booleanValue();
    }

    public static boolean a() {
        return Build.DISPLAY.indexOf("Flyme") >= 0 || Build.USER.equals(BDAccountPlatformEntity.PLAT_NAME_FLYME);
    }

    public static String a(String str, Map<String, String> map) {
        ArrayList arrayList = new ArrayList();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                arrayList.add(new Pair(entry.getKey(), entry.getValue()));
            }
        }
        return a(str, arrayList);
    }

    public static String a(String str, List<Pair<String, String>> list) {
        StringBuilder sb = new StringBuilder(str);
        if (str.indexOf(63) < 0) {
            sb.append("?");
        } else {
            sb.append("&");
        }
        sb.append(g.a(list, "UTF-8"));
        return sb.toString();
    }

    public static int d(Context context) {
        if (context == null) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            try {
                return ((Boolean) com.bytedance.common.push.utility.d.a.a((NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION)).a("areNotificationsEnabled").a()).booleanValue() ? 1 : 0;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        try {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i = applicationInfo.uid;
            com.bytedance.common.push.utility.d.a aVarA = com.bytedance.common.push.utility.d.a.a(appOpsManager);
            return ((Integer) aVarA.a("checkOpNoThrow", new Class[]{Integer.TYPE, Integer.TYPE, String.class}, new Object[]{Integer.valueOf(((Integer) aVarA.a("OP_POST_NOTIFICATION", new Class[]{Integer.TYPE}).a()).intValue()), Integer.valueOf(i), packageName}).a()).intValue() == 0 ? 1 : 0;
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public static boolean b() {
        try {
            if (i.a(Build.BRAND) || !Build.BRAND.toLowerCase().startsWith(BDAccountPlatformEntity.PLAT_NAME_HUAWEI)) {
                if (i.a(Build.MANUFACTURER)) {
                    return false;
                }
                if (!Build.MANUFACTURER.toLowerCase().startsWith(BDAccountPlatformEntity.PLAT_NAME_HUAWEI)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = c();
        }
        return (!TextUtils.isEmpty(str) && str.toLowerCase().startsWith("emotionui")) || b();
    }

    public static String c() {
        if (Build.VERSION.SDK_INT >= 21) {
            return c("ro.build.version.emui");
        }
        return d();
    }

    public static String d() {
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, "ro.build.version.emui");
            com.bytedance.common.push.utility.a.b(ToolUtils.TAG, "getEMUI: emuiVersion " + objInvoke);
            if (objInvoke instanceof String) {
                return (String) objInvoke;
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0072 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String c(java.lang.String r7) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            r2.<init>()     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            r2.append(r7)     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L45 java.lang.Throwable -> L47
            java.lang.String r1 = r2.readLine()     // Catch: java.lang.Throwable -> L43 java.lang.Throwable -> L6e
            r2.close()     // Catch: java.lang.Throwable -> L3e java.lang.Throwable -> L6e
            r2.close()     // Catch: java.io.IOException -> L35
            goto L3d
        L35:
            r7 = move-exception
            java.lang.String r0 = "ToolUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            com.bytedance.common.push.utility.a.e(r0, r2, r7)
        L3d:
            return r1
        L3e:
            r0 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
            goto L49
        L43:
            r1 = move-exception
            goto L49
        L45:
            r7 = move-exception
            goto L70
        L47:
            r1 = move-exception
            r2 = r0
        L49:
            java.lang.String r3 = "ToolUtils"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6e
            r4.<init>()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r5 = "Unable to read sysprop "
            r4.append(r5)     // Catch: java.lang.Throwable -> L6e
            r4.append(r7)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L6e
            com.bytedance.common.push.utility.a.e(r3, r7, r1)     // Catch: java.lang.Throwable -> L6e
            if (r2 == 0) goto L6d
            r2.close()     // Catch: java.io.IOException -> L65
            goto L6d
        L65:
            r7 = move-exception
            java.lang.String r1 = "ToolUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            com.bytedance.common.push.utility.a.e(r1, r2, r7)
        L6d:
            return r0
        L6e:
            r7 = move-exception
            r0 = r2
        L70:
            if (r0 == 0) goto L7e
            r0.close()     // Catch: java.io.IOException -> L76
            goto L7e
        L76:
            r0 = move-exception
            java.lang.String r1 = "ToolUtils"
            java.lang.String r2 = "Exception while closing InputStream"
            com.bytedance.common.push.utility.a.e(r1, r2, r0)
        L7e:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.message.a.b.c(java.lang.String):java.lang.String");
    }

    public static boolean e() {
        if (!d) {
            try {
                if (Class.forName("miui.os.Build") != null) {
                    c = true;
                    d = true;
                    return c;
                }
            } catch (Exception unused) {
            }
            d = true;
        }
        return c;
    }

    public static String f() {
        StringBuilder sb = new StringBuilder();
        try {
            if (e()) {
                sb.append("MIUI-");
            } else if (a()) {
                sb.append("FLYME-");
            } else {
                String strC = c();
                if (a(strC) && !g()) {
                    sb.append("EMUI-");
                }
                if (!TextUtils.isEmpty(strC)) {
                    sb.append(strC);
                    sb.append(Constants.ACCEPT_TIME_SEPARATOR_SERVER);
                }
            }
            sb.append(Build.VERSION.INCREMENTAL);
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    public static boolean g() {
        return "angler".equals(Build.BOARD) && Build.MODEL != null && Build.MODEL.toLowerCase().contains("nexus") && Build.MODEL.toLowerCase().contains("6p");
    }

    public static void b(String str) {
        e = str;
    }

    public static boolean e(Context context) {
        String strA = a(context);
        if (strA == null || !strA.endsWith(a)) {
            return false;
        }
        f = false;
        return true;
    }

    public static boolean f(Context context) {
        String strA = a(context);
        if (strA == null || !strA.endsWith(b)) {
            return false;
        }
        f = false;
        return true;
    }

    public static String g(Context context) {
        try {
            ComponentName componentNameResolveActivity = com.bytedance.common.push.b.a.a.a.a.a(context, context.getPackageName()).resolveActivity(context.getPackageManager());
            if (componentNameResolveActivity != null) {
                return componentNameResolveActivity.getClassName();
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
