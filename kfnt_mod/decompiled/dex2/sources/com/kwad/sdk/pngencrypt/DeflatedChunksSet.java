package com.kwad.sdk.pngencrypt;

import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DeflatedChunksSet {
    protected byte[] a;
    State b;
    protected final boolean c;
    public final String f;
    private int g;
    private int h;
    private int i;
    private Inflater j;
    private final boolean k;
    private d l;
    private long m = 0;
    private long n = 0;
    int d = -1;
    int e = -1;

    private enum State {
        WAITING_FOR_INPUT,
        ROW_READY,
        DONE,
        CLOSED;

        public boolean isClosed() {
            return this == CLOSED;
        }

        public boolean isDone() {
            return this == DONE || this == CLOSED;
        }
    }

    public DeflatedChunksSet(String str, boolean z, int i, int i2, Inflater inflater, byte[] bArr) throws DataFormatException {
        this.b = State.WAITING_FOR_INPUT;
        this.f = str;
        this.c = z;
        this.h = i;
        boolean z2 = true;
        if (i < 1 || i2 < i) {
            throw new PngjException("bad inital row len " + i);
        }
        if (inflater != null) {
            this.j = inflater;
            z2 = false;
        } else {
            this.j = new Inflater();
        }
        this.k = z2;
        this.a = (bArr == null || bArr.length < i) ? new byte[i2] : bArr;
        this.i = -1;
        this.b = State.WAITING_FOR_INPUT;
        try {
            a(i);
        } catch (RuntimeException e) {
            f();
            throw e;
        }
    }

    private boolean i() throws DataFormatException {
        State state;
        int iInflate;
        try {
            if (this.b == State.ROW_READY) {
                com.kwad.sdk.core.d.a.a(new PngjException("invalid state"));
            }
            if (this.b.isDone()) {
                return false;
            }
            if (this.a == null || this.a.length < this.h) {
                this.a = new byte[this.h];
            }
            if (this.g < this.h && !this.j.finished()) {
                try {
                    iInflate = this.j.inflate(this.a, this.g, this.h - this.g);
                } catch (DataFormatException e) {
                    com.kwad.sdk.core.d.a.a(new PngjException("error decompressing zlib stream ", e));
                    iInflate = 0;
                }
                this.g += iInflate;
                this.n += iInflate;
            }
            if (this.g != this.h) {
                state = !this.j.finished() ? State.WAITING_FOR_INPUT : this.g > 0 ? State.ROW_READY : State.DONE;
            }
            this.b = state;
            if (this.b != State.ROW_READY) {
                return false;
            }
            a();
            return true;
        } catch (RuntimeException e2) {
            f();
            throw e2;
        }
    }

    protected void a() {
    }

    public void a(int i) throws DataFormatException {
        this.g = 0;
        this.i++;
        if (i < 1 || this.j.finished()) {
            this.h = 0;
            g();
            return;
        }
        this.b = State.WAITING_FOR_INPUT;
        this.h = i;
        if (this.c) {
            return;
        }
        i();
    }

    protected void a(d dVar) {
        if (!this.f.equals(dVar.a().c)) {
            com.kwad.sdk.core.d.a.a(new PngjException("Bad chunk inside IdatSet, id:" + dVar.a().c + ", expected:" + this.f));
        }
        this.l = dVar;
        this.d++;
        if (this.e >= 0) {
            dVar.a(this.d + this.e);
        }
    }

    protected void a(byte[] bArr, int i, int i2) {
        this.m += i2;
        if (i2 < 1 || this.b.isDone()) {
            return;
        }
        if (this.b == State.ROW_READY) {
            com.kwad.sdk.core.d.a.a(new PngjException("this should only be called if waitingForMoreInput"));
        }
        if (this.j.needsDictionary() || !this.j.needsInput()) {
            throw new RuntimeException("should not happen");
        }
        this.j.setInput(bArr, i, i2);
        if (!this.c) {
            i();
            return;
        }
        while (i()) {
            a(b());
            if (d()) {
                c();
            }
        }
    }

    public boolean a(String str) {
        if (this.b.isClosed()) {
            return false;
        }
        if (str.equals(this.f) || b(str)) {
            return true;
        }
        if (this.b.isDone()) {
            if (!this.b.isClosed()) {
                f();
            }
            return false;
        }
        throw new PngjException("Unexpected chunk " + str + " while " + this.f + " set is not done");
    }

    protected int b() {
        throw new PngjException("not implemented");
    }

    public boolean b(String str) {
        return false;
    }

    protected void c() {
    }

    public boolean d() {
        return this.b.isDone();
    }

    public boolean e() {
        return this.b.isClosed();
    }

    public void f() {
        try {
            if (!this.b.isClosed()) {
                this.b = State.CLOSED;
            }
            if (!this.k || this.j == null) {
                return;
            }
            this.j.end();
            this.j = null;
        } catch (Exception unused) {
        }
    }

    public void g() {
        if (d()) {
            return;
        }
        this.b = State.DONE;
    }

    public int h() {
        return this.i;
    }

    public String toString() {
        return new StringBuilder("idatSet : " + this.l.a().c + " state=" + this.b + " rows=" + this.i + " bytes=" + this.m + "/" + this.n).toString();
    }
}
