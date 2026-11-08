package c;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class i implements y {

    /* renamed from: a, reason: collision with root package name */
    private final y f2145a;

    public i(y yVar) {
        if (yVar == null) {
            throw new IllegalArgumentException("delegate == null");
        }
        this.f2145a = yVar;
    }

    public final y b() {
        return this.f2145a;
    }

    @Override // c.y
    public long a(c cVar, long j) throws IOException {
        return this.f2145a.a(cVar, j);
    }

    @Override // c.y
    public z a() {
        return this.f2145a.a();
    }

    @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f2145a.close();
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.f2145a.toString() + ")";
    }
}
