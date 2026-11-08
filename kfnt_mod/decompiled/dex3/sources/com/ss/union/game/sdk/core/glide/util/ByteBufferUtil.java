package com.ss.union.game.sdk.core.glide.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ByteBufferUtil {
    private static final int a = 16384;
    private static final AtomicReference<byte[]> b = new AtomicReference<>();

    private ByteBufferUtil() {
    }

    public static ByteBuffer fromFile(File file) throws Throwable {
        RandomAccessFile randomAccessFile;
        FileChannel fileChannel = null;
        try {
            long length = file.length();
            if (length > 2147483647L) {
                throw new IOException("File too large to map into memory");
            }
            if (length == 0) {
                throw new IOException("File unsuitable for memory mapping");
            }
            randomAccessFile = new RandomAccessFile(file, "r");
            try {
                FileChannel channel = randomAccessFile.getChannel();
                try {
                    MappedByteBuffer mappedByteBufferLoad = channel.map(FileChannel.MapMode.READ_ONLY, 0L, length).load();
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException unused) {
                        }
                    }
                    try {
                        randomAccessFile.close();
                    } catch (IOException unused2) {
                    }
                    return mappedByteBufferLoad;
                } catch (Throwable th) {
                    fileChannel = channel;
                    th = th;
                    if (fileChannel != null) {
                        try {
                            fileChannel.close();
                        } catch (IOException unused3) {
                        }
                    }
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                            throw th;
                        } catch (IOException unused4) {
                            throw th;
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
        }
    }

    public static void toFile(ByteBuffer byteBuffer, File file) throws Throwable {
        FileChannel channel;
        RandomAccessFile randomAccessFile;
        byteBuffer.position(0);
        try {
            randomAccessFile = new RandomAccessFile(file, "rw");
            try {
                channel = randomAccessFile.getChannel();
                try {
                    channel.write(byteBuffer);
                    channel.force(false);
                    channel.close();
                    randomAccessFile.close();
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException unused) {
                        }
                    }
                    try {
                        randomAccessFile.close();
                    } catch (IOException unused2) {
                    }
                } catch (Throwable th) {
                    th = th;
                    if (channel != null) {
                        try {
                            channel.close();
                        } catch (IOException unused3) {
                        }
                    }
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                            throw th;
                        } catch (IOException unused4) {
                            throw th;
                        }
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                channel = null;
            }
        } catch (Throwable th3) {
            th = th3;
            channel = null;
            randomAccessFile = null;
        }
    }

    public static void toStream(ByteBuffer byteBuffer, OutputStream outputStream) throws IOException {
        b bVarA = a(byteBuffer);
        if (bVarA != null) {
            outputStream.write(bVarA.c, bVarA.a, bVarA.a + bVarA.b);
            return;
        }
        byte[] andSet = b.getAndSet(null);
        if (andSet == null) {
            andSet = new byte[16384];
        }
        while (byteBuffer.remaining() > 0) {
            int iMin = Math.min(byteBuffer.remaining(), andSet.length);
            byteBuffer.get(andSet, 0, iMin);
            outputStream.write(andSet, 0, iMin);
        }
        b.set(andSet);
    }

    public static byte[] toBytes(ByteBuffer byteBuffer) {
        b bVarA = a(byteBuffer);
        if (bVarA != null && bVarA.a == 0 && bVarA.b == bVarA.c.length) {
            return byteBuffer.array();
        }
        ByteBuffer byteBufferAsReadOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        byte[] bArr = new byte[byteBufferAsReadOnlyBuffer.limit()];
        byteBufferAsReadOnlyBuffer.position(0);
        byteBufferAsReadOnlyBuffer.get(bArr);
        return bArr;
    }

    public static InputStream toStream(ByteBuffer byteBuffer) {
        return new a(byteBuffer);
    }

    public static ByteBuffer fromStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(16384);
        byte[] andSet = b.getAndSet(null);
        if (andSet == null) {
            andSet = new byte[16384];
        }
        while (true) {
            int i = inputStream.read(andSet);
            if (i >= 0) {
                byteArrayOutputStream.write(andSet, 0, i);
            } else {
                b.set(andSet);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                return (ByteBuffer) ByteBuffer.allocateDirect(byteArray.length).put(byteArray).position(0);
            }
        }
    }

    private static b a(ByteBuffer byteBuffer) {
        if (byteBuffer.isReadOnly() || !byteBuffer.hasArray()) {
            return null;
        }
        return new b(byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.limit());
    }

    static final class b {
        final int a;
        final int b;
        final byte[] c;

        b(byte[] bArr, int i, int i2) {
            this.c = bArr;
            this.a = i;
            this.b = i2;
        }
    }

    private static class a extends InputStream {
        private static final int a = -1;
        private final ByteBuffer b;
        private int c = -1;

        @Override // java.io.InputStream
        public boolean markSupported() {
            return true;
        }

        a(ByteBuffer byteBuffer) {
            this.b = byteBuffer;
        }

        @Override // java.io.InputStream
        public int available() {
            return this.b.remaining();
        }

        @Override // java.io.InputStream
        public int read() {
            if (this.b.hasRemaining()) {
                return this.b.get();
            }
            return -1;
        }

        @Override // java.io.InputStream
        public synchronized void mark(int i) {
            this.c = this.b.position();
        }

        @Override // java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            if (!this.b.hasRemaining()) {
                return -1;
            }
            int iMin = Math.min(i2, available());
            this.b.get(bArr, i, iMin);
            return iMin;
        }

        @Override // java.io.InputStream
        public synchronized void reset() throws IOException {
            if (this.c == -1) {
                throw new IOException("Cannot reset to unset mark position");
            }
            this.b.position(this.c);
        }

        @Override // java.io.InputStream
        public long skip(long j) throws IOException {
            if (!this.b.hasRemaining()) {
                return -1L;
            }
            long jMin = Math.min(j, available());
            this.b.position((int) (this.b.position() + jMin));
            return jMin;
        }
    }
}
