package b;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class af implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    private Reader f1015a;

    @Nullable
    public abstract x a();

    public abstract long b();

    public abstract c.e c();

    public final InputStream d() {
        return c().h();
    }

    public final byte[] e() throws IOException {
        long jB = b();
        if (jB > 2147483647L) {
            throw new IOException("Cannot buffer entire body for content length: " + jB);
        }
        c.e eVarC = c();
        try {
            byte[] bArrX = eVarC.x();
            b.a.c.a(eVarC);
            if (jB == -1 || jB == bArrX.length) {
                return bArrX;
            }
            throw new IOException("Content-Length (" + jB + ") and stream length (" + bArrX.length + ") disagree");
        } catch (Throwable th) {
            b.a.c.a(eVarC);
            throw th;
        }
    }

    public final Reader f() {
        Reader reader = this.f1015a;
        if (reader != null) {
            return reader;
        }
        a aVar = new a(c(), h());
        this.f1015a = aVar;
        return aVar;
    }

    public final String g() throws IOException {
        c.e eVarC = c();
        try {
            return eVarC.a(b.a.c.a(eVarC, h()));
        } finally {
            b.a.c.a(eVarC);
        }
    }

    private Charset h() {
        x xVarA = a();
        return xVarA != null ? xVarA.a(b.a.c.e) : b.a.c.e;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        b.a.c.a(c());
    }

    public static af a(@Nullable x xVar, String str) {
        Charset charsetC = b.a.c.e;
        if (xVar != null && (charsetC = xVar.c()) == null) {
            charsetC = b.a.c.e;
            xVar = x.a(xVar + "; charset=utf-8");
        }
        c.c cVarB = new c.c().b(str, charsetC);
        return a(xVar, cVarB.b(), cVarB);
    }

    public static af a(@Nullable x xVar, byte[] bArr) {
        return a(xVar, bArr.length, new c.c().d(bArr));
    }

    public static af a(@Nullable final x xVar, final long j, final c.e eVar) {
        if (eVar == null) {
            throw new NullPointerException("source == null");
        }
        return new af() { // from class: b.af.1
            @Override // b.af
            @Nullable
            public x a() {
                return xVar;
            }

            @Override // b.af
            public long b() {
                return j;
            }

            @Override // b.af
            public c.e c() {
                return eVar;
            }
        };
    }

    static final class a extends Reader {

        /* renamed from: a, reason: collision with root package name */
        private final c.e f1019a;

        /* renamed from: b, reason: collision with root package name */
        private final Charset f1020b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f1021c;
        private Reader d;

        a(c.e eVar, Charset charset) {
            this.f1019a = eVar;
            this.f1020b = charset;
        }

        @Override // java.io.Reader
        public int read(char[] cArr, int i, int i2) throws IOException {
            if (this.f1021c) {
                throw new IOException("Stream closed");
            }
            Reader reader = this.d;
            if (reader == null) {
                InputStreamReader inputStreamReader = new InputStreamReader(this.f1019a.h(), b.a.c.a(this.f1019a, this.f1020b));
                this.d = inputStreamReader;
                reader = inputStreamReader;
            }
            return reader.read(cArr, i, i2);
        }

        @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.f1021c = true;
            if (this.d != null) {
                this.d.close();
            } else {
                this.f1019a.close();
            }
        }
    }
}
