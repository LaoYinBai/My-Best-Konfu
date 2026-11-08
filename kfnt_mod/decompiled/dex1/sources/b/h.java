package b;

import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class h {

    /* renamed from: a, reason: collision with root package name */
    private final String f1239a;

    /* renamed from: b, reason: collision with root package name */
    private final String f1240b;

    /* renamed from: c, reason: collision with root package name */
    private final Charset f1241c;

    public h(String str, String str2) {
        this(str, str2, b.a.c.f);
    }

    private h(String str, String str2, Charset charset) {
        if (str == null) {
            throw new NullPointerException("scheme == null");
        }
        if (str2 == null) {
            throw new NullPointerException("realm == null");
        }
        if (charset == null) {
            throw new NullPointerException("charset == null");
        }
        this.f1239a = str;
        this.f1240b = str2;
        this.f1241c = charset;
    }

    public h a(Charset charset) {
        return new h(this.f1239a, this.f1240b, charset);
    }

    public String a() {
        return this.f1239a;
    }

    public String b() {
        return this.f1240b;
    }

    public Charset c() {
        return this.f1241c;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof h) {
            h hVar = (h) obj;
            if (hVar.f1239a.equals(this.f1239a) && hVar.f1240b.equals(this.f1240b) && hVar.f1241c.equals(this.f1241c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((899 + this.f1240b.hashCode()) * 31) + this.f1239a.hashCode()) * 31) + this.f1241c.hashCode();
    }

    public String toString() {
        return this.f1239a + " realm=\"" + this.f1240b + "\" charset=\"" + this.f1241c + "\"";
    }
}
