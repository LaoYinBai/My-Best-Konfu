package com.mbridge.msdk.playercommon.exoplayer2.extractor.mkv;

import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.ExtractorInput;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class VarintReader {
    private static final int STATE_BEGIN_READING = 0;
    private static final int STATE_READ_CONTENTS = 1;
    private static final long[] VARINT_LENGTH_MASKS = {128, 64, 32, 16, 8, 4, 2, 1};
    private int length;
    private final byte[] scratch = new byte[8];
    private int state;

    public final void reset() {
        this.state = 0;
        this.length = 0;
    }

    public final long readUnsignedVarint(ExtractorInput extractorInput, boolean z, boolean z2, int i) throws InterruptedException, IOException {
        if (this.state == 0) {
            if (!extractorInput.readFully(this.scratch, 0, 1, z)) {
                return -1L;
            }
            this.length = parseUnsignedVarintLength(this.scratch[0] & FileDownloadStatus.error);
            if (this.length == -1) {
                throw new IllegalStateException("No valid varint length mask found");
            }
            this.state = 1;
        }
        if (this.length > i) {
            this.state = 0;
            return -2L;
        }
        if (this.length != 1) {
            extractorInput.readFully(this.scratch, 1, this.length - 1);
        }
        this.state = 0;
        return assembleVarint(this.scratch, this.length, z2);
    }

    public final int getLastLength() {
        return this.length;
    }

    public static int parseUnsignedVarintLength(int i) {
        for (int i2 = 0; i2 < VARINT_LENGTH_MASKS.length; i2++) {
            if ((VARINT_LENGTH_MASKS[i2] & i) != 0) {
                return i2 + 1;
            }
        }
        return -1;
    }

    public static long assembleVarint(byte[] bArr, int i, boolean z) {
        long j = bArr[0] & 255;
        if (z) {
            j &= ~VARINT_LENGTH_MASKS[i - 1];
        }
        for (int i2 = 1; i2 < i; i2++) {
            j = (j << 8) | (bArr[i2] & 255);
        }
        return j;
    }
}
