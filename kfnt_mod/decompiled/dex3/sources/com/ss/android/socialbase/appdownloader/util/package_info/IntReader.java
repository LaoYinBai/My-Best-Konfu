package com.ss.android.socialbase.appdownloader.util.package_info;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class IntReader {
    private boolean m_bigEndian;
    private int m_position;
    private InputStream m_stream;

    public IntReader() {
    }

    public IntReader(InputStream inputStream, boolean z) {
        reset(inputStream, z);
    }

    public final void reset(InputStream inputStream, boolean z) {
        this.m_stream = inputStream;
        this.m_bigEndian = z;
        this.m_position = 0;
    }

    public final void close() throws IOException {
        if (this.m_stream != null) {
            try {
                this.m_stream.close();
            } catch (IOException unused) {
            }
            reset((InputStream) null, false);
        }
    }

    public final InputStream getStream() {
        return this.m_stream;
    }

    public final boolean isBigEndian() {
        return this.m_bigEndian;
    }

    public final void setBigEndian(boolean z) {
        this.m_bigEndian = z;
    }

    public final int readByte() throws IOException {
        return readInt(1);
    }

    public final int readShort() throws IOException {
        return readInt(2);
    }

    public final int readInt() throws IOException {
        return readInt(4);
    }

    public final int readInt(int i) throws IOException {
        if (i >= 0 && i <= 4) {
            int i2 = 0;
            if (this.m_bigEndian) {
                for (int i3 = (i - 1) * 8; i3 >= 0; i3 -= 8) {
                    int i4 = this.m_stream.read();
                    if (i4 == -1) {
                        throw new EOFException();
                    }
                    this.m_position++;
                    i2 |= i4 << i3;
                }
                return i2;
            }
            int i5 = i * 8;
            int i6 = 0;
            while (i2 != i5) {
                int i7 = this.m_stream.read();
                if (i7 == -1) {
                    throw new EOFException();
                }
                this.m_position++;
                i6 |= i7 << i2;
                i2 += 8;
            }
            return i6;
        }
        throw new IllegalArgumentException();
    }

    public final int[] readIntArray(int i) throws IOException {
        int[] iArr = new int[i];
        readIntArray(iArr, 0, i);
        return iArr;
    }

    public final void readIntArray(int[] iArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            iArr[i] = readInt();
            i2--;
            i++;
        }
    }

    public final byte[] readByteArray(int i) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = this.m_stream.read(bArr);
        this.m_position += i2;
        if (i2 == i) {
            return bArr;
        }
        throw new EOFException();
    }

    public final void skip(int i) throws IOException {
        if (i > 0) {
            long j = i;
            long jSkip = this.m_stream.skip(j);
            this.m_position = (int) (this.m_position + jSkip);
            if (jSkip != j) {
                throw new EOFException();
            }
        }
    }

    public final void skipInt() throws IOException {
        skip(4);
    }

    public final int available() throws IOException {
        return this.m_stream.available();
    }

    public final int getPosition() {
        return this.m_position;
    }
}
