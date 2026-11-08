package com.kwad.sdk.pngencrypt;

import com.google.android.gms.common.api.Api;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements Closeable {
    static final /* synthetic */ boolean a = !a.class.desiredAssertionStatus();
    private InputStream b;
    private byte[] c;
    private int d;
    private int e;
    private boolean f;
    private boolean g;
    private long h;

    public a(InputStream inputStream) {
        this(inputStream, 16384);
    }

    public a(InputStream inputStream, int i) {
        this.f = false;
        this.g = true;
        this.h = 0L;
        this.b = inputStream;
        this.c = new byte[i < 1 ? 16384 : i];
    }

    public int a(f fVar) {
        return a(fVar, Api.BaseClientBuilder.API_PRIORITY_OTHER);
    }

    public int a(f fVar, int i) {
        a();
        if (i <= 0 || i >= this.d) {
            i = this.d;
        }
        if (i <= 0) {
            if (!this.f) {
                com.kwad.sdk.core.d.a.a(new PngjException("This should not happen"));
            }
            return fVar.b() ? -1 : 0;
        }
        int iA = fVar.a(this.c, this.e, i);
        if (iA > 0) {
            this.e += iA;
            this.d -= iA;
            if (!a && this.d < 0) {
                throw new AssertionError();
            }
        }
        if (iA > 0) {
            return iA;
        }
        if (!fVar.b()) {
            com.kwad.sdk.core.d.a.a(new PngjException("This should not happen!"));
        }
        return -1;
    }

    protected void a() {
        if (this.d > 0 || this.f) {
            return;
        }
        try {
            this.e = 0;
            this.d = this.b.read(this.c);
            if (this.d == 0) {
                com.kwad.sdk.core.d.a.a(new PngjException("This should not happen: stream.read(buf) returned 0"));
            } else if (this.d < 0) {
                close();
            } else {
                this.h += this.d;
            }
        } catch (IOException e) {
            com.kwad.sdk.core.d.a.a(new PngjException(e));
        }
    }

    public void a(boolean z) {
        this.g = z;
    }

    public int b(f fVar, int i) {
        int i2 = i;
        while (i2 > 0) {
            int iA = a(fVar, i2);
            if (iA <= 0) {
                return iA;
            }
            i2 -= iA;
        }
        if (a || i2 == 0) {
            return i;
        }
        throw new AssertionError();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f = true;
        this.c = null;
        this.d = 0;
        this.e = 0;
        if (this.b != null && this.g) {
            try {
                this.b.close();
            } catch (Exception unused) {
            }
        }
        this.b = null;
    }
}
