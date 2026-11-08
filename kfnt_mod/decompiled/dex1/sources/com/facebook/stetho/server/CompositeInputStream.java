package com.facebook.stetho.server;

import com.facebook.stetho.common.LogUtil;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CompositeInputStream extends InputStream {
    private int mCurrentIndex;
    private final InputStream[] mStreams;

    @Override // java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public CompositeInputStream(InputStream[] inputStreamArr) {
        if (inputStreamArr == null || inputStreamArr.length < 2) {
            throw new IllegalArgumentException("Streams must be non-null and have more than 1 entry");
        }
        this.mStreams = inputStreamArr;
        this.mCurrentIndex = 0;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.mStreams[this.mCurrentIndex].available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        closeAll(this.mCurrentIndex);
    }

    private void closeAll(int i) throws IOException {
        IOException iOException = null;
        for (int i2 = 0; i2 < this.mStreams.length; i2++) {
            try {
                this.mStreams[i2].close();
            } catch (IOException e) {
                e = e;
                if (i2 != i && iOException != null) {
                    e = iOException;
                }
                if (iOException != null && iOException != e) {
                    LogUtil.w(iOException, "Suppressing exception");
                }
                iOException = e;
            }
        }
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3;
        do {
            i3 = this.mStreams[this.mCurrentIndex].read(bArr, i, i2);
            if (i3 != -1) {
                break;
            }
        } while (tryMoveToNextStream());
        return i3;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i;
        do {
            i = this.mStreams[this.mCurrentIndex].read();
            if (i != -1) {
                break;
            }
        } while (tryMoveToNextStream());
        return i;
    }

    private boolean tryMoveToNextStream() {
        if (this.mCurrentIndex + 1 >= this.mStreams.length) {
            return false;
        }
        this.mCurrentIndex++;
        return true;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        int i = read(new byte[(int) j]);
        if (i >= 0) {
            return i;
        }
        return -1L;
    }
}
