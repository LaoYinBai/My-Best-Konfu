package com.bytedance.sdk.adnet.game.ok3.okio;

import android.support.v4.media.session.PlaybackStateCompat;
import com.bytedance.common.utility.UIUtils;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class RealBufferedSource implements BufferedSource {
    public final Buffer buffer = new Buffer();
    boolean closed;
    public final Source source;

    RealBufferedSource(Source source) {
        if (source == null) {
            throw new NullPointerException("source == null");
        }
        this.source = source;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.source.close();
        this.buffer.clear();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public boolean exhausted() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        return this.buffer.exhausted() && this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public Buffer getBuffer() {
        return this.buffer;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long indexOf(byte b2) {
        return indexOf(b2, 0L, Long.MAX_VALUE);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long indexOf(byte b2, long j) {
        return indexOf(b2, j, Long.MAX_VALUE);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long indexOf(byte b2, long j, long j2) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long jIndexOf = this.buffer.indexOf(b2, j, j2);
            if (jIndexOf != -1) {
                return jIndexOf;
            }
            long j3 = this.buffer.size;
            if (j3 >= j2 || this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                break;
            }
            j = Math.max(j, j3);
        }
        return -1L;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long indexOf(ByteString byteString) {
        return indexOf(byteString, 0L);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long indexOf(ByteString byteString, long j) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jIndexOf = this.buffer.indexOf(byteString, j);
            if (jIndexOf != -1) {
                return jIndexOf;
            }
            long j2 = this.buffer.size;
            if (this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, (j2 - byteString.size()) + 1);
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long indexOfElement(ByteString byteString) {
        return indexOfElement(byteString, 0L);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long indexOfElement(ByteString byteString, long j) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jIndexOfElement = this.buffer.indexOfElement(byteString, j);
            if (jIndexOfElement != -1) {
                return jIndexOfElement;
            }
            long j2 = this.buffer.size;
            if (this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, j2);
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: com.bytedance.sdk.adnet.game.ok3.okio.RealBufferedSource.1
            @Override // java.io.InputStream
            public int available() throws IOException {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                return (int) Math.min(RealBufferedSource.this.buffer.size, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
                RealBufferedSource.this.close();
            }

            @Override // java.io.InputStream
            public int read() throws IOException {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                if (RealBufferedSource.this.buffer.size == 0 && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                return RealBufferedSource.this.buffer.readByte() & 255;
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                Util.checkOffsetAndCount(bArr.length, i, i2);
                if (RealBufferedSource.this.buffer.size == 0 && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                return RealBufferedSource.this.buffer.read(bArr, i, i2);
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }
        };
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public BufferedSource peek() {
        return Okio.buffer(new PeekSource(this));
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public boolean rangeEquals(long j, ByteString byteString) {
        return rangeEquals(j, byteString, 0, byteString.size());
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public boolean rangeEquals(long j, ByteString byteString, int i, int i2) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || i < 0 || i2 < 0 || byteString.size() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = i3 + j;
            if (!request(1 + j2) || this.buffer.getByte(j2) != byteString.getByte(i + i3)) {
                return false;
            }
        }
        return true;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) {
        if (this.buffer.size == 0 && this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.buffer.read(byteBuffer);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public int read(byte[] bArr, int i, int i2) {
        long j = i2;
        Util.checkOffsetAndCount(bArr.length, i, j);
        if (this.buffer.size == 0 && this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.buffer.read(bArr, i, (int) Math.min(j, this.buffer.size));
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Source
    public long read(Buffer buffer, long j) {
        if (buffer == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        if (this.buffer.size == 0 && this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        }
        return this.buffer.read(buffer, Math.min(j, this.buffer.size));
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long readAll(Sink sink) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j = 0;
        while (this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
            long jCompleteSegmentByteCount = this.buffer.completeSegmentByteCount();
            if (jCompleteSegmentByteCount > 0) {
                j += jCompleteSegmentByteCount;
                sink.write(this.buffer, jCompleteSegmentByteCount);
            }
        }
        if (this.buffer.size() <= 0) {
            return j;
        }
        long size = j + this.buffer.size();
        sink.write(this.buffer, this.buffer.size());
        return size;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public byte readByte() throws EOFException {
        require(1L);
        return this.buffer.readByte();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public byte[] readByteArray() {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteArray();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public byte[] readByteArray(long j) throws EOFException {
        require(j);
        return this.buffer.readByteArray(j);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public ByteString readByteString() {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteString();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public ByteString readByteString(long j) throws EOFException {
        require(j);
        return this.buffer.readByteString(j);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long readDecimalLong() throws EOFException {
        byte b2;
        require(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request(i2)) {
                break;
            }
            b2 = this.buffer.getByte(i);
            if ((b2 < 48 || b2 > 57) && !(i == 0 && b2 == 45)) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(b2)));
        }
        return this.buffer.readDecimalLong();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public void readFully(Buffer buffer, long j) throws EOFException {
        try {
            require(j);
            this.buffer.readFully(buffer, j);
        } catch (EOFException e) {
            buffer.writeAll(this.buffer);
            throw e;
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public void readFully(byte[] bArr) throws EOFException {
        try {
            require(bArr.length);
            this.buffer.readFully(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (this.buffer.size > 0) {
                int i2 = this.buffer.read(bArr, i, (int) this.buffer.size);
                if (i2 == -1) {
                    throw new AssertionError();
                }
                i += i2;
            }
            throw e;
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long readHexadecimalUnsignedLong() throws EOFException {
        byte b2;
        require(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request(i2)) {
                break;
            }
            b2 = this.buffer.getByte(i);
            if ((b2 < 48 || b2 > 57) && ((b2 < 97 || b2 > 102) && (b2 < 65 || b2 > 70))) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(b2)));
        }
        return this.buffer.readHexadecimalUnsignedLong();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public int readInt() throws EOFException {
        require(4L);
        return this.buffer.readInt();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public int readIntLe() throws EOFException {
        require(4L);
        return this.buffer.readIntLe();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long readLong() throws EOFException {
        require(8L);
        return this.buffer.readLong();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public long readLongLe() throws EOFException {
        require(8L);
        return this.buffer.readLongLe();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public short readShort() throws EOFException {
        require(2L);
        return this.buffer.readShort();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public short readShortLe() throws EOFException {
        require(2L);
        return this.buffer.readShortLe();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public String readString(long j, Charset charset) throws EOFException {
        require(j);
        if (charset != null) {
            return this.buffer.readString(j, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public String readString(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.buffer.writeAll(this.source);
        return this.buffer.readString(charset);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public String readUtf8() {
        this.buffer.writeAll(this.source);
        return this.buffer.readUtf8();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public String readUtf8(long j) throws EOFException {
        require(j);
        return this.buffer.readUtf8(j);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        long j;
        require(1L);
        byte b2 = this.buffer.getByte(0L);
        if ((b2 & 224) == 192) {
            j = 2;
        } else {
            if ((b2 & 240) != 224) {
                if ((b2 & 248) == 240) {
                    j = 4;
                }
                return this.buffer.readUtf8CodePoint();
            }
            j = 3;
        }
        require(j);
        return this.buffer.readUtf8CodePoint();
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public String readUtf8Line() {
        long jIndexOf = indexOf((byte) 10);
        if (jIndexOf != -1) {
            return this.buffer.readUtf8Line(jIndexOf);
        }
        if (this.buffer.size != 0) {
            return readUtf8(this.buffer.size);
        }
        return null;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public String readUtf8LineStrict() {
        return readUtf8LineStrict(Long.MAX_VALUE);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public String readUtf8LineStrict(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jIndexOf = indexOf((byte) 10, 0L, j2);
        if (jIndexOf != -1) {
            return this.buffer.readUtf8Line(jIndexOf);
        }
        if (j2 < Long.MAX_VALUE && request(j2) && this.buffer.getByte(j2 - 1) == 13 && request(1 + j2) && this.buffer.getByte(j2) == 10) {
            return this.buffer.readUtf8Line(j2);
        }
        Buffer buffer = new Buffer();
        this.buffer.copyTo(buffer, 0L, Math.min(32L, this.buffer.size()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.buffer.size(), j) + " content=" + buffer.readByteString().hex() + UIUtils.ELLIPSIS_CHAR);
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public boolean request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (this.buffer.size < j) {
            if (this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public void require(long j) throws EOFException {
        if (!request(j)) {
            throw new EOFException();
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public int select(Options options) throws EOFException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        do {
            int iSelectPrefix = this.buffer.selectPrefix(options, true);
            if (iSelectPrefix == -1) {
                return -1;
            }
            if (iSelectPrefix != -2) {
                this.buffer.skip(options.byteStrings[iSelectPrefix].size());
                return iSelectPrefix;
            }
        } while (this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1);
        return -1;
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.BufferedSource
    public void skip(long j) throws EOFException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.buffer.size == 0 && this.source.read(this.buffer, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.buffer.size());
            this.buffer.skip(jMin);
            j -= jMin;
        }
    }

    @Override // com.bytedance.sdk.adnet.game.ok3.okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        return "buffer(" + this.source + ")";
    }
}
