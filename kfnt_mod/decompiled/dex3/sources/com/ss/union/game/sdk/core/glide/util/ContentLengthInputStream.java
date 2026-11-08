package com.ss.union.game.sdk.core.glide.util;

import android.text.TextUtils;
import android.util.Log;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ContentLengthInputStream extends FilterInputStream {
    private static final String a = "ContentLengthStream";
    private static final int b = -1;
    private final long c;
    private int d;

    public static InputStream obtain(InputStream inputStream, String str) {
        return obtain(inputStream, a(str));
    }

    public static InputStream obtain(InputStream inputStream, long j) {
        return new ContentLengthInputStream(inputStream, j);
    }

    private static int a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                if (Log.isLoggable(a, 3)) {
                    Log.d(a, "failed to parse content length header: " + str, e);
                }
            }
        }
        return -1;
    }

    private ContentLengthInputStream(InputStream inputStream, long j) {
        super(inputStream);
        this.c = j;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int available() throws IOException {
        return (int) Math.max(this.c - this.d, this.in.available());
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read() throws IOException {
        int i;
        i = super.read();
        a(i >= 0 ? 1 : -1);
        return i;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public synchronized int read(byte[] bArr, int i, int i2) throws IOException {
        return a(super.read(bArr, i, i2));
    }

    private int a(int i) throws IOException {
        if (i >= 0) {
            this.d += i;
        } else if (this.c - this.d > 0) {
            throw new IOException("Failed to read all expected data, expected: " + this.c + ", but read: " + this.d);
        }
        return i;
    }
}
