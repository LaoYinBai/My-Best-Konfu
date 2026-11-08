package com.bytedance.pangle.f;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.ArrayMap;
import android.util.Pair;
import androidx.annotation.RequiresApi;
import androidx.core.view.InputDeviceCompat;
import com.bytedance.common.utility.StringEncryptUtils;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PSSParameterSpec;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class f {
    static m a(RandomAccessFile randomAccessFile, int i) throws n, IOException {
        Pair<ByteBuffer, Long> pairA = a(randomAccessFile);
        ByteBuffer byteBuffer = (ByteBuffer) pairA.first;
        long jLongValue = ((Long) pairA.second).longValue();
        if (s.a(randomAccessFile, jLongValue)) {
            throw new n("ZIP64 APK not supported");
        }
        long jA = a(byteBuffer, jLongValue);
        Pair<ByteBuffer, Long> pairA2 = a(randomAccessFile, jA);
        ByteBuffer byteBuffer2 = (ByteBuffer) pairA2.first;
        return new m(c(byteBuffer2, i), ((Long) pairA2.second).longValue(), jA, jLongValue, byteBuffer);
    }

    @RequiresApi(api = 21)
    static void a(Map<Integer, byte[]> map, RandomAccessFile randomAccessFile, m mVar) {
        if (map.isEmpty()) {
            throw new SecurityException("No digests provided");
        }
        ArrayMap arrayMap = new ArrayMap();
        boolean z = true;
        if (map.containsKey(1)) {
            arrayMap.put(1, map.get(1));
        }
        if (map.containsKey(2)) {
            arrayMap.put(2, map.get(2));
        }
        if (!arrayMap.isEmpty()) {
            try {
                a(arrayMap, randomAccessFile.getFD(), mVar);
                z = false;
            } catch (IOException e) {
                throw new SecurityException("Cannot get FD", e);
            }
        }
        if (map.containsKey(3)) {
            a(map.get(3), randomAccessFile, mVar);
            z = false;
        }
        if (z) {
            throw new SecurityException("No known digest exists for integrity check");
        }
    }

    private static void a(Map<Integer, byte[]> map, FileDescriptor fileDescriptor, m mVar) {
        l lVar = new l(fileDescriptor, 0L, mVar.f4622b);
        l lVar2 = new l(fileDescriptor, mVar.f4623c, mVar.d - mVar.f4623c);
        ByteBuffer byteBufferDuplicate = mVar.e.duplicate();
        byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
        long j = mVar.f4622b;
        s.c(byteBufferDuplicate);
        int iPosition = byteBufferDuplicate.position() + 16;
        if (j < 0 || j > 4294967295L) {
            throw new IllegalArgumentException("uint32 value of out range: ".concat(String.valueOf(j)));
        }
        byteBufferDuplicate.putInt(byteBufferDuplicate.position() + iPosition, (int) j);
        h hVar = new h(byteBufferDuplicate);
        int[] iArr = new int[map.size()];
        Iterator<Integer> it = map.keySet().iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        try {
            byte[][] bArrA = a(iArr, new k[]{lVar, lVar2, hVar});
            for (int i2 = 0; i2 < iArr.length; i2++) {
                int i3 = iArr[i2];
                if (!MessageDigest.isEqual(map.get(Integer.valueOf(i3)), bArrA[i2])) {
                    throw new SecurityException(b(i3) + " digest of contents did not verify");
                }
            }
        } catch (DigestException e) {
            throw new SecurityException("Failed to compute digest(s) of contents", e);
        }
    }

    private static byte[][] a(int[] iArr, k[] kVarArr) throws DigestException {
        int i;
        long j;
        long j2 = 0;
        long jA = 0;
        int i2 = 0;
        while (true) {
            i = 3;
            j = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            if (i2 >= 3) {
                break;
            }
            jA += ((kVarArr[i2].a() + PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) - 1) / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
            i2++;
        }
        if (jA >= 2097151) {
            throw new DigestException("Too many chunks: ".concat(String.valueOf(jA)));
        }
        int i3 = (int) jA;
        byte[][] bArr = new byte[iArr.length][];
        for (int i4 = 0; i4 < iArr.length; i4++) {
            byte[] bArr2 = new byte[(e(iArr[i4]) * i3) + 5];
            bArr2[0] = 90;
            a(i3, bArr2);
            bArr[i4] = bArr2;
        }
        byte[] bArr3 = new byte[5];
        bArr3[0] = -91;
        MessageDigest[] messageDigestArr = new MessageDigest[iArr.length];
        for (int i5 = 0; i5 < iArr.length; i5++) {
            String strB = b(iArr[i5]);
            try {
                messageDigestArr[i5] = MessageDigest.getInstance(strB);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(strB + " digest not supported", e);
            }
        }
        a aVar = new a(messageDigestArr);
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        while (i6 < i) {
            k kVar = kVarArr[i6];
            long j3 = j2;
            int i9 = i6;
            long jA2 = kVar.a();
            while (jA2 > j2) {
                int iMin = (int) Math.min(jA2, j);
                a(iMin, bArr3);
                for (MessageDigest messageDigest : messageDigestArr) {
                    messageDigest.update(bArr3);
                }
                long j4 = j3;
                try {
                    kVar.a(aVar, j4, iMin);
                    int i10 = 0;
                    while (i10 < iArr.length) {
                        int i11 = iArr[i10];
                        byte[] bArr4 = bArr[i10];
                        int iE = e(i11);
                        k kVar2 = kVar;
                        MessageDigest messageDigest2 = messageDigestArr[i10];
                        byte[] bArr5 = bArr3;
                        int iDigest = messageDigest2.digest(bArr4, (i8 * iE) + 5, iE);
                        if (iDigest != iE) {
                            throw new RuntimeException("Unexpected output size of " + messageDigest2.getAlgorithm() + " digest: " + iDigest);
                        }
                        i10++;
                        kVar = kVar2;
                        bArr3 = bArr5;
                    }
                    long j5 = iMin;
                    j3 = j4 + j5;
                    jA2 -= j5;
                    i8++;
                    j2 = 0;
                    j = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                } catch (IOException e2) {
                    throw new DigestException("Failed to digest chunk #" + i8 + " of section #" + i7, e2);
                }
            }
            i7++;
            i6 = i9 + 1;
            j2 = 0;
            i = 3;
            j = PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
        }
        byte[][] bArr6 = new byte[iArr.length][];
        for (int i12 = 0; i12 < iArr.length; i12++) {
            int i13 = iArr[i12];
            byte[] bArr7 = bArr[i12];
            String strB2 = b(i13);
            try {
                bArr6[i12] = MessageDigest.getInstance(strB2).digest(bArr7);
            } catch (NoSuchAlgorithmException e3) {
                throw new RuntimeException(strB2 + " digest not supported", e3);
            }
        }
        return bArr6;
    }

    static byte[] a(byte[] bArr, long j, m mVar) {
        if (bArr.length != 40) {
            throw new SecurityException("Verity digest size is wrong: " + bArr.length);
        }
        ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.position(32);
        if (byteBufferOrder.getLong() != j - (mVar.f4623c - mVar.f4622b)) {
            throw new SecurityException("APK content size did not verify");
        }
        return Arrays.copyOfRange(bArr, 0, 32);
    }

    private static void a(byte[] bArr, RandomAccessFile randomAccessFile, m mVar) {
        try {
            if (Arrays.equals(a(bArr, randomAccessFile.length(), mVar), g.a(randomAccessFile, mVar, new i() { // from class: com.bytedance.pangle.f.f.1
                @Override // com.bytedance.pangle.f.i
                public final ByteBuffer a(int i) {
                    return ByteBuffer.allocate(i);
                }
            }).f4613b)) {
            } else {
                throw new SecurityException("APK verity digest of contents did not verify");
            }
        } catch (IOException | DigestException | NoSuchAlgorithmException e) {
            throw new SecurityException("Error during verification", e);
        }
    }

    private static Pair<ByteBuffer, Long> a(RandomAccessFile randomAccessFile) throws n, IOException {
        Pair<ByteBuffer, Long> pairA = s.a(randomAccessFile);
        if (pairA != null) {
            return pairA;
        }
        throw new n("Not an APK file: ZIP End of Central Directory record not found");
    }

    private static long a(ByteBuffer byteBuffer, long j) throws n {
        long jA = s.a(byteBuffer);
        if (jA <= j) {
            if (s.b(byteBuffer) + jA == j) {
                return jA;
            }
            throw new n("ZIP Central Directory is not immediately followed by End of Central Directory");
        }
        throw new n("ZIP Central Directory offset out of range: " + jA + ". ZIP End of Central Directory offset: " + j);
    }

    static int a(int i, int i2) {
        return b(a(i), a(i2));
    }

    private static int b(int i, int i2) {
        switch (i) {
            case 1:
                switch (i2) {
                    case 1:
                        return 0;
                    case 2:
                    case 3:
                        return -1;
                    default:
                        throw new IllegalArgumentException("Unknown digestAlgorithm2: ".concat(String.valueOf(i2)));
                }
            case 2:
                switch (i2) {
                    case 1:
                    case 3:
                        return 1;
                    case 2:
                        return 0;
                    default:
                        throw new IllegalArgumentException("Unknown digestAlgorithm2: ".concat(String.valueOf(i2)));
                }
            case 3:
                switch (i2) {
                    case 1:
                        return 1;
                    case 2:
                        return -1;
                    case 3:
                        return 0;
                    default:
                        throw new IllegalArgumentException("Unknown digestAlgorithm2: ".concat(String.valueOf(i2)));
                }
            default:
                throw new IllegalArgumentException("Unknown digestAlgorithm1: ".concat(String.valueOf(i)));
        }
    }

    static int a(int i) {
        if (i == 769) {
            return 1;
        }
        if (i == 1057 || i == 1059 || i == 1061) {
            return 3;
        }
        switch (i) {
            case InputDeviceCompat.SOURCE_KEYBOARD /* 257 */:
            case 259:
                return 1;
            case 258:
            case 260:
                return 2;
            default:
                switch (i) {
                    case InputDeviceCompat.SOURCE_DPAD /* 513 */:
                        return 1;
                    case 514:
                        return 2;
                    default:
                        throw new IllegalArgumentException("Unknown signature algorithm: 0x" + Long.toHexString(i & (-1)));
                }
        }
    }

    static String b(int i) {
        switch (i) {
            case 1:
            case 3:
                return StringEncryptUtils.SHA_256;
            case 2:
                return "SHA-512";
            default:
                throw new IllegalArgumentException("Unknown content digest algorthm: ".concat(String.valueOf(i)));
        }
    }

    private static int e(int i) {
        switch (i) {
            case 1:
            case 3:
                return 32;
            case 2:
                return 64;
            default:
                throw new IllegalArgumentException("Unknown content digest algorthm: ".concat(String.valueOf(i)));
        }
    }

    static String c(int i) {
        if (i == 769) {
            return "DSA";
        }
        if (i == 1057) {
            return "RSA";
        }
        if (i == 1059) {
            return "EC";
        }
        if (i == 1061) {
            return "DSA";
        }
        switch (i) {
            case InputDeviceCompat.SOURCE_KEYBOARD /* 257 */:
            case 258:
            case 259:
            case 260:
                return "RSA";
            default:
                switch (i) {
                    case InputDeviceCompat.SOURCE_DPAD /* 513 */:
                    case 514:
                        return "EC";
                    default:
                        throw new IllegalArgumentException("Unknown signature algorithm: 0x" + Long.toHexString(i & (-1)));
                }
        }
    }

    static Pair<String, ? extends AlgorithmParameterSpec> d(int i) {
        if (i != 769) {
            if (i != 1057) {
                if (i != 1059) {
                    if (i != 1061) {
                        switch (i) {
                            case InputDeviceCompat.SOURCE_KEYBOARD /* 257 */:
                                return Pair.create("SHA256withRSA/PSS", new PSSParameterSpec(StringEncryptUtils.SHA_256, "MGF1", MGF1ParameterSpec.SHA256, 32, 1));
                            case 258:
                                return Pair.create("SHA512withRSA/PSS", new PSSParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, 64, 1));
                            case 259:
                                break;
                            case 260:
                                return Pair.create("SHA512withRSA", null);
                            default:
                                switch (i) {
                                    case InputDeviceCompat.SOURCE_DPAD /* 513 */:
                                        break;
                                    case 514:
                                        return Pair.create("SHA512withECDSA", null);
                                    default:
                                        throw new IllegalArgumentException("Unknown signature algorithm: 0x" + Long.toHexString(i & (-1)));
                                }
                        }
                    }
                }
                return Pair.create("SHA256withECDSA", null);
            }
            return Pair.create("SHA256withRSA", null);
        }
        return Pair.create("SHA256withDSA", null);
    }

    private static ByteBuffer a(ByteBuffer byteBuffer, int i) {
        if (i < 8) {
            throw new IllegalArgumentException("end < start: " + i + " < 8");
        }
        int iCapacity = byteBuffer.capacity();
        if (i > byteBuffer.capacity()) {
            throw new IllegalArgumentException("end > capacity: " + i + " > " + iCapacity);
        }
        int iLimit = byteBuffer.limit();
        int iPosition = byteBuffer.position();
        try {
            byteBuffer.position(0);
            byteBuffer.limit(i);
            byteBuffer.position(8);
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            byteBufferSlice.order(byteBuffer.order());
            return byteBufferSlice;
        } finally {
            byteBuffer.position(0);
            byteBuffer.limit(iLimit);
            byteBuffer.position(iPosition);
        }
    }

    private static ByteBuffer b(ByteBuffer byteBuffer, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("size: ".concat(String.valueOf(i)));
        }
        int iLimit = byteBuffer.limit();
        int iPosition = byteBuffer.position();
        int i2 = i + iPosition;
        if (i2 < iPosition || i2 > iLimit) {
            throw new BufferUnderflowException();
        }
        byteBuffer.limit(i2);
        try {
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            byteBufferSlice.order(byteBuffer.order());
            byteBuffer.position(i2);
            return byteBufferSlice;
        } finally {
            byteBuffer.limit(iLimit);
        }
    }

    static ByteBuffer a(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() < 4) {
            throw new IOException("Remaining buffer too short to contain length of length-prefixed field. Remaining: " + byteBuffer.remaining());
        }
        int i = byteBuffer.getInt();
        if (i < 0) {
            throw new IllegalArgumentException("Negative length");
        }
        if (i > byteBuffer.remaining()) {
            throw new IOException("Length-prefixed field longer than remaining buffer. Field length: " + i + ", remaining: " + byteBuffer.remaining());
        }
        return b(byteBuffer, i);
    }

    static byte[] b(ByteBuffer byteBuffer) {
        int i = byteBuffer.getInt();
        if (i < 0) {
            throw new IOException("Negative length");
        }
        if (i > byteBuffer.remaining()) {
            throw new IOException("Underflow while reading length-prefixed value. Length: " + i + ", available: " + byteBuffer.remaining());
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return bArr;
    }

    private static void a(int i, byte[] bArr) {
        bArr[1] = (byte) (i & 255);
        bArr[2] = (byte) ((i >>> 8) & 255);
        bArr[3] = (byte) ((i >>> 16) & 255);
        bArr[4] = (byte) ((i >>> 24) & 255);
    }

    private static Pair<ByteBuffer, Long> a(RandomAccessFile randomAccessFile, long j) throws n, IOException {
        if (j < 32) {
            throw new n("APK too small for APK Signing Block. ZIP Central Directory offset: ".concat(String.valueOf(j)));
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(24);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        randomAccessFile.seek(j - byteBufferAllocate.capacity());
        randomAccessFile.readFully(byteBufferAllocate.array(), byteBufferAllocate.arrayOffset(), byteBufferAllocate.capacity());
        if (byteBufferAllocate.getLong(8) != 2334950737559900225L || byteBufferAllocate.getLong(16) != 3617552046287187010L) {
            throw new n("No APK Signing Block before ZIP Central Directory");
        }
        long j2 = byteBufferAllocate.getLong(0);
        if (j2 < byteBufferAllocate.capacity() || j2 > 2147483639) {
            throw new n("APK Signing Block size out of range: ".concat(String.valueOf(j2)));
        }
        int i = (int) (8 + j2);
        long j3 = j - i;
        if (j3 < 0) {
            throw new n("APK Signing Block offset out of range: ".concat(String.valueOf(j3)));
        }
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(i);
        byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
        randomAccessFile.seek(j3);
        randomAccessFile.readFully(byteBufferAllocate2.array(), byteBufferAllocate2.arrayOffset(), byteBufferAllocate2.capacity());
        long j4 = byteBufferAllocate2.getLong(0);
        if (j4 != j2) {
            throw new n("APK Signing Block sizes in header and footer do not match: " + j4 + " vs " + j2);
        }
        return Pair.create(byteBufferAllocate2, Long.valueOf(j3));
    }

    private static ByteBuffer c(ByteBuffer byteBuffer, int i) throws n {
        c(byteBuffer);
        ByteBuffer byteBufferA = a(byteBuffer, byteBuffer.capacity() - 24);
        int i2 = 0;
        while (byteBufferA.hasRemaining()) {
            i2++;
            if (byteBufferA.remaining() < 8) {
                throw new n("Insufficient data to read size of APK Signing Block entry #".concat(String.valueOf(i2)));
            }
            long j = byteBufferA.getLong();
            if (j < 4 || j > 2147483647L) {
                throw new n("APK Signing Block entry #" + i2 + " size out of range: " + j);
            }
            int i3 = (int) j;
            int iPosition = byteBufferA.position() + i3;
            if (i3 > byteBufferA.remaining()) {
                throw new n("APK Signing Block entry #" + i2 + " size out of range: " + i3 + ", available: " + byteBufferA.remaining());
            }
            if (byteBufferA.getInt() == i) {
                return b(byteBufferA, i3 - 4);
            }
            byteBufferA.position(iPosition);
        }
        throw new n("No block with ID " + i + " in APK Signing Block.");
    }

    private static void c(ByteBuffer byteBuffer) {
        if (byteBuffer.order() != ByteOrder.LITTLE_ENDIAN) {
            throw new IllegalArgumentException("ByteBuffer byte order must be little endian");
        }
    }

    static class a implements j {

        /* renamed from: a, reason: collision with root package name */
        private final MessageDigest[] f4610a;

        a(MessageDigest[] messageDigestArr) {
            this.f4610a = messageDigestArr;
        }

        @Override // com.bytedance.pangle.f.j
        public final void a(ByteBuffer byteBuffer) {
            ByteBuffer byteBufferSlice = byteBuffer.slice();
            for (MessageDigest messageDigest : this.f4610a) {
                byteBufferSlice.position(0);
                messageDigest.update(byteBufferSlice);
            }
        }
    }
}
