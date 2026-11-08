package com.ss.union.game.sdk.core.glide.load.data;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ExifOrientationStream extends FilterInputStream {
    private static final int a = 2;
    private static final byte[] b = {-1, -31, 0, 28, 69, 120, 105, 102, 0, 0, 77, 77, 0, 0, 0, 0, 0, 8, 0, 1, 1, 18, 0, 2, 0, 0, 0, 1, 0};
    private static final int c = b.length;
    private static final int d = c + 2;
    private final byte e;
    private int f;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public ExifOrientationStream(InputStream inputStream, int i) {
        super(inputStream);
        if (i < -1 || i > 8) {
            throw new IllegalArgumentException("Cannot add invalid orientation: " + i);
        }
        this.e = (byte) i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void mark(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        int i;
        if (this.f < 2 || this.f > d) {
            i = super.read();
        } else if (this.f == d) {
            i = this.e;
        } else {
            i = b[this.f - 2] & UByte.MAX_VALUE;
        }
        if (i != -1) {
            this.f++;
        }
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        if (this.f > d) {
            i3 = super.read(bArr, i, i2);
        } else if (this.f == d) {
            bArr[i] = this.e;
            i3 = 1;
        } else if (this.f < 2) {
            i3 = super.read(bArr, i, 2 - this.f);
        } else {
            int iMin = Math.min(d - this.f, i2);
            System.arraycopy(b, this.f - 2, bArr, i, iMin);
            i3 = iMin;
        }
        if (i3 > 0) {
            this.f += i3;
        }
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long jSkip = super.skip(j);
        if (jSkip > 0) {
            this.f = (int) (this.f + jSkip);
        }
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public void reset() throws IOException {
        throw new UnsupportedOperationException();
    }
}
