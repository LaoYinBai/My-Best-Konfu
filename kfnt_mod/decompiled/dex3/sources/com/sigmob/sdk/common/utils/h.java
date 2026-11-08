package com.sigmob.sdk.common.utils;

import com.sigmob.logger.SigmobLog;
import java.io.FileInputStream;
import java.util.HashMap;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h {
    private static final HashMap<String, String> a = new HashMap<>();

    static {
        a.put("FFD8", "jpg");
        a.put("8950", "png");
        a.put("4749", "gif");
        a.put("4949", "tif");
        a.put("424D", "bmp");
        a.put("5745", "webp");
    }

    public static String a(String str) {
        return a.get(b(str));
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String upperCase = Integer.toHexString(b & UByte.MAX_VALUE).toUpperCase();
            if (upperCase.length() < 2) {
                sb.append(0);
            }
            sb.append(upperCase);
        }
        return sb.toString();
    }

    public static String b(String str) throws Throwable {
        String strA;
        FileInputStream fileInputStream;
        String strA2;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
            }
            try {
                try {
                    byte[] bArr = new byte[2];
                    fileInputStream.read(bArr, 0, bArr.length);
                    strA = a(bArr);
                    try {
                        if (strA.equals("5249")) {
                            fileInputStream.skip(6L);
                            fileInputStream.read(bArr, 0, bArr.length);
                            strA2 = a(bArr);
                        } else {
                            strA2 = strA;
                        }
                        try {
                            fileInputStream.close();
                            return strA2;
                        } catch (Throwable unused) {
                            return strA2;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream2 = fileInputStream;
                        SigmobLog.e(th.getMessage());
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (Throwable unused2) {
                            }
                        }
                        return strA;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    strA = null;
                }
            } catch (Throwable th4) {
                th = th4;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable unused3) {
                    }
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            strA = null;
        }
    }
}
