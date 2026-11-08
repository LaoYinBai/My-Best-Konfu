package com.bytedance.common.utility.device;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class CmdSystemProperties {
    private static final int DEFAULT_CACHE_SIZE = 1024;
    private static final String TAG = "CmdSystemProperties";

    private CmdSystemProperties() {
    }

    /* JADX WARN: Not initialized variable reg: 2, insn: 0x006c: MOVE (r0 I:??[OBJECT, ARRAY]) = (r2 I:??[OBJECT, ARRAY]), block:B:24:0x006c */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.String getSystemProperty(java.lang.String r6) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            r2.<init>()     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            java.lang.String r3 = "getprop "
            r2.append(r3)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            r2.append(r6)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            java.io.InputStream r1 = r1.getInputStream()     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            java.lang.String r4 = "UTF-8"
            r3.<init>(r1, r4)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            r1 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3, r1)     // Catch: java.lang.Throwable -> L42 java.io.IOException -> L44
            java.lang.String r1 = r2.readLine()     // Catch: java.io.IOException -> L40 java.lang.Throwable -> L6b
            r2.close()     // Catch: java.io.IOException -> L40 java.lang.Throwable -> L6b
            r2.close()     // Catch: java.io.IOException -> L37
            goto L3f
        L37:
            r6 = move-exception
            java.lang.String r0 = "CmdSystemProperties"
            java.lang.String r2 = "Exception while closing InputStream"
            android.util.Log.e(r0, r2, r6)
        L3f:
            return r1
        L40:
            r1 = move-exception
            goto L46
        L42:
            r6 = move-exception
            goto L6d
        L44:
            r1 = move-exception
            r2 = r0
        L46:
            java.lang.String r3 = "CmdSystemProperties"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6b
            r4.<init>()     // Catch: java.lang.Throwable -> L6b
            java.lang.String r5 = "Unable to read sysprop "
            r4.append(r5)     // Catch: java.lang.Throwable -> L6b
            r4.append(r6)     // Catch: java.lang.Throwable -> L6b
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L6b
            android.util.Log.e(r3, r6, r1)     // Catch: java.lang.Throwable -> L6b
            if (r2 == 0) goto L6a
            r2.close()     // Catch: java.io.IOException -> L62
            goto L6a
        L62:
            r6 = move-exception
            java.lang.String r1 = "CmdSystemProperties"
            java.lang.String r2 = "Exception while closing InputStream"
            android.util.Log.e(r1, r2, r6)
        L6a:
            return r0
        L6b:
            r6 = move-exception
            r0 = r2
        L6d:
            if (r0 == 0) goto L7b
            r0.close()     // Catch: java.io.IOException -> L73
            goto L7b
        L73:
            r0 = move-exception
            java.lang.String r1 = "CmdSystemProperties"
            java.lang.String r2 = "Exception while closing InputStream"
            android.util.Log.e(r1, r2, r0)
        L7b:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.utility.device.CmdSystemProperties.getSystemProperty(java.lang.String):java.lang.String");
    }
}
