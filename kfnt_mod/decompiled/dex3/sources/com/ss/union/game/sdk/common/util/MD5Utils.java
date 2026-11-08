package com.ss.union.game.sdk.common.util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MD5Utils {
    private MD5Utils() {
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getMD5Str(java.lang.String r5) throws java.security.NoSuchAlgorithmException {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 == 0) goto L9
            java.lang.String r5 = ""
            return r5
        L9:
            r0 = 0
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch: java.io.UnsupportedEncodingException -> L21 java.security.NoSuchAlgorithmException -> L27
            r1.reset()     // Catch: java.io.UnsupportedEncodingException -> L1d java.security.NoSuchAlgorithmException -> L1f
            java.lang.String r0 = "UTF-8"
            byte[] r5 = r5.getBytes(r0)     // Catch: java.io.UnsupportedEncodingException -> L1d java.security.NoSuchAlgorithmException -> L1f
            r1.update(r5)     // Catch: java.io.UnsupportedEncodingException -> L1d java.security.NoSuchAlgorithmException -> L1f
            goto L2c
        L1d:
            r5 = move-exception
            goto L23
        L1f:
            r5 = move-exception
            goto L29
        L21:
            r5 = move-exception
            r1 = r0
        L23:
            r5.printStackTrace()
            goto L2c
        L27:
            r5 = move-exception
            r1 = r0
        L29:
            r5.printStackTrace()
        L2c:
            byte[] r5 = r1.digest()
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            r1 = 0
            r2 = 0
        L37:
            int r3 = r5.length
            if (r2 >= r3) goto L68
            r3 = r5[r2]
            r3 = r3 & 255(0xff, float:3.57E-43)
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            int r3 = r3.length()
            r4 = 1
            if (r3 != r4) goto L5a
            java.lang.String r3 = "0"
            r0.append(r3)
            r3 = r5[r2]
            r3 = r3 & 255(0xff, float:3.57E-43)
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            r0.append(r3)
            goto L65
        L5a:
            r3 = r5[r2]
            r3 = r3 & 255(0xff, float:3.57E-43)
            java.lang.String r3 = java.lang.Integer.toHexString(r3)
            r0.append(r3)
        L65:
            int r2 = r2 + 1
            goto L37
        L68:
            int r5 = r0.length()
            java.lang.String r5 = r0.substring(r1, r5)
            java.lang.String r5 = r5.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.common.util.MD5Utils.getMD5Str(java.lang.String):java.lang.String");
    }
}
