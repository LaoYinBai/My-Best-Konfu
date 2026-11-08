package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class p {

    /* renamed from: a, reason: collision with root package name */
    private static final MessageDigest f2023a = a();

    /* renamed from: b, reason: collision with root package name */
    public static final Charset f2024b = Charset.forName("UTF-8");

    /* renamed from: c, reason: collision with root package name */
    private static final char[] f2025c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private p() {
    }

    private static MessageDigest a() {
        try {
            return MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static String a(String str) {
        byte[] bArrDigest;
        MessageDigest messageDigest = f2023a;
        if (str == null) {
            return "";
        }
        byte[] bytes = str.getBytes(f2024b);
        synchronized (p.class) {
            bArrDigest = messageDigest.digest(bytes);
        }
        return a(bArrDigest);
    }

    public static String a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return null;
        }
        char[] cArr = new char[bArr.length << 1];
        int i = 0;
        for (byte b2 : bArr) {
            int i2 = i + 1;
            char[] cArr2 = f2025c;
            cArr[i] = cArr2[(b2 & 240) >> 4];
            i = i2 + 1;
            cArr[i2] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }
}
