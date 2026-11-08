package com.bytedance.pangle.f;

import android.content.pm.Signature;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    private static final AtomicReference<byte[]> f4599a = new AtomicReference<>();

    public static o a(String str) throws Throwable {
        Throwable e;
        JarFile jarFile = null;
        try {
            try {
                try {
                    JarFile jarFile2 = new JarFile(str);
                    try {
                        try {
                            ArrayList<JarEntry> arrayList = new ArrayList();
                            JarEntry jarEntry = jarFile2.getJarEntry("AndroidManifest.xml");
                            if (jarEntry == null) {
                                throw new q(1, "Package " + str + " has no manifest");
                            }
                            Certificate[][] certificateArrA = a(jarFile2, jarEntry);
                            if (com.bytedance.pangle.util.b.a(certificateArrA)) {
                                throw new q(4, "Package " + str + " has no certificates at entry AndroidManifest.xml");
                            }
                            Signature[] signatureArrA = d.a(certificateArrA);
                            Enumeration<JarEntry> enumerationEntries = jarFile2.entries();
                            while (enumerationEntries.hasMoreElements()) {
                                JarEntry jarEntryNextElement = enumerationEntries.nextElement();
                                if (!jarEntryNextElement.isDirectory()) {
                                    String name = jarEntryNextElement.getName();
                                    if (!name.startsWith("META-INF/") && !name.equals("AndroidManifest.xml")) {
                                        arrayList.add(jarEntryNextElement);
                                    }
                                }
                            }
                            for (JarEntry jarEntry2 : arrayList) {
                                Certificate[][] certificateArrA2 = a(jarFile2, jarEntry2);
                                if (com.bytedance.pangle.util.b.a(certificateArrA2)) {
                                    throw new q(4, "Package " + str + " has no certificates at entry " + jarEntry2.getName());
                                }
                                if (!o.a(signatureArrA, d.a(certificateArrA2))) {
                                    throw new q(3, "Package " + str + " has mismatched certificates at entry " + jarEntry2.getName());
                                }
                            }
                            o oVar = new o(signatureArrA, 1, null, null, null);
                            try {
                                jarFile2.close();
                            } catch (Exception unused) {
                            }
                            return oVar;
                        } catch (IOException | RuntimeException e2) {
                            e = e2;
                            throw new q(4, "Failed to collect certificates from ".concat(String.valueOf(str)), e);
                        }
                    } catch (GeneralSecurityException e3) {
                        e = e3;
                        throw new q(2, "Failed to collect certificates from ".concat(String.valueOf(str)), e);
                    }
                } catch (Throwable th) {
                    th = th;
                    if (0 != 0) {
                        try {
                            jarFile.close();
                        } catch (Exception unused2) {
                        }
                    }
                    throw th;
                }
            } catch (IOException | RuntimeException e4) {
                e = e4;
            } catch (GeneralSecurityException e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x0061 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.security.cert.Certificate[][] a(java.util.jar.JarFile r7, java.util.jar.JarEntry r8) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.InputStream r1 = r7.getInputStream(r8)     // Catch: java.lang.Throwable -> L37 java.lang.Throwable -> L3a
            java.util.concurrent.atomic.AtomicReference<byte[]> r2 = com.bytedance.pangle.f.a.f4599a     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            java.lang.Object r0 = r2.getAndSet(r0)     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            byte[] r0 = (byte[]) r0     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            if (r0 != 0) goto L13
            r0 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r0]     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
        L13:
            int r2 = r0.length     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            r3 = 0
            int r2 = r1.read(r0, r3, r2)     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            r4 = -1
            if (r2 != r4) goto L13
            java.util.concurrent.atomic.AtomicReference<byte[]> r2 = com.bytedance.pangle.f.a.f4599a     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            r2.set(r0)     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            r0 = 1
            java.security.cert.Certificate[][] r0 = new java.security.cert.Certificate[r0][]     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            java.security.cert.Certificate[] r2 = r8.getCertificates()     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            r0[r3] = r2     // Catch: java.lang.Throwable -> L33 java.lang.Throwable -> L35
            if (r1 == 0) goto L32
            r1.close()     // Catch: java.lang.RuntimeException -> L30 java.lang.Exception -> L32
            goto L32
        L30:
            r7 = move-exception
            throw r7
        L32:
            return r0
        L33:
            r7 = move-exception
            goto L5f
        L35:
            r0 = move-exception
            goto L3e
        L37:
            r7 = move-exception
            r1 = r0
            goto L5f
        L3a:
            r1 = move-exception
            r6 = r1
            r1 = r0
            r0 = r6
        L3e:
            com.bytedance.pangle.f.q r2 = new com.bytedance.pangle.f.q     // Catch: java.lang.Throwable -> L33
            r3 = 5
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L33
            java.lang.String r5 = "Failed reading "
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L33
            java.lang.String r8 = r8.getName()     // Catch: java.lang.Throwable -> L33
            r4.append(r8)     // Catch: java.lang.Throwable -> L33
            java.lang.String r8 = " in "
            r4.append(r8)     // Catch: java.lang.Throwable -> L33
            r4.append(r7)     // Catch: java.lang.Throwable -> L33
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L33
            r2.<init>(r3, r7, r0)     // Catch: java.lang.Throwable -> L33
            throw r2     // Catch: java.lang.Throwable -> L33
        L5f:
            if (r1 == 0) goto L67
            r1.close()     // Catch: java.lang.RuntimeException -> L65 java.lang.Exception -> L67
            goto L67
        L65:
            r7 = move-exception
            throw r7
        L67:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.f.a.a(java.util.jar.JarFile, java.util.jar.JarEntry):java.security.cert.Certificate[][]");
    }
}
