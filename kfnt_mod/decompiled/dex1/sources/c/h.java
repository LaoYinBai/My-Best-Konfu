package c;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class h implements x {

    /* renamed from: a, reason: collision with root package name */
    private final x f2144a;

    public h(x xVar) {
        if (xVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f2144a = xVar;
    }

    public final x b() {
        return this.f2144a;
    }

    @Override // c.x
    public void a_(c cVar, long j) throws IOException {
        this.f2144a.a_(cVar, j);
    }

    @Override // c.x, java.io.Flushable
    public void flush() throws IOException {
        this.f2144a.flush();
    }

    @Override // c.x
    public z a() {
        return this.f2144a.a();
    }

    @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f2144a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f2144a.toString() + ")";
    }
}
