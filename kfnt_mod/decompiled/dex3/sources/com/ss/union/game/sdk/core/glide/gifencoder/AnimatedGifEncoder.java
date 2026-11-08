package com.ss.union.game.sdk.core.glide.gifencoder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AnimatedGifEncoder {
    private static final String a = "AnimatedGifEncoder";
    private static final double b = 4.0d;
    private int c;
    private int d;
    private int e;
    private int f;
    private int h;
    private OutputStream l;
    private Bitmap m;
    private byte[] n;
    private byte[] o;
    private int p;
    private byte[] q;
    private boolean y;
    private Integer g = null;
    private int i = -1;
    private int j = 0;
    private boolean k = false;
    private boolean[] r = new boolean[ApkDownloadComplianceInterface.INSTALL_BITS];
    private int s = 7;
    private int t = -1;
    private boolean u = false;
    private boolean v = true;
    private boolean w = false;
    private int x = 10;

    public void setDelay(int i) {
        this.j = Math.round(i / 10.0f);
    }

    public void setDispose(int i) {
        if (i >= 0) {
            this.t = i;
        }
    }

    public void setRepeat(int i) {
        if (i >= 0) {
            this.i = i;
        }
    }

    public void setTransparent(int i) {
        this.g = Integer.valueOf(i);
    }

    public boolean addFrame(Bitmap bitmap) {
        return addFrame(bitmap, 0, 0);
    }

    public boolean addFrame(Bitmap bitmap, int i, int i2) {
        if (bitmap == null || !this.k) {
            return false;
        }
        try {
            if (this.w) {
                a(this.e, this.f);
            } else {
                a(bitmap.getWidth(), bitmap.getHeight());
            }
            this.m = bitmap;
            b();
            a();
            if (this.v) {
                d();
                f();
                if (this.i >= 0) {
                    e();
                }
            }
            c();
            b(i, i2);
            if (!this.v) {
                f();
            }
            g();
            this.v = false;
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public boolean finish() throws IOException {
        boolean z;
        if (!this.k) {
            return false;
        }
        this.k = false;
        try {
            this.l.write(59);
            this.l.flush();
            if (this.u) {
                this.l.close();
            }
            z = true;
        } catch (IOException unused) {
            z = false;
        }
        this.h = 0;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.q = null;
        this.u = false;
        this.v = true;
        return z;
    }

    public void setFrameRate(float f) {
        if (f != 0.0f) {
            this.j = Math.round(100.0f / f);
        }
    }

    public void setQuality(int i) {
        if (i < 1) {
            i = 1;
        }
        this.x = i;
    }

    public void setSize(int i, int i2) {
        if (this.k) {
            return;
        }
        this.e = i;
        this.f = i2;
        if (this.e < 1) {
            this.e = 320;
        }
        if (this.f < 1) {
            this.f = 240;
        }
        this.w = true;
    }

    private void a(int i, int i2) {
        this.c = i;
        this.d = i2;
    }

    public boolean start(OutputStream outputStream) {
        boolean z = false;
        if (outputStream == null) {
            return false;
        }
        this.u = false;
        this.l = outputStream;
        try {
            a("GIF89a");
            z = true;
        } catch (IOException unused) {
        }
        this.k = z;
        return z;
    }

    public boolean start(String str) {
        boolean zStart;
        try {
            this.l = new BufferedOutputStream(new FileOutputStream(str));
            zStart = start(this.l);
            this.u = true;
        } catch (IOException unused) {
            zStart = false;
        }
        this.k = zStart;
        return zStart;
    }

    private void a() {
        int length = this.n.length;
        int i = length / 3;
        this.o = new byte[i];
        b bVar = new b(this.n, length, this.x);
        this.q = bVar.d();
        for (int i2 = 0; i2 < this.q.length; i2 += 3) {
            byte b2 = this.q[i2];
            int i3 = i2 + 2;
            this.q[i2] = this.q[i3];
            this.q[i3] = b2;
            this.r[i2 / 3] = false;
        }
        int i4 = 0;
        int i5 = 0;
        while (i4 < i) {
            int i6 = i5 + 1;
            int i7 = i6 + 1;
            int iA = bVar.a(this.n[i5] & UByte.MAX_VALUE, this.n[i6] & UByte.MAX_VALUE, this.n[i7] & UByte.MAX_VALUE);
            this.r[iA] = true;
            this.o[i4] = (byte) iA;
            i4++;
            i5 = i7 + 1;
        }
        this.n = null;
        this.p = 8;
        this.s = 7;
        if (this.g != null) {
            this.h = a(this.g.intValue());
        } else if (this.y) {
            this.h = a(0);
        }
    }

    private int a(int i) {
        if (this.q == null) {
            return -1;
        }
        int iRed = Color.red(i);
        int iGreen = Color.green(i);
        int iBlue = Color.blue(i);
        int length = this.q.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = DownloadExpSwitchCode.BACK_PARTIAL;
        while (i2 < length) {
            int i5 = i2 + 1;
            int i6 = iRed - (this.q[i2] & UByte.MAX_VALUE);
            int i7 = i5 + 1;
            int i8 = iGreen - (this.q[i5] & UByte.MAX_VALUE);
            int i9 = iBlue - (this.q[i7] & UByte.MAX_VALUE);
            int i10 = (i6 * i6) + (i8 * i8) + (i9 * i9);
            int i11 = i7 / 3;
            if (this.r[i11] && i10 < i4) {
                i4 = i10;
                i3 = i11;
            }
            i2 = i7 + 1;
        }
        return i3;
    }

    private void b() {
        int width = this.m.getWidth();
        int height = this.m.getHeight();
        if (width != this.c || height != this.d) {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(this.c, this.d, Bitmap.Config.ARGB_8888);
            new Canvas(bitmapCreateBitmap).drawBitmap(bitmapCreateBitmap, 0.0f, 0.0f, (Paint) null);
            this.m = bitmapCreateBitmap;
        }
        int[] iArr = new int[width * height];
        this.m.getPixels(iArr, 0, width, 0, 0, width, height);
        this.n = new byte[iArr.length * 3];
        this.y = false;
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = iArr[i];
            if (i4 == 0) {
                i2++;
            }
            int i5 = i3 + 1;
            this.n[i3] = (byte) (i4 & 255);
            int i6 = i5 + 1;
            this.n[i5] = (byte) ((i4 >> 8) & 255);
            this.n[i6] = (byte) ((i4 >> 16) & 255);
            i++;
            i3 = i6 + 1;
        }
        double length2 = (i2 * 100) / iArr.length;
        this.y = length2 > b;
        if (Log.isLoggable(a, 3)) {
            Log.d(a, "got pixels for frame with " + length2 + "% transparent pixels");
        }
    }

    private void c() throws IOException {
        int i;
        int i2;
        this.l.write(33);
        this.l.write(249);
        this.l.write(4);
        if (this.g != null || this.y) {
            i = 2;
            i2 = 1;
        } else {
            i = 0;
            i2 = 0;
        }
        if (this.t >= 0) {
            i = this.t & 7;
        }
        this.l.write((i << 2) | 0 | 0 | i2);
        b(this.j);
        this.l.write(this.h);
        this.l.write(0);
    }

    private void b(int i, int i2) throws IOException {
        this.l.write(44);
        b(i);
        b(i2);
        b(this.c);
        b(this.d);
        if (this.v) {
            this.l.write(0);
        } else {
            this.l.write(this.s | 128);
        }
    }

    private void d() throws IOException {
        b(this.c);
        b(this.d);
        this.l.write(this.s | 240);
        this.l.write(0);
        this.l.write(0);
    }

    private void e() throws IOException {
        this.l.write(33);
        this.l.write(255);
        this.l.write(11);
        a("NETSCAPE2.0");
        this.l.write(3);
        this.l.write(1);
        b(this.i);
        this.l.write(0);
    }

    private void f() throws IOException {
        this.l.write(this.q, 0, this.q.length);
        int length = 768 - this.q.length;
        for (int i = 0; i < length; i++) {
            this.l.write(0);
        }
    }

    private void g() throws IOException {
        new a(this.c, this.d, this.o, this.p).b(this.l);
    }

    private void b(int i) throws IOException {
        this.l.write(i & 255);
        this.l.write((i >> 8) & 255);
    }

    private void a(String str) throws IOException {
        for (int i = 0; i < str.length(); i++) {
            this.l.write((byte) str.charAt(i));
        }
    }
}
