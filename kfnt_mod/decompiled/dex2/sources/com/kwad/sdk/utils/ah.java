package com.kwad.sdk.utils;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.Build;
import android.os.Process;
import android.provider.Settings;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ah {
    private static Map<String, Integer> a;
    private static Set<String> b = new HashSet();
    private static Method c;

    static {
        b.add("android.permission.REQUEST_INSTALL_PACKAGES");
        b.add("android.permission.WRITE_SETTINGS");
        b.add("android.permission.SYSTEM_ALERT_WINDOW");
    }

    public static int a(Context context, String str) {
        int iB;
        if (a == null) {
            try {
                a(context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        if (b.contains(str) && (iB = b(context, str)) != -2) {
            return iB;
        }
        int iC = c(context, str);
        return iC != -2 ? iC : context.checkPermission(str, Process.myPid(), Process.myUid());
    }

    private static String a(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(".");
        if (iLastIndexOf < 0) {
            return str;
        }
        try {
            return str.substring(iLastIndexOf + 1);
        } catch (Exception unused) {
            return str;
        }
    }

    private static void a(String[] strArr) {
        if (Build.VERSION.SDK_INT < 19 || strArr == null) {
            return;
        }
        a = new HashMap();
        for (String str : strArr) {
            try {
                Field declaredField = AppOpsManager.class.getDeclaredField("OP_" + a(str));
                declaredField.setAccessible(true);
                int iIntValue = ((Integer) declaredField.get(null)).intValue();
                if (iIntValue >= 0) {
                    a.put(str, Integer.valueOf(iIntValue));
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean a(Context context) {
        int i;
        try {
            i = Settings.Secure.getInt(context.getContentResolver(), "accessibility_enabled");
        } catch (Throwable unused) {
            i = 0;
        }
        return i == 1;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static int b(android.content.Context r4, java.lang.String r5) {
        /*
            int r0 = r5.hashCode()
            r1 = -2078357533(0xffffffff841ec7e3, float:-1.8664594E-36)
            r2 = 0
            r3 = -1
            if (r0 == r1) goto L2a
            r1 = -1561629405(0xffffffffa2eb6d23, float:-6.381243E-18)
            if (r0 == r1) goto L20
            r1 = 1777263169(0x69eee241, float:3.60991E25)
            if (r0 == r1) goto L16
            goto L34
        L16:
            java.lang.String r0 = "android.permission.REQUEST_INSTALL_PACKAGES"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = 0
            goto L35
        L20:
            java.lang.String r0 = "android.permission.SYSTEM_ALERT_WINDOW"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = 2
            goto L35
        L2a:
            java.lang.String r0 = "android.permission.WRITE_SETTINGS"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L34
            r5 = 1
            goto L35
        L34:
            r5 = -1
        L35:
            r0 = 23
            switch(r5) {
                case 0: goto L53;
                case 1: goto L48;
                case 2: goto L3b;
                default: goto L3a;
            }
        L3a:
            goto L64
        L3b:
            int r5 = android.os.Build.VERSION.SDK_INT
            if (r5 < r0) goto L64
            boolean r4 = android.provider.Settings.canDrawOverlays(r4)
            if (r4 == 0) goto L46
            goto L65
        L46:
            r2 = -1
            goto L65
        L48:
            int r5 = android.os.Build.VERSION.SDK_INT
            if (r5 < r0) goto L64
            boolean r4 = android.provider.Settings.System.canWrite(r4)
            if (r4 == 0) goto L46
            goto L65
        L53:
            int r5 = android.os.Build.VERSION.SDK_INT
            r0 = 26
            if (r5 < r0) goto L64
            android.content.pm.PackageManager r4 = r4.getPackageManager()
            boolean r4 = r4.canRequestPackageInstalls()
            if (r4 == 0) goto L46
            goto L65
        L64:
            r2 = -2
        L65:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.utils.ah.b(android.content.Context, java.lang.String):int");
    }

    private static int c(Context context, String str) {
        if (str == null) {
            return -2;
        }
        if (Build.VERSION.SDK_INT < 19) {
            return 0;
        }
        if (!a.containsKey(str)) {
            return -2;
        }
        int iIntValue = a.get(str).intValue();
        try {
            if (c == null) {
                c = AppOpsManager.class.getDeclaredMethod("checkOp", Integer.TYPE, Integer.TYPE, String.class);
                c.setAccessible(true);
            }
            return ((Integer) c.invoke((AppOpsManager) context.getSystemService("appops"), Integer.valueOf(iIntValue), Integer.valueOf(Binder.getCallingUid()), context.getPackageName())).intValue() == 0 ? 0 : -1;
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
            return 0;
        }
    }
}
