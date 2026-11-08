package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122;

import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r;
import java.io.Closeable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a0 implements Closeable {

    /* renamed from: a, reason: collision with root package name */
    final y f1499a;

    /* renamed from: b, reason: collision with root package name */
    final w f1500b;

    /* renamed from: c, reason: collision with root package name */
    final int f1501c;
    final String d;
    final q e;
    final r f;
    final b0 g;
    final a0 h;
    final a0 i;
    final a0 j;
    final long k;
    final long l;
    private volatile d m;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        y f1502a;

        /* renamed from: b, reason: collision with root package name */
        w f1503b;

        /* renamed from: c, reason: collision with root package name */
        int f1504c;
        String d;
        q e;
        r.a f;
        b0 g;
        a0 h;
        a0 i;
        a0 j;
        long k;
        long l;

        public a() {
            this.f1504c = -1;
            this.f = new r.a();
        }

        private void d(a0 a0Var) {
            if (a0Var.g != null) {
                throw new IllegalArgumentException("priorResponse.body != null");
            }
        }

        public a a(y yVar) {
            this.f1502a = yVar;
            return this;
        }

        public a b(a0 a0Var) {
            if (a0Var != null) {
                a("networkResponse", a0Var);
            }
            this.h = a0Var;
            return this;
        }

        public a c(a0 a0Var) {
            if (a0Var != null) {
                d(a0Var);
            }
            this.j = a0Var;
            return this;
        }

        public a a(w wVar) {
            this.f1503b = wVar;
            return this;
        }

        public a a(int i) {
            this.f1504c = i;
            return this;
        }

        public a b(long j) {
            this.k = j;
            return this;
        }

        public a a(String str) {
            this.d = str;
            return this;
        }

        public a a(q qVar) {
            this.e = qVar;
            return this;
        }

        public a a(String str, String str2) {
            this.f.a(str, str2);
            return this;
        }

        public a a(r rVar) {
            this.f = rVar.a();
            return this;
        }

        public a a(b0 b0Var) {
            this.g = b0Var;
            return this;
        }

        public a a(a0 a0Var) {
            if (a0Var != null) {
                a("cacheResponse", a0Var);
            }
            this.i = a0Var;
            return this;
        }

        private void a(String str, a0 a0Var) {
            if (a0Var.g == null) {
                if (a0Var.h == null) {
                    if (a0Var.i == null) {
                        if (a0Var.j == null) {
                            return;
                        }
                        throw new IllegalArgumentException(str + ".priorResponse != null");
                    }
                    throw new IllegalArgumentException(str + ".cacheResponse != null");
                }
                throw new IllegalArgumentException(str + ".networkResponse != null");
            }
            throw new IllegalArgumentException(str + ".body != null");
        }

        a(a0 a0Var) {
            this.f1504c = -1;
            this.f1502a = a0Var.f1499a;
            this.f1503b = a0Var.f1500b;
            this.f1504c = a0Var.f1501c;
            this.d = a0Var.d;
            this.e = a0Var.e;
            this.f = a0Var.f.a();
            this.g = a0Var.g;
            this.h = a0Var.h;
            this.i = a0Var.i;
            this.j = a0Var.j;
            this.k = a0Var.k;
            this.l = a0Var.l;
        }

        public a a(long j) {
            this.l = j;
            return this;
        }

        public a0 a() {
            if (this.f1502a != null) {
                if (this.f1503b != null) {
                    if (this.f1504c >= 0) {
                        if (this.d != null) {
                            return new a0(this);
                        }
                        throw new IllegalStateException("message == null");
                    }
                    throw new IllegalStateException("code < 0: " + this.f1504c);
                }
                throw new IllegalStateException("protocol == null");
            }
            throw new IllegalStateException("request == null");
        }
    }

    a0(a aVar) {
        this.f1499a = aVar.f1502a;
        this.f1500b = aVar.f1503b;
        this.f1501c = aVar.f1504c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f.a();
        this.g = aVar.g;
        this.h = aVar.h;
        this.i = aVar.i;
        this.j = aVar.j;
        this.k = aVar.k;
        this.l = aVar.l;
    }

    public String a(String str, String str2) {
        String strA = this.f.a(str);
        return strA != null ? strA : str2;
    }

    public String b(String str) {
        return a(str, null);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        b0 b0Var = this.g;
        if (b0Var == null) {
            throw new IllegalStateException("response is not eligible for a body and must not be closed");
        }
        b0Var.close();
    }

    public b0 i() {
        return this.g;
    }

    public d j() {
        d dVar = this.m;
        if (dVar != null) {
            return dVar;
        }
        d dVarA = d.a(this.f);
        this.m = dVarA;
        return dVarA;
    }

    public int k() {
        return this.f1501c;
    }

    public q l() {
        return this.e;
    }

    public r m() {
        return this.f;
    }

    public boolean n() {
        int i = this.f1501c;
        return i >= 200 && i < 300;
    }

    public String o() {
        return this.d;
    }

    public a p() {
        return new a(this);
    }

    public a0 q() {
        return this.j;
    }

    public long r() {
        return this.l;
    }

    public y s() {
        return this.f1499a;
    }

    public long t() {
        return this.k;
    }

    public String toString() {
        return "Response{protocol=" + this.f1500b + ", code=" + this.f1501c + ", message=" + this.d + ", url=" + this.f1499a.g() + '}';
    }
}
