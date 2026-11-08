package com.mbridge.msdk.foundation.same.c;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.mbridge.msdk.foundation.tools.q;

/* compiled from: CommonBitmapUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static Bitmap a(String str) {
        Bitmap bitmap = null;
        if (!q.a(str)) {
            return null;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(str, options);
            options.inJustDecodeBounds = false;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inDither = true;
            return BitmapFactory.decodeFile(str, options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            System.gc();
            b.b();
            try {
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str, options);
                try {
                    options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                    return bitmapDecodeFile;
                } catch (OutOfMemoryError e3) {
                    e = e3;
                    bitmap = bitmapDecodeFile;
                    e.printStackTrace();
                    return bitmap;
                }
            } catch (OutOfMemoryError e4) {
                e = e4;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x0044 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.io.InputStream a(android.graphics.Bitmap r4) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L28
            r1.<init>()     // Catch: java.lang.Throwable -> L25 java.lang.Exception -> L28
            android.graphics.Bitmap$CompressFormat r2 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch: java.lang.Exception -> L23 java.lang.Throwable -> L41
            r3 = 100
            r4.compress(r2, r3, r1)     // Catch: java.lang.Exception -> L23 java.lang.Throwable -> L41
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch: java.lang.Exception -> L23 java.lang.Throwable -> L41
            byte[] r2 = r1.toByteArray()     // Catch: java.lang.Exception -> L23 java.lang.Throwable -> L41
            r4.<init>(r2)     // Catch: java.lang.Exception -> L23 java.lang.Throwable -> L41
            r1.close()     // Catch: java.lang.Exception -> L1a
            goto L40
        L1a:
            r0 = move-exception
            boolean r1 = com.mbridge.msdk.MBridgeConstans.DEBUG
            if (r1 == 0) goto L40
            r0.printStackTrace()
            goto L40
        L23:
            r4 = move-exception
            goto L2a
        L25:
            r4 = move-exception
            r1 = r0
            goto L42
        L28:
            r4 = move-exception
            r1 = r0
        L2a:
            boolean r2 = com.mbridge.msdk.MBridgeConstans.DEBUG     // Catch: java.lang.Throwable -> L41
            if (r2 == 0) goto L31
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L41
        L31:
            if (r1 == 0) goto L3f
            r1.close()     // Catch: java.lang.Exception -> L37
            goto L3f
        L37:
            r4 = move-exception
            boolean r1 = com.mbridge.msdk.MBridgeConstans.DEBUG
            if (r1 == 0) goto L3f
            r4.printStackTrace()
        L3f:
            r4 = r0
        L40:
            return r4
        L41:
            r4 = move-exception
        L42:
            if (r1 == 0) goto L50
            r1.close()     // Catch: java.lang.Exception -> L48
            goto L50
        L48:
            r0 = move-exception
            boolean r1 = com.mbridge.msdk.MBridgeConstans.DEBUG
            if (r1 == 0) goto L50
            r0.printStackTrace()
        L50:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mbridge.msdk.foundation.same.c.a.a(android.graphics.Bitmap):java.io.InputStream");
    }
}
