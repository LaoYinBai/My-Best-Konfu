package c.a.a.a.a;

import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j {

    /* renamed from: a, reason: collision with root package name */
    public static final Charset f2104a = Charset.forName("UTF-8");

    public static byte[] a(String str) {
        return a(str, f2104a);
    }

    private static byte[] a(String str, Charset charset) {
        if (str == null) {
            return null;
        }
        return str.getBytes(charset);
    }
}
