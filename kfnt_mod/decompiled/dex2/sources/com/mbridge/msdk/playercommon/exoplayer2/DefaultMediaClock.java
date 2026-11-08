package com.mbridge.msdk.playercommon.exoplayer2;

import com.mbridge.msdk.playercommon.exoplayer2.util.Clock;
import com.mbridge.msdk.playercommon.exoplayer2.util.MediaClock;
import com.mbridge.msdk.playercommon.exoplayer2.util.StandaloneMediaClock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class DefaultMediaClock implements MediaClock {
    private final PlaybackParameterListener listener;
    private MediaClock rendererClock;
    private Renderer rendererClockSource;
    private final StandaloneMediaClock standaloneMediaClock;

    public interface PlaybackParameterListener {
        void onPlaybackParametersChanged(PlaybackParameters playbackParameters);
    }

    public DefaultMediaClock(PlaybackParameterListener playbackParameterListener, Clock clock) {
        this.listener = playbackParameterListener;
        this.standaloneMediaClock = new StandaloneMediaClock(clock);
    }

    public final void start() {
        this.standaloneMediaClock.start();
    }

    public final void stop() {
        this.standaloneMediaClock.stop();
    }

    public final void resetPosition(long j) {
        this.standaloneMediaClock.resetPosition(j);
    }

    public final void onRendererEnabled(Renderer renderer) throws ExoPlaybackException {
        MediaClock mediaClock = renderer.getMediaClock();
        if (mediaClock == null || mediaClock == this.rendererClock) {
            return;
        }
        if (this.rendererClock != null) {
            throw ExoPlaybackException.createForUnexpected(new IllegalStateException("Multiple renderer media clocks enabled."));
        }
        this.rendererClock = mediaClock;
        this.rendererClockSource = renderer;
        this.rendererClock.setPlaybackParameters(this.standaloneMediaClock.getPlaybackParameters());
        ensureSynced();
    }

    public final void onRendererDisabled(Renderer renderer) {
        if (renderer == this.rendererClockSource) {
            this.rendererClock = null;
            this.rendererClockSource = null;
        }
    }

    public final long syncAndGetPositionUs() {
        if (isUsingRendererClock()) {
            ensureSynced();
            return this.rendererClock.getPositionUs();
        }
        return this.standaloneMediaClock.getPositionUs();
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.util.MediaClock
    public final long getPositionUs() {
        if (isUsingRendererClock()) {
            return this.rendererClock.getPositionUs();
        }
        return this.standaloneMediaClock.getPositionUs();
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.util.MediaClock
    public final PlaybackParameters setPlaybackParameters(PlaybackParameters playbackParameters) {
        if (this.rendererClock != null) {
            playbackParameters = this.rendererClock.setPlaybackParameters(playbackParameters);
        }
        this.standaloneMediaClock.setPlaybackParameters(playbackParameters);
        this.listener.onPlaybackParametersChanged(playbackParameters);
        return playbackParameters;
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.util.MediaClock
    public final PlaybackParameters getPlaybackParameters() {
        return this.rendererClock != null ? this.rendererClock.getPlaybackParameters() : this.standaloneMediaClock.getPlaybackParameters();
    }

    private void ensureSynced() {
        this.standaloneMediaClock.resetPosition(this.rendererClock.getPositionUs());
        PlaybackParameters playbackParameters = this.rendererClock.getPlaybackParameters();
        if (playbackParameters.equals(this.standaloneMediaClock.getPlaybackParameters())) {
            return;
        }
        this.standaloneMediaClock.setPlaybackParameters(playbackParameters);
        this.listener.onPlaybackParametersChanged(playbackParameters);
    }

    private boolean isUsingRendererClock() {
        return (this.rendererClockSource == null || this.rendererClockSource.isEnded() || (!this.rendererClockSource.isReady() && this.rendererClockSource.hasReadStreamToEnd())) ? false : true;
    }
}
