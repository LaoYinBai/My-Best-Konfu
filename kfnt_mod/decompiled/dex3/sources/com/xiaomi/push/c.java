package com.xiaomi.push;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class c {
    private final int a;

    /* renamed from: a, reason: collision with other field name */
    private final OutputStream f173a;

    /* renamed from: a, reason: collision with other field name */
    private final byte[] f174a;
    private int b;

    public static class a extends IOException {
        a() {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.");
        }
    }

    private c(OutputStream outputStream, byte[] bArr) {
        this.f173a = outputStream;
        this.f174a = bArr;
        this.b = 0;
        this.a = bArr.length;
    }

    private c(byte[] bArr, int i, int i2) {
        this.f173a = null;
        this.f174a = bArr;
        this.b = i;
        this.a = i + i2;
    }

    public static int a(int i) {
        if (i >= 0) {
            return d(i);
        }
        return 10;
    }

    public static int a(int i, int i2) {
        return c(i) + a(i2);
    }

    public static int a(int i, long j) {
        return c(i) + a(j);
    }

    public static int a(int i, com.xiaomi.push.a aVar) {
        return c(i) + a(aVar);
    }

    public static int a(int i, e eVar) {
        return c(i) + a(eVar);
    }

    public static int a(int i, String str) {
        return c(i) + a(str);
    }

    public static int a(int i, boolean z) {
        return c(i) + a(z);
    }

    public static int a(long j) {
        return c(j);
    }

    public static int a(com.xiaomi.push.a aVar) {
        return d(aVar.a()) + aVar.a();
    }

    public static int a(e eVar) {
        int iB = eVar.b();
        return d(iB) + iB;
    }

    public static int a(String str) throws UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return d(bytes.length) + bytes.length;
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public static int a(boolean z) {
        return 1;
    }

    public static c a(OutputStream outputStream) {
        return a(outputStream, 4096);
    }

    public static c a(OutputStream outputStream, int i) {
        return new c(outputStream, new byte[i]);
    }

    public static c a(byte[] bArr, int i, int i2) {
        return new c(bArr, i, i2);
    }

    public static int b(int i) {
        return d(i);
    }

    public static int b(int i, int i2) {
        return c(i) + b(i2);
    }

    public static int b(int i, long j) {
        return c(i) + b(j);
    }

    public static int b(long j) {
        return c(j);
    }

    public static int c(int i) {
        return d(f.a(i, 0));
    }

    public static int c(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    private void c() throws IOException {
        if (this.f173a == null) {
            throw new a();
        }
        this.f173a.write(this.f174a, 0, this.b);
        this.b = 0;
    }

    public static int d(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public int a() {
        if (this.f173a == null) {
            return this.a - this.b;
        }
        throw new UnsupportedOperationException("spaceLeft() can only be called on CodedOutputStreams that are writing to a flat array.");
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m147a() throws IOException {
        if (this.f173a != null) {
            c();
        }
    }

    public void a(byte b) throws IOException {
        if (this.b == this.a) {
            c();
        }
        byte[] bArr = this.f174a;
        int i = this.b;
        this.b = i + 1;
        bArr[i] = b;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m148a(int i) throws IOException {
        if (i >= 0) {
            m167d(i);
        } else {
            m166c(i);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m149a(int i, int i2) throws IOException {
        c(i, 0);
        m148a(i2);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m150a(int i, long j) throws IOException {
        c(i, 0);
        m155a(j);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m151a(int i, com.xiaomi.push.a aVar) throws IOException {
        c(i, 2);
        m156a(aVar);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m152a(int i, e eVar) throws IOException {
        c(i, 2);
        m157a(eVar);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m153a(int i, String str) throws IOException {
        c(i, 2);
        m158a(str);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m154a(int i, boolean z) throws IOException {
        c(i, 0);
        m159a(z);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m155a(long j) throws IOException {
        m166c(j);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m156a(com.xiaomi.push.a aVar) throws IOException {
        byte[] bArrM88a = aVar.m88a();
        m167d(bArrM88a.length);
        a(bArrM88a);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m157a(e eVar) throws IOException {
        m167d(eVar.a());
        eVar.a(this);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m158a(String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        m167d(bytes.length);
        a(bytes);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m159a(boolean z) throws IOException {
        m165c(z ? 1 : 0);
    }

    public void a(byte[] bArr) throws IOException {
        m160a(bArr, 0, bArr.length);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m160a(byte[] bArr, int i, int i2) throws IOException {
        if (this.a - this.b >= i2) {
            System.arraycopy(bArr, i, this.f174a, this.b, i2);
            this.b += i2;
            return;
        }
        int i3 = this.a - this.b;
        System.arraycopy(bArr, i, this.f174a, this.b, i3);
        int i4 = i + i3;
        int i5 = i2 - i3;
        this.b = this.a;
        c();
        if (i5 > this.a) {
            this.f173a.write(bArr, i4, i5);
        } else {
            System.arraycopy(bArr, i4, this.f174a, 0, i5);
            this.b = i5;
        }
    }

    public void b() {
        if (a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    public void m161b(int i) throws IOException {
        m167d(i);
    }

    /* renamed from: b, reason: collision with other method in class */
    public void m162b(int i, int i2) throws IOException {
        c(i, 0);
        m161b(i2);
    }

    /* renamed from: b, reason: collision with other method in class */
    public void m163b(int i, long j) throws IOException {
        c(i, 0);
        m164b(j);
    }

    /* renamed from: b, reason: collision with other method in class */
    public void m164b(long j) throws IOException {
        m166c(j);
    }

    /* renamed from: c, reason: collision with other method in class */
    public void m165c(int i) throws IOException {
        a((byte) i);
    }

    public void c(int i, int i2) throws IOException {
        m167d(f.a(i, i2));
    }

    /* renamed from: c, reason: collision with other method in class */
    public void m166c(long j) throws IOException {
        while (((-128) & j) != 0) {
            m165c((((int) j) & 127) | 128);
            j >>>= 7;
        }
        m165c((int) j);
    }

    /* renamed from: d, reason: collision with other method in class */
    public void m167d(int i) throws IOException {
        while ((i & (-128)) != 0) {
            m165c((i & 127) | 128);
            i >>>= 7;
        }
        m165c(i);
    }
}
