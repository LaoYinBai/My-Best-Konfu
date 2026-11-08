package com.kwad.sdk.utils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class o {
    /* JADX WARN: Removed duplicated region for block: B:23:0x003e A[Catch: all -> 0x0042, Throwable -> 0x0045, TryCatch #2 {all -> 0x0042, blocks: (B:5:0x0009, B:19:0x0035, B:24:0x0041, B:23:0x003e, B:22:0x003a), top: B:46:0x0009 }] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0035 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @androidx.annotation.RequiresApi(api = 19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(byte[] r5) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r5 == 0) goto L64
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.io.IOException -> L5c
            int r2 = r5.length     // Catch: java.io.IOException -> L5c
            r1.<init>(r2)     // Catch: java.io.IOException -> L5c
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L45
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L45
            r2.write(r5)     // Catch: java.lang.Throwable -> L2a java.lang.Throwable -> L2d
            r2.flush()     // Catch: java.lang.Throwable -> L2a java.lang.Throwable -> L2d
            r2.close()     // Catch: java.lang.Throwable -> L2a java.lang.Throwable -> L2d
            r1.flush()     // Catch: java.lang.Throwable -> L2a java.lang.Throwable -> L2d
            r1.close()     // Catch: java.lang.Throwable -> L2a java.lang.Throwable -> L2d
            byte[] r5 = r1.toByteArray()     // Catch: java.lang.Throwable -> L2a java.lang.Throwable -> L2d
            r2.close()     // Catch: java.lang.Throwable -> L28 java.lang.Throwable -> L4a
            r1.close()     // Catch: java.io.IOException -> L5a
            goto L65
        L28:
            r0 = move-exception
            goto L49
        L2a:
            r5 = move-exception
            r3 = r0
            goto L33
        L2d:
            r5 = move-exception
            throw r5     // Catch: java.lang.Throwable -> L2f
        L2f:
            r3 = move-exception
            r4 = r3
            r3 = r5
            r5 = r4
        L33:
            if (r3 == 0) goto L3e
            r2.close()     // Catch: java.lang.Throwable -> L39 java.lang.Throwable -> L42
            goto L41
        L39:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L45
            goto L41
        L3e:
            r2.close()     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L45
        L41:
            throw r5     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L45
        L42:
            r2 = move-exception
            r5 = r0
            goto L4b
        L45:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
        L49:
            throw r0     // Catch: java.lang.Throwable -> L4a
        L4a:
            r2 = move-exception
        L4b:
            if (r0 == 0) goto L56
            r1.close()     // Catch: java.lang.Throwable -> L51 java.io.IOException -> L5a
            goto L59
        L51:
            r1 = move-exception
            r0.addSuppressed(r1)     // Catch: java.io.IOException -> L5a
            goto L59
        L56:
            r1.close()     // Catch: java.io.IOException -> L5a
        L59:
            throw r2     // Catch: java.io.IOException -> L5a
        L5a:
            r0 = move-exception
            goto L60
        L5c:
            r5 = move-exception
            r4 = r0
            r0 = r5
            r5 = r4
        L60:
            com.kwad.sdk.core.d.a.a(r0)
            goto L65
        L64:
            r5 = r0
        L65:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.utils.o.a(byte[]):byte[]");
    }
}
