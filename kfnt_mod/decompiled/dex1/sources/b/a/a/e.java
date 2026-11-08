package b.a.a;

import c.h;
import c.x;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class e extends h {

    /* renamed from: a, reason: collision with root package name */
    private boolean f779a;

    protected void a(IOException iOException) {
    }

    e(x xVar) {
        super(xVar);
    }

    @Override // c.h, c.x
    public void a_(c.c cVar, long j) throws IOException {
        if (this.f779a) {
            cVar.i(j);
            return;
        }
        try {
            super.a_(cVar, j);
        } catch (IOException e) {
            this.f779a = true;
            a(e);
        }
    }

    @Override // c.h, c.x, java.io.Flushable
    public void flush() throws IOException {
        if (this.f779a) {
            return;
        }
        try {
            super.flush();
        } catch (IOException e) {
            this.f779a = true;
            a(e);
        }
    }

    @Override // c.h, c.x, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f779a) {
            return;
        }
        try {
            super.close();
        } catch (IOException e) {
            this.f779a = true;
            a(e);
        }
    }
}
