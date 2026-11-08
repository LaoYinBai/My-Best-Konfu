package anet.channel.util;

import android.util.Base64;
import java.net.InetSocketAddress;
import java.net.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    public static g f613a;

    /* renamed from: b, reason: collision with root package name */
    private final Proxy f614b;

    /* renamed from: c, reason: collision with root package name */
    private final String f615c;
    private final String d;

    public static g a() {
        return f613a;
    }

    public Proxy b() {
        return this.f614b;
    }

    public g(String str, int i, String str2, String str3) {
        this.f614b = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i));
        this.f615c = str2;
        this.d = str3;
    }

    public String c() {
        StringBuilder sb = new StringBuilder(32);
        sb.append(this.f615c);
        sb.append(":");
        sb.append(this.d);
        String strEncodeToString = Base64.encodeToString(sb.toString().getBytes(), 0);
        StringBuilder sb2 = new StringBuilder(64);
        sb2.append("Basic ");
        sb2.append(strEncodeToString);
        return sb2.toString();
    }
}
