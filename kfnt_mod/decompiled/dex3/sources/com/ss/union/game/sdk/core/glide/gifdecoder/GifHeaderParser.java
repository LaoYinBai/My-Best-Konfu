package com.ss.union.game.sdk.core.glide.gifdecoder;

import android.util.Log;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GifHeaderParser {
    static final int a = 2;
    static final int b = 10;
    private static final String c = "GifHeaderParser";
    private static final int d = 255;
    private static final int e = 44;
    private static final int f = 33;
    private static final int g = 59;
    private static final int h = 249;
    private static final int i = 255;
    private static final int j = 254;
    private static final int k = 1;
    private static final int l = 28;
    private static final int m = 2;
    private static final int n = 1;
    private static final int o = 128;
    private static final int p = 64;
    private static final int q = 7;
    private static final int r = 128;
    private static final int s = 7;
    private static final int t = 256;
    private ByteBuffer v;
    private GifHeader w;
    private final byte[] u = new byte[256];
    private int x = 0;

    public GifHeaderParser setData(ByteBuffer byteBuffer) {
        a();
        this.v = byteBuffer.asReadOnlyBuffer();
        this.v.position(0);
        this.v.order(ByteOrder.LITTLE_ENDIAN);
        return this;
    }

    public GifHeaderParser setData(byte[] bArr) {
        if (bArr != null) {
            setData(ByteBuffer.wrap(bArr));
        } else {
            this.v = null;
            this.w.b = 2;
        }
        return this;
    }

    public void clear() {
        this.v = null;
        this.w = null;
    }

    private void a() {
        this.v = null;
        Arrays.fill(this.u, (byte) 0);
        this.w = new GifHeader();
        this.x = 0;
    }

    public GifHeader parseHeader() {
        if (this.v == null) {
            throw new IllegalStateException("You must call setData() before parseHeader()");
        }
        if (m()) {
            return this.w;
        }
        f();
        if (!m()) {
            b();
            if (this.w.c < 0) {
                this.w.b = 1;
            }
        }
        return this.w;
    }

    public boolean isAnimated() {
        f();
        if (!m()) {
            a(2);
        }
        return this.w.c > 1;
    }

    private void b() {
        a(Integer.MAX_VALUE);
    }

    private void a(int i2) {
        boolean z = false;
        while (!z && !m() && this.w.c <= i2) {
            int iK = k();
            if (iK == 33) {
                int iK2 = k();
                if (iK2 != 1) {
                    if (iK2 == h) {
                        this.w.d = new a();
                        c();
                    } else {
                        switch (iK2) {
                            case j /* 254 */:
                                i();
                                break;
                            case 255:
                                j();
                                StringBuilder sb = new StringBuilder();
                                for (int i3 = 0; i3 < 11; i3++) {
                                    sb.append((char) this.u[i3]);
                                }
                                if (sb.toString().equals("NETSCAPE2.0")) {
                                    e();
                                    break;
                                } else {
                                    i();
                                    break;
                                }
                            default:
                                i();
                                break;
                        }
                    }
                } else {
                    i();
                }
            } else if (iK == 44) {
                if (this.w.d == null) {
                    this.w.d = new a();
                }
                d();
            } else if (iK != 59) {
                this.w.b = 1;
            } else {
                z = true;
            }
        }
    }

    private void c() {
        k();
        int iK = k();
        this.w.d.k = (iK & 28) >> 2;
        if (this.w.d.k == 0) {
            this.w.d.k = 1;
        }
        this.w.d.j = (iK & 1) != 0;
        int iL = l();
        if (iL < 2) {
            iL = 10;
        }
        this.w.d.m = iL * 10;
        this.w.d.l = k();
        k();
    }

    private void d() {
        this.w.d.e = l();
        this.w.d.f = l();
        this.w.d.g = l();
        this.w.d.h = l();
        int iK = k();
        boolean z = (iK & 128) != 0;
        int iPow = (int) Math.pow(2.0d, (iK & 7) + 1);
        this.w.d.i = (iK & 64) != 0;
        if (z) {
            this.w.d.o = b(iPow);
        } else {
            this.w.d.o = null;
        }
        this.w.d.n = this.v.position();
        h();
        if (m()) {
            return;
        }
        this.w.c++;
        this.w.e.add(this.w.d);
    }

    private void e() {
        do {
            j();
            if (this.u[0] == 1) {
                this.w.m = (this.u[1] & UByte.MAX_VALUE) | ((this.u[2] & UByte.MAX_VALUE) << 8);
            }
            if (this.x <= 0) {
                return;
            }
        } while (!m());
    }

    private void f() {
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < 6; i2++) {
            sb.append((char) k());
        }
        if (!sb.toString().startsWith("GIF")) {
            this.w.b = 1;
            return;
        }
        g();
        if (!this.w.h || m()) {
            return;
        }
        this.w.a = b(this.w.i);
        this.w.l = this.w.a[this.w.j];
    }

    private void g() {
        this.w.f = l();
        this.w.g = l();
        this.w.h = (k() & 128) != 0;
        this.w.i = (int) Math.pow(2.0d, (r0 & 7) + 1);
        this.w.j = k();
        this.w.k = k();
    }

    private int[] b(int i2) {
        int[] iArr;
        byte[] bArr = new byte[i2 * 3];
        try {
            this.v.get(bArr);
            iArr = new int[256];
            int i3 = 0;
            int i4 = 0;
            while (i3 < i2) {
                int i5 = i4 + 1;
                try {
                    int i6 = i5 + 1;
                    int i7 = i6 + 1;
                    int i8 = i3 + 1;
                    iArr[i3] = ((bArr[i4] & UByte.MAX_VALUE) << 16) | RoundedDrawable.DEFAULT_BORDER_COLOR | ((bArr[i5] & UByte.MAX_VALUE) << 8) | (bArr[i6] & UByte.MAX_VALUE);
                    i4 = i7;
                    i3 = i8;
                } catch (BufferUnderflowException e2) {
                    e = e2;
                    if (Log.isLoggable(c, 3)) {
                        Log.d(c, "Format Error Reading Color Table", e);
                    }
                    this.w.b = 1;
                    return iArr;
                }
            }
        } catch (BufferUnderflowException e3) {
            e = e3;
            iArr = null;
        }
        return iArr;
    }

    private void h() {
        k();
        i();
    }

    private void i() {
        int iK;
        do {
            iK = k();
            this.v.position(Math.min(this.v.position() + iK, this.v.limit()));
        } while (iK > 0);
    }

    private void j() {
        this.x = k();
        if (this.x > 0) {
            int i2 = 0;
            int i3 = 0;
            while (i2 < this.x) {
                try {
                    i3 = this.x - i2;
                    this.v.get(this.u, i2, i3);
                    i2 += i3;
                } catch (Exception e2) {
                    if (Log.isLoggable(c, 3)) {
                        Log.d(c, "Error Reading Block n: " + i2 + " count: " + i3 + " blockSize: " + this.x, e2);
                    }
                    this.w.b = 1;
                    return;
                }
            }
        }
    }

    private int k() {
        try {
            return this.v.get() & UByte.MAX_VALUE;
        } catch (Exception unused) {
            this.w.b = 1;
            return 0;
        }
    }

    private int l() {
        return this.v.getShort();
    }

    private boolean m() {
        return this.w.b != 0;
    }
}
