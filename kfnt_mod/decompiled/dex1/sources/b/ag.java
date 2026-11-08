package b;

import java.net.InetSocketAddress;
import java.net.Proxy;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ag {

    /* renamed from: a, reason: collision with root package name */
    final a f1022a;

    /* renamed from: b, reason: collision with root package name */
    final Proxy f1023b;

    /* renamed from: c, reason: collision with root package name */
    final InetSocketAddress f1024c;

    public ag(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (aVar == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        this.f1022a = aVar;
        this.f1023b = proxy;
        this.f1024c = inetSocketAddress;
    }

    public a a() {
        return this.f1022a;
    }

    public Proxy b() {
        return this.f1023b;
    }

    public InetSocketAddress c() {
        return this.f1024c;
    }

    public boolean d() {
        return this.f1022a.i != null && this.f1023b.type() == Proxy.Type.HTTP;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof ag) {
            ag agVar = (ag) obj;
            if (agVar.f1022a.equals(this.f1022a) && agVar.f1023b.equals(this.f1023b) && agVar.f1024c.equals(this.f1024c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((527 + this.f1022a.hashCode()) * 31) + this.f1023b.hashCode()) * 31) + this.f1024c.hashCode();
    }

    public String toString() {
        return "Route{" + this.f1024c + com.alipay.sdk.util.f.d;
    }
}
