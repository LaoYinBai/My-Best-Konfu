package com.taobao.accs.utl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.common.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class w {
    public static final String SP_AGOO_BIND_FILE_NAME = "AGOO_BIND";
    private static final byte[] b = new byte[0];
    private static int c = -1;
    static int a = -1;

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (c == -1) {
            c = context.getApplicationInfo().targetSdkVersion;
        }
        return c >= 26 && Build.VERSION.SDK_INT >= 26;
    }

    public static void a(Context context, int i) {
        try {
            synchronized (b) {
                a = i;
                SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                editorEdit.putInt(Constants.SP_KEY_DEBUG_MODE, i);
                editorEdit.apply();
            }
        } catch (Throwable th) {
            ALog.e("Utils", "setMode", th, new Object[0]);
        }
    }

    public static int b(Context context) {
        int i;
        if (a != -1) {
            return a;
        }
        try {
            try {
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            context = null;
        }
        synchronized (b) {
            try {
                i = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).getInt(Constants.SP_KEY_DEBUG_MODE, 0);
                return i;
            } catch (Throwable th3) {
                th = th3;
                context = null;
                try {
                    throw th;
                } catch (Throwable th4) {
                    th = th4;
                    ALog.e("Utils", "getMode", th, new Object[0]);
                    i = context;
                    return i;
                }
            }
        }
    }

    public static void c(Context context) {
        try {
            synchronized (b) {
                SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                editorEdit.clear();
                editorEdit.apply();
            }
        } catch (Throwable th) {
            ALog.e("Utils", "clearAllSharePreferences", th, new Object[0]);
        }
    }

    public static void d(Context context) {
        try {
            Class<?> clsLoadClass = com.taobao.accs.d.a.a().b().loadClass("com.taobao.accs.utl.UtilityImpl");
            clsLoadClass.getMethod("killService", Context.class).invoke(clsLoadClass, context);
        } catch (Throwable th) {
            ALog.e("Utils", "killService", th, new Object[0]);
        }
    }

    public static boolean e(Context context) {
        boolean zBooleanValue;
        try {
            Class<?> clsLoadClass = com.taobao.accs.d.a.a().b().loadClass("com.taobao.accs.utl.UtilityImpl");
            zBooleanValue = ((Boolean) clsLoadClass.getMethod("isMainProcess", Context.class).invoke(clsLoadClass, context)).booleanValue();
        } catch (Throwable th) {
            ALog.e("Utils", "killservice", th, new Object[0]);
            th.printStackTrace();
            zBooleanValue = true;
        }
        ALog.i("Utils", "isMainProcess", "result", Boolean.valueOf(zBooleanValue));
        return zBooleanValue;
    }

    public static void a(Context context, String str) {
        try {
            Class<?> clsLoadClass = com.taobao.accs.d.a.a().b().loadClass("org.android.agoo.common.Config");
            clsLoadClass.getMethod("setAgooAppKey", Context.class, String.class).invoke(clsLoadClass, context, str);
        } catch (Throwable th) {
            ALog.e("Utils", "setAgooAppkey", th, new Object[0]);
            th.printStackTrace();
        }
    }

    @Deprecated
    public static void a() {
        try {
            Class<?> clsLoadClass = com.taobao.accs.d.a.a().b().loadClass("com.taobao.accs.client.AccsConfig");
            clsLoadClass.getMethod("build", new Class[0]).invoke(clsLoadClass, new Object[0]);
        } catch (Throwable th) {
            ALog.e("Utils", "initConfig", th, new Object[0]);
            th.printStackTrace();
        }
    }

    public static void a(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            ALog.e("Utils", "setSpValue null", new Object[0]);
            return;
        }
        try {
            synchronized (b) {
                SharedPreferences.Editor editorEdit = context.getSharedPreferences(Constants.SP_FILE_NAME, 0).edit();
                editorEdit.putString(str, str2);
                editorEdit.apply();
            }
            ALog.i("Utils", "setSpValue", com.ss.union.game.sdk.redemptionCode.a.p, str, "value", str2);
        } catch (Exception e) {
            ALog.e("Utils", "setSpValue fail", e, new Object[0]);
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't find top splitter block for handler:B:15:0x0037
        	at jadx.core.utils.BlockUtils.getTopSplitterForHandler(BlockUtils.java:1178)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:53)
        	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
        */
    public static java.lang.String b(android.content.Context r4, java.lang.String r5, java.lang.String r6) {
        /*
            r0 = 0
            r1 = 0
            byte[] r2 = com.taobao.accs.utl.w.b     // Catch: java.lang.Throwable -> L3a
            monitor-enter(r2)     // Catch: java.lang.Throwable -> L3a
            java.lang.String r3 = "ACCS_SDK"
            android.content.SharedPreferences r4 = r4.getSharedPreferences(r3, r1)     // Catch: java.lang.Throwable -> L37
            java.lang.String r4 = r4.getString(r5, r0)     // Catch: java.lang.Throwable -> L37
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L34
            java.lang.String r5 = "Utils"
            java.lang.String r0 = "getSpValue"
            r2 = 2
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L32
            java.lang.String r3 = "value"
            r2[r1] = r3     // Catch: java.lang.Throwable -> L32
            r3 = 1
            r2[r3] = r4     // Catch: java.lang.Throwable -> L32
            com.taobao.accs.utl.ALog.i(r5, r0, r2)     // Catch: java.lang.Throwable -> L32
            boolean r5 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L32
            if (r5 == 0) goto L45
            java.lang.String r5 = "Utils"
            java.lang.String r0 = "getSpValue use default!"
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L32
            com.taobao.accs.utl.ALog.e(r5, r0, r2)     // Catch: java.lang.Throwable -> L32
            r4 = r6
            goto L45
        L32:
            r5 = move-exception
            goto L3c
        L34:
            r5 = move-exception
            r0 = r4
            goto L38
        L37:
            r5 = move-exception
        L38:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L37
            throw r5     // Catch: java.lang.Throwable -> L3a
        L3a:
            r5 = move-exception
            r4 = r0
        L3c:
            java.lang.String r6 = "Utils"
            java.lang.String r0 = "getSpValue fail"
            java.lang.Object[] r1 = new java.lang.Object[r1]
            com.taobao.accs.utl.ALog.e(r6, r0, r5, r1)
        L45:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taobao.accs.utl.w.b(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    public static void f(Context context) {
        try {
            SharedPreferences.Editor editorEdit = context.getSharedPreferences("AGOO_BIND", 0).edit();
            editorEdit.clear();
            editorEdit.apply();
        } catch (Exception e) {
            ALog.e("Utils", "clearAgooBindCache", e, new Object[0]);
        }
    }

    public static Bundle g(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null) {
                return applicationInfo.metaData;
            }
            return null;
        } catch (Throwable th) {
            ALog.e("Utils", "getMetaInfo", th, new Object[0]);
            return null;
        }
    }
}
