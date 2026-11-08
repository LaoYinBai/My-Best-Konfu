package com.bytedance.push.g;

import com.bytedance.sdk.b.d.a;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {
    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        int iIndexOf = str.indexOf(a.f.f5151a);
        if (iIndexOf >= 0) {
            iIndexOf = str.indexOf("ssmix=", iIndexOf + a.f.f5151a.length());
        }
        return iIndexOf > 0;
    }

    public static void a(byte[] bArr, int i) throws IOException {
        if (bArr == null || i <= 0) {
            return;
        }
        byte[] bArr2 = {-99, -114, 127, 90};
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) (bArr[i2] ^ bArr2[i2 % 4]);
        }
    }

    public static void a(Closeable closeable) throws IOException {
        a(closeable, (String) null);
    }

    private static void a(Closeable closeable, String str) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                if (!com.bytedance.common.push.utility.a.b() || str == null) {
                    return;
                }
                com.bytedance.common.push.utility.a.b("NetworkUtils", str + " " + e);
            }
        }
    }

    public static byte[] a(boolean z, int i, InputStream inputStream, int[] iArr, g gVar) throws Exception {
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArrA = a(z, i, inputStream, iArr);
            if (bArrA == null || iArr[0] <= 0) {
                return null;
            }
            return bArrA;
        } catch (Exception e) {
            if (gVar != null) {
                try {
                    gVar.a();
                } catch (Throwable unused) {
                }
            }
            throw e;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0093, code lost:
    
        if (r1 <= 0) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0095, code lost:
    
        r10[0] = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0097, code lost:
    
        a(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x009a, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x009b, code lost:
    
        a(r9);
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x009e, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(boolean r7, int r8, java.io.InputStream r9, int[] r10) throws java.io.IOException {
        /*
            if (r8 > 0) goto L4
            r8 = 5242880(0x500000, float:7.34684E-39)
        L4:
            r0 = 1048576(0x100000, float:1.469368E-39)
            if (r8 >= r0) goto La
            r8 = 1048576(0x100000, float:1.469368E-39)
        La:
            r0 = 0
            if (r9 != 0) goto Le
            return r0
        Le:
            if (r7 == 0) goto L1a
            java.util.zip.GZIPInputStream r1 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L17
            r1.<init>(r9)     // Catch: java.lang.Throwable -> L17
            r9 = r1
            goto L1a
        L17:
            r7 = move-exception
            goto La0
        L1a:
            r1 = 8192(0x2000, float:1.148E-41)
            byte[] r1 = new byte[r1]     // Catch: java.lang.Throwable -> L17
            r2 = 4096(0x1000, float:5.74E-42)
            r3 = 0
            r4 = r1
            r1 = 0
        L23:
            int r5 = r1 + 4096
            int r6 = r4.length     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L47 java.io.EOFException -> L78
            if (r5 <= r6) goto L31
            int r5 = r4.length     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L47 java.io.EOFException -> L78
            int r5 = r5 * 2
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L47 java.io.EOFException -> L78
            java.lang.System.arraycopy(r4, r3, r5, r3, r1)     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L47 java.io.EOFException -> L78
            r4 = r5
        L31:
            int r5 = r9.read(r4, r1, r2)     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L47 java.io.EOFException -> L78
            if (r5 <= 0) goto L93
            int r1 = r1 + r5
            if (r8 <= 0) goto L23
            if (r1 <= r8) goto L23
            java.lang.String r8 = "NetworkUtils"
            java.lang.String r2 = "entity length did exceed given maxLength"
            com.bytedance.common.push.utility.a.d(r8, r2)     // Catch: java.lang.Throwable -> L17 java.io.IOException -> L47 java.io.EOFException -> L78
            a(r9)
            return r0
        L47:
            r8 = move-exception
            java.lang.String r2 = r8.getMessage()     // Catch: java.lang.Throwable -> L17
            if (r7 == 0) goto L77
            if (r1 <= 0) goto L77
            java.lang.String r7 = "CRC mismatch"
            boolean r7 = r7.equals(r2)     // Catch: java.lang.Throwable -> L17
            if (r7 != 0) goto L60
            java.lang.String r7 = "Size mismatch"
            boolean r7 = r7.equals(r2)     // Catch: java.lang.Throwable -> L17
            if (r7 == 0) goto L77
        L60:
            java.lang.String r7 = "NetworkUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L17
            r2.<init>()     // Catch: java.lang.Throwable -> L17
            java.lang.String r5 = "ungzip got exception "
            r2.append(r5)     // Catch: java.lang.Throwable -> L17
            r2.append(r8)     // Catch: java.lang.Throwable -> L17
            java.lang.String r8 = r2.toString()     // Catch: java.lang.Throwable -> L17
            com.bytedance.common.push.utility.a.d(r7, r8)     // Catch: java.lang.Throwable -> L17
            goto L93
        L77:
            throw r8     // Catch: java.lang.Throwable -> L17
        L78:
            r8 = move-exception
            if (r7 == 0) goto L9f
            if (r1 <= 0) goto L9f
            java.lang.String r7 = "NetworkUtils"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L17
            r2.<init>()     // Catch: java.lang.Throwable -> L17
            java.lang.String r5 = "ungzip got exception "
            r2.append(r5)     // Catch: java.lang.Throwable -> L17
            r2.append(r8)     // Catch: java.lang.Throwable -> L17
            java.lang.String r8 = r2.toString()     // Catch: java.lang.Throwable -> L17
            com.bytedance.common.push.utility.a.d(r7, r8)     // Catch: java.lang.Throwable -> L17
        L93:
            if (r1 <= 0) goto L9b
            r10[r3] = r1     // Catch: java.lang.Throwable -> L17
            a(r9)
            return r4
        L9b:
            a(r9)
            return r0
        L9f:
            throw r8     // Catch: java.lang.Throwable -> L17
        La0:
            a(r9)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.push.g.h.a(boolean, int, java.io.InputStream, int[]):byte[]");
    }
}
