package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122;

import java.net.InetSocketAddress;
import java.net.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c0 {

    /* renamed from: a, reason: collision with root package name */
    final a f1682a;

    /* renamed from: b, reason: collision with root package name */
    final Proxy f1683b;

    /* renamed from: c, reason: collision with root package name */
    final InetSocketAddress f1684c;

    public c0(a aVar, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (aVar == null) {
            throw new NullPointerException("address == null");
        }
        if (proxy == null) {
            throw new NullPointerException("proxy == null");
        }
        if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        }
        this.f1682a = aVar;
        this.f1683b = proxy;
        this.f1684c = inetSocketAddress;
    }

    public a a() {
        return this.f1682a;
    }

    public Proxy b() {
        return this.f1683b;
    }

    public boolean c() {
        return this.f1682a.i != null && this.f1683b.type() == Proxy.Type.HTTP;
    }

    public InetSocketAddress d() {
        return this.f1684c;
    }

    public boolean equals(Object obj) {
        if (obj instanceof c0) {
            c0 c0Var = (c0) obj;
            if (c0Var.f1682a.equals(this.f1682a) && c0Var.f1683b.equals(this.f1683b) && c0Var.f1684c.equals(this.f1684c)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f1682a.hashCode() + 527) * 31) + this.f1683b.hashCode()) * 31) + this.f1684c.hashCode();
    }

    public String toString() {
        return "Route{" + this.f1684c + com.alipay.sdk.util.f.d;
    }
}
