package c.a.a.a.a;

import java.io.BufferedInputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bh {
    /* JADX WARN: Removed duplicated region for block: B:26:0x00b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static synchronized java.lang.String a(android.content.Context r8) {
        /*
            java.lang.Class<c.a.a.a.a.bh> r0 = c.a.a.a.a.bh.class
            monitor-enter(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La9
            r1.<init>()     // Catch: java.lang.Throwable -> La9
            java.io.File r2 = r8.getFilesDir()     // Catch: java.lang.Throwable -> La9
            java.lang.String r2 = r2.getAbsolutePath()     // Catch: java.lang.Throwable -> La9
            r1.append(r2)     // Catch: java.lang.Throwable -> La9
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch: java.lang.Throwable -> La9
            java.lang.String r2 = "cid"
            r1.append(r2)     // Catch: java.lang.Throwable -> La9
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La9
            java.io.File r2 = new java.io.File     // Catch: java.lang.Throwable -> La9
            r2.<init>(r1)     // Catch: java.lang.Throwable -> La9
            boolean r2 = r2.exists()     // Catch: java.lang.Throwable -> La9
            if (r2 == 0) goto L3a
            java.lang.String r2 = a(r1)     // Catch: java.lang.Throwable -> La9
            if (r2 == 0) goto L3a
            int r3 = r2.length()     // Catch: java.lang.Throwable -> La9
            if (r3 <= 0) goto L3a
            monitor-exit(r0)
            return r2
        L3a:
            android.content.res.Resources r8 = r8.getResources()     // Catch: java.lang.Throwable -> La9
            android.content.res.AssetManager r8 = r8.getAssets()     // Catch: java.lang.Throwable -> La9
            java.lang.String r2 = "cid"
            java.io.InputStream r8 = r8.open(r2)     // Catch: java.lang.Throwable -> La9
            java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> La9
            r2.<init>()     // Catch: java.lang.Throwable -> La9
            r3 = 4096(0x1000, float:5.74E-42)
            byte[] r4 = new byte[r3]     // Catch: java.lang.Throwable -> La9
        L51:
            r5 = 0
            int r6 = r8.read(r4, r5, r3)     // Catch: java.lang.Throwable -> La9
            r7 = -1
            if (r6 == r7) goto L5d
            r2.write(r4, r5, r6)     // Catch: java.lang.Throwable -> La9
            goto L51
        L5d:
            java.io.FileOutputStream r8 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> La9
            r8.<init>(r1)     // Catch: java.lang.Throwable -> La9
            byte[] r2 = r2.toByteArray()     // Catch: java.lang.Throwable -> La9
            r8.write(r2)     // Catch: java.lang.Throwable -> La9
            r8.close()     // Catch: java.lang.Throwable -> La9
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La9
            r8.<init>()     // Catch: java.lang.Throwable -> La9
            java.lang.String r2 = "chmod 777 "
            r8.append(r2)     // Catch: java.lang.Throwable -> La9
            r8.append(r1)     // Catch: java.lang.Throwable -> La9
            java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> La9
            a(r8)     // Catch: java.lang.Throwable -> La9
            java.lang.String r8 = a(r1)     // Catch: java.lang.Throwable -> La9
            if (r8 == 0) goto L8c
            int r2 = r8.length()     // Catch: java.lang.Throwable -> La9
            if (r2 != 0) goto Lab
        L8c:
            java.io.RandomAccessFile r8 = new java.io.RandomAccessFile     // Catch: java.lang.Throwable -> La9
            java.lang.String r2 = "rw"
            r8.<init>(r1, r2)     // Catch: java.lang.Throwable -> La9
            r2 = 1
            byte[] r2 = new byte[r2]     // Catch: java.lang.Throwable -> La9
            r3 = 2
            r2[r5] = r3     // Catch: java.lang.Throwable -> La9
            r3 = 16
            r8.seek(r3)     // Catch: java.lang.Throwable -> La9
            r8.write(r2)     // Catch: java.lang.Throwable -> La9
            r8.close()     // Catch: java.lang.Throwable -> La9
            java.lang.String r8 = a(r1)     // Catch: java.lang.Throwable -> La9
            goto Lab
        La9:
            java.lang.String r8 = "0[<!>]EXCEPTION[<!>]"
        Lab:
            if (r8 == 0) goto Lb3
            int r1 = r8.length()     // Catch: java.lang.Throwable -> Lb7
            if (r1 != 0) goto Lb5
        Lb3:
            java.lang.String r8 = "0[<!>]ERROR[<!>]"
        Lb5:
            monitor-exit(r0)
            return r8
        Lb7:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a.a.bh.a(android.content.Context):java.lang.String");
    }

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

    /* JADX WARN: Removed duplicated region for block: B:61:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x008a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0064 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0080 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:85:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(java.lang.String r5) throws java.lang.Throwable {
        /*
            r0 = 0
            java.lang.Runtime r1 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5b
            java.lang.String r2 = "sh"
            java.lang.Process r1 = r1.exec(r2)     // Catch: java.lang.Throwable -> L57 java.lang.Exception -> L5b
            java.io.BufferedOutputStream r2 = new java.io.BufferedOutputStream     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.io.OutputStream r3 = r1.getOutputStream()     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L53 java.lang.Exception -> L55
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            java.io.InputStream r4 = r1.getInputStream()     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L4e java.lang.Exception -> L50
            byte[] r5 = r5.getBytes()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L7c
            r2.write(r5)     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L7c
            r5 = 10
            r2.write(r5)     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L7c
            r2.flush()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L7c
            r2.close()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L7c
            r1.waitFor()     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L7c
            java.lang.String r5 = a(r3)     // Catch: java.lang.Exception -> L4c java.lang.Throwable -> L7c
            r2.close()     // Catch: java.io.IOException -> L3a
            goto L3e
        L3a:
            r0 = move-exception
            r0.printStackTrace()
        L3e:
            r3.close()     // Catch: java.io.IOException -> L42
            goto L46
        L42:
            r0 = move-exception
            r0.printStackTrace()
        L46:
            if (r1 == 0) goto L4a
            r0 = r5
            goto L78
        L4a:
            r0 = r5
            goto L7b
        L4c:
            r5 = move-exception
            goto L5f
        L4e:
            r5 = move-exception
            goto L7e
        L50:
            r5 = move-exception
            r3 = r0
            goto L5f
        L53:
            r5 = move-exception
            goto L59
        L55:
            r5 = move-exception
            goto L5d
        L57:
            r5 = move-exception
            r1 = r0
        L59:
            r2 = r0
            goto L7e
        L5b:
            r5 = move-exception
            r1 = r0
        L5d:
            r2 = r0
            r3 = r2
        L5f:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L7c
            if (r2 == 0) goto L6c
            r2.close()     // Catch: java.io.IOException -> L68
            goto L6c
        L68:
            r5 = move-exception
            r5.printStackTrace()
        L6c:
            if (r3 == 0) goto L76
            r3.close()     // Catch: java.io.IOException -> L72
            goto L76
        L72:
            r5 = move-exception
            r5.printStackTrace()
        L76:
            if (r1 == 0) goto L7b
        L78:
            r1.destroy()
        L7b:
            return r0
        L7c:
            r5 = move-exception
            r0 = r3
        L7e:
            if (r2 == 0) goto L88
            r2.close()     // Catch: java.io.IOException -> L84
            goto L88
        L84:
            r2 = move-exception
            r2.printStackTrace()
        L88:
            if (r0 == 0) goto L92
            r0.close()     // Catch: java.io.IOException -> L8e
            goto L92
        L8e:
            r0 = move-exception
            r0.printStackTrace()
        L92:
            if (r1 == 0) goto L97
            r1.destroy()
        L97:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.a.a.a.bh.a(java.lang.String):java.lang.String");
    }
}
