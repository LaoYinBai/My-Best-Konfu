package com.mbridge.msdk.optimize;

/* compiled from: SimpleDownload.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    /* JADX WARN: Removed duplicated region for block: B:66:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x009f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.io.File r11, java.lang.String r12, java.lang.String r13, com.mbridge.msdk.optimize.b r14) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            r1.<init>()     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            r1.append(r12)     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            java.lang.String r12 = ".apk"
            r1.append(r12)     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            java.lang.String r12 = r1.toString()     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            java.io.File r1 = new java.io.File     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            r1.<init>(r11, r12)     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            boolean r11 = r1.exists()     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            if (r11 == 0) goto L20
            r1.delete()     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
        L20:
            if (r14 == 0) goto L25
            r14.a()     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
        L25:
            java.net.URL r11 = new java.net.URL     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            r11.<init>(r13)     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            java.net.URLConnection r11 = r11.openConnection()     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            r12 = 8000(0x1f40, float:1.121E-41)
            r11.setConnectTimeout(r12)     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            int r12 = r11.getContentLength()     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            r2 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r4 = (double) r12     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            double r2 = r2 / r4
            java.io.InputStream r11 = r11.getInputStream()     // Catch: java.lang.Throwable -> L85 java.lang.Throwable -> L88
            r13 = 1024(0x400, float:1.435E-42)
            byte[] r13 = new byte[r13]     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L8f
            java.io.FileOutputStream r4 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L8f
            r5 = 1
            r4.<init>(r1, r5)     // Catch: java.lang.Throwable -> L89 java.lang.Throwable -> L8f
            r0 = 0
            r6 = 0
            r7 = 0
        L4c:
            int r8 = r11.read(r13)     // Catch: java.lang.Throwable -> L80 java.lang.Throwable -> L83
            r9 = -1
            if (r8 == r9) goto L6b
            r4.write(r13, r0, r8)     // Catch: java.lang.Throwable -> L80 java.lang.Throwable -> L83
            int r6 = r6 + r8
            double r8 = (double) r6     // Catch: java.lang.Throwable -> L80 java.lang.Throwable -> L83
            double r8 = r8 * r2
            int r8 = (int) r8     // Catch: java.lang.Throwable -> L80 java.lang.Throwable -> L83
            r9 = 512(0x200, float:7.17E-43)
            if (r7 >= r9) goto L63
            r9 = 100
            if (r8 != r9) goto L69
        L63:
            if (r14 == 0) goto L68
            r14.a(r8)     // Catch: java.lang.Throwable -> L80 java.lang.Throwable -> L83
        L68:
            r7 = 0
        L69:
            int r7 = r7 + r5
            goto L4c
        L6b:
            if (r6 != r12) goto L72
            if (r14 == 0) goto L72
            r14.a(r1)     // Catch: java.lang.Throwable -> L80 java.lang.Throwable -> L83
        L72:
            r4.close()     // Catch: java.io.IOException -> L76
            goto L7a
        L76:
            r12 = move-exception
            r12.printStackTrace()
        L7a:
            if (r11 == 0) goto Lbc
            r11.close()     // Catch: java.io.IOException -> Lb8
            goto Lbc
        L80:
            r12 = move-exception
            r0 = r4
            goto L90
        L83:
            r0 = r4
            goto L89
        L85:
            r11 = move-exception
            r12 = r0
            goto L93
        L88:
            r11 = r0
        L89:
            if (r14 == 0) goto La8
            r14.b()     // Catch: java.lang.Throwable -> L8f
            goto La8
        L8f:
            r12 = move-exception
        L90:
            r10 = r12
            r12 = r11
            r11 = r10
        L93:
            if (r0 == 0) goto L9d
            r0.close()     // Catch: java.io.IOException -> L99
            goto L9d
        L99:
            r13 = move-exception
            r13.printStackTrace()
        L9d:
            if (r12 == 0) goto La7
            r12.close()     // Catch: java.io.IOException -> La3
            goto La7
        La3:
            r12 = move-exception
            r12.printStackTrace()
        La7:
            throw r11
        La8:
            if (r0 == 0) goto Lb2
            r0.close()     // Catch: java.io.IOException -> Lae
            goto Lb2
        Lae:
            r12 = move-exception
            r12.printStackTrace()
        Lb2:
            if (r11 == 0) goto Lbc
            r11.close()     // Catch: java.io.IOException -> Lb8
            goto Lbc
        Lb8:
            r11 = move-exception
            r11.printStackTrace()
        Lbc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.optimize.a.a(java.io.File, java.lang.String, java.lang.String, com.mbridge.msdk.optimize.b):void");
    }
}
