package com.bytedance.gameprotect;

import java.io.BufferedInputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {
    private static String a(BufferedInputStream bufferedInputStream) {
        int i;
        if (bufferedInputStream == null) {
            return "";
        }
        byte[] bArr = new byte[4096];
        StringBuilder sb = new StringBuilder();
        do {
            try {
                i = bufferedInputStream.read(bArr);
                if (i > 0) {
                    sb.append(new String(bArr, 0, i));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (i >= 4096);
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x005c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0057 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0052 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0069 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:66:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.lang.String r6) throws java.lang.Throwable {
        /*
            java.lang.String r0 = ""
            r1 = 0
            java.lang.Runtime r2 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L60
            java.lang.String r3 = "sh"
            java.lang.Process r2 = r2.exec(r3)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L60
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L61
            java.io.OutputStream r4 = r2.getOutputStream()     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L61
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L4b java.lang.Exception -> L61
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L62
            java.io.InputStream r5 = r2.getInputStream()     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L62
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L49 java.lang.Exception -> L62
            byte[] r6 = r6.getBytes()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            r3.write(r6)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            r6 = 10
            r3.write(r6)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            r3.flush()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            r3.close()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            r2.waitFor()     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            java.lang.String r6 = a(r4)     // Catch: java.lang.Throwable -> L44 java.lang.Exception -> L47
            r3.close()     // Catch: java.io.IOException -> L3b
        L3b:
            r4.close()     // Catch: java.io.IOException -> L3e
        L3e:
            if (r2 == 0) goto L42
            r0 = r6
            goto L6e
        L42:
            r0 = r6
            goto L71
        L44:
            r6 = move-exception
            r1 = r4
            goto L50
        L47:
            r1 = r4
            goto L62
        L49:
            r6 = move-exception
            goto L50
        L4b:
            r6 = move-exception
            goto L4f
        L4d:
            r6 = move-exception
            r2 = r1
        L4f:
            r3 = r1
        L50:
            if (r3 == 0) goto L55
            r3.close()     // Catch: java.io.IOException -> L55
        L55:
            if (r1 == 0) goto L5a
            r1.close()     // Catch: java.io.IOException -> L5a
        L5a:
            if (r2 == 0) goto L5f
            r2.destroy()
        L5f:
            throw r6
        L60:
            r2 = r1
        L61:
            r3 = r1
        L62:
            if (r3 == 0) goto L67
            r3.close()     // Catch: java.io.IOException -> L67
        L67:
            if (r1 == 0) goto L6c
            r1.close()     // Catch: java.io.IOException -> L6c
        L6c:
            if (r2 == 0) goto L71
        L6e:
            r2.destroy()
        L71:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.gameprotect.f.a(java.lang.String):java.lang.String");
    }

    public static String b(String str) {
        String strA;
        try {
            strA = a(str);
        } catch (Throwable unused) {
            strA = "0[<!>]EXCEPTION[<!>]";
        }
        return (strA == null || strA.length() == 0) ? "0[<!>]ERROR[<!>]" : strA;
    }
}
