package com.qq.e.comm.net.rr;

import android.annotation.TargetApi;
import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static Cipher b;
    private static Cipher c;
    private static final byte[] a = {91, -62};
    public static final String d = String.format("AES/%s/PKCS7Padding", "ECB");
    public static final byte[] e = Base64.decode("4M3PpUC4Vu1uMp+Y0Mxd+vfc6v4ggJAINfgTlH74pis=", 0);

    /* renamed from: com.qq.e.comm.net.rr.a$a, reason: collision with other inner class name */
    private static class C0001a extends Exception {
        public C0001a(String str, Throwable th) {
            super(str, th);
        }
    }

    public static class b extends Exception {
        public b(String str, Throwable th) {
            super(str, th);
        }
    }

    private static byte[] a(byte[] bArr) throws C0001a {
        Cipher cipher;
        synchronized (a.class) {
            cipher = c;
            if (cipher == null) {
                try {
                    cipher = Cipher.getInstance(d);
                    cipher.init(2, new SecretKeySpec(e, "AES"));
                    c = cipher;
                } catch (Exception e2) {
                    throw new C0001a("Fail To Init Cipher", e2);
                }
            }
        }
        try {
            return cipher.doFinal(bArr);
        } catch (Exception e3) {
            throw new C0001a("Exception While dencrypt byte array", e3);
        }
    }

    private static byte[] b(byte[] bArr) throws C0001a {
        Cipher cipher;
        synchronized (a.class) {
            cipher = b;
            if (cipher == null) {
                try {
                    cipher = Cipher.getInstance(d);
                    cipher.init(1, new SecretKeySpec(e, "AES"));
                    b = cipher;
                } catch (Exception e2) {
                    throw new C0001a("Fail To Init Cipher", e2);
                }
            }
        }
        try {
            return cipher.doFinal(bArr);
        } catch (Exception e3) {
            throw new C0001a("Exception While encrypt byte array", e3);
        }
    }

    public static byte[] c(byte[] bArr) throws b, IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.write(a);
            dataOutputStream.writeByte(1);
            dataOutputStream.writeByte(2);
            dataOutputStream.write(b(f(bArr)));
            dataOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e2) {
            throw new b("Exception while packaging byte array", e2);
        }
    }

    @TargetApi(9)
    public static byte[] d(byte[] bArr) throws b, IOException {
        if (bArr == null || bArr.length < 4) {
            throw new b("S2SS Package FormatError", null);
        }
        try {
            byte[] bArr2 = new byte[4];
            new DataInputStream(new ByteArrayInputStream(bArr)).read(bArr2);
            if (a[0] == bArr2[0] && a[1] == bArr2[1] && 1 == bArr2[2] && 2 == bArr2[3]) {
                return e(a(Arrays.copyOfRange(bArr, 4, bArr.length)));
            }
            throw new b("S2SS Package Magic/Version FormatError", null);
        } catch (Exception e2) {
            throw new b("Exception while packaging byte array", e2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] e(byte[] r6) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r6 == 0) goto L72
            int r1 = r6.length
            if (r1 != 0) goto L8
            goto L72
        L8:
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream
            r1.<init>(r6)
            java.io.ByteArrayOutputStream r6 = new java.io.ByteArrayOutputStream
            r6.<init>()
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r2 = new byte[r2]
            java.util.zip.GZIPInputStream r3 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L46
            r3.<init>(r1)     // Catch: java.lang.Throwable -> L42 java.lang.Exception -> L46
        L1b:
            int r4 = r3.read(r2)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            r5 = -1
            if (r4 == r5) goto L27
            r5 = 0
            r6.write(r2, r5, r4)     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            goto L1b
        L27:
            r6.flush()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            byte[] r2 = r6.toByteArray()     // Catch: java.lang.Throwable -> L3e java.lang.Exception -> L40
            r3.close()     // Catch: java.lang.Exception -> L38
            r1.close()     // Catch: java.lang.Exception -> L38
            r6.close()     // Catch: java.lang.Exception -> L38
            goto L3c
        L38:
            r6 = move-exception
            r6.printStackTrace()
        L3c:
            r6 = r2
            goto L72
        L3e:
            r0 = move-exception
            goto L5f
        L40:
            r2 = move-exception
            goto L48
        L42:
            r2 = move-exception
            r3 = r0
            r0 = r2
            goto L5f
        L46:
            r2 = move-exception
            r3 = r0
        L48:
            r2.printStackTrace()     // Catch: java.lang.Throwable -> L3e
            if (r3 == 0) goto L53
            r3.close()     // Catch: java.lang.Exception -> L51
            goto L53
        L51:
            r6 = move-exception
            goto L5a
        L53:
            r1.close()     // Catch: java.lang.Exception -> L51
            r6.close()     // Catch: java.lang.Exception -> L51
            goto L5d
        L5a:
            r6.printStackTrace()
        L5d:
            r6 = r0
            goto L72
        L5f:
            if (r3 == 0) goto L67
            r3.close()     // Catch: java.lang.Exception -> L65
            goto L67
        L65:
            r6 = move-exception
            goto L6e
        L67:
            r1.close()     // Catch: java.lang.Exception -> L65
            r6.close()     // Catch: java.lang.Exception -> L65
            goto L71
        L6e:
            r6.printStackTrace()
        L71:
            throw r0
        L72:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.net.rr.a.e(byte[]):byte[]");
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0045 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] f(byte[] r3) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r3 == 0) goto L53
            int r1 = r3.length
            if (r1 != 0) goto L7
            goto L53
        L7:
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.util.zip.GZIPOutputStream r2 = new java.util.zip.GZIPOutputStream     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            r2.<init>(r1)     // Catch: java.lang.Throwable -> L29 java.lang.Exception -> L2b
            r2.write(r3)     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L41
            r2.finish()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L41
            byte[] r3 = r1.toByteArray()     // Catch: java.lang.Exception -> L27 java.lang.Throwable -> L41
            r2.close()     // Catch: java.lang.Exception -> L22
            r1.close()     // Catch: java.lang.Exception -> L22
            goto L53
        L22:
            r0 = move-exception
            r0.printStackTrace()
            goto L53
        L27:
            r3 = move-exception
            goto L2d
        L29:
            r3 = move-exception
            goto L43
        L2b:
            r3 = move-exception
            r2 = r0
        L2d:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L38
            r2.close()     // Catch: java.lang.Exception -> L36
            goto L38
        L36:
            r3 = move-exception
            goto L3c
        L38:
            r1.close()     // Catch: java.lang.Exception -> L36
            goto L3f
        L3c:
            r3.printStackTrace()
        L3f:
            r3 = r0
            goto L53
        L41:
            r3 = move-exception
            r0 = r2
        L43:
            if (r0 == 0) goto L4b
            r0.close()     // Catch: java.lang.Exception -> L49
            goto L4b
        L49:
            r0 = move-exception
            goto L4f
        L4b:
            r1.close()     // Catch: java.lang.Exception -> L49
            goto L52
        L4f:
            r0.printStackTrace()
        L52:
            throw r3
        L53:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.e.comm.net.rr.a.f(byte[]):byte[]");
    }
}
