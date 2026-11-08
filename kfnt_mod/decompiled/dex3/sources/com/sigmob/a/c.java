package com.sigmob.a;

import java.io.InputStream;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class c extends InputStream {
    private byte[] a = new byte[1];
    private long b = 0;

    private void a(long j) {
        if (j != -1) {
            this.b += j;
        }
    }

    protected long a() {
        return this.b;
    }

    protected void a(int i) {
        a(i);
    }

    @Override // java.io.InputStream
    public int read() {
        if (read(this.a, 0, 1) == -1) {
            return -1;
        }
        return this.a[0] & UByte.MAX_VALUE;
    }
}
