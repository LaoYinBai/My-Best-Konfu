package com.bytedance.pangle.e.a;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    InputStream f4592a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4593b;

    /* renamed from: c, reason: collision with root package name */
    private int f4594c;

    public c() {
    }

    public c(InputStream inputStream) {
        a(inputStream);
    }

    public final void a(InputStream inputStream) {
        this.f4592a = inputStream;
        this.f4593b = false;
        this.f4594c = 0;
    }

    public final int a() {
        return b();
    }

    public final int b() throws IOException {
        int i = 0;
        if (this.f4593b) {
            for (int i2 = 24; i2 >= 0; i2 -= 8) {
                int i3 = this.f4592a.read();
                if (i3 == -1) {
                    throw new EOFException();
                }
                this.f4594c++;
                i |= i3 << i2;
            }
            return i;
        }
        int i4 = 0;
        while (i != 32) {
            int i5 = this.f4592a.read();
            if (i5 == -1) {
                throw new EOFException();
            }
            this.f4594c++;
            i4 |= i5 << i;
            i += 8;
        }
        return i4;
    }

    public final int[] a(int i) {
        int[] iArr = new int[i];
        a(iArr, 0, i);
        return iArr;
    }

    public final void c() throws IOException {
        long jSkip = this.f4592a.skip(4L);
        this.f4594c = (int) (this.f4594c + jSkip);
        if (jSkip != 4) {
            throw new EOFException();
        }
    }

    private void a(int[] iArr, int i, int i2) {
        while (i2 > 0) {
            iArr[i] = b();
            i2--;
            i++;
        }
    }
}
