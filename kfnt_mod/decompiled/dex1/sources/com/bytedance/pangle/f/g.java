package com.bytedance.pangle.f;

import android.support.v4.media.session.PlaybackStateCompat;
import com.bytedance.common.utility.StringEncryptUtils;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.DigestException;
import java.security.MessageDigest;
import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
abstract class g {

    /* renamed from: a, reason: collision with root package name */
    private static final byte[] f4611a = new byte[8];

    static class a {

        /* renamed from: a, reason: collision with root package name */
        public final ByteBuffer f4612a;

        /* renamed from: b, reason: collision with root package name */
        public final byte[] f4613b;

        a(ByteBuffer byteBuffer, byte[] bArr) {
            this.f4612a = byteBuffer;
            this.f4613b = bArr;
        }
    }

    static a a(RandomAccessFile randomAccessFile, m mVar, i iVar) throws DigestException, IOException {
        int[] iArrA = a(randomAccessFile.length() - (mVar.f4623c - mVar.f4622b));
        int i = iArrA[iArrA.length - 1];
        int i2 = i + 4096;
        ByteBuffer byteBufferA = iVar.a(i2);
        byteBufferA.order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer byteBufferA2 = a(byteBufferA, 0, i);
        int i3 = i + 64;
        ByteBuffer byteBufferA3 = a(byteBufferA, i, i3);
        ByteBuffer byteBufferA4 = a(byteBufferA, i3, i2);
        byte[] bArr = new byte[32];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        if (mVar.f4622b % PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM != 0) {
            throw new IllegalArgumentException("APK Signing Block does not start at the page  boundary: " + mVar.f4622b);
        }
        if ((mVar.f4623c - mVar.f4622b) % PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM == 0) {
            long j = mVar.f4623c - mVar.f4622b;
            int[] iArrA2 = a(randomAccessFile.length() - j);
            if (byteBufferA2 != null) {
                byte[] bArrA = a(randomAccessFile, mVar, f4611a, iArrA2, byteBufferA2);
                if (byteBufferWrap != null) {
                    byteBufferWrap.put(bArrA);
                    byteBufferWrap.flip();
                }
            }
            if (byteBufferA3 != null) {
                byteBufferA3.order(ByteOrder.LITTLE_ENDIAN);
                long length = randomAccessFile.length();
                byte[] bArr2 = f4611a;
                if (bArr2.length != 8) {
                    throw new IllegalArgumentException("salt is not 8 bytes long");
                }
                byteBufferA3.put("TrueBrew".getBytes());
                byteBufferA3.put((byte) 1);
                byteBufferA3.put((byte) 0);
                byteBufferA3.put((byte) 12);
                byteBufferA3.put((byte) 7);
                byteBufferA3.putShort((short) 1);
                byteBufferA3.putShort((short) 1);
                byteBufferA3.putInt(0);
                byteBufferA3.putInt(0);
                byteBufferA3.putLong(length);
                byteBufferA3.put((byte) 2);
                byteBufferA3.put((byte) 0);
                byteBufferA3.put(bArr2);
                a(byteBufferA3, 22);
                byteBufferA3.flip();
            }
            if (byteBufferA4 != null) {
                byteBufferA4.order(ByteOrder.LITTLE_ENDIAN);
                long j2 = mVar.f4622b;
                long j3 = mVar.d;
                byteBufferA4.putInt(24);
                byteBufferA4.putShort((short) 1);
                a(byteBufferA4, 2);
                byteBufferA4.putLong(j2);
                byteBufferA4.putLong(j);
                byteBufferA4.putInt(20);
                byteBufferA4.putShort((short) 2);
                a(byteBufferA4, 2);
                byteBufferA4.putLong(j3 + 16);
                byteBufferA4.putInt(c(j2));
                a(byteBufferA4, 4);
                byteBufferA4.flip();
            }
            byteBufferA.position(i3 + byteBufferA4.limit());
            byteBufferA.putInt(byteBufferA4.limit() + 64 + 4);
            byteBufferA.flip();
            return new a(byteBufferA, bArr);
        }
        throw new IllegalArgumentException("Size of APK Signing Block is not a multiple of 4096: " + (mVar.f4623c - mVar.f4622b));
    }

    static class b implements j {

        /* renamed from: a, reason: collision with root package name */
        private int f4614a;

        /* renamed from: b, reason: collision with root package name */
        private final ByteBuffer f4615b;

        /* renamed from: c, reason: collision with root package name */
        private final MessageDigest f4616c;
        private final byte[] d;
        private final byte[] e;

        /* synthetic */ b(byte[] bArr, ByteBuffer byteBuffer, byte b2) {
            this(bArr, byteBuffer);
        }

