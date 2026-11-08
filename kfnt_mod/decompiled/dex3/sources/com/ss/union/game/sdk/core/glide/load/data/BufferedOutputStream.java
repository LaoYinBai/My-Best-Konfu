package com.ss.union.game.sdk.core.glide.load.data;

import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import java.io.IOException;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class BufferedOutputStream extends OutputStream {
    private final OutputStream a;
    private byte[] b;
    private ArrayPool c;
    private int d;

    public BufferedOutputStream(OutputStream outputStream, ArrayPool arrayPool) {
        this(outputStream, arrayPool, 65536);
    }

    BufferedOutputStream(OutputStream outputStream, ArrayPool arrayPool, int i) {
        this.a = outputStream;
        this.c = arrayPool;
        this.b = (byte[]) arrayPool.get(i, byte[].class);
    }

    @Override // java.io.OutputStream
    public void write(int i) throws IOException {
        byte[] bArr = this.b;
        int i2 = this.d;
        this.d = i2 + 1;
        bArr[i2] = (byte) i;
        b();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) throws IOException {
        write(bArr, 0, bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        do {
            int i4 = i2 - i3;
            int i5 = i + i3;
            if (this.d == 0 && i4 >= this.b.length) {
                this.a.write(bArr, i5, i4);
                return;
            }
            int iMin = Math.min(i4, this.b.length - this.d);
            System.arraycopy(bArr, i5, this.b, this.d, iMin);
            this.d += iMin;
            i3 += iMin;
            b();
        } while (i3 < i2);
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        a();
        this.a.flush();
    }

    private void a() throws IOException {
        if (this.d > 0) {
            this.a.write(this.b, 0, this.d);
            this.d = 0;
        }
    }

    private void b() throws IOException {
        if (this.d == this.b.length) {
            a();
        }
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        try {
            flush();
            this.a.close();
            c();
        } catch (Throwable th) {
            this.a.close();
            throw th;
        }
    }

    private void c() {
        if (this.b != null) {
            this.c.put(this.b);
            this.b = null;
        }
    }
}
