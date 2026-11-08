package com.mbridge.msdk.playercommon.exoplayer2.util;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class ParsableByteArray {
    public byte[] data;
    private int limit;
    private int position;

    public ParsableByteArray() {
    }

    public ParsableByteArray(int i) {
        this.data = new byte[i];
        this.limit = i;
    }

    public ParsableByteArray(byte[] bArr) {
        this.data = bArr;
        this.limit = bArr.length;
    }

    public ParsableByteArray(byte[] bArr, int i) {
        this.data = bArr;
        this.limit = i;
    }

    public final void reset(int i) {
        reset(capacity() < i ? new byte[i] : this.data, i);
    }

    public final void reset(byte[] bArr, int i) {
        this.data = bArr;
        this.limit = i;
        this.position = 0;
    }

    public final void reset() {
        this.position = 0;
        this.limit = 0;
    }

    public final int bytesLeft() {
        return this.limit - this.position;
    }

    public final int limit() {
        return this.limit;
    }

    public final void setLimit(int i) {
        Assertions.checkArgument(i >= 0 && i <= this.data.length);
        this.limit = i;
    }

    public final int getPosition() {
        return this.position;
    }

    public final int capacity() {
        if (this.data == null) {
            return 0;
        }
        return this.data.length;
    }

    public final void setPosition(int i) {
        Assertions.checkArgument(i >= 0 && i <= this.limit);
        this.position = i;
    }

    public final void skipBytes(int i) {
        setPosition(this.position + i);
    }

    public final void readBytes(ParsableBitArray parsableBitArray, int i) {
        readBytes(parsableBitArray.data, 0, i);
        parsableBitArray.setPosition(0);
    }

    public final void readBytes(byte[] bArr, int i, int i2) {
        System.arraycopy(this.data, this.position, bArr, i, i2);
        this.position += i2;
    }

    public final void readBytes(ByteBuffer byteBuffer, int i) {
        byteBuffer.put(this.data, this.position, i);
        this.position += i;
    }

    public final int peekUnsignedByte() {
        return this.data[this.position] & FileDownloadStatus.error;
    }

    public final char peekChar() {
        return (char) (((this.data[this.position] & FileDownloadStatus.error) << 8) | (this.data[this.position + 1] & FileDownloadStatus.error));
    }

    public final int readUnsignedByte() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        return bArr[i] & FileDownloadStatus.error;
    }

    public final int readUnsignedShort() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = (bArr[i] & FileDownloadStatus.error) << 8;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        return i2 | (bArr2[i3] & FileDownloadStatus.error);
    }

    public final int readLittleEndianUnsignedShort() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = bArr[i] & FileDownloadStatus.error;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        return i2 | ((bArr2[i3] & FileDownloadStatus.error) << 8);
    }

    public final short readShort() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = (bArr[i] & FileDownloadStatus.error) << 8;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        return (short) (i2 | (bArr2[i3] & FileDownloadStatus.error));
    }

    public final short readLittleEndianShort() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = bArr[i] & FileDownloadStatus.error;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        return (short) (i2 | ((bArr2[i3] & FileDownloadStatus.error) << 8));
    }

    public final int readUnsignedInt24() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = (bArr[i] & FileDownloadStatus.error) << 16;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        int i4 = i2 | ((bArr2[i3] & FileDownloadStatus.error) << 8);
        byte[] bArr3 = this.data;
        int i5 = this.position;
        this.position = i5 + 1;
        return i4 | (bArr3[i5] & FileDownloadStatus.error);
    }

    public final int readInt24() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = ((bArr[i] & FileDownloadStatus.error) << 24) >> 8;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        int i4 = i2 | ((bArr2[i3] & FileDownloadStatus.error) << 8);
        byte[] bArr3 = this.data;
        int i5 = this.position;
        this.position = i5 + 1;
        return i4 | (bArr3[i5] & FileDownloadStatus.error);
    }

    public final int readLittleEndianInt24() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = bArr[i] & FileDownloadStatus.error;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        int i4 = i2 | ((bArr2[i3] & FileDownloadStatus.error) << 8);
        byte[] bArr3 = this.data;
        int i5 = this.position;
        this.position = i5 + 1;
        return i4 | ((bArr3[i5] & FileDownloadStatus.error) << 16);
    }

    public final int readLittleEndianUnsignedInt24() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = bArr[i] & FileDownloadStatus.error;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        int i4 = i2 | ((bArr2[i3] & FileDownloadStatus.error) << 8);
        byte[] bArr3 = this.data;
        int i5 = this.position;
        this.position = i5 + 1;
        return i4 | ((bArr3[i5] & FileDownloadStatus.error) << 16);
    }

    public final long readUnsignedInt() {
        byte[] bArr = this.data;
        this.position = this.position + 1;
        byte[] bArr2 = this.data;
        this.position = this.position + 1;
        long j = ((bArr[r1] & 255) << 24) | ((bArr2[r5] & 255) << 16);
        byte[] bArr3 = this.data;
        this.position = this.position + 1;
        long j2 = j | ((bArr3[r5] & 255) << 8);
        byte[] bArr4 = this.data;
        this.position = this.position + 1;
        return j2 | (255 & bArr4[r5]);
    }

    public final long readLittleEndianUnsignedInt() {
        byte[] bArr = this.data;
        this.position = this.position + 1;
        byte[] bArr2 = this.data;
        this.position = this.position + 1;
        long j = (bArr[r1] & 255) | ((bArr2[r5] & 255) << 8);
        byte[] bArr3 = this.data;
        this.position = this.position + 1;
        long j2 = j | ((bArr3[r5] & 255) << 16);
        byte[] bArr4 = this.data;
        this.position = this.position + 1;
        return j2 | ((255 & bArr4[r5]) << 24);
    }

    public final int readInt() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = (bArr[i] & FileDownloadStatus.error) << 24;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        int i4 = i2 | ((bArr2[i3] & FileDownloadStatus.error) << 16);
        byte[] bArr3 = this.data;
        int i5 = this.position;
        this.position = i5 + 1;
        int i6 = i4 | ((bArr3[i5] & FileDownloadStatus.error) << 8);
        byte[] bArr4 = this.data;
        int i7 = this.position;
        this.position = i7 + 1;
        return i6 | (bArr4[i7] & FileDownloadStatus.error);
    }

    public final int readLittleEndianInt() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = bArr[i] & FileDownloadStatus.error;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        int i4 = i2 | ((bArr2[i3] & FileDownloadStatus.error) << 8);
        byte[] bArr3 = this.data;
        int i5 = this.position;
        this.position = i5 + 1;
        int i6 = i4 | ((bArr3[i5] & FileDownloadStatus.error) << 16);
        byte[] bArr4 = this.data;
        int i7 = this.position;
        this.position = i7 + 1;
        return i6 | ((bArr4[i7] & FileDownloadStatus.error) << 24);
    }

    public final long readLong() {
        byte[] bArr = this.data;
        this.position = this.position + 1;
        byte[] bArr2 = this.data;
        this.position = this.position + 1;
        long j = ((bArr[r1] & 255) << 56) | ((bArr2[r5] & 255) << 48);
        byte[] bArr3 = this.data;
        this.position = this.position + 1;
        long j2 = j | ((bArr3[r5] & 255) << 40);
        byte[] bArr4 = this.data;
        this.position = this.position + 1;
        long j3 = j2 | ((bArr4[r5] & 255) << 32);
        byte[] bArr5 = this.data;
        this.position = this.position + 1;
        long j4 = j3 | ((bArr5[r5] & 255) << 24);
        byte[] bArr6 = this.data;
        this.position = this.position + 1;
        long j5 = j4 | ((bArr6[r5] & 255) << 16);
        byte[] bArr7 = this.data;
        this.position = this.position + 1;
        long j6 = j5 | ((bArr7[r5] & 255) << 8);
        byte[] bArr8 = this.data;
        this.position = this.position + 1;
        return j6 | (255 & bArr8[r5]);
    }

    public final long readLittleEndianLong() {
        byte[] bArr = this.data;
        this.position = this.position + 1;
        byte[] bArr2 = this.data;
        this.position = this.position + 1;
        long j = (bArr[r1] & 255) | ((bArr2[r5] & 255) << 8);
        byte[] bArr3 = this.data;
        this.position = this.position + 1;
        long j2 = j | ((bArr3[r5] & 255) << 16);
        byte[] bArr4 = this.data;
        this.position = this.position + 1;
        long j3 = j2 | ((bArr4[r5] & 255) << 24);
        byte[] bArr5 = this.data;
        this.position = this.position + 1;
        long j4 = j3 | ((bArr5[r5] & 255) << 32);
        byte[] bArr6 = this.data;
        this.position = this.position + 1;
        long j5 = j4 | ((bArr6[r5] & 255) << 40);
        byte[] bArr7 = this.data;
        this.position = this.position + 1;
        long j6 = j5 | ((bArr7[r5] & 255) << 48);
        byte[] bArr8 = this.data;
        this.position = this.position + 1;
        return j6 | ((255 & bArr8[r5]) << 56);
    }

    public final int readUnsignedFixedPoint1616() {
        byte[] bArr = this.data;
        int i = this.position;
        this.position = i + 1;
        int i2 = (bArr[i] & FileDownloadStatus.error) << 8;
        byte[] bArr2 = this.data;
        int i3 = this.position;
        this.position = i3 + 1;
        int i4 = i2 | (bArr2[i3] & FileDownloadStatus.error);
        this.position += 2;
        return i4;
    }

    public final int readSynchSafeInt() {
        return (readUnsignedByte() << 21) | (readUnsignedByte() << 14) | (readUnsignedByte() << 7) | readUnsignedByte();
    }

    public final int readUnsignedIntToInt() {
        int i = readInt();
        if (i >= 0) {
            return i;
        }
        throw new IllegalStateException("Top bit not zero: " + i);
    }

    public final int readLittleEndianUnsignedIntToInt() {
        int littleEndianInt = readLittleEndianInt();
        if (littleEndianInt >= 0) {
            return littleEndianInt;
        }
        throw new IllegalStateException("Top bit not zero: " + littleEndianInt);
    }

    public final long readUnsignedLongToLong() {
        long j = readLong();
        if (j >= 0) {
            return j;
        }
        throw new IllegalStateException("Top bit not zero: " + j);
    }

    public final float readFloat() {
        return Float.intBitsToFloat(readInt());
    }

    public final double readDouble() {
        return Double.longBitsToDouble(readLong());
    }

    public final String readString(int i) {
        return readString(i, Charset.forName(C.UTF8_NAME));
    }

    public final String readString(int i, Charset charset) {
        String str = new String(this.data, this.position, i, charset);
        this.position += i;
        return str;
    }

    public final String readNullTerminatedString(int i) {
        if (i == 0) {
            return "";
        }
        int i2 = (this.position + i) - 1;
        String strFromUtf8Bytes = Util.fromUtf8Bytes(this.data, this.position, (i2 >= this.limit || this.data[i2] != 0) ? i : i - 1);
        this.position += i;
        return strFromUtf8Bytes;
    }

    public final String readNullTerminatedString() {
        if (bytesLeft() == 0) {
            return null;
        }
        int i = this.position;
        while (i < this.limit && this.data[i] != 0) {
            i++;
        }
        String strFromUtf8Bytes = Util.fromUtf8Bytes(this.data, this.position, i - this.position);
        this.position = i;
        if (this.position < this.limit) {
            this.position++;
        }
        return strFromUtf8Bytes;
    }

    public final String readLine() {
        if (bytesLeft() == 0) {
            return null;
        }
        int i = this.position;
        while (i < this.limit && !Util.isLinebreak(this.data[i])) {
            i++;
        }
        if (i - this.position >= 3 && this.data[this.position] == -17 && this.data[this.position + 1] == -69 && this.data[this.position + 2] == -65) {
            this.position += 3;
        }
        String strFromUtf8Bytes = Util.fromUtf8Bytes(this.data, this.position, i - this.position);
        this.position = i;
        if (this.position == this.limit) {
            return strFromUtf8Bytes;
        }
        if (this.data[this.position] == 13) {
            this.position++;
            if (this.position == this.limit) {
                return strFromUtf8Bytes;
            }
        }
        if (this.data[this.position] == 10) {
            this.position++;
        }
        return strFromUtf8Bytes;
    }

    public final long readUtf8EncodedLong() {
        int i;
        int i2;
        long j = this.data[this.position];
        int i3 = 7;
        while (true) {
            if (i3 < 0) {
                break;
            }
            if (((1 << i3) & j) != 0) {
                i3--;
            } else if (i3 < 6) {
                j &= r6 - 1;
                i2 = 7 - i3;
            } else if (i3 == 7) {
                i2 = 1;
            }
        }
        i2 = 0;
        if (i2 == 0) {
            throw new NumberFormatException("Invalid UTF-8 sequence first byte: " + j);
        }
        for (i = 1; i < i2; i++) {
            if ((this.data[this.position + i] & 192) != 128) {
                throw new NumberFormatException("Invalid UTF-8 sequence continuation byte: " + j);
            }
            j = (j << 6) | (r3 & 63);
        }
        this.position += i2;
        return j;
    }
}
