package cn.com.chinatelecom.account.api.e;

import com.facebook.stetho.dumpapp.Framer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class l {

    /* renamed from: a, reason: collision with root package name */
    private static final String f2304a = "l";

    /* renamed from: b, reason: collision with root package name */
    private static byte[] f2305b = {68, 64, 94, Framer.STDOUT_FRAME_PREFIX, 69, 35, Framer.STDERR_FRAME_PREFIX, 83};

    public static String a(byte[] bArr) {
        try {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr2[i] = bArr[i];
                for (byte b2 : f2305b) {
                    bArr2[i] = (byte) (b2 ^ bArr2[i]);
                }
            }
            return new String(bArr2);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}
