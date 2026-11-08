package com.mbridge.msdk.playercommon.exoplayer2.util;

import com.liulishuo.filedownloader.model.FileDownloadStatus;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class ParsableNalUnitBitArray {
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    private byte[] data;

    public ParsableNalUnitBitArray(byte[] bArr, int i, int i2) {
        reset(bArr, i, i2);
    }

    public final void reset(byte[] bArr, int i, int i2) {
        this.data = bArr;
        this.byteOffset = i;
        this.byteLimit = i2;
        this.bitOffset = 0;
        assertValidOffset();
    }

    public final void skipBit() {
        int i = this.bitOffset + 1;
        this.bitOffset = i;
        if (i == 8) {
            this.bitOffset = 0;
            this.byteOffset += shouldSkipByte(this.byteOffset + 1) ? 2 : 1;
        }
        assertValidOffset();
    }

    public final void skipBits(int i) {
        int i2 = this.byteOffset;
        int i3 = i / 8;
        this.byteOffset += i3;
        this.bitOffset += i - (i3 * 8);
        if (this.bitOffset > 7) {
            this.byteOffset++;
            this.bitOffset -= 8;
        }
        while (true) {
            i2++;
            if (i2 <= this.byteOffset) {
                if (shouldSkipByte(i2)) {
                    this.byteOffset++;
                    i2 += 2;
                }
            } else {
                assertValidOffset();
                return;
            }
        }
    }

    public final boolean canReadBits(int i) {
        int i2 = this.byteOffset;
        int i3 = i / 8;
        int i4 = this.byteOffset + i3;
        int i5 = (this.bitOffset + i) - (i3 * 8);
        if (i5 > 7) {
            i4++;
            i5 -= 8;
        }
        while (true) {
            i2++;
            if (i2 > i4 || i4 >= this.byteLimit) {
                break;
            }
            if (shouldSkipByte(i2)) {
                i4++;
                i2 += 2;
            }
        }
        if (i4 >= this.byteLimit) {
            return i4 == this.byteLimit && i5 == 0;
        }
        return true;
    }

    public final boolean readBit() {
        boolean z = (this.data[this.byteOffset] & (128 >> this.bitOffset)) != 0;
        skipBit();
        return z;
    }

    public final int readBits(int i) {
        this.bitOffset += i;
        int i2 = 0;
        while (true) {
            if (this.bitOffset <= 8) {
                break;
            }
            this.bitOffset -= 8;
            i2 |= (this.data[this.byteOffset] & FileDownloadStatus.error) << this.bitOffset;
            int i3 = this.byteOffset;
            if (!shouldSkipByte(this.byteOffset + 1)) {
                i = 1;
            }
            this.byteOffset = i3 + i;
        }
        int i4 = ((-1) >>> (32 - i)) & (i2 | ((this.data[this.byteOffset] & FileDownloadStatus.error) >> (8 - this.bitOffset)));
        if (this.bitOffset == 8) {
            this.bitOffset = 0;
            this.byteOffset += shouldSkipByte(this.byteOffset + 1) ? 2 : 1;
        }
        assertValidOffset();
        return i4;
    }

    public final boolean canReadExpGolombCodedNum() {
        int i = this.byteOffset;
        int i2 = this.bitOffset;
        int i3 = 0;
        while (this.byteOffset < this.byteLimit && !readBit()) {
            i3++;
        }
        boolean z = this.byteOffset == this.byteLimit;
        this.byteOffset = i;
        this.bitOffset = i2;
        return !z && canReadBits((i3 * 2) + 1);
    }

    public final int readUnsignedExpGolombCodedInt() {
        return readExpGolombCodeNum();
    }

    public final int readSignedExpGolombCodedInt() {
        int expGolombCodeNum = readExpGolombCodeNum();
        return (expGolombCodeNum % 2 == 0 ? -1 : 1) * ((expGolombCodeNum + 1) / 2);
    }

    private int readExpGolombCodeNum() {
        int i = 0;
        while (!readBit()) {
            i++;
        }
        return ((1 << i) - 1) + (i > 0 ? readBits(i) : 0);
    }

    private boolean shouldSkipByte(int i) {
        return 2 <= i && i < this.byteLimit && this.data[i] == 3 && this.data[i + (-2)] == 0 && this.data[i - 1] == 0;
    }

    private void assertValidOffset() {
        Assertions.checkState(this.byteOffset >= 0 && (this.byteOffset < this.byteLimit || (this.byteOffset == this.byteLimit && this.bitOffset == 0)));
    }
}
