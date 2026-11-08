package com.sigmob.a.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class d {
    private InputStream a;
    private OutputStream b = null;
    private byte[] c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    public d(InputStream inputStream, int i, int i2) {
        this.a = inputStream;
        a(i, i2);
    }

    private void a(int i, int i2) {
        int i3;
        this.f = i;
        this.g = i2;
        this.h = this.f / this.g;
        this.c = new byte[this.f];
        if (this.a != null) {
            this.d = -1;
            i3 = this.h;
        } else {
            i3 = 0;
            this.d = 0;
        }
        this.e = i3;
    }

    private boolean e() throws IOException {
        if (this.a == null) {
            throw new IOException("reading from an output buffer");
        }
        this.e = 0;
        int i = this.f;
        int i2 = 0;
        while (true) {
            if (i <= 0) {
                break;
            }
            long j = this.a.read(this.c, i2, i);
            if (j != -1) {
                i2 = (int) (i2 + j);
                i = (int) (i - j);
                int i3 = this.f;
            } else {
                if (i2 == 0) {
                    return false;
                }
                Arrays.fill(this.c, i2, i + i2, (byte) 0);
            }
        }
        this.d++;
        return true;
    }

    private void f() throws IOException {
        if (this.b == null) {
            throw new IOException("writing to an input buffer");
        }
        this.b.write(this.c, 0, this.f);
        this.b.flush();
        this.e = 0;
        this.d++;
        Arrays.fill(this.c, (byte) 0);
    }

    public int a() {
        return this.g;
    }

    public boolean a(byte[] bArr) {
        int iA = a();
        for (int i = 0; i < iA; i++) {
            if (bArr[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public byte[] b() throws IOException {
        if (this.a == null) {
            if (this.b == null) {
                throw new IOException("input buffer is closed");
            }
            throw new IOException("reading from an output buffer");
        }
        if (this.e >= this.h && !e()) {
            return null;
        }
        byte[] bArr = new byte[this.g];
        System.arraycopy(this.c, this.e * this.g, bArr, 0, this.g);
        this.e++;
        return bArr;
    }

    void c() throws IOException {
        if (this.b == null) {
            throw new IOException("writing to an input buffer");
        }
        if (this.e > 0) {
            f();
        }
    }

    public void d() throws IOException {
        if (this.b == null) {
            if (this.a != null) {
                if (this.a != System.in) {
                    this.a.close();
                }
                this.a = null;
                return;
            }
            return;
        }
        c();
        if (this.b == System.out || this.b == System.err) {
            return;
        }
        this.b.close();
        this.b = null;
    }
}
