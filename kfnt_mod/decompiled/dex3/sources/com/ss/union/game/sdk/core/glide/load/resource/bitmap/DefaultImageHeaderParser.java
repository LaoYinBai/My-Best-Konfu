package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.ImageHeaderParser;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class DefaultImageHeaderParser implements ImageHeaderParser {
    static final int a = 65496;
    static final int c = 255;
    static final int d = 225;
    private static final String e = "DfltImageHeaderParser";
    private static final int f = 4671814;
    private static final int g = -1991225785;
    private static final int h = 19789;
    private static final int i = 18761;
    private static final int k = 218;
    private static final int l = 217;
    private static final int m = 274;
    private static final int o = 1380533830;
    private static final int p = 1464156752;
    private static final int q = 1448097792;
    private static final int r = -256;
    private static final int s = 255;
    private static final int t = 88;
    private static final int u = 76;
    private static final int v = 16;
    private static final int w = 8;
    private static final String j = "Exif\u0000\u0000";
    static final byte[] b = j.getBytes(Charset.forName("UTF-8"));
    private static final int[] n = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8};

    private interface c {
        int a() throws IOException;

        int a(byte[] bArr, int i) throws IOException;

        long a(long j) throws IOException;

        short b() throws IOException;

        int c() throws IOException;
    }

    private static int a(int i2, int i3) {
        return i2 + 2 + (i3 * 12);
    }

    private static boolean a(int i2) {
        return (i2 & a) == a || i2 == h || i2 == i;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ImageHeaderParser
    public ImageHeaderParser.ImageType getType(InputStream inputStream) throws IOException {
        return a(new d((InputStream) Preconditions.checkNotNull(inputStream)));
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ImageHeaderParser
    public ImageHeaderParser.ImageType getType(ByteBuffer byteBuffer) throws IOException {
        return a(new a((ByteBuffer) Preconditions.checkNotNull(byteBuffer)));
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ImageHeaderParser
    public int getOrientation(InputStream inputStream, ArrayPool arrayPool) throws IOException {
        return a(new d((InputStream) Preconditions.checkNotNull(inputStream)), (ArrayPool) Preconditions.checkNotNull(arrayPool));
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ImageHeaderParser
    public int getOrientation(ByteBuffer byteBuffer, ArrayPool arrayPool) throws IOException {
        return a(new a((ByteBuffer) Preconditions.checkNotNull(byteBuffer)), (ArrayPool) Preconditions.checkNotNull(arrayPool));
    }

    private ImageHeaderParser.ImageType a(c cVar) throws IOException {
        int iA = cVar.a();
        if (iA == a) {
            return ImageHeaderParser.ImageType.JPEG;
        }
        int iA2 = ((iA << 16) & (-65536)) | (cVar.a() & 65535);
        if (iA2 == g) {
            cVar.a(21L);
            return cVar.c() >= 3 ? ImageHeaderParser.ImageType.PNG_A : ImageHeaderParser.ImageType.PNG;
        }
        if ((iA2 >> 8) == f) {
            return ImageHeaderParser.ImageType.GIF;
        }
        if (iA2 != o) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        cVar.a(4L);
        if ((((cVar.a() << 16) & (-65536)) | (cVar.a() & 65535)) != p) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        int iA3 = ((cVar.a() << 16) & (-65536)) | (cVar.a() & 65535);
        if ((iA3 & r) != q) {
            return ImageHeaderParser.ImageType.UNKNOWN;
        }
        int i2 = iA3 & 255;
        if (i2 == 88) {
            cVar.a(4L);
            return (cVar.c() & 16) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
        }
        if (i2 == 76) {
            cVar.a(4L);
            return (cVar.c() & 8) != 0 ? ImageHeaderParser.ImageType.WEBP_A : ImageHeaderParser.ImageType.WEBP;
        }
        return ImageHeaderParser.ImageType.WEBP;
    }

    private int a(c cVar, ArrayPool arrayPool) throws IOException {
        int iA = cVar.a();
        if (!a(iA)) {
            if (Log.isLoggable(e, 3)) {
                Log.d(e, "Parser doesn't handle magic number: " + iA);
            }
            return -1;
        }
        int iB = b(cVar);
        if (iB == -1) {
            if (Log.isLoggable(e, 3)) {
                Log.d(e, "Failed to parse exif segment length, or exif segment not found");
            }
            return -1;
        }
        byte[] bArr = (byte[]) arrayPool.get(iB, byte[].class);
        try {
            return a(cVar, bArr, iB);
        } finally {
            arrayPool.put(bArr);
        }
    }

    private int a(c cVar, byte[] bArr, int i2) throws IOException {
        int iA = cVar.a(bArr, i2);
        if (iA != i2) {
            if (Log.isLoggable(e, 3)) {
                Log.d(e, "Unable to read exif segment data, length: " + i2 + ", actually read: " + iA);
            }
            return -1;
        }
        if (a(bArr, i2)) {
            return a(new b(bArr, i2));
        }
        if (Log.isLoggable(e, 3)) {
            Log.d(e, "Missing jpeg exif preamble");
        }
        return -1;
    }

    private boolean a(byte[] bArr, int i2) {
        boolean z = bArr != null && i2 > b.length;
        if (!z) {
            return z;
        }
        for (int i3 = 0; i3 < b.length; i3++) {
            if (bArr[i3] != b[i3]) {
                return false;
            }
        }
        return z;
    }

    private int b(c cVar) throws IOException {
        short sB;
        int iA;
        long j2;
        long jA;
        do {
            short sB2 = cVar.b();
            if (sB2 != 255) {
                if (Log.isLoggable(e, 3)) {
                    Log.d(e, "Unknown segmentId=" + ((int) sB2));
                }
                return -1;
            }
            sB = cVar.b();
            if (sB == k) {
                return -1;
            }
            if (sB == l) {
                if (Log.isLoggable(e, 3)) {
                    Log.d(e, "Found MARKER_EOI in exif segment");
                }
                return -1;
            }
            iA = cVar.a() - 2;
            if (sB == d) {
                return iA;
            }
            j2 = iA;
            jA = cVar.a(j2);
        } while (jA == j2);
        if (Log.isLoggable(e, 3)) {
            Log.d(e, "Unable to skip enough data, type: " + ((int) sB) + ", wanted to skip: " + iA + ", but actually skipped: " + jA);
        }
        return -1;
    }

    private static int a(b bVar) {
        ByteOrder byteOrder;
        int length = j.length();
        short sB = bVar.b(length);
        if (sB == i) {
            byteOrder = ByteOrder.LITTLE_ENDIAN;
        } else {
            if (sB != h && Log.isLoggable(e, 3)) {
                Log.d(e, "Unknown endianness = " + ((int) sB));
            }
            byteOrder = ByteOrder.BIG_ENDIAN;
        }
        bVar.a(byteOrder);
        int iA = bVar.a(length + 4) + length;
        short sB2 = bVar.b(iA);
        for (int i2 = 0; i2 < sB2; i2++) {
            int iA2 = a(iA, i2);
            short sB3 = bVar.b(iA2);
            if (sB3 == m) {
                short sB4 = bVar.b(iA2 + 2);
                if (sB4 < 1 || sB4 > 12) {
                    if (Log.isLoggable(e, 3)) {
                        Log.d(e, "Got invalid format code = " + ((int) sB4));
                    }
                } else {
                    int iA3 = bVar.a(iA2 + 4);
                    if (iA3 < 0) {
                        if (Log.isLoggable(e, 3)) {
                            Log.d(e, "Negative tiff component count");
                        }
                    } else {
                        if (Log.isLoggable(e, 3)) {
                            Log.d(e, "Got tagIndex=" + i2 + " tagType=" + ((int) sB3) + " formatCode=" + ((int) sB4) + " componentCount=" + iA3);
                        }
                        int i3 = iA3 + n[sB4];
                        if (i3 > 4) {
                            if (Log.isLoggable(e, 3)) {
                                Log.d(e, "Got byte count > 4, not orientation, continuing, formatCode=" + ((int) sB4));
                            }
                        } else {
                            int i4 = iA2 + 8;
                            if (i4 < 0 || i4 > bVar.a()) {
                                if (Log.isLoggable(e, 3)) {
                                    Log.d(e, "Illegal tagValueOffset=" + i4 + " tagType=" + ((int) sB3));
                                }
                            } else if (i3 < 0 || i3 + i4 > bVar.a()) {
                                if (Log.isLoggable(e, 3)) {
                                    Log.d(e, "Illegal number of bytes for TI tag data tagType=" + ((int) sB3));
                                }
                            } else {
                                return bVar.b(i4);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static final class b {
        private final ByteBuffer a;

        b(byte[] bArr, int i) {
            this.a = (ByteBuffer) ByteBuffer.wrap(bArr).order(ByteOrder.BIG_ENDIAN).limit(i);
        }

        void a(ByteOrder byteOrder) {
            this.a.order(byteOrder);
        }

        int a() {
            return this.a.remaining();
        }

        int a(int i) {
            if (a(i, 4)) {
                return this.a.getInt(i);
            }
            return -1;
        }

        short b(int i) {
            if (a(i, 2)) {
                return this.a.getShort(i);
            }
            return (short) -1;
        }

        private boolean a(int i, int i2) {
            return this.a.remaining() - i >= i2;
        }
    }

    private static final class a implements c {
        private final ByteBuffer a;

        a(ByteBuffer byteBuffer) {
            this.a = byteBuffer;
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser.c
        public int a() {
            return ((c() << 8) & 65280) | (c() & 255);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser.c
        public short b() {
            return (short) (c() & 255);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser.c
        public long a(long j) {
            int iMin = (int) Math.min(this.a.remaining(), j);
            this.a.position(this.a.position() + iMin);
            return iMin;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser.c
        public int a(byte[] bArr, int i) {
            int iMin = Math.min(i, this.a.remaining());
            if (iMin == 0) {
                return -1;
            }
            this.a.get(bArr, 0, iMin);
            return iMin;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser.c
        public int c() {
            if (this.a.remaining() < 1) {
                return -1;
            }
            return this.a.get();
        }
    }

    private static final class d implements c {
        private final InputStream a;

        d(InputStream inputStream) {
            this.a = inputStream;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser.c
        public int a() throws IOException {
            return ((this.a.read() << 8) & 65280) | (this.a.read() & 255);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser.c
        public short b() throws IOException {
            return (short) (this.a.read() & 255);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser.c
        public long a(long j) throws IOException {
            if (j < 0) {
                return 0L;
            }
            long j2 = j;
            while (j2 > 0) {
                long jSkip = this.a.skip(j2);
                if (jSkip > 0) {
                    j2 -= jSkip;
                } else {
                    if (this.a.read() == -1) {
                        break;
                    }
                    j2--;
                }
            }
            return j - j2;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser.c
        public int a(byte[] bArr, int i) throws IOException {
            int i2 = i;
            while (i2 > 0) {
                int i3 = this.a.read(bArr, i - i2, i2);
                if (i3 == -1) {
                    break;
                }
                i2 -= i3;
            }
            return i - i2;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DefaultImageHeaderParser.c
        public int c() throws IOException {
            return this.a.read();
        }
    }
}
