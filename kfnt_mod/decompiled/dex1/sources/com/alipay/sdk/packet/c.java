package com.alipay.sdk.packet;

import com.alipay.sdk.util.k;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2617a;

    /* renamed from: b, reason: collision with root package name */
    private String f2618b = k.a(24);

    public c(boolean z) {
        this.f2617a = z;
    }

    public d a(b bVar, boolean z, String str) {
        byte[] bArrA;
        if (bVar == null) {
            return null;
        }
        byte[] bytes = bVar.a().getBytes();
        byte[] bytes2 = bVar.b().getBytes();
        if (z) {
            try {
                bytes2 = com.alipay.sdk.encrypt.b.a(bytes2);
            } catch (Exception unused) {
                z = false;
            }
        }
        if (this.f2617a) {
            bArrA = a(bytes, a(this.f2618b, com.alipay.sdk.cons.a.e), a(this.f2618b, bytes2, str));
        } else {
            bArrA = a(bytes, bytes2);
        }
        return new d(z, bArrA);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0079 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.alipay.sdk.packet.b a(com.alipay.sdk.packet.d r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            java.io.ByteArrayInputStream r1 = new java.io.ByteArrayInputStream     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            byte[] r2 = r6.b()     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L5c java.lang.Exception -> L5f
            r2 = 5
            byte[] r3 = new byte[r2]     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r1.read(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r4.<init>(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            int r3 = a(r4)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            byte[] r3 = new byte[r3]     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r1.read(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            r4.<init>(r3)     // Catch: java.lang.Exception -> L59 java.lang.Throwable -> L76
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r1.read(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r3.<init>(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            int r2 = a(r3)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            if (r2 <= 0) goto L52
            byte[] r2 = new byte[r2]     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r1.read(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            boolean r3 = r5.f2617a     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            if (r3 == 0) goto L42
            java.lang.String r3 = r5.f2618b     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            byte[] r2 = b(r3, r2, r7)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
        L42:
            boolean r6 = r6.a()     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            if (r6 == 0) goto L4c
            byte[] r2 = com.alipay.sdk.encrypt.b.b(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
        L4c:
            java.lang.String r6 = new java.lang.String     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            r6.<init>(r2)     // Catch: java.lang.Exception -> L57 java.lang.Throwable -> L76
            goto L53
        L52:
            r6 = r0
        L53:
            r1.close()     // Catch: java.lang.Exception -> L6b
            goto L6b
        L57:
            r6 = move-exception
            goto L62
        L59:
            r6 = move-exception
            r4 = r0
            goto L62
        L5c:
            r6 = move-exception
            r1 = r0
            goto L77
        L5f:
            r6 = move-exception
            r1 = r0
            r4 = r1
        L62:
            com.alipay.sdk.util.c.a(r6)     // Catch: java.lang.Throwable -> L76
            if (r1 == 0) goto L6a
            r1.close()     // Catch: java.lang.Exception -> L6a
        L6a:
            r6 = r0
        L6b:
            if (r4 != 0) goto L70
            if (r6 != 0) goto L70
            return r0
        L70:
            com.alipay.sdk.packet.b r7 = new com.alipay.sdk.packet.b
            r7.<init>(r4, r6)
            return r7
        L76:
            r6 = move-exception
        L77:
            if (r1 == 0) goto L7c
            r1.close()     // Catch: java.lang.Exception -> L7c
        L7c:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.packet.c.a(com.alipay.sdk.packet.d, java.lang.String):com.alipay.sdk.packet.b");
    }

    private static byte[] a(String str, String str2) {
        return com.alipay.sdk.encrypt.d.a(str, str2);
    }

    private static byte[] a(String str, byte[] bArr, String str2) {
        return com.alipay.sdk.encrypt.e.a(str, bArr, str2);
    }

    private static byte[] b(String str, byte[] bArr, String str2) {
        return com.alipay.sdk.encrypt.e.b(str, bArr, str2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4, types: [java.io.ByteArrayOutputStream] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.ByteArrayOutputStream, java.io.OutputStream] */
    private static byte[] a(byte[]... bArr) throws Throwable {
        DataOutputStream dataOutputStream;
        DataOutputStream dataOutputStream2;
        if (bArr != null) {
            ?? length = bArr.length;
            try {
                if (length != 0) {
                    try {
                        length = new ByteArrayOutputStream();
                        try {
                            dataOutputStream2 = new DataOutputStream(length);
                            try {
                                for (byte[] bArr2 : bArr) {
                                    dataOutputStream2.write(a(bArr2.length).getBytes());
                                    dataOutputStream2.write(bArr2);
                                }
                                dataOutputStream2.flush();
                                byte[] byteArray = length.toByteArray();
                                try {
                                    length.close();
                                } catch (Exception unused) {
                                }
                                try {
                                    dataOutputStream2.close();
                                    return byteArray;
                                } catch (Exception unused2) {
                                    return byteArray;
                                }
                            } catch (Exception e) {
                                e = e;
                                com.alipay.sdk.util.c.a(e);
                                if (length != 0) {
                                    try {
                                        length.close();
                                    } catch (Exception unused3) {
                                    }
                                }
                                if (dataOutputStream2 != null) {
                                    try {
                                        dataOutputStream2.close();
                                    } catch (Exception unused4) {
                                    }
                                }
                                return null;
                            }
                        } catch (Exception e2) {
                            e = e2;
                            dataOutputStream2 = null;
                        } catch (Throwable th) {
                            th = th;
                            dataOutputStream = null;
                            if (length != 0) {
                                try {
                                    length.close();
                                } catch (Exception unused5) {
                                }
                            }
                            if (dataOutputStream != null) {
                                try {
                                    dataOutputStream.close();
                                    throw th;
                                } catch (Exception unused6) {
                                    throw th;
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        length = 0;
                        dataOutputStream2 = null;
                    } catch (Throwable th2) {
                        th = th2;
                        length = 0;
                        dataOutputStream = null;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return null;
    }

    private static String a(int i) {
        return String.format(Locale.getDefault(), "%05d", Integer.valueOf(i));
    }

    private static int a(String str) {
        return Integer.parseInt(str);
    }
}
