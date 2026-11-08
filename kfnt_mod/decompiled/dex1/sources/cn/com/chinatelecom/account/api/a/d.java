package cn.com.chinatelecom.account.api.a;

import com.facebook.stetho.dumpapp.Framer;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static final String f2210a = "d";

    /* renamed from: b, reason: collision with root package name */
    private static final Charset f2211b = Charset.forName("UTF-8");

    /* renamed from: c, reason: collision with root package name */
    private static byte[] f2212c = {68, 64, 94, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 83};

    public static String a(byte[] bArr) {
        try {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr2[i] = bArr[i];
                for (byte b2 : f2212c) {
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
