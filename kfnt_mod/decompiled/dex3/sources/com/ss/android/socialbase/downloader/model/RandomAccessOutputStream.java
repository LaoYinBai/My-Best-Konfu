package com.ss.android.socialbase.downloader.model;

import com.ss.android.socialbase.downloader.constants.DownloadErrorCode;
import com.ss.android.socialbase.downloader.exception.BaseException;
import com.ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RandomAccessOutputStream implements Closeable {
    private static final int MAX_FLUSH_BUFFER_SIZE = 131072;
    private static final int MIN_FLUSH_BUFFER_SIZE = 8192;
    private FileDescriptor fd;
    private BufferedOutputStream outputStream;
    private RandomAccessFile randomAccess;

    public RandomAccessOutputStream(File file, int i) throws BaseException {
        try {
            this.randomAccess = new RandomAccessFile(file, "rw");
            this.fd = this.randomAccess.getFD();
            if (i > 0) {
                int i2 = MAX_FLUSH_BUFFER_SIZE;
                if (i < 8192) {
                    i2 = 8192;
                } else if (i <= MAX_FLUSH_BUFFER_SIZE) {
                    i2 = i;
                }
                this.outputStream = new BufferedOutputStream(new FileOutputStream(this.randomAccess.getFD()), i2);
                return;
            }
            this.outputStream = new BufferedOutputStream(new FileOutputStream(this.randomAccess.getFD()));
        } catch (IOException e) {
            throw new BaseException(DownloadErrorCode.ERROR_OUTPUT_STREAM_CREATE_IO, e);
        }
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.outputStream.write(bArr, i, i2);
    }

    public void flushAndSync() throws IOException {
        if (this.outputStream != null) {
            this.outputStream.flush();
        }
        if (this.fd != null) {
            this.fd.sync();
        }
    }

    public void flush() throws IOException {
        if (this.outputStream != null) {
            this.outputStream.flush();
        }
    }

    public void sync() throws IOException {
        if (this.fd != null) {
            this.fd.sync();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        DownloadUtils.safeClose(this.randomAccess, this.outputStream);
    }

    public void seek(long j) throws IOException {
        this.randomAccess.seek(j);
    }

    public void setLength(long j) throws IOException {
        this.randomAccess.setLength(j);
    }
}
