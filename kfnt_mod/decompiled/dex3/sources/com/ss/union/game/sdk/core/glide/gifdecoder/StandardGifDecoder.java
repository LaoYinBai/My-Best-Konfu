package com.ss.union.game.sdk.core.glide.gifdecoder;

import android.graphics.Bitmap;
import android.util.Log;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder;
import com.taobao.accs.data.Message;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StandardGifDecoder implements GifDecoder {
    private static final String a = "StandardGifDecoder";
    private static final int b = 4096;
    private static final int c = -1;
    private static final int d = -1;
    private static final int e = 4;
    private static final int f = 255;
    private static final int g = 0;
    private Boolean A;
    private Bitmap.Config B;
    private int[] h;
    private final int[] i;
    private final GifDecoder.BitmapProvider j;
    private ByteBuffer k;
    private byte[] l;
    private GifHeaderParser m;
    private short[] n;
    private byte[] o;
    private byte[] p;
    private byte[] q;
    private int[] r;
    private int s;
    private GifHeader t;
    private Bitmap u;
    private boolean v;
    private int w;
    private int x;
    private int y;
    private int z;

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer) {
        this(bitmapProvider, gifHeader, byteBuffer, 1);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider, GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        this(bitmapProvider);
        setData(gifHeader, byteBuffer, i);
    }

    public StandardGifDecoder(GifDecoder.BitmapProvider bitmapProvider) {
        this.i = new int[ApkDownloadComplianceInterface.INSTALL_BITS];
        this.B = Bitmap.Config.ARGB_8888;
        this.j = bitmapProvider;
        this.t = new GifHeader();
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int getWidth() {
        return this.t.f;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int getHeight() {
        return this.t.g;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public ByteBuffer getData() {
        return this.k;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int getStatus() {
        return this.w;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public void advance() {
        this.s = (this.s + 1) % this.t.c;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int getDelay(int i) {
        if (i < 0 || i >= this.t.c) {
            return -1;
        }
        return this.t.e.get(i).m;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int getNextDelay() {
        if (this.t.c <= 0 || this.s < 0) {
            return 0;
        }
        return getDelay(this.s);
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int getFrameCount() {
        return this.t.c;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int getCurrentFrameIndex() {
        return this.s;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public void resetFrameIndex() {
        this.s = -1;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    @Deprecated
    public int getLoopCount() {
        if (this.t.m == -1) {
            return 1;
        }
        return this.t.m;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int getNetscapeLoopCount() {
        return this.t.m;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int getTotalIterationCount() {
        if (this.t.m == -1) {
            return 1;
        }
        if (this.t.m == 0) {
            return 0;
        }
        return this.t.m + 1;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int getByteSize() {
        return this.k.limit() + this.q.length + (this.r.length * 4);
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public synchronized Bitmap getNextFrame() {
        if (this.t.c <= 0 || this.s < 0) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Unable to decode frame, frameCount=" + this.t.c + ", framePointer=" + this.s);
            }
            this.w = 1;
        }
        if (this.w != 1 && this.w != 2) {
            this.w = 0;
            if (this.l == null) {
                this.l = this.j.obtainByteArray(255);
            }
            a aVar = this.t.e.get(this.s);
            int i = this.s - 1;
            a aVar2 = i >= 0 ? this.t.e.get(i) : null;
            this.h = aVar.o != null ? aVar.o : this.t.a;
            if (this.h == null) {
                if (Log.isLoggable(a, 3)) {
                    Log.d(a, "No valid color table found for frame #" + this.s);
                }
                this.w = 1;
                return null;
            }
            if (aVar.j) {
                System.arraycopy(this.h, 0, this.i, 0, this.h.length);
                this.h = this.i;
                this.h[aVar.l] = 0;
            }
            return a(aVar, aVar2);
        }
        if (Log.isLoggable(a, 3)) {
            Log.d(a, "Unable to decode frame, status=" + this.w);
        }
        return null;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public int read(InputStream inputStream, int i) throws IOException {
        if (inputStream != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(i > 0 ? i + 4096 : Message.FLAG_REQ_BIT1);
                byte[] bArr = new byte[Message.FLAG_REQ_BIT1];
                while (true) {
                    int i2 = inputStream.read(bArr, 0, bArr.length);
                    if (i2 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                }
                byteArrayOutputStream.flush();
                read(byteArrayOutputStream.toByteArray());
            } catch (IOException e2) {
                Log.w(a, "Error reading data from stream", e2);
            }
        } else {
            this.w = 2;
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e3) {
                Log.w(a, "Error closing stream", e3);
            }
        }
        return this.w;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public void clear() {
        this.t = null;
        if (this.q != null) {
            this.j.release(this.q);
        }
        if (this.r != null) {
            this.j.release(this.r);
        }
        if (this.u != null) {
            this.j.release(this.u);
        }
        this.u = null;
        this.k = null;
        this.A = null;
        if (this.l != null) {
            this.j.release(this.l);
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader gifHeader, byte[] bArr) {
        setData(gifHeader, ByteBuffer.wrap(bArr));
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer) {
        setData(gifHeader, byteBuffer, 1);
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public synchronized void setData(GifHeader gifHeader, ByteBuffer byteBuffer, int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Sample size must be >=0, not: " + i);
        }
        int iHighestOneBit = Integer.highestOneBit(i);
        this.w = 0;
        this.t = gifHeader;
        this.s = -1;
        this.k = byteBuffer.asReadOnlyBuffer();
        this.k.position(0);
        this.k.order(ByteOrder.LITTLE_ENDIAN);
        this.v = false;
        Iterator<a> it = gifHeader.e.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().k == 3) {
                this.v = true;
                break;
            }
        }
        this.x = iHighestOneBit;
        this.z = gifHeader.f / iHighestOneBit;
        this.y = gifHeader.g / iHighestOneBit;
        this.q = this.j.obtainByteArray(gifHeader.f * gifHeader.g);
        this.r = this.j.obtainIntArray(this.z * this.y);
    }

    private GifHeaderParser a() {
        if (this.m == null) {
            this.m = new GifHeaderParser();
        }
        return this.m;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public synchronized int read(byte[] bArr) {
        this.t = a().setData(bArr).parseHeader();
        if (bArr != null) {
            setData(this.t, bArr);
        }
        return this.w;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder
    public void setDefaultBitmapConfig(Bitmap.Config config) {
        if (config != Bitmap.Config.ARGB_8888 && config != Bitmap.Config.RGB_565) {
            throw new IllegalArgumentException("Unsupported format: " + config + ", must be one of " + Bitmap.Config.ARGB_8888 + " or " + Bitmap.Config.RGB_565);
        }
        this.B = config;
    }

    private Bitmap a(a aVar, a aVar2) {
        int[] iArr = this.r;
        int i = 0;
        if (aVar2 == null) {
            if (this.u != null) {
                this.j.release(this.u);
            }
            this.u = null;
            Arrays.fill(iArr, 0);
        }
        if (aVar2 != null && aVar2.k == 3 && this.u == null) {
            Arrays.fill(iArr, 0);
        }
        if (aVar2 != null && aVar2.k > 0) {
            if (aVar2.k == 2) {
                if (!aVar.j) {
                    int i2 = this.t.l;
                    if (aVar.o == null || this.t.j != aVar.l) {
                        i = i2;
                    }
                } else if (this.s == 0) {
                    this.A = true;
                }
                int i3 = aVar2.h / this.x;
                int i4 = aVar2.f / this.x;
                int i5 = aVar2.g / this.x;
                int i6 = (i4 * this.z) + (aVar2.e / this.x);
                int i7 = (i3 * this.z) + i6;
                while (i6 < i7) {
                    int i8 = i6 + i5;
                    for (int i9 = i6; i9 < i8; i9++) {
                        iArr[i9] = i;
                    }
                    i6 += this.z;
                }
            } else if (aVar2.k == 3 && this.u != null) {
                this.u.getPixels(iArr, 0, this.z, 0, 0, this.z, this.y);
            }
        }
        c(aVar);
        if (aVar.i || this.x != 1) {
            b(aVar);
        } else {
            a(aVar);
        }
        if (this.v && (aVar.k == 0 || aVar.k == 1)) {
            if (this.u == null) {
                this.u = d();
            }
            this.u.setPixels(iArr, 0, this.z, 0, 0, this.z, this.y);
        }
        Bitmap bitmapD = d();
        bitmapD.setPixels(iArr, 0, this.z, 0, 0, this.z, this.y);
        return bitmapD;
    }

    private void a(a aVar) {
        a aVar2 = aVar;
        int[] iArr = this.r;
        int i = aVar2.h;
        int i2 = aVar2.f;
        int i3 = aVar2.g;
        int i4 = aVar2.e;
        boolean z = this.s == 0;
        int i5 = this.z;
        byte[] bArr = this.q;
        int[] iArr2 = this.h;
        int i6 = 0;
        byte b2 = -1;
        while (i6 < i) {
            int i7 = (i6 + i2) * i5;
            int i8 = i7 + i4;
            int i9 = i8 + i3;
            int i10 = i7 + i5;
            if (i10 < i9) {
                i9 = i10;
            }
            byte b3 = b2;
            int i11 = aVar2.g * i6;
            int i12 = i8;
            while (i12 < i9) {
                byte b4 = bArr[i11];
                int i13 = i;
                int i14 = b4 & UByte.MAX_VALUE;
                if (i14 != b3) {
                    int i15 = iArr2[i14];
                    if (i15 != 0) {
                        iArr[i12] = i15;
                    } else {
                        b3 = b4;
                    }
                }
                i11++;
                i12++;
                i = i13;
            }
            i6++;
            b2 = b3;
            aVar2 = aVar;
        }
        this.A = Boolean.valueOf(this.A == null && z && b2 != -1);
    }

    private void b(a aVar) {
        int i;
        int i2;
        int i3;
        int[] iArr = this.r;
        int i4 = aVar.h / this.x;
        int i5 = aVar.f / this.x;
        int i6 = aVar.g / this.x;
        int i7 = aVar.e / this.x;
        boolean z = this.s == 0;
        int i8 = this.x;
        int i9 = this.z;
        int i10 = this.y;
        byte[] bArr = this.q;
        int[] iArr2 = this.h;
        Boolean bool = this.A;
        int i11 = 0;
        int i12 = 0;
        int i13 = 1;
        int i14 = 8;
        while (i12 < i4) {
            if (aVar.i) {
                if (i11 >= i4) {
                    i13++;
                    switch (i13) {
                        case 2:
                            i11 = 4;
                            break;
                        case 3:
                            i11 = 2;
                            i14 = 4;
                            break;
                        case 4:
                            i11 = 1;
                            i14 = 2;
                            break;
                    }
                }
                i = i11 + i14;
            } else {
                i = i11;
                i11 = i12;
            }
            int i15 = i11 + i5;
            int i16 = i4;
            boolean z2 = i8 == 1;
            if (i15 < i10) {
                int i17 = i15 * i9;
                int i18 = i17 + i7;
                i2 = i5;
                int i19 = i18 + i6;
                int i20 = i17 + i9;
                if (i20 < i19) {
                    i19 = i20;
                }
                i3 = i6;
                int i21 = i12 * i8 * aVar.g;
                if (z2) {
                    for (int i22 = i18; i22 < i19; i22++) {
                        int i23 = iArr2[bArr[i21] & UByte.MAX_VALUE];
                        if (i23 != 0) {
                            iArr[i22] = i23;
                        } else if (z && bool == null) {
                            bool = true;
                        }
                        i21 += i8;
                    }
                } else {
                    int i24 = ((i19 - i18) * i8) + i21;
                    int i25 = i18;
                    while (i25 < i19) {
                        int i26 = i19;
                        int iA = a(i21, i24, aVar.g);
                        if (iA != 0) {
                            iArr[i25] = iA;
                        } else {
                            if (z && bool == null) {
                                bool = true;
                            }
                            i21 += i8;
                            i25++;
                            i19 = i26;
                        }
                        i21 += i8;
                        i25++;
                        i19 = i26;
                    }
                }
            } else {
                i2 = i5;
                i3 = i6;
            }
            i12++;
            i11 = i;
            i4 = i16;
            i5 = i2;
            i6 = i3;
        }
        if (this.A == null) {
            this.A = Boolean.valueOf(bool == null ? false : bool.booleanValue());
        }
    }

    private int a(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        for (int i9 = i; i9 < this.x + i && i9 < this.q.length && i9 < i2; i9++) {
            int i10 = this.h[this.q[i9] & UByte.MAX_VALUE];
            if (i10 != 0) {
                i4 += (i10 >> 24) & 255;
                i5 += (i10 >> 16) & 255;
                i6 += (i10 >> 8) & 255;
                i7 += i10 & 255;
                i8++;
            }
        }
        int i11 = i + i3;
        for (int i12 = i11; i12 < this.x + i11 && i12 < this.q.length && i12 < i2; i12++) {
            int i13 = this.h[this.q[i12] & UByte.MAX_VALUE];
            if (i13 != 0) {
                i4 += (i13 >> 24) & 255;
                i5 += (i13 >> 16) & 255;
                i6 += (i13 >> 8) & 255;
                i7 += i13 & 255;
                i8++;
            }
        }
        if (i8 == 0) {
            return 0;
        }
        return ((i4 / i8) << 24) | ((i5 / i8) << 16) | ((i6 / i8) << 8) | (i7 / i8);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v16, types: [short] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v9 */
    private void c(a aVar) {
        int i;
        short s;
        StandardGifDecoder standardGifDecoder = this;
        if (aVar != null) {
            standardGifDecoder.k.position(aVar.n);
        }
        int i2 = aVar == null ? standardGifDecoder.t.f * standardGifDecoder.t.g : aVar.h * aVar.g;
        if (standardGifDecoder.q == null || standardGifDecoder.q.length < i2) {
            standardGifDecoder.q = standardGifDecoder.j.obtainByteArray(i2);
        }
        byte[] bArr = standardGifDecoder.q;
        if (standardGifDecoder.n == null) {
            standardGifDecoder.n = new short[4096];
        }
        short[] sArr = standardGifDecoder.n;
        if (standardGifDecoder.o == null) {
            standardGifDecoder.o = new byte[4096];
        }
        byte[] bArr2 = standardGifDecoder.o;
        if (standardGifDecoder.p == null) {
            standardGifDecoder.p = new byte[4097];
        }
        byte[] bArr3 = standardGifDecoder.p;
        int iB = b();
        int i3 = 1 << iB;
        int i4 = i3 + 1;
        int i5 = i3 + 2;
        int i6 = iB + 1;
        int i7 = (1 << i6) - 1;
        int i8 = 0;
        for (int i9 = 0; i9 < i3; i9++) {
            sArr[i9] = 0;
            bArr2[i9] = (byte) i9;
        }
        byte[] bArr4 = standardGifDecoder.l;
        int i10 = i6;
        int i11 = i5;
        int i12 = i7;
        int iC = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = -1;
        int i18 = 0;
        int i19 = 0;
        while (true) {
            if (i8 >= i2) {
                break;
            }
            if (iC == 0) {
                iC = c();
                if (iC <= 0) {
                    standardGifDecoder.w = 3;
                    break;
                }
                i16 = 0;
            }
            i15 += (bArr4[i16] & UByte.MAX_VALUE) << i14;
            i16++;
            iC--;
            int i20 = i14 + 8;
            int i21 = i17;
            int i22 = i18;
            int i23 = i11;
            int i24 = i13;
            int i25 = i8;
            int i26 = i10;
            while (true) {
                if (i20 < i26) {
                    i11 = i23;
                    i17 = i21;
                    i10 = i26;
                    i8 = i25;
                    i13 = i24;
                    i18 = i22;
                    i14 = i20;
                    standardGifDecoder = this;
                    break;
                }
                int i27 = i15 & i12;
                i15 >>= i26;
                i20 -= i26;
                if (i27 == i3) {
                    i26 = i6;
                    i23 = i5;
                    i12 = i7;
                    i21 = -1;
                } else {
                    if (i27 == i4) {
                        i17 = i21;
                        i10 = i26;
                        i8 = i25;
                        i13 = i24;
                        i11 = i23;
                        i18 = i22;
                        i14 = i20;
                        break;
                    }
                    if (i21 == -1) {
                        bArr[i24] = bArr2[i27];
                        i24++;
                        i25++;
                        i21 = i27;
                        i22 = i21;
                    } else {
                        int i28 = i23;
                        if (i27 >= i28) {
                            i = i20;
                            bArr3[i19] = (byte) i22;
                            i19++;
                            s = i21;
                        } else {
                            i = i20;
                            s = i27;
                        }
                        while (s >= i3) {
                            bArr3[i19] = bArr2[s];
                            i19++;
                            s = sArr[s];
                        }
                        int i29 = bArr2[s] & UByte.MAX_VALUE;
                        int i30 = i6;
                        byte b2 = (byte) i29;
                        bArr[i24] = b2;
                        i24++;
                        i25++;
                        while (i19 > 0) {
                            i19--;
                            bArr[i24] = bArr3[i19];
                            i24++;
                            i25++;
                        }
                        if (i28 < 4096) {
                            sArr[i28] = (short) i21;
                            bArr2[i28] = b2;
                            i28++;
                            if ((i28 & i12) == 0 && i28 < 4096) {
                                i26++;
                                i12 += i28;
                            }
                        }
                        i23 = i28;
                        i21 = i27;
                        i20 = i;
                        i6 = i30;
                        i22 = i29;
                    }
                    standardGifDecoder = this;
                }
            }
        }
        Arrays.fill(bArr, i13, i2, (byte) 0);
    }

    private int b() {
        return this.k.get() & UByte.MAX_VALUE;
    }

    private int c() {
        int iB = b();
        if (iB <= 0) {
            return iB;
        }
        this.k.get(this.l, 0, Math.min(iB, this.k.remaining()));
        return iB;
    }

    private Bitmap d() {
        Bitmap bitmapObtain = this.j.obtain(this.z, this.y, (this.A == null || this.A.booleanValue()) ? Bitmap.Config.ARGB_8888 : this.B);
        bitmapObtain.setHasAlpha(true);
        return bitmapObtain;
    }
}
