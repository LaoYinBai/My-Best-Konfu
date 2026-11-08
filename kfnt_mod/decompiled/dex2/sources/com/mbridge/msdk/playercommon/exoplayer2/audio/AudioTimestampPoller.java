package com.mbridge.msdk.playercommon.exoplayer2.audio;

import android.media.AudioTimestamp;
import android.media.AudioTrack;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;
import com.mbridge.msdk.playercommon.exoplayer2.util.Util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class AudioTimestampPoller {
    private static final int ERROR_POLL_INTERVAL_US = 500000;
    private static final int FAST_POLL_INTERVAL_US = 5000;
    private static final int INITIALIZING_DURATION_US = 500000;
    private static final int SLOW_POLL_INTERVAL_US = 10000000;
    private static final int STATE_ERROR = 4;
    private static final int STATE_INITIALIZING = 0;
    private static final int STATE_NO_TIMESTAMP = 3;
    private static final int STATE_TIMESTAMP = 1;
    private static final int STATE_TIMESTAMP_ADVANCING = 2;
    private final AudioTimestampV19 audioTimestamp;
    private long initialTimestampPositionFrames;
    private long initializeSystemTimeUs;
    private long lastTimestampSampleTimeUs;
    private long sampleIntervalUs;
    private int state;

    private @interface State {
    }

    public AudioTimestampPoller(AudioTrack audioTrack) {
        if (Util.SDK_INT >= 19) {
            this.audioTimestamp = new AudioTimestampV19(audioTrack);
            reset();
        } else {
            this.audioTimestamp = null;
            updateState(3);
        }
    }

    public final boolean maybePollTimestamp(long j) {
        if (this.audioTimestamp == null || j - this.lastTimestampSampleTimeUs < this.sampleIntervalUs) {
            return false;
        }
        this.lastTimestampSampleTimeUs = j;
        boolean zMaybeUpdateTimestamp = this.audioTimestamp.maybeUpdateTimestamp();
        switch (this.state) {
            case 0:
                if (!zMaybeUpdateTimestamp) {
                    if (j - this.initializeSystemTimeUs <= 500000) {
                        return zMaybeUpdateTimestamp;
                    }
                    updateState(3);
                    return zMaybeUpdateTimestamp;
                }
                if (this.audioTimestamp.getTimestampSystemTimeUs() < this.initializeSystemTimeUs) {
                    return false;
                }
                this.initialTimestampPositionFrames = this.audioTimestamp.getTimestampPositionFrames();
                updateState(1);
                return zMaybeUpdateTimestamp;
            case 1:
                if (!zMaybeUpdateTimestamp) {
                    reset();
                    return zMaybeUpdateTimestamp;
                }
                if (this.audioTimestamp.getTimestampPositionFrames() <= this.initialTimestampPositionFrames) {
                    return zMaybeUpdateTimestamp;
                }
                updateState(2);
                return zMaybeUpdateTimestamp;
            case 2:
                if (zMaybeUpdateTimestamp) {
                    return zMaybeUpdateTimestamp;
                }
                reset();
                return zMaybeUpdateTimestamp;
            case 3:
                if (!zMaybeUpdateTimestamp) {
                    return zMaybeUpdateTimestamp;
                }
                reset();
                return zMaybeUpdateTimestamp;
            case 4:
                return zMaybeUpdateTimestamp;
            default:
                throw new IllegalStateException();
        }
    }

    public final void rejectTimestamp() {
        updateState(4);
    }

    public final void acceptTimestamp() {
        if (this.state == 4) {
            reset();
        }
    }

    public final boolean hasTimestamp() {
        return this.state == 1 || this.state == 2;
    }

    public final boolean isTimestampAdvancing() {
        return this.state == 2;
    }

    public final void reset() {
        if (this.audioTimestamp != null) {
            updateState(0);
        }
    }

    public final long getTimestampSystemTimeUs() {
        return this.audioTimestamp != null ? this.audioTimestamp.getTimestampSystemTimeUs() : C.TIME_UNSET;
    }

    public final long getTimestampPositionFrames() {
        if (this.audioTimestamp != null) {
            return this.audioTimestamp.getTimestampPositionFrames();
        }
        return -1L;
    }

    private void updateState(int i) {
        this.state = i;
        switch (i) {
            case 0:
                this.lastTimestampSampleTimeUs = 0L;
                this.initialTimestampPositionFrames = -1L;
                this.initializeSystemTimeUs = System.nanoTime() / 1000;
                this.sampleIntervalUs = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
                return;
            case 1:
                this.sampleIntervalUs = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
                return;
            case 2:
            case 3:
                this.sampleIntervalUs = 10000000L;
                return;
            case 4:
                this.sampleIntervalUs = 500000L;
                return;
            default:
                throw new IllegalStateException();
        }
    }

    private static final class AudioTimestampV19 {
        private final AudioTimestamp audioTimestamp = new AudioTimestamp();
        private final AudioTrack audioTrack;
        private long lastTimestampPositionFrames;
        private long lastTimestampRawPositionFrames;
        private long rawTimestampFramePositionWrapCount;

        public AudioTimestampV19(AudioTrack audioTrack) {
            this.audioTrack = audioTrack;
        }

        public final boolean maybeUpdateTimestamp() {
            boolean timestamp = this.audioTrack.getTimestamp(this.audioTimestamp);
            if (timestamp) {
                long j = this.audioTimestamp.framePosition;
                if (this.lastTimestampRawPositionFrames > j) {
                    this.rawTimestampFramePositionWrapCount++;
                }
                this.lastTimestampRawPositionFrames = j;
                this.lastTimestampPositionFrames = j + (this.rawTimestampFramePositionWrapCount << 32);
            }
            return timestamp;
        }

        public final long getTimestampSystemTimeUs() {
            return this.audioTimestamp.nanoTime / 1000;
        }

        public final long getTimestampPositionFrames() {
            return this.lastTimestampPositionFrames;
        }
    }
}
