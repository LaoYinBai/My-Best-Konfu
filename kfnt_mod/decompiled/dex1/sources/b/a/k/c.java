package b.a.k;

import c.c;
import c.e;
import c.f;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c {

    /* renamed from: a, reason: collision with root package name */
    final boolean f979a;

    /* renamed from: b, reason: collision with root package name */
    final e f980b;

    /* renamed from: c, reason: collision with root package name */
    final a f981c;
    boolean d;
    int e;
    long f;
    boolean g;
    boolean h;
    private final c.c i = new c.c();
    private final c.c j = new c.c();
    private final byte[] k;
    private final c.a l;

    public interface a {
        void b(int i, String str);

        void b(f fVar) throws IOException;

        void b(String str) throws IOException;

        void c(f fVar);

        void d(f fVar);
    }

    c(boolean z, e eVar, a aVar) {
        if (eVar == null) {
            throw new NullPointerException("source == null");
        }
        if (aVar == null) {
            throw new NullPointerException("frameCallback == null");
        }
        this.f979a = z;
        this.f980b = eVar;
        this.f981c = aVar;
        this.k = z ? null : new byte[4];
        this.l = z ? null : new c.a();
    }

    void a() throws IOException {
        b();
        if (this.h) {
            c();
        } else {
            d();
        }
    }

    /* JADX WARN: Finally extract failed */
    private void b() throws IOException {
        if (this.d) {
            throw new IOException("closed");
        }
        long jB_ = this.f980b.a().b_();
        this.f980b.a().d_();
        try {
            int iJ = this.f980b.j() & 255;
            this.f980b.a().a(jB_, TimeUnit.NANOSECONDS);
            this.e = iJ & 15;
            this.g = (iJ & 128) != 0;
            this.h = (iJ & 8) != 0;
            if (this.h && !this.g) {
                throw new ProtocolException("Control frames must be final.");
            }
            boolean z = (iJ & 64) != 0;
            boolean z2 = (iJ & 32) != 0;
            boolean z3 = (iJ & 16) != 0;
            if (z || z2 || z3) {
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            boolean z4 = ((this.f980b.j() & 255) & 128) != 0;
            if (z4 == this.f979a) {
                throw new ProtocolException(this.f979a ? "Server-sent frames must not be masked." : "Client-sent frames must be masked.");
            }
            this.f = r0 & 127;
            if (this.f == 126) {
                this.f = this.f980b.k() & 65535;
            } else if (this.f == 127) {
                this.f = this.f980b.m();
                if (this.f < 0) {
                    throw new ProtocolException("Frame length 0x" + Long.toHexString(this.f) + " > 0x7FFFFFFFFFFFFFFF");
                }
            }
            if (this.h && this.f > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            }
            if (z4) {
                this.f980b.b(this.k);
            }
        } catch (Throwable th) {
            this.f980b.a().a(jB_, TimeUnit.NANOSECONDS);
            throw th;
        }
    }

    private void c() throws IOException {
        if (this.f > 0) {
            this.f980b.b(this.i, this.f);
            if (!this.f979a) {
                this.i.b(this.l);
                this.l.a(0L);
                b.a(this.l, this.k);
                this.l.close();
            }
        }
        switch (this.e) {
            case 8:
                short sK = 1005;
                String strT = "";
                long jB = this.i.b();
                if (jB == 1) {
                    throw new ProtocolException("Malformed close payload length of 1.");
                }
                if (jB != 0) {
                    sK = this.i.k();
                    strT = this.i.t();
                    String strA = b.a(sK);
                    if (strA != null) {
                        throw new ProtocolException(strA);
                    }
                }
                this.f981c.b(sK, strT);
                this.d = true;
                return;
            case 9:
                this.f981c.c(this.i.s());
                return;
            case 10:
                this.f981c.d(this.i.s());
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.e));
        }
    }

    private void d() throws IOException {
        int i = this.e;
        if (i != 1 && i != 2) {
            throw new ProtocolException("Unknown opcode: " + Integer.toHexString(i));
        }
        f();
        if (i == 1) {
            this.f981c.b(this.j.t());
        } else {
            this.f981c.b(this.j.s());
        }
    }

    private void e() throws IOException {
        while (!this.d) {
            b();
            if (!this.h) {
                return;
            } else {
                c();
            }
        }
    }

    private void f() throws IOException {
        while (!this.d) {
            if (this.f > 0) {
                this.f980b.b(this.j, this.f);
                if (!this.f979a) {
                    this.j.b(this.l);
                    this.l.a(this.j.b() - this.f);
                    b.a(this.l, this.k);
                    this.l.close();
                }
            }
            if (this.g) {
                return;
            }
            e();
            if (this.e != 0) {
                throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(this.e));
            }
        }
        throw new IOException("closed");
    }
}
