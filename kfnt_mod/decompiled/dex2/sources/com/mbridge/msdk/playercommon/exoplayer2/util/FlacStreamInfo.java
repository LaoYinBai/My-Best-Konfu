package com.mbridge.msdk.playercommon.exoplayer2.util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class FlacStreamInfo {
    public final int bitsPerSample;
    public final int channels;
    public final int maxBlockSize;
    public final int maxFrameSize;
    public final int minBlockSize;
    public final int minFrameSize;
    public final int sampleRate;
    public final long totalSamples;

    public FlacStreamInfo(byte[] bArr, int i) {
        ParsableBitArray parsableBitArray = new ParsableBitArray(bArr);
        parsableBitArray.setPosition(i * 8);
        this.minBlockSize = parsableBitArray.readBits(16);
        this.maxBlockSize = parsableBitArray.readBits(16);
        this.minFrameSize = parsableBitArray.readBits(24);
        this.maxFrameSize = parsableBitArray.readBits(24);
        this.sampleRate = parsableBitArray.readBits(20);
        this.channels = parsableBitArray.readBits(3) + 1;
        this.bitsPerSample = parsableBitArray.readBits(5) + 1;
        this.totalSamples = ((parsableBitArray.readBits(4) & 15) << 32) | (parsableBitArray.readBits(32) & 4294967295L);
    }

    public FlacStreamInfo(int i, int i2, int i3, int i4, int i5, int i6, int i7, long j) {
        this.minBlockSize = i;
        this.maxBlockSize = i2;
        this.minFrameSize = i3;
        this.maxFrameSize = i4;
        this.sampleRate = i5;
        this.channels = i6;
        this.bitsPerSample = i7;
        this.totalSamples = j;
    }

    public final int maxDecodedFrameSize() {
        return this.maxBlockSize * this.channels * (this.bitsPerSample / 8);
    }

    public final int bitRate() {
        return this.bitsPerSample * this.sampleRate;
    }

    public final long durationUs() {
        return (this.totalSamples * 1000000) / this.sampleRate;
    }

    public final long getSampleIndex(long j) {
        return Util.constrainValue((j * this.sampleRate) / 1000000, 0L, this.totalSamples - 1);
    }

    public final long getApproxBytesPerFrame() {
        if (this.maxFrameSize > 0) {
            return ((this.maxFrameSize + this.minFrameSize) / 2) + 1;
        }
        return (((((this.minBlockSize != this.maxBlockSize || this.minBlockSize <= 0) ? 4096L : this.minBlockSize) * this.channels) * this.bitsPerSample) / 8) + 64;
    }
}
