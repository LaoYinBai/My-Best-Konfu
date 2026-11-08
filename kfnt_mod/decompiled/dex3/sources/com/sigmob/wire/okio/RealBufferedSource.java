package com.sigmob.wire.okio;

import com.umeng.message.proguard.l;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class RealBufferedSource implements BufferedSource {
    public final Buffer buffer = new Buffer();
    boolean closed;
    public final Source source;

    RealBufferedSource(Source source) {
        if (source == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.source = source;
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public Buffer buffer() {
        return this.buffer;
    }

    @Override // com.sigmob.wire.okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.source.close();
        this.buffer.clear();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public boolean exhausted() {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        return this.buffer.exhausted() && this.source.read(this.buffer, 8192L) == -1;
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public long indexOf(byte b) {
        return indexOf(b, 0L);
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public long indexOf(byte b, long j) {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jIndexOf = this.buffer.indexOf(b, j);
            if (jIndexOf != -1) {
                return jIndexOf;
            }
            long j2 = this.buffer.size;
            if (this.source.read(this.buffer, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, j2);
        }
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public long indexOf(ByteString byteString) {
        return indexOf(byteString, 0L);
    }

    @Override // com.sigmob.wire.okio.BufferedSource
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
            if (this.source.read(this.buffer, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, (j2 - byteString.size()) + 1);
        }
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public long indexOfElement(ByteString byteString) {
        return indexOfElement(byteString, 0L);
    }

    @Override // com.sigmob.wire.okio.BufferedSource
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
            if (this.source.read(this.buffer, 8192L) == -1) {
                return -1L;
            }
            j = Math.max(j, j2);
        }
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public InputStream inputStream() {
        return new InputStream() { // from class: com.sigmob.wire.okio.RealBufferedSource.1
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
                if (RealBufferedSource.this.buffer.size == 0 && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 8192L) == -1) {
                    return -1;
                }
                return RealBufferedSource.this.buffer.readByte() & UByte.MAX_VALUE;
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (RealBufferedSource.this.closed) {
                    throw new IOException("closed");
                }
                Util.checkOffsetAndCount(bArr.length, i, i2);
                if (RealBufferedSource.this.buffer.size == 0 && RealBufferedSource.this.source.read(RealBufferedSource.this.buffer, 8192L) == -1) {
                    return -1;
                }
                return RealBufferedSource.this.buffer.read(bArr, i, i2);
            }

            public String toString() {
                return RealBufferedSource.this + ".inputStream()";
            }
        };
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public int read(byte[] bArr) {
        return read(bArr, 0, bArr.length);
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public int read(byte[] bArr, int i, int i2) {
        long j = i2;
        Util.checkOffsetAndCount(bArr.length, i, j);
        if (this.buffer.size == 0 && this.source.read(this.buffer, 8192L) == -1) {
            return -1;
        }
        return this.buffer.read(bArr, i, (int) Math.min(j, this.buffer.size));
    }

    @Override // com.sigmob.wire.okio.Source
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
        if (this.buffer.size == 0 && this.source.read(this.buffer, 8192L) == -1) {
            return -1L;
        }
        return this.buffer.read(buffer, Math.min(j, this.buffer.size));
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public long readAll(Sink sink) {
        if (sink == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j = 0;
        while (this.source.read(this.buffer, 8192L) != -1) {
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

    @Override // com.sigmob.wire.okio.BufferedSource
    public byte readByte() throws EOFException {
        require(1L);
        return this.buffer.readByte();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public byte[] readByteArray() {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteArray();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public byte[] readByteArray(long j) throws EOFException {
        require(j);
        return this.buffer.readByteArray(j);
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public ByteString readByteString() {
        this.buffer.writeAll(this.source);
        return this.buffer.readByteString();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public ByteString readByteString(long j) throws EOFException {
        require(j);
        return this.buffer.readByteString(j);
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public long readDecimalLong() throws EOFException {
        byte b;
        require(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request(i2)) {
                break;
            }
            b = this.buffer.getByte(i);
            if ((b < 48 || b > 57) && !(i == 0 && b == 45)) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(b)));
        }
        return this.buffer.readDecimalLong();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public void readFully(Buffer buffer, long j) throws EOFException {
        try {
            require(j);
            this.buffer.readFully(buffer, j);
        } catch (EOFException e) {
            buffer.writeAll(this.buffer);
            throw e;
        }
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public void readFully(byte[] bArr) throws EOFException {
        try {
            require(bArr.length);
            this.buffer.readFully(bArr);
        } catch (Throwable th) {
            int i = 0;
            while (this.buffer.size > 0) {
                int i2 = this.buffer.read(bArr, i, (int) this.buffer.size);
                if (i2 == -1) {
                    throw new AssertionError();
                }
                i += i2;
            }
            throw th;
        }
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public long readHexadecimalUnsignedLong() throws EOFException {
        byte b;
        require(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!request(i2)) {
                break;
            }
            b = this.buffer.getByte(i);
            if ((b < 48 || b > 57) && ((b < 97 || b > 102) && (b < 65 || b > 70))) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(b)));
        }
        return this.buffer.readHexadecimalUnsignedLong();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public int readInt() throws EOFException {
        require(4L);
        return this.buffer.readInt();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public int readIntLe() throws EOFException {
        require(4L);
        return this.buffer.readIntLe();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public long readLong() throws EOFException {
        require(8L);
        return this.buffer.readLong();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public long readLongLe() throws EOFException {
        require(8L);
        return this.buffer.readLongLe();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public short readShort() throws EOFException {
        require(2L);
        return this.buffer.readShort();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public short readShortLe() throws EOFException {
        require(2L);
        return this.buffer.readShortLe();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public String readString(long j, Charset charset) throws EOFException {
        require(j);
        if (charset != null) {
            return this.buffer.readString(j, charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public String readString(Charset charset) {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.buffer.writeAll(this.source);
        return this.buffer.readString(charset);
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public String readUtf8() {
        this.buffer.writeAll(this.source);
        return this.buffer.readUtf8();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public String readUtf8(long j) throws EOFException {
        require(j);
        return this.buffer.readUtf8(j);
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public int readUtf8CodePoint() throws EOFException {
        long j;
        require(1L);
        byte b = this.buffer.getByte(0L);
        if ((b & 224) == 192) {
            j = 2;
        } else {
            if ((b & 240) != 224) {
                if ((b & 248) == 240) {
                    j = 4;
                }
                return this.buffer.readUtf8CodePoint();
            }
            j = 3;
        }
        require(j);
        return this.buffer.readUtf8CodePoint();
    }

    @Override // com.sigmob.wire.okio.BufferedSource
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

    @Override // com.sigmob.wire.okio.BufferedSource
    public String readUtf8LineStrict() throws EOFException {
        long jIndexOf = indexOf((byte) 10);
        if (jIndexOf != -1) {
            return this.buffer.readUtf8Line(jIndexOf);
        }
        Buffer buffer = new Buffer();
        this.buffer.copyTo(buffer, 0L, Math.min(32L, this.buffer.size()));
        throw new EOFException("\\n not found: size=" + this.buffer.size() + " content=" + buffer.readByteString().hex() + "…");
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public boolean request(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (this.buffer.size < j) {
            if (this.source.read(this.buffer, 8192L) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public void require(long j) throws EOFException {
        if (!request(j)) {
            throw new EOFException();
        }
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public int select(Options options) throws EOFException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        do {
            int iSelectPrefix = this.buffer.selectPrefix(options);
            if (iSelectPrefix == -1) {
                return -1;
            }
            long size = options.byteStrings[iSelectPrefix].size();
            if (size <= this.buffer.size) {
                this.buffer.skip(size);
                return iSelectPrefix;
            }
        } while (this.source.read(this.buffer, 8192L) != -1);
        return -1;
    }

    @Override // com.sigmob.wire.okio.BufferedSource
    public void skip(long j) throws EOFException {
        if (this.closed) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.buffer.size == 0 && this.source.read(this.buffer, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.buffer.size());
            this.buffer.skip(jMin);
            j -= jMin;
        }
    }

    @Override // com.sigmob.wire.okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }

    public String toString() {
        return "buffer(" + this.source + l.t;
    }
}
