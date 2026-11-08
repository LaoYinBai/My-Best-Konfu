package c;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j extends z {

    /* renamed from: a, reason: collision with root package name */
    private z f2146a;

    public j(z zVar) {
        if (zVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f2146a = zVar;
    }

    public final z a() {
        return this.f2146a;
    }

    public final j a(z zVar) {
        if (zVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f2146a = zVar;
        return this;
    }

    @Override // c.z
    public z a(long j, TimeUnit timeUnit) {
        return this.f2146a.a(j, timeUnit);
    }

    @Override // c.z
    public long b_() {
        return this.f2146a.b_();
    }

    @Override // c.z
    public boolean c_() {
        return this.f2146a.c_();
    }

    @Override // c.z
    public long d() {
        return this.f2146a.d();
    }

    @Override // c.z
    public z a(long j) {
        return this.f2146a.a(j);
    }

    @Override // c.z
    public z d_() {
        return this.f2146a.d_();
    }

    @Override // c.z
    public z f() {
        return this.f2146a.f();
    }

    @Override // c.z
    public void g() throws IOException {
        this.f2146a.g();
    }
}
