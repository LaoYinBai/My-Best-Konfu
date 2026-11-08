package com.ss.union.game.sdk.core.glide.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MarkEnforcingInputStream extends FilterInputStream {
    private static final int a = Integer.MIN_VALUE;
    private static final int b = -1;
    private int c;

    public MarkEnforcingInputStream(InputStream inputStream) {
        super(inputStream);
        this.c = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void mark(int i) {
        super.mark(i);
        this.c = i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (a(1L) == -1) {
            return -1;
        }
        int i = super.read();
        b(1L);
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int iA = (int) a(i2);
        if (iA == -1) {
            return -1;
        }
        int i3 = super.read(bArr, i, iA);
        b(i3);
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized void reset() throws IOException {
        super.reset();
        this.c = Integer.MIN_VALUE;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public long skip(long j) throws IOException {
        long jA = a(j);
        if (jA == -1) {
            return 0L;
        }
        long jSkip = super.skip(jA);
        b(jSkip);
        return jSkip;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return this.c == Integer.MIN_VALUE ? super.available() : Math.min(this.c, super.available());
    }

    private long a(long j) {
        if (this.c == 0) {
            return -1L;
        }
        return (this.c == Integer.MIN_VALUE || j <= ((long) this.c)) ? j : this.c;
    }

    private void b(long j) {
        if (this.c == Integer.MIN_VALUE || j == -1) {
            return;
        }
        this.c = (int) (this.c - j);
    }
}
