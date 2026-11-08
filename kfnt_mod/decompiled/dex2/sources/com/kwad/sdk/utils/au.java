package com.kwad.sdk.utils;

import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class au {
    private static Class a;

    private static Object a(String str, Class<?>[] clsArr, Object[] objArr) {
        try {
            if (a == null) {
                a = Class.forName("android.os.SystemProperties");
            }
            Method declaredMethod = a.getDeclaredMethod(str, clsArr);
            declaredMethod.setAccessible(true);
            return declaredMethod.invoke(null, objArr);
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a(th);
            return null;
        }
    }

    public static String a(String str) {
        Object objA = a("get", new Class[]{String.class}, new Object[]{str});
        return objA instanceof String ? (String) objA : b(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0046 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String b(java.lang.String r4) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            r2.<init>()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            r2.append(r4)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            java.lang.String r4 = r2.toString()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            java.lang.Process r4 = r1.exec(r4)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            java.io.InputStream r4 = r4.getInputStream()     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            r4 = 1024(0x400, float:1.435E-42)
            r1.<init>(r2, r4)     // Catch: java.lang.Throwable -> L34 java.io.IOException -> L36
            java.lang.String r4 = r1.readLine()     // Catch: java.io.IOException -> L32 java.lang.Throwable -> L42
            r1.close()     // Catch: java.lang.Throwable -> L41
            goto L41
        L32:
            r4 = move-exception
            goto L38
        L34:
            r4 = move-exception
            goto L44
        L36:
            r4 = move-exception
            r1 = r0
        L38:
            com.kwad.sdk.core.d.a.a(r4)     // Catch: java.lang.Throwable -> L42
            if (r1 == 0) goto L40
            r1.close()     // Catch: java.lang.Throwable -> L40
        L40:
            r4 = r0
        L41:
            return r4
        L42:
            r4 = move-exception
            r0 = r1
        L44:
            if (r0 == 0) goto L49
            r0.close()     // Catch: java.lang.Throwable -> L49
        L49:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.utils.au.b(java.lang.String):java.lang.String");
    }
}
