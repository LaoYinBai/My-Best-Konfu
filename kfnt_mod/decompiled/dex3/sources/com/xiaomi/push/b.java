package com.xiaomi.push;

import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.io.InputStream;
import java.util.Vector;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class b {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    private final InputStream f145a;

    /* renamed from: a, reason: collision with other field name */
    private final byte[] f146a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;

    private b(InputStream inputStream) {
        this.f = Integer.MAX_VALUE;
        this.h = 64;
        this.i = DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS;
        this.f146a = new byte[4096];
        this.a = 0;
        this.c = 0;
        this.f145a = inputStream;
    }

    private b(byte[] bArr, int i, int i2) {
        this.f = Integer.MAX_VALUE;
        this.h = 64;
        this.i = DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS;
        this.f146a = bArr;
        this.a = i2 + i;
        this.c = i;
        this.f145a = null;
    }

    public static b a(InputStream inputStream) {
        return new b(inputStream);
    }

    public static b a(byte[] bArr, int i, int i2) {
        return new b(bArr, i, i2);
    }

    private boolean a(boolean z) throws d {
        if (this.c < this.a) {
            throw new IllegalStateException("refillBuffer() called when buffer wasn't empty.");
        }
        if (this.e + this.a == this.f) {
            if (z) {
                throw d.a();
            }
            return false;
        }
        this.e += this.a;
        this.c = 0;
        this.a = this.f145a == null ? -1 : this.f145a.read(this.f146a);
        if (this.a == 0 || this.a < -1) {
            throw new IllegalStateException("InputStream#read(byte[]) returned invalid result: " + this.a + "\nThe InputStream implementation is buggy.");
        }
        if (this.a == -1) {
            this.a = 0;
            if (z) {
                throw d.a();
            }
            return false;
        }
        b();
        int i = this.e + this.a + this.b;
        if (i > this.i || i < 0) {
            throw d.h();
        }
        return true;
    }

    private void b() {
        this.a += this.b;
        int i = this.e + this.a;
        if (i <= this.f) {
            this.b = 0;
        } else {
            this.b = i - this.f;
            this.a -= this.b;
        }
    }

    public byte a() throws d {
        if (this.c == this.a) {
            a(true);
        }
        byte[] bArr = this.f146a;
        int i = this.c;
        this.c = i + 1;
        return bArr[i];
    }

    /* renamed from: a, reason: collision with other method in class */
    public int m109a() throws d {
        if (m120b()) {
            this.d = 0;
            return 0;
        }
        this.d = d();
        if (this.d != 0) {
            return this.d;
        }
        throw d.d();
    }

    public int a(int i) throws d {
        if (i < 0) {
            throw d.b();
        }
        int i2 = i + this.e + this.c;
        int i3 = this.f;
        if (i2 > i3) {
            throw d.a();
        }
        this.f = i2;
        b();
        return i3;
    }

    /* renamed from: a, reason: collision with other method in class */
    public long m110a() {
        return m121c();
    }

    /* renamed from: a, reason: collision with other method in class */
    public a m111a() throws d {
        int iD = d();
        if (iD > this.a - this.c || iD <= 0) {
            return a.a(m117a(iD));
        }
        a aVarA = a.a(this.f146a, this.c, iD);
        this.c += iD;
        return aVarA;
    }

    /* renamed from: a, reason: collision with other method in class */
    public String m112a() throws d {
        int iD = d();
        if (iD > this.a - this.c || iD <= 0) {
            return new String(m117a(iD), "UTF-8");
        }
        String str = new String(this.f146a, this.c, iD, "UTF-8");
        this.c += iD;
        return str;
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m113a() throws d {
        int iM109a;
        do {
            iM109a = m109a();
            if (iM109a == 0) {
                return;
            }
        } while (m116a(iM109a));
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m114a(int i) throws d {
        if (this.d != i) {
            throw d.e();
        }
    }

    public void a(e eVar) throws d {
        int iD = d();
        if (this.g >= this.h) {
            throw d.g();
        }
        int iA = a(iD);
        this.g++;
        eVar.a(this);
        m114a(0);
        this.g--;
        b(iA);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m115a() {
        return d() != 0;
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m116a(int i) throws d {
        switch (f.a(i)) {
            case 0:
                m118b();
                return true;
            case 1:
                m122d();
                return true;
            case 2:
                c(d());
                return true;
            case 3:
                m113a();
                m114a(f.a(f.b(i), 4));
                return true;
            case 4:
                return false;
            case 5:
                e();
                return true;
            default:
                throw d.f();
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public byte[] m117a(int i) throws d {
        if (i < 0) {
            throw d.b();
        }
        if (this.e + this.c + i > this.f) {
            c((this.f - this.e) - this.c);
            throw d.a();
        }
        if (i <= this.a - this.c) {
            byte[] bArr = new byte[i];
            System.arraycopy(this.f146a, this.c, bArr, 0, i);
            this.c += i;
            return bArr;
        }
        if (i >= 4096) {
            int i2 = this.c;
            int i3 = this.a;
            this.e += this.a;
            this.c = 0;
            this.a = 0;
            int length = i3 - i2;
            int length2 = i - length;
            Vector vector = new Vector();
            while (length2 > 0) {
                byte[] bArr2 = new byte[Math.min(length2, 4096)];
                int i4 = 0;
                while (i4 < bArr2.length) {
                    int i5 = this.f145a == null ? -1 : this.f145a.read(bArr2, i4, bArr2.length - i4);
                    if (i5 == -1) {
                        throw d.a();
                    }
                    this.e += i5;
                    i4 += i5;
                }
                length2 -= bArr2.length;
                vector.addElement(bArr2);
            }
            byte[] bArr3 = new byte[i];
            System.arraycopy(this.f146a, i2, bArr3, 0, length);
            for (int i6 = 0; i6 < vector.size(); i6++) {
                byte[] bArr4 = (byte[]) vector.elementAt(i6);
                System.arraycopy(bArr4, 0, bArr3, length, bArr4.length);
                length += bArr4.length;
            }
            return bArr3;
        }
        byte[] bArr5 = new byte[i];
        int i7 = this.a - this.c;
        System.arraycopy(this.f146a, this.c, bArr5, 0, i7);
        this.c = this.a;
        while (true) {
            a(true);
            int i8 = i - i7;
            if (i8 <= this.a) {
                System.arraycopy(this.f146a, 0, bArr5, i7, i8);
                this.c = i8;
                return bArr5;
            }
            System.arraycopy(this.f146a, 0, bArr5, i7, this.a);
            i7 += this.a;
            this.c = this.a;
        }
    }

    /* renamed from: b, reason: collision with other method in class */
    public int m118b() {
        return d();
    }

    /* renamed from: b, reason: collision with other method in class */
    public long m119b() {
        return m121c();
    }

    public void b(int i) {
        this.f = i;
        b();
    }

    /* renamed from: b, reason: collision with other method in class */
    public boolean m120b() {
        return this.c == this.a && !a(false);
    }

    public int c() {
        return d();
    }

    /* renamed from: c, reason: collision with other method in class */
    public long m121c() throws d {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            j |= (r3 & 127) << i;
            if ((a() & 128) == 0) {
                return j;
            }
        }
        throw d.c();
    }

    public void c(int i) throws d {
        if (i < 0) {
            throw d.b();
        }
        if (this.e + this.c + i > this.f) {
            c((this.f - this.e) - this.c);
            throw d.a();
        }
        if (i <= this.a - this.c) {
            this.c += i;
            return;
        }
        int i2 = this.a - this.c;
        this.e += this.a;
        this.c = 0;
        this.a = 0;
        while (i2 < i) {
            int iSkip = this.f145a == null ? -1 : (int) this.f145a.skip(i - i2);
            if (iSkip <= 0) {
                throw d.a();
            }
            i2 += iSkip;
            this.e += iSkip;
        }
    }

    public int d() throws d {
        int i;
        byte bA = a();
        if (bA >= 0) {
            return bA;
        }
        int i2 = bA & 127;
        byte bA2 = a();
        if (bA2 >= 0) {
            i = bA2 << 7;
        } else {
            i2 |= (bA2 & 127) << 7;
            byte bA3 = a();
            if (bA3 >= 0) {
                i = bA3 << com.umeng.commonsdk.proguard.az.l;
            } else {
                i2 |= (bA3 & 127) << 14;
                byte bA4 = a();
                if (bA4 < 0) {
                    int i3 = i2 | ((bA4 & 127) << 21);
                    byte bA5 = a();
                    int i4 = i3 | (bA5 << 28);
                    if (bA5 >= 0) {
                        return i4;
                    }
                    for (int i5 = 0; i5 < 5; i5++) {
                        if (a() >= 0) {
                            return i4;
                        }
                    }
                    throw d.c();
                }
                i = bA4 << 21;
            }
        }
        return i2 | i;
    }

    /* renamed from: d, reason: collision with other method in class */
    public long m122d() throws d {
        return ((a() & 255) << 8) | (a() & 255) | ((a() & 255) << 16) | ((a() & 255) << 24) | ((a() & 255) << 32) | ((a() & 255) << 40) | ((a() & 255) << 48) | ((a() & 255) << 56);
    }

    public int e() throws d {
        return (a() & UByte.MAX_VALUE) | ((a() & UByte.MAX_VALUE) << 8) | ((a() & UByte.MAX_VALUE) << 16) | ((a() & UByte.MAX_VALUE) << 24);
    }
}
