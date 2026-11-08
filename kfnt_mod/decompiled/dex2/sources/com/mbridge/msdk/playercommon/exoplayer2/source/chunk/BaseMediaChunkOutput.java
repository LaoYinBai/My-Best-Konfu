package com.mbridge.msdk.playercommon.exoplayer2.source.chunk;

import android.util.Log;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.DummyTrackOutput;
import com.mbridge.msdk.playercommon.exoplayer2.extractor.TrackOutput;
import com.mbridge.msdk.playercommon.exoplayer2.source.SampleQueue;
import com.mbridge.msdk.playercommon.exoplayer2.source.chunk.ChunkExtractorWrapper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class BaseMediaChunkOutput implements ChunkExtractorWrapper.TrackOutputProvider {
    private static final String TAG = "BaseMediaChunkOutput";
    private final SampleQueue[] sampleQueues;
    private final int[] trackTypes;

    public BaseMediaChunkOutput(int[] iArr, SampleQueue[] sampleQueueArr) {
        this.trackTypes = iArr;
        this.sampleQueues = sampleQueueArr;
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.source.chunk.ChunkExtractorWrapper.TrackOutputProvider
    public final TrackOutput track(int i, int i2) {
        for (int i3 = 0; i3 < this.trackTypes.length; i3++) {
            if (i2 == this.trackTypes[i3]) {
                return this.sampleQueues[i3];
            }
        }
        Log.e(TAG, "Unmatched track of type: " + i2);
        return new DummyTrackOutput();
    }

    public final int[] getWriteIndices() {
        int[] iArr = new int[this.sampleQueues.length];
        for (int i = 0; i < this.sampleQueues.length; i++) {
            if (this.sampleQueues[i] != null) {
                iArr[i] = this.sampleQueues[i].getWriteIndex();
            }
        }
        return iArr;
    }

    public final void setSampleOffsetUs(long j) {
        for (SampleQueue sampleQueue : this.sampleQueues) {
            if (sampleQueue != null) {
                sampleQueue.setSampleOffsetUs(j);
            }
        }
    }
}
