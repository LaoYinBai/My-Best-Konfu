package b;

import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class o {
    private o() {
    }

    public static String a(String str, String str2) {
        return a(str, str2, b.a.c.f);
    }

    public static String a(String str, String str2, Charset charset) {
        return "Basic " + c.f.a(str + ":" + str2, charset).b();
    }
}
