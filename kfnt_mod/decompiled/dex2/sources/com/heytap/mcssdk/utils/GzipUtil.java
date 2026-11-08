package com.heytap.mcssdk.utils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class GzipUtil {
    private static final int IO_BUF_SIZE = 1024;

    /* JADX WARN: Removed duplicated region for block: B:35:0x0063 A[Catch: IOException -> 0x006a, TryCatch #0 {IOException -> 0x006a, blocks: (B:33:0x005e, B:35:0x0063, B:36:0x0066), top: B:43:0x005e }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] compress(java.lang.String r6) throws java.lang.Throwable {
        /*
            if (r6 == 0) goto L6f
            int r0 = r6.length()
            if (r0 != 0) goto La
            goto L6f
        La:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            byte[] r6 = r6.getBytes()
            r1.<init>(r6)
            r6 = 0
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L41
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L41
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L5d
        L22:
            int r3 = r1.read(r6)     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L5d
            if (r3 <= 0) goto L30
            r4 = 0
            r2.write(r6, r4, r3)     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L5d
            r2.flush()     // Catch: java.io.IOException -> L3a java.lang.Throwable -> L5d
            goto L22
        L30:
            r1.close()     // Catch: java.io.IOException -> L54
            r2.close()     // Catch: java.io.IOException -> L54
            r0.close()     // Catch: java.io.IOException -> L54
            goto L58
        L3a:
            r6 = move-exception
            goto L45
        L3c:
            r2 = move-exception
            r5 = r2
            r2 = r6
            r6 = r5
            goto L5e
        L41:
            r2 = move-exception
            r5 = r2
            r2 = r6
            r6 = r5
        L45:
            com.heytap.mcssdk.utils.LogUtil.e(r6)     // Catch: java.lang.Throwable -> L5d
            r1.close()     // Catch: java.io.IOException -> L54
            if (r2 == 0) goto L50
            r2.close()     // Catch: java.io.IOException -> L54
        L50:
            r0.close()     // Catch: java.io.IOException -> L54
            goto L58
        L54:
            r6 = move-exception
            r6.printStackTrace()
        L58:
            byte[] r6 = r0.toByteArray()
            return r6
        L5d:
            r6 = move-exception
        L5e:
            r1.close()     // Catch: java.io.IOException -> L6a
            if (r2 == 0) goto L66
            r2.close()     // Catch: java.io.IOException -> L6a
        L66:
            r0.close()     // Catch: java.io.IOException -> L6a
            goto L6e
        L6a:
            r0 = move-exception
            r0.printStackTrace()
        L6e:
            throw r6
        L6f:
            java.lang.String r6 = ""
            byte[] r6 = r6.getBytes()
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.mcssdk.utils.GzipUtil.compress(java.lang.String):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x005b A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String uncompress(byte[] r6) throws java.lang.Throwable {
        /*
            if (r6 == 0) goto L6c
            int r0 = r6.length
            if (r0 != 0) goto L7
            goto L6c
        L7:
            java.io.ByteArrayOutputStream r0 = new java.io.ByteArrayOutputStream
            r0.<init>()
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r1.<init>(r6)
            r6 = 0
            java.util.zip.GZIPInputStream r2 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L3a
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L3a
            r6 = 1024(0x400, float:1.435E-42)
            byte[] r6 = new byte[r6]     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L58
        L1b:
            int r3 = r2.read(r6)     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L58
            if (r3 <= 0) goto L29
            r4 = 0
            r0.write(r6, r4, r3)     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L58
            r0.flush()     // Catch: java.io.IOException -> L33 java.lang.Throwable -> L58
            goto L1b
        L29:
            r2.close()     // Catch: java.io.IOException -> L47
            r1.close()     // Catch: java.io.IOException -> L47
            r0.close()     // Catch: java.io.IOException -> L47
            goto L53
        L33:
            r6 = move-exception
            goto L3e
        L35:
            r2 = move-exception
            r5 = r2
            r2 = r6
            r6 = r5
            goto L59
        L3a:
            r2 = move-exception
            r5 = r2
            r2 = r6
            r6 = r5
        L3e:
            com.heytap.mcssdk.utils.LogUtil.e(r6)     // Catch: java.lang.Throwable -> L58
            if (r2 == 0) goto L49
            r2.close()     // Catch: java.io.IOException -> L47
            goto L49
        L47:
            r6 = move-exception
            goto L50
        L49:
            r1.close()     // Catch: java.io.IOException -> L47
            r0.close()     // Catch: java.io.IOException -> L47
            goto L53
        L50:
            r6.printStackTrace()
        L53:
            java.lang.String r6 = r0.toString()
            return r6
        L58:
            r6 = move-exception
        L59:
            if (r2 == 0) goto L61
            r2.close()     // Catch: java.io.IOException -> L5f
            goto L61
        L5f:
            r0 = move-exception
            goto L68
        L61:
            r1.close()     // Catch: java.io.IOException -> L5f
            r0.close()     // Catch: java.io.IOException -> L5f
            goto L6b
        L68:
            r0.printStackTrace()
        L6b:
            throw r6
        L6c:
            java.lang.String r6 = ""
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.heytap.mcssdk.utils.GzipUtil.uncompress(byte[]):java.lang.String");
    }
}
