package b;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class r {

    /* renamed from: a, reason: collision with root package name */
    public static final r f1266a = new r() { // from class: b.r.1
    };

    public interface a {
        r a(e eVar);
    }

    public void a(e eVar) {
    }

    public void a(e eVar, long j) {
    }

    public void a(e eVar, ac acVar) {
    }

    public void a(e eVar, ae aeVar) {
    }

    public void a(e eVar, j jVar) {
    }

    public void a(e eVar, @Nullable t tVar) {
    }

    public void a(e eVar, IOException iOException) {
    }

    public void a(e eVar, String str) {
    }

    public void a(e eVar, String str, List<InetAddress> list) {
    }

    public void a(e eVar, InetSocketAddress inetSocketAddress, Proxy proxy) {
    }

    public void a(e eVar, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable aa aaVar) {
    }

    public void a(e eVar, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable aa aaVar, IOException iOException) {
    }

    public void b(e eVar) {
    }

    public void b(e eVar, long j) {
    }

    public void b(e eVar, j jVar) {
    }

    public void c(e eVar) {
    }

    public void d(e eVar) {
    }

    public void e(e eVar) {
    }

    public void f(e eVar) {
    }

    public void g(e eVar) {
    }

    static a a(r rVar) {
        return new a() { // from class: b.r.2
            @Override // b.r.a
            public r a(e eVar) {
                return r.this;
            }
        };
    }
}
