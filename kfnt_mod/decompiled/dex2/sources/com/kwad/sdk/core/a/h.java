package com.kwad.sdk.core.a;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h {
    private static int a(KeyFactory keyFactory, Key key) {
        try {
            return (((RSAPublicKeySpec) keyFactory.getKeySpec(key, RSAPublicKeySpec.class)).getModulus().bitLength() / 8) - 11;
        } catch (Exception unused) {
            return 117;
        }
    }

    public static byte[] a(byte[] bArr, String str) {
        return a(bArr, str, "RSA/ECB/PKCS1Padding");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.ByteArrayOutputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(byte[] r5, java.lang.String r6, java.lang.String r7) throws java.lang.Throwable {
        /*
            com.kwad.sdk.core.a.c$a r0 = com.kwad.sdk.core.a.c.b()
            byte[] r6 = r0.a(r6)
            java.security.spec.X509EncodedKeySpec r0 = new java.security.spec.X509EncodedKeySpec
            r0.<init>(r6)
            java.lang.String r6 = "RSA"
            java.security.KeyFactory r6 = java.security.KeyFactory.getInstance(r6)
            java.security.PublicKey r0 = r6.generatePublic(r0)
            javax.crypto.Cipher r7 = javax.crypto.Cipher.getInstance(r7)
            r1 = 1
            r7.init(r1, r0)
            int r6 = a(r6, r0)
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            r1.<init>()     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4f
            int r2 = r5.length     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L60
            r3 = 0
        L2b:
            int r4 = r5.length     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L60
            if (r3 >= r4) goto L3d
            int r4 = r2 - r3
            if (r4 >= r6) goto L33
            goto L34
        L33:
            r4 = r6
        L34:
            byte[] r4 = r7.doFinal(r5, r3, r4)     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L60
            r1.write(r4)     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L60
            int r3 = r3 + r6
            goto L2b
        L3d:
            byte[] r5 = r1.toByteArray()     // Catch: java.lang.Exception -> L4a java.lang.Throwable -> L60
            r1.close()     // Catch: java.lang.Exception -> L45
            goto L5f
        L45:
            r6 = move-exception
            com.kwad.sdk.core.d.a.a(r6)
            goto L5f
        L4a:
            r5 = move-exception
            goto L51
        L4c:
            r5 = move-exception
            r1 = r0
            goto L61
        L4f:
            r5 = move-exception
            r1 = r0
        L51:
            com.kwad.sdk.core.d.a.a(r5)     // Catch: java.lang.Throwable -> L60
            if (r1 == 0) goto L5e
            r1.close()     // Catch: java.lang.Exception -> L5a
            goto L5e
        L5a:
            r5 = move-exception
            com.kwad.sdk.core.d.a.a(r5)
        L5e:
            r5 = r0
        L5f:
            return r5
        L60:
            r5 = move-exception
        L61:
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.lang.Exception -> L67
            goto L6b
        L67:
            r6 = move-exception
            com.kwad.sdk.core.d.a.a(r6)
        L6b:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.a.h.a(byte[], java.lang.String, java.lang.String):byte[]");
    }

    private static int b(KeyFactory keyFactory, Key key) {
        try {
            return ((RSAPrivateKeySpec) keyFactory.getKeySpec(key, RSAPrivateKeySpec.class)).getModulus().bitLength() / 8;
        } catch (Exception unused) {
            return 128;
        }
    }

    public static String b(byte[] bArr, String str) {
        return new String(b(bArr, str, "RSA/ECB/PKCS1Padding"), C.UTF8_NAME);
    }

    public static byte[] b(byte[] bArr, String str, String str2) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(c.b().a(str));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKeyGeneratePrivate = keyFactory.generatePrivate(pKCS8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance(str2);
        cipher.init(2, privateKeyGeneratePrivate);
        int iB = b(keyFactory, privateKeyGeneratePrivate);
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                int length = bArr.length;
                for (int i = 0; i < length; i += iB) {
                    int i2 = length - i;
                    if (i2 >= iB) {
                        i2 = iB;
                    }
                    byteArrayOutputStream.write(cipher.doFinal(bArr, i, i2));
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                    return byteArray;
                } catch (Exception e) {
                    com.kwad.sdk.core.d.a.a(e);
                    return byteArray;
                }
            } catch (Exception unused) {
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e2) {
                        com.kwad.sdk.core.d.a.a(e2);
                    }
                }
                return null;
            } catch (Throwable th) {
                th = th;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Exception e3) {
                        com.kwad.sdk.core.d.a.a(e3);
                    }
                }
                throw th;
            }
        } catch (Exception unused2) {
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
        }
    }
}
