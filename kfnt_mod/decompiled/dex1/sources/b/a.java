package b;

import anet.channel.util.HttpConstant;
import b.v;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.annotation.Nullable;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a {

    /* renamed from: a, reason: collision with root package name */
    final v f747a;

    /* renamed from: b, reason: collision with root package name */
    final q f748b;

    /* renamed from: c, reason: collision with root package name */
    final SocketFactory f749c;
    final b d;
    final List<aa> e;
    final List<l> f;
    final ProxySelector g;

    @Nullable
    final Proxy h;

    @Nullable
    final SSLSocketFactory i;

    @Nullable
    final HostnameVerifier j;

    @Nullable
    final g k;

    public a(String str, int i, q qVar, SocketFactory socketFactory, @Nullable SSLSocketFactory sSLSocketFactory, @Nullable HostnameVerifier hostnameVerifier, @Nullable g gVar, b bVar, @Nullable Proxy proxy, List<aa> list, List<l> list2, ProxySelector proxySelector) {
        this.f747a = new v.a().a(sSLSocketFactory != null ? "https" : HttpConstant.HTTP).f(str).a(i).c();
        if (qVar == null) {
            throw new NullPointerException("dns == null");
        }
        this.f748b = qVar;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.f749c = socketFactory;
        if (bVar == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.d = bVar;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.e = b.a.c.a(list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.f = b.a.c.a(list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.g = proxySelector;
        this.h = proxy;
        this.i = sSLSocketFactory;
        this.j = hostnameVerifier;
        this.k = gVar;
    }

    public v a() {
        return this.f747a;
    }

    public q b() {
        return this.f748b;
    }

    public SocketFactory c() {
        return this.f749c;
    }

    public b d() {
        return this.d;
    }

    public List<aa> e() {
        return this.e;
    }

    public List<l> f() {
        return this.f;
    }

    public ProxySelector g() {
        return this.g;
    }

    @Nullable
    public Proxy h() {
        return this.h;
    }

    @Nullable
    public SSLSocketFactory i() {
        return this.i;
    }

    @Nullable
    public HostnameVerifier j() {
        return this.j;
    }

    @Nullable
    public g k() {
        return this.k;
    }

    public boolean equals(@Nullable Object obj) {
        if (obj instanceof a) {
            a aVar = (a) obj;
            if (this.f747a.equals(aVar.f747a) && a(aVar)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((((((((((((((((527 + this.f747a.hashCode()) * 31) + this.f748b.hashCode()) * 31) + this.d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31) + (this.h != null ? this.h.hashCode() : 0)) * 31) + (this.i != null ? this.i.hashCode() : 0)) * 31) + (this.j != null ? this.j.hashCode() : 0)) * 31) + (this.k != null ? this.k.hashCode() : 0);
    }

    boolean a(a aVar) {
        return this.f748b.equals(aVar.f748b) && this.d.equals(aVar.d) && this.e.equals(aVar.e) && this.f.equals(aVar.f) && this.g.equals(aVar.g) && b.a.c.a(this.h, aVar.h) && b.a.c.a(this.i, aVar.i) && b.a.c.a(this.j, aVar.j) && b.a.c.a(this.k, aVar.k) && a().j() == aVar.a().j();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Address{");
        sb.append(this.f747a.i());
        sb.append(":");
        sb.append(this.f747a.j());
        if (this.h != null) {
            sb.append(", proxy=");
            sb.append(this.h);
        } else {
            sb.append(", proxySelector=");
            sb.append(this.g);
        }
        sb.append(com.alipay.sdk.util.f.d);
        return sb.toString();
    }
}
