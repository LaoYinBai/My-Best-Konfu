package c.a.a.a.a;

import com.bytedance.common.utility.StringEncryptUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {
    public static MessageDigest a() {
        return a("MD5");
    }

    public static MessageDigest a(String str) {
        try {
            return MessageDigest.getInstance(str);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static byte[] a(byte[] bArr) {
        return a().digest(bArr);
    }

    public static String b(byte[] bArr) {
        return d.a(a(bArr));
    }

    public static MessageDigest b() {
        return a(StringEncryptUtils.SHA_1);
    }

    public static byte[] b(String str) {
        return a(j.a(str));
    }

    public static String c(String str) {
        return d.a(b(str));
    }

    public static String c(byte[] bArr) {
        return d.a(d(bArr));
    }

    public static byte[] d(byte[] bArr) {
        return b().digest(bArr);
    }
}
