package com.kwad.sdk.utils;

import android.os.Build;
import android.text.TextUtils;
import com.kwad.v8.Platform;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class an {
    private static String a;
    private static String b;

    public static boolean a() {
        return a("EMUI");
    }

    public static boolean a(String str) throws Throwable {
        String upperCase;
        if (a == null) {
            String strB = b("ro.build.version.opporom");
            b = strB;
            if (TextUtils.isEmpty(strB)) {
                String strB2 = b("ro.vivo.os.version");
                b = strB2;
                if (TextUtils.isEmpty(strB2)) {
                    String strB3 = b("ro.build.version.emui");
                    b = strB3;
                    if (TextUtils.isEmpty(strB3)) {
                        String strB4 = b("ro.miui.ui.version.name");
                        b = strB4;
                        if (TextUtils.isEmpty(strB4)) {
                            String strB5 = b("ro.product.system.manufacturer");
                            b = strB5;
                            if (TextUtils.isEmpty(strB5)) {
                                String strB6 = b("ro.smartisan.version");
                                b = strB6;
                                if (!TextUtils.isEmpty(strB6)) {
                                    upperCase = "SMARTISAN";
                                } else if (b("ro.product.manufacturer").toUpperCase().contains("SAMSUNG")) {
                                    upperCase = "SAMSUNG";
                                } else {
                                    b = Build.DISPLAY;
                                    if (b.toUpperCase().contains("FLYME")) {
                                        upperCase = "FLYME";
                                    } else {
                                        b = Platform.UNKNOWN;
                                        upperCase = Build.MANUFACTURER.toUpperCase();
                                    }
                                }
                            } else {
                                upperCase = "OnePlus";
                            }
                        } else {
                            upperCase = "MIUI";
                        }
                    } else {
                        upperCase = "EMUI";
                    }
                } else {
                    upperCase = "VIVO";
                }
            } else {
                upperCase = "OPPO";
            }
            a = upperCase;
        }
        return a.contains(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:44:0x003a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @android.annotation.SuppressLint({"PrivateApi"})
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String b(java.lang.String r7) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.String r1 = "android.os.SystemProperties"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.reflect.InvocationTargetException -> L20 java.lang.IllegalAccessException -> L25 java.lang.NoSuchMethodException -> L2a java.lang.ClassNotFoundException -> L2f
            java.lang.String r2 = "get"
            r3 = 1
            java.lang.Class[] r4 = new java.lang.Class[r3]     // Catch: java.lang.reflect.InvocationTargetException -> L20 java.lang.IllegalAccessException -> L25 java.lang.NoSuchMethodException -> L2a java.lang.ClassNotFoundException -> L2f
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            r6 = 0
            r4[r6] = r5     // Catch: java.lang.reflect.InvocationTargetException -> L20 java.lang.IllegalAccessException -> L25 java.lang.NoSuchMethodException -> L2a java.lang.ClassNotFoundException -> L2f
            java.lang.reflect.Method r2 = r1.getDeclaredMethod(r2, r4)     // Catch: java.lang.reflect.InvocationTargetException -> L20 java.lang.IllegalAccessException -> L25 java.lang.NoSuchMethodException -> L2a java.lang.ClassNotFoundException -> L2f
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.reflect.InvocationTargetException -> L20 java.lang.IllegalAccessException -> L25 java.lang.NoSuchMethodException -> L2a java.lang.ClassNotFoundException -> L2f
            r3[r6] = r7     // Catch: java.lang.reflect.InvocationTargetException -> L20 java.lang.IllegalAccessException -> L25 java.lang.NoSuchMethodException -> L2a java.lang.ClassNotFoundException -> L2f
            java.lang.Object r1 = r2.invoke(r1, r3)     // Catch: java.lang.reflect.InvocationTargetException -> L20 java.lang.IllegalAccessException -> L25 java.lang.NoSuchMethodException -> L2a java.lang.ClassNotFoundException -> L2f
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.reflect.InvocationTargetException -> L20 java.lang.IllegalAccessException -> L25 java.lang.NoSuchMethodException -> L2a java.lang.ClassNotFoundException -> L2f
            goto L34
        L20:
            r1 = move-exception
            r1.printStackTrace()
            goto L33
        L25:
            r1 = move-exception
            r1.printStackTrace()
            goto L33
        L2a:
            r1 = move-exception
            r1.printStackTrace()
            goto L33
        L2f:
            r1 = move-exception
            r1.printStackTrace()
        L33:
            r1 = r0
        L34:
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L83
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            r3.<init>()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.lang.String r4 = "getprop "
            r3.append(r4)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            r3.append(r7)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.lang.String r7 = r3.toString()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.lang.Process r7 = r2.exec(r7)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.io.InputStream r7 = r7.getInputStream()     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            r3.<init>(r7)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            r7 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r7)     // Catch: java.lang.Throwable -> L71 java.io.IOException -> L73
            java.lang.String r7 = r2.readLine()     // Catch: java.lang.Throwable -> L6b java.io.IOException -> L6e
            r2.close()     // Catch: java.io.IOException -> L84
            goto L84
        L6b:
            r7 = move-exception
            r0 = r2
            goto L7d
        L6e:
            r7 = move-exception
            r0 = r2
            goto L74
        L71:
            r7 = move-exception
            goto L7d
        L73:
            r7 = move-exception
        L74:
            r7.printStackTrace()     // Catch: java.lang.Throwable -> L71
            if (r0 == 0) goto L83
            r0.close()     // Catch: java.io.IOException -> L83
            goto L83
        L7d:
            if (r0 == 0) goto L82
            r0.close()     // Catch: java.io.IOException -> L82
        L82:
            throw r7
        L83:
            r7 = r1
        L84:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.utils.an.b(java.lang.String):java.lang.String");
    }

    public static boolean b() {
        return a("MIUI");
    }

    public static boolean c() {
        return a("FLYME");
    }

    public static String d() throws Throwable {
        if (a == null) {
            a("");
        }
        return a;
    }

    public static String e() throws Throwable {
        if (b == null) {
            a("");
        }
        return b;
    }
}
