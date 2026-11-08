package com.mbridge.msdk.playercommon.exoplayer2.util;

import com.liulishuo.filedownloader.model.FileDownloadStatus;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class ParsableBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;

    public ParsableBitArray() {
    }

    public ParsableBitArray(byte[] bArr) {
        this(bArr, bArr.length);
    }

    public ParsableBitArray(byte[] bArr, int i) {
        this.data = bArr;
        this.byteLimit = i;
    }

    public final void reset(byte[] bArr) {
        reset(bArr, bArr.length);
    }

    public final void reset(ParsableByteArray parsableByteArray) {
        reset(parsableByteArray.data, parsableByteArray.limit());
        setPosition(parsableByteArray.getPosition() * 8);
    }

    public final void reset(byte[] bArr, int i) {
        this.data = bArr;
        this.byteOffset = 0;
        this.bitOffset = 0;
        this.byteLimit = i;
    }

    public final int bitsLeft() {
        return ((this.byteLimit - this.byteOffset) * 8) - this.bitOffset;
    }

    public final int getPosition() {
        return (this.byteOffset * 8) + this.bitOffset;
    }

    public final int getBytePosition() {
        Assertions.checkState(this.bitOffset == 0);
        return this.byteOffset;
    }

    public final void setPosition(int i) {
        this.byteOffset = i / 8;
        this.bitOffset = i - (this.byteOffset * 8);
        assertValidOffset();
    }

    public final void skipBit() {
        int i = this.bitOffset + 1;
        this.bitOffset = i;
        if (i == 8) {
            this.bitOffset = 0;
            this.byteOffset++;
        }
        assertValidOffset();
    }

    public final void skipBits(int i) {
        int i2 = i / 8;
        this.byteOffset += i2;
        this.bitOffset += i - (i2 * 8);
        if (this.bitOffset > 7) {
            this.byteOffset++;
            this.bitOffset -= 8;
        }
        assertValidOffset();
    }

    public final boolean readBit() {
        boolean z = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z;
    }

    public final int readBits(int i) {
        if (i == 0) {
            return 0;
        }
        this.bitOffset += i;
        int i2 = 0;
        while (this.bitOffset > 8) {
            this.bitOffset -= 8;
            byte[] bArr = this.data;
            int i3 = this.byteOffset;
            this.byteOffset = i3 + 1;
            i2 |= (bArr[i3] & FileDownloadStatus.error) << this.bitOffset;
        }
        int i4 = ((-1) >>> (32 - i)) & (i2 | ((this.data[this.byteOffset] & FileDownloadStatus.error) >> (8 - this.bitOffset)));
        if (this.bitOffset == 8) {
            this.bitOffset = 0;
            this.byteOffset++;
        }
        assertValidOffset();
        return i4;
    }

    public final void readBits(byte[] bArr, int i, int i2) {
        int i3 = (i2 >> 3) + i;
        while (i < i3) {
            byte[] bArr2 = this.data;
            int i4 = this.byteOffset;
            this.byteOffset = i4 + 1;
            bArr[i] = (byte) (bArr2[i4] << this.bitOffset);
            bArr[i] = (byte) (((255 & this.data[this.byteOffset]) >> (8 - this.bitOffset)) | bArr[i]);
            i++;
        }
        int i5 = i2 & 7;
        if (i5 == 0) {
            return;
        }
        bArr[i3] = (byte) (bArr[i3] & (255 >> i5));
        if (this.bitOffset + i5 > 8) {
            byte b = bArr[i3];
            byte[] bArr3 = this.data;
            int i6 = this.byteOffset;
            this.byteOffset = i6 + 1;
            bArr[i3] = (byte) (b | ((bArr3[i6] & FileDownloadStatus.error) << this.bitOffset));
            this.bitOffset -= 8;
        }
        this.bitOffset += i5;
        bArr[i3] = (byte) (((byte) (((this.data[this.byteOffset] & FileDownloadStatus.error) >> (8 - this.bitOffset)) << (8 - i5))) | bArr[i3]);
        if (this.bitOffset == 8) {
            this.bitOffset = 0;
            this.byteOffset++;
        }
        assertValidOffset();
    }

    public final void byteAlign() {
        if (this.bitOffset == 0) {
            return;
        }
        this.bitOffset = 0;
        this.byteOffset++;
        assertValidOffset();
    }

    public final void readBytes(byte[] bArr, int i, int i2) {
        Assertions.checkState(this.bitOffset == 0);
        System.arraycopy(this.data, this.byteOffset, bArr, i, i2);
        this.byteOffset += i2;
        assertValidOffset();
    }

    public final void skipBytes(int i) {
        Assertions.checkState(this.bitOffset == 0);
        this.byteOffset += i;
        assertValidOffset();
    }

    public final void putInt(int i, int i2) {
        if (i2 < 32) {
            i &= (1 << i2) - 1;
        }
        int iMin = Math.min(8 - this.bitOffset, i2);
        int i3 = (8 - this.bitOffset) - iMin;
        this.data[this.byteOffset] = (byte) (((65280 >> this.bitOffset) | ((1 << i3) - 1)) & this.data[this.byteOffset]);
        int i4 = i2 - iMin;
        this.data[this.byteOffset] = (byte) (((i >>> i4) << i3) | this.data[this.byteOffset]);
        int i5 = this.byteOffset + 1;
        while (i4 > 8) {
            this.data[i5] = (byte) (i >>> (i4 - 8));
            i4 -= 8;
            i5++;
        }
        int i6 = 8 - i4;
        this.data[i5] = (byte) (this.data[i5] & ((1 << i6) - 1));
        this.data[i5] = (byte) (((i & ((1 << i4) - 1)) << i6) | this.data[i5]);
        skipBits(i2);
        assertValidOffset();
    }

    private void assertValidOffset() {
        Assertions.checkState(this.byteOffset >= 0 && (this.byteOffset < this.byteLimit || (this.byteOffset == this.byteLimit && this.bitOffset == 0)));
    }
}