        private b(byte[] bArr, ByteBuffer byteBuffer) {
            this.d = new byte[32];
            this.e = bArr;
            this.f4615b = byteBuffer.slice();
            this.f4616c = MessageDigest.getInstance(StringEncryptUtils.SHA_256);
            this.f4616c.update(this.e);
            this.f4614a = 0;
        }

        @Override // com.bytedance.pangle.f.j
        public final void a(ByteBuffer byteBuffer) throws DigestException {
            byteBuffer.position();
            int iRemaining = byteBuffer.remaining();
            while (iRemaining > 0) {
                int iMin = Math.min(iRemaining, 4096 - this.f4614a);
                byteBuffer.limit(byteBuffer.position() + iMin);
                this.f4616c.update(byteBuffer);
                iRemaining -= iMin;
                this.f4614a += iMin;
                if (this.f4614a == 4096) {
                    this.f4616c.digest(this.d, 0, this.d.length);
                    this.f4615b.put(this.d);
                    this.f4616c.update(this.e);
                    this.f4614a = 0;
                }
            }
        }

        public final void a() {
            if (this.f4614a == 0) {
                return;
            }
            throw new IllegalStateException("Buffer is not empty: " + this.f4614a);
        }

        final void b() {
            int iPosition = this.f4615b.position() % 4096;
            if (iPosition == 0) {
                return;
            }
            this.f4615b.put(ByteBuffer.allocate(4096 - iPosition));
        }
    }

    private static void a(j jVar, k kVar, int i) {
        long jA = kVar.a();
        long j = 0;
        while (jA > 0) {
            int iMin = (int) Math.min(jA, i);
            kVar.a(jVar, j, iMin);
            long j2 = iMin;
            j += j2;
            jA -= j2;
        }
    }

    private static byte[] a(RandomAccessFile randomAccessFile, m mVar, byte[] bArr, int[] iArr, ByteBuffer byteBuffer) throws DigestException {
        byte b2 = 0;
        b bVar = new b(bArr, a(byteBuffer, iArr[iArr.length - 2], iArr[iArr.length - 1]), b2);
        a(bVar, new l(randomAccessFile.getFD(), 0L, mVar.f4622b), 1048576);
        long j = mVar.d + 16;
        a(bVar, new l(randomAccessFile.getFD(), mVar.f4623c, j - mVar.f4623c), 1048576);
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.putInt(c(mVar.f4622b));
        byteBufferOrder.flip();
        bVar.a(byteBufferOrder);
        long j2 = j + 4;
        a(bVar, new l(randomAccessFile.getFD(), j2, randomAccessFile.length() - j2), 1048576);
        int length = (int) (randomAccessFile.length() % PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
        if (length != 0) {
            bVar.a(ByteBuffer.allocate(4096 - length));
        }
        bVar.a();
        bVar.b();
        for (int length2 = iArr.length - 3; length2 >= 0; length2--) {
            int i = length2 + 1;
            ByteBuffer byteBufferA = a(byteBuffer, iArr[i], iArr[length2 + 2]);
            ByteBuffer byteBufferA2 = a(byteBuffer, iArr[length2], iArr[i]);
            h hVar = new h(byteBufferA);
            b bVar2 = new b(bArr, byteBufferA2, b2);
            a(bVar2, hVar, 4096);
            bVar2.a();
            bVar2.b();
        }
        byte[] bArr2 = new byte[32];
        b bVar3 = new b(bArr, ByteBuffer.wrap(bArr2), b2);
        bVar3.a(a(byteBuffer, 0, 4096));
        bVar3.a();
        return bArr2;
    }

    private static int[] a(long j) {
        ArrayList arrayList = new ArrayList();
        do {
            j = b(j) * 32;
            arrayList.add(Long.valueOf(b(j) * PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM));
        } while (j > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM);
        int[] iArr = new int[arrayList.size() + 1];
        int i = 0;
        iArr[0] = 0;
        while (i < arrayList.size()) {
            int i2 = i + 1;
            iArr[i2] = iArr[i] + c(((Long) arrayList.get((arrayList.size() - i) - 1)).longValue());
            i = i2;
        }
        return iArr;
    }

    private static ByteBuffer a(ByteBuffer byteBuffer, int i, int i2) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(0);
        byteBufferDuplicate.limit(i2);
        byteBufferDuplicate.position(i);
        return byteBufferDuplicate.slice();
    }

    private static void a(ByteBuffer byteBuffer, int i) {
        byteBuffer.position(byteBuffer.position() + i);
    }

    private static long b(long j) {
        return ((j + PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) - 1) / PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
    }

    private static int c(long j) {
        int i = (int) j;
        if (i == j) {
            return i;
        }
        throw new ArithmeticException("integer overflow");
    }
}
