package com.sigmob.sdk.common.utils;

import com.sigmob.logger.SigmobLog;
import com.umeng.commonsdk.proguard.az;
import java.io.File;
import java.io.FileInputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class k {
    private static final String a = "k";

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] bytes = str.getBytes("UTF-8");
            MessageDigest messageDigest = MessageDigest.getInstance("md5");
            messageDigest.update(bytes);
            byte[] bArrDigest = messageDigest.digest();
            char[] cArr2 = new char[bArrDigest.length * 2];
            int i = 0;
            for (byte b : bArrDigest) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & az.m];
            }
            return new String(cArr2);
        } catch (Throwable th) {
            SigmobLog.e(th.getMessage());
            return null;
        }
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & UByte.MAX_VALUE);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.io.FileInputStream, java.io.InputStream] */
    public static String b(String str) throws Throwable {
        DigestInputStream digestInputStream;
        DigestInputStream digestInputStream2;
        if (str != null) {
            ?? Exists = new File(str).exists();
            try {
                if (Exists != 0) {
                    try {
                        Exists = new FileInputStream(str);
                        try {
                            digestInputStream2 = new DigestInputStream(Exists, MessageDigest.getInstance("md5"));
                            try {
                                while (digestInputStream2.read(new byte[com.ss.union.game.sdk.feedback.picture.select.d.d]) > 0) {
                                }
                                String strA = a(digestInputStream2.getMessageDigest().digest());
                                try {
                                    Exists.close();
                                } catch (Throwable th) {
                                    SigmobLog.e(th.getMessage());
                                }
                                try {
                                    digestInputStream2.close();
                                } catch (Throwable th2) {
                                    SigmobLog.e(th2.getMessage());
                                }
                                return strA;
                            } catch (Throwable th3) {
                                th = th3;
                                SigmobLog.e(th.getMessage());
                                if (Exists != 0) {
                                    try {
                                        Exists.close();
                                    } catch (Throwable th4) {
                                        SigmobLog.e(th4.getMessage());
                                    }
                                }
                                if (digestInputStream2 != null) {
                                    try {
                                        digestInputStream2.close();
                                    } catch (Throwable th5) {
                                        SigmobLog.e(th5.getMessage());
                                    }
                                }
                                return null;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            digestInputStream2 = null;
                        }
                    } catch (Throwable th7) {
                        th = th7;
                        Exists = 0;
                        digestInputStream = null;
                    }
                }
            } catch (Throwable th8) {
                th = th8;
            }
        }
        return null;
    }
}
