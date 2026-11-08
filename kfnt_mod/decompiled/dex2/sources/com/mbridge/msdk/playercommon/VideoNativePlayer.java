package com.mbridge.msdk.playercommon;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.Surface;
import android.view.View;
import android.webkit.URLUtil;
import com.mbridge.msdk.foundation.same.report.d;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultLoadControl;
import com.mbridge.msdk.playercommon.exoplayer2.DefaultRenderersFactory;
import com.mbridge.msdk.playercommon.exoplayer2.ExoPlaybackException;
import com.mbridge.msdk.playercommon.exoplayer2.ExoPlayerFactory;
import com.mbridge.msdk.playercommon.exoplayer2.PlaybackParameters;
import com.mbridge.msdk.playercommon.exoplayer2.Player;
import com.mbridge.msdk.playercommon.exoplayer2.SimpleExoPlayer;
import com.mbridge.msdk.playercommon.exoplayer2.Timeline;
import com.mbridge.msdk.playercommon.exoplayer2.source.ExtractorMediaSource;
import com.mbridge.msdk.playercommon.exoplayer2.source.MediaSource;
import com.mbridge.msdk.playercommon.exoplayer2.source.TrackGroupArray;
import com.mbridge.msdk.playercommon.exoplayer2.trackselection.DefaultTrackSelector;
import com.mbridge.msdk.playercommon.exoplayer2.trackselection.TrackSelectionArray;
import com.mbridge.msdk.playercommon.exoplayer2.upstream.DefaultDataSourceFactory;
import com.mbridge.msdk.playercommon.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class VideoNativePlayer implements Player.EventListener {
    public static final int INTERVAL_TIME_PLAY_TIME_CD_THREAD = 1000;
    public static final int INTERVAL_TIME_PLAY_TIME_PROGRESS = 100;
    public static final String TAG = "VideoNativePlayer";
    private SimpleExoPlayer exoPlayer;
    private Timer mBufferTimeoutTimer;
    private long mCurrentPosition;
    private VideoPlayerStatusListener mInnerVFPLisener;
    private View mLoadingView;
    private String mNetUrl;
    private VideoPlayerStatusListener mOutterVFListener;
    private String mPlayUrl;
    private Surface mSurfaceHolder;
    private MediaSource mediaSource;
    private boolean mIsComplete = false;
    private boolean mIsPlaying = false;
    private boolean mHasPrepare = false;
    private boolean mIsStartPlay = true;
    private boolean mIsAllowLoopPlay = true;
    private boolean mHasChaoDi = false;
    private boolean mIsBuffering = false;
    private boolean mIsNeedBufferingTimeout = false;
    private boolean mIsFrontDesk = true;
    private int mBufferTime = 5;
    private boolean mIsOpenSound = true;
    private final Handler mHandler = new Handler(Looper.getMainLooper()) { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    };
    private Runnable playProgressRunnable = new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.2
        @Override // java.lang.Runnable
        public void run() {
            try {
                if (VideoNativePlayer.this.exoPlayer == null || !VideoNativePlayer.this.exoPlayerIsPlaying()) {
                    return;
                }
                VideoNativePlayer.this.mCurrentPosition = VideoNativePlayer.this.exoPlayer.getCurrentPosition();
                int i = (int) (VideoNativePlayer.this.mCurrentPosition / 1000);
                s.b(VideoNativePlayer.TAG, "currentPosition:" + i + " mCurrentPosition:" + VideoNativePlayer.this.mCurrentPosition);
                int duration = (VideoNativePlayer.this.exoPlayer == null || VideoNativePlayer.this.exoPlayer.getDuration() <= 0) ? 0 : (int) (VideoNativePlayer.this.exoPlayer.getDuration() / 1000);
                if (VideoNativePlayer.this.mIsStartPlay) {
                    VideoNativePlayer.this.postOnPlayStartOnMainThread(duration);
                    s.b(VideoNativePlayer.TAG, "onPlayStarted()");
                    VideoNativePlayer.this.mIsStartPlay = false;
                }
                if (i >= 0 && duration > 0 && VideoNativePlayer.this.exoPlayerIsPlaying()) {
                    VideoNativePlayer.this.postOnPlayProgressOnMainThread(i, duration);
                }
                VideoNativePlayer.this.mIsComplete = false;
                if (!VideoNativePlayer.this.mIsBuffering) {
                    VideoNativePlayer.this.hideLoading();
                }
                VideoNativePlayer.this.mHandler.postDelayed(this, 1000L);
            } catch (Exception e) {
                s.d(VideoNativePlayer.TAG, e.getMessage());
            }
        }
    };
    private Runnable playProgressMSRunnable = new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.3
        @Override // java.lang.Runnable
        public void run() {
            try {
                if (VideoNativePlayer.this.exoPlayer == null || !VideoNativePlayer.this.exoPlayerIsPlaying()) {
                    return;
                }
                VideoNativePlayer.this.mCurrentPosition = VideoNativePlayer.this.exoPlayer.getCurrentPosition();
                long j = VideoNativePlayer.this.mCurrentPosition / 100;
                long duration = (VideoNativePlayer.this.exoPlayer == null || VideoNativePlayer.this.exoPlayer.getDuration() <= 0) ? 0L : VideoNativePlayer.this.exoPlayer.getDuration() / 100;
                if (j >= 0 && duration > 0 && VideoNativePlayer.this.exoPlayerIsPlaying()) {
                    VideoNativePlayer.this.postOnPlayProgressMSOnMainThread(VideoNativePlayer.this.mCurrentPosition / 100, duration);
                }
                VideoNativePlayer.this.mHandler.postDelayed(this, 100L);
            } catch (Exception e) {
                s.d(VideoNativePlayer.TAG, e.getMessage());
            }
        }
    };

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Player.EventListener
    public void onLoadingChanged(boolean z) {
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Player.EventListener
    public void onRepeatModeChanged(int i) {
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Player.EventListener
    public void onSeekProcessed() {
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Player.EventListener
    public void onShuffleModeEnabledChanged(boolean z) {
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Player.EventListener
    public void onTimelineChanged(Timeline timeline, Object obj, int i) {
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Player.EventListener
    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    public void play(Context context, String str, int i) {
        try {
            s.d(TAG, "进来播放 currentionPosition:" + this.mCurrentPosition);
            this.mCurrentPosition = (long) i;
            if (TextUtils.isEmpty(str)) {
                postOnPlayErrorOnMainThread(PlayerErrorConstant.PLAY_URL_ILLEGAL);
                return;
            }
            showLoading();
            this.mPlayUrl = str;
            this.mHasPrepare = false;
            this.mIsFrontDesk = true;
            setDataSource(context);
            s.b(TAG, "mPlayUrl:" + this.mPlayUrl);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
            releasePlayer();
            hideLoading();
            postOnPlayErrorOnMainThread(PlayerErrorConstant.PLAY_CANNOT_PALY);
        }
    }

    public void play(Context context, String str, Surface surface) {
        try {
            if (TextUtils.isEmpty(str)) {
                postOnPlayErrorOnMainThread(PlayerErrorConstant.PLAY_URL_ILLEGAL);
                return;
            }
            showLoading();
            this.mPlayUrl = str;
            this.mHasPrepare = false;
            this.mIsFrontDesk = true;
            this.mSurfaceHolder = surface;
            setDataSource(context);
            s.b(TAG, "mPlayUrl:" + this.mPlayUrl);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
            releasePlayer();
            hideLoading();
            postOnPlayErrorOnMainThread(PlayerErrorConstant.PLAY_CANNOT_PALY);
        }
    }

    private void startPlayProgressTimer() {
        try {
            cancelPlayProgressTimer();
            this.mHandler.post(this.playProgressRunnable);
            this.mHandler.post(this.playProgressMSRunnable);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    private void cancelPlayProgressTimer() {
        try {
            this.mHandler.removeCallbacks(this.playProgressRunnable);
            this.mHandler.removeCallbacks(this.playProgressMSRunnable);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    private void cancelBufferTimeoutTimer() {
        try {
            if (this.mBufferTimeoutTimer != null) {
                this.mBufferTimeoutTimer.cancel();
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    private void startBufferingTimer(final String str) {
        if (!this.mIsNeedBufferingTimeout) {
            s.d(TAG, "不需要缓冲超时功能");
            return;
        }
        cancelBufferTimeoutTimer();
        this.mBufferTimeoutTimer = new Timer();
        this.mBufferTimeoutTimer.schedule(new TimerTask() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.4
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                try {
                    if (!VideoNativePlayer.this.mHasPrepare || VideoNativePlayer.this.mIsBuffering) {
                        s.d(VideoNativePlayer.TAG, "缓冲超时");
                        VideoNativePlayer.this.postOnBufferingStarOnMainThread(str);
                    }
                } catch (Exception e) {
                    s.d(VideoNativePlayer.TAG, e.getMessage());
                }
            }
        }, this.mBufferTime * 1000);
    }

    public void initBufferIngParam(int i) {
        if (i > 0) {
            this.mBufferTime = i;
        }
        this.mIsNeedBufferingTimeout = true;
        s.b(TAG, "mIsNeedBufferingTimeout:" + this.mIsNeedBufferingTimeout + "  mMaxBufferTime:" + this.mBufferTime);
    }

    public void onCompletion() {
        try {
            this.mIsComplete = true;
            this.mIsPlaying = false;
            this.mCurrentPosition = 0L;
            hideLoading();
            postOnPlayCompletedOnMainThread();
            s.b(TAG, "======onCompletion");
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void onPrepared() {
        try {
            s.b(TAG, "onPrepared:" + this.mHasPrepare);
            if (this.mIsFrontDesk) {
                this.mHasPrepare = true;
                postOnBufferinEndOnMainThread();
                startPlayProgressTimer();
                if (this.exoPlayer != null) {
                    this.mIsPlaying = true;
                }
                s.b(TAG, "onprepare mCurrentPosition:" + this.mCurrentPosition + " mHasPrepare：" + this.mHasPrepare);
                return;
            }
            s.d(TAG, "At background, Do not process");
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    public void showLoading() {
        try {
            if (this.mHandler == null) {
                return;
            }
            this.mHandler.post(new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.5
                @Override // java.lang.Runnable
                public void run() {
                    if (VideoNativePlayer.this.mLoadingView != null) {
                        VideoNativePlayer.this.mLoadingView.setVisibility(0);
                    }
                }
            });
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideLoading() {
        try {
            if (this.mHandler == null) {
                return;
            }
            this.mHandler.post(new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.6
                @Override // java.lang.Runnable
                public void run() {
                    if (VideoNativePlayer.this.mLoadingView != null) {
                        VideoNativePlayer.this.mLoadingView.setVisibility(8);
                    }
                }
            });
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postOnPlayProgressOnMainThread(final int i, final int i2) {
        try {
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.7
                    @Override // java.lang.Runnable
                    public void run() {
                        if (VideoNativePlayer.this.mOutterVFListener != null) {
                            VideoNativePlayer.this.mOutterVFListener.onPlayProgress(i, i2);
                        }
                        if (VideoNativePlayer.this.mInnerVFPLisener != null) {
                            VideoNativePlayer.this.mInnerVFPLisener.onPlayProgress(i, i2);
                        }
                    }
                });
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postOnPlayProgressMSOnMainThread(final long j, final long j2) {
        try {
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.8
                    @Override // java.lang.Runnable
                    public void run() {
                        if (VideoNativePlayer.this.mOutterVFListener != null) {
                            VideoNativePlayer.this.mOutterVFListener.onPlayProgressMS((int) j, (int) j2);
                        }
                        if (VideoNativePlayer.this.mInnerVFPLisener != null) {
                            VideoNativePlayer.this.mInnerVFPLisener.onPlayProgressMS((int) j, (int) j2);
                        }
                    }
                });
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postOnBufferingStarOnMainThread(final String str) {
        try {
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.9
                    @Override // java.lang.Runnable
                    public void run() {
                        if (VideoNativePlayer.this.mOutterVFListener != null) {
                            VideoNativePlayer.this.mOutterVFListener.onBufferingStart(str);
                        }
                        if (VideoNativePlayer.this.mInnerVFPLisener != null) {
                            VideoNativePlayer.this.mInnerVFPLisener.onBufferingStart(str);
                        }
                    }
                });
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    private void postOnBufferinEndOnMainThread() {
        try {
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.10
                    @Override // java.lang.Runnable
                    public void run() {
                        if (VideoNativePlayer.this.mOutterVFListener != null) {
                            VideoNativePlayer.this.mOutterVFListener.onBufferingEnd();
                        }
                        if (VideoNativePlayer.this.mInnerVFPLisener != null) {
                            VideoNativePlayer.this.mInnerVFPLisener.onBufferingEnd();
                        }
                    }
                });
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void postOnPlayStartOnMainThread(final int i) {
        try {
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.11
                    @Override // java.lang.Runnable
                    public void run() {
                        if (VideoNativePlayer.this.mOutterVFListener != null) {
                            VideoNativePlayer.this.mOutterVFListener.onPlayStarted(i);
                        }
                        if (VideoNativePlayer.this.mInnerVFPLisener != null) {
                            VideoNativePlayer.this.mInnerVFPLisener.onPlayStarted(i);
                        }
                    }
                });
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    private void postOnPlayErrorOnMainThread(final String str) {
        try {
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.12
                    @Override // java.lang.Runnable
                    public void run() {
                        if (VideoNativePlayer.this.mOutterVFListener != null) {
                            VideoNativePlayer.this.mOutterVFListener.onPlayError(str);
                        }
                        if (VideoNativePlayer.this.mInnerVFPLisener != null) {
                            VideoNativePlayer.this.mInnerVFPLisener.onPlayError(str);
                        }
                    }
                });
            }
            d.a(42, this.mPlayUrl, str);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    private void postOnPlaySetDataSourceError2MainThread(final String str) {
        try {
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.13
                    @Override // java.lang.Runnable
                    public void run() {
                        if (VideoNativePlayer.this.mInnerVFPLisener != null) {
                            VideoNativePlayer.this.mInnerVFPLisener.onPlaySetDataSourceError(str);
                        }
                        if (VideoNativePlayer.this.mOutterVFListener != null) {
                            VideoNativePlayer.this.mOutterVFListener.onPlaySetDataSourceError(str);
                        }
                    }
                });
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    private void postOnPlayCompletedOnMainThread() {
        try {
            if (this.mHandler != null) {
                this.mHandler.post(new Runnable() { // from class: com.mbridge.msdk.playercommon.VideoNativePlayer.14
                    @Override // java.lang.Runnable
                    public void run() {
                        if (VideoNativePlayer.this.mOutterVFListener != null) {
                            VideoNativePlayer.this.mOutterVFListener.onPlayCompleted();
                        }
                        if (VideoNativePlayer.this.mInnerVFPLisener != null) {
                            VideoNativePlayer.this.mInnerVFPLisener.onPlayCompleted();
                        }
                    }
                });
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void pause() {
        try {
            s.b(TAG, "player pause");
            if (!this.mHasPrepare) {
                s.b(TAG, "pause !mHasPrepare retrun");
                return;
            }
            if (this.exoPlayer == null || !exoPlayerIsPlaying()) {
                return;
            }
            s.b(TAG, "pause " + this.mIsPlaying);
            hideLoading();
            this.exoPlayer.setPlayWhenReady(false);
            this.mIsPlaying = false;
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void stop() {
        try {
            if (!this.mHasPrepare) {
                s.b(TAG, "stop !mHasPrepare retrun");
                return;
            }
            if (this.exoPlayer == null || !exoPlayerIsPlaying()) {
                return;
            }
            hideLoading();
            this.exoPlayer.stop();
            cancelPlayProgressTimer();
            this.mIsPlaying = false;
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void start() {
        start((Surface) null);
    }

    public void start(Surface surface) {
        try {
            if (!this.mHasPrepare) {
                s.b(TAG, "start !mHasPrepare retrun");
                return;
            }
            boolean z = true;
            if (this.exoPlayer != null && !exoPlayerIsPlaying()) {
                showLoading();
                if (surface != null) {
                    this.mSurfaceHolder = surface;
                    this.exoPlayer.setVideoSurface(surface);
                }
                play();
                startPlayProgressTimer();
                this.mIsPlaying = true;
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("exoplayer is null : ");
            if (this.exoPlayer != null) {
                z = false;
            }
            sb.append(z);
            s.b(TAG, sb.toString());
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void play() {
        try {
            this.exoPlayer.setPlayWhenReady(true);
            startPlayProgressTimer();
            this.mIsPlaying = true;
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void start(int i) {
        try {
            if (!this.mHasPrepare) {
                s.b(TAG, "start mHasprepare is false return");
                return;
            }
            if (this.exoPlayer == null || exoPlayerIsPlaying()) {
                return;
            }
            if (i > 0) {
                this.exoPlayer.seekTo(i);
            }
            play();
            startPlayProgressTimer();
            this.mIsPlaying = true;
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public boolean initParameter(String str, boolean z, boolean z2, View view, VideoPlayerStatusListener videoPlayerStatusListener) {
        try {
            if (TextUtils.isEmpty(str)) {
                s.b(TAG, "netUrl为空 return");
                postOnPlayErrorOnMainThread(PlayerErrorConstant.MEDIAPLAYER_INIT_FAILED);
                return false;
            }
            if (view == null) {
                s.b(TAG, "loadingView为空 return");
                postOnPlayErrorOnMainThread(PlayerErrorConstant.MEDIAPLAYER_INIT_FAILED);
                return false;
            }
            this.mIsOpenSound = z;
            this.mIsAllowLoopPlay = z2;
            this.mLoadingView = view;
            this.mNetUrl = str;
            this.mOutterVFListener = videoPlayerStatusListener;
            return true;
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
            postOnPlayErrorOnMainThread(th.toString());
            return false;
        }
    }

    public void setDataSource(Context context) {
        try {
            s.b(TAG, "setDataSource begin");
            if (this.exoPlayer != null) {
                try {
                    if (exoPlayerIsPlaying()) {
                        this.exoPlayer.stop();
                    }
                    this.exoPlayer.release();
                } catch (Throwable th) {
                    s.d(TAG, th.getMessage());
                }
            }
            if (!this.mIsOpenSound) {
                closeSound();
            }
            if (!TextUtils.isEmpty(this.mPlayUrl)) {
                this.exoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(context), new DefaultTrackSelector(), new DefaultLoadControl());
                Uri uri = Uri.parse(this.mPlayUrl);
                if (this.mPlayUrl.startsWith("http") || this.mPlayUrl.startsWith("https")) {
                    this.mediaSource = new ExtractorMediaSource.Factory(new DefaultHttpDataSourceFactory("MBridge_ExoPlayer")).createMediaSource(uri);
                } else {
                    this.mediaSource = new ExtractorMediaSource.Factory(new DefaultDataSourceFactory(context, "MBridge_ExoPlayer")).createMediaSource(uri);
                }
                this.exoPlayer.setRepeatMode(this.mIsAllowLoopPlay ? 2 : 0);
                this.exoPlayer.prepare(this.mediaSource);
                this.exoPlayer.setPlayWhenReady(true);
                this.exoPlayer.addListener(this);
            }
            if (this.mSurfaceHolder != null) {
                this.exoPlayer.setVideoSurface(this.mSurfaceHolder);
            }
            startBufferingTimer(PlayerErrorConstant.PREPARE_TIMEOUT);
            s.b(TAG, "setDataSource done");
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
            hideLoading();
            if (URLUtil.isNetworkUrl(this.mPlayUrl)) {
                s.b(TAG, "setDataSource error : Is Online source : " + this.mNetUrl);
                postOnPlayErrorOnMainThread(PlayerErrorConstant.PLAY_CANNOT_PALY);
                postOnPlaySetDataSourceError2MainThread(PlayerErrorConstant.SET_DATA_SOURCE_ERROR);
            } else if (!TextUtils.isEmpty(this.mNetUrl) && !this.mHasChaoDi) {
                this.mHasChaoDi = true;
                s.b(TAG, "setDataSource error : Will play online source : " + this.mNetUrl);
                this.mPlayUrl = this.mNetUrl;
                showLoading();
                setDataSource(context);
            } else {
                postOnPlayErrorOnMainThread(PlayerErrorConstant.PLAY_CANNOT_PALY);
            }
            postOnPlaySetDataSourceError2MainThread(PlayerErrorConstant.SET_DATA_SOURCE_ERROR);
        }
    }

    public void setSelfVideoFeedsPlayerListener(VideoPlayerStatusListener videoPlayerStatusListener) {
        this.mInnerVFPLisener = videoPlayerStatusListener;
    }

    public void releasePlayer() {
        try {
            s.b(TAG, "releasePlayer");
            cancelPlayProgressTimer();
            cancelBufferTimeoutTimer();
            if (this.exoPlayer != null) {
                stop();
                this.exoPlayer.removeListener(this);
                this.exoPlayer.release();
                this.exoPlayer = null;
                this.mIsPlaying = false;
            }
        } catch (Throwable th) {
            s.b(TAG, th.getMessage(), th);
        }
        hideLoading();
    }

    public void closeSound() {
        try {
            if (this.exoPlayer == null) {
                return;
            }
            this.exoPlayer.setVolume(0.0f);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void openSound() {
        try {
            if (this.exoPlayer == null) {
                return;
            }
            this.exoPlayer.setVolume(1.0f);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public int getCurPosition() {
        return (int) this.mCurrentPosition;
    }

    public boolean isPlayIng() {
        try {
            if (this.exoPlayer != null) {
                return exoPlayerIsPlaying();
            }
            return false;
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
            return false;
        }
    }

    public boolean onError(int i, String str) {
        try {
            s.d(TAG, "onError what:" + i + " extra:" + str);
            hideLoading();
            this.mHasPrepare = false;
            this.mIsPlaying = false;
            postOnPlayErrorOnMainThread(str);
            return true;
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
            return true;
        }
    }

    public void setVideoFeedsPlayerListener(VideoPlayerStatusListener videoPlayerStatusListener) {
        this.mOutterVFListener = videoPlayerStatusListener;
    }

    public boolean hasPrepare() {
        return this.mHasPrepare;
    }

    public void setIsFrontDesk(boolean z) {
        try {
            this.mIsFrontDesk = z;
            StringBuilder sb = new StringBuilder();
            sb.append("isFrontDesk: ");
            sb.append(z ? "frontStage" : "backStage");
            s.d(TAG, sb.toString());
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public boolean isComplete() {
        return this.mIsComplete;
    }

    public void setIsComplete(boolean z) {
        this.mIsComplete = z;
    }

    public boolean isPlaying() {
        return exoPlayerIsPlaying();
    }

    public boolean loadingViewIsVisible() {
        try {
            if (this.mLoadingView != null) {
                return this.mLoadingView.getVisibility() == 0;
            }
            return false;
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
            return false;
        }
    }

    public boolean exoPlayerIsPlaying() {
        return this.exoPlayer.getPlaybackState() == 3 && this.exoPlayer.getPlayWhenReady();
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Player.EventListener
    public void onPlayerStateChanged(boolean z, int i) {
        s.d(TAG, "onPlaybackStateChanged : " + i);
        switch (i) {
            case 1:
                s.d(TAG, "onPlaybackStateChanged : IDLE");
                break;
            case 2:
                s.d(TAG, "onPlaybackStateChanged : Buffering");
                this.mIsBuffering = true;
                showLoading();
                startBufferingTimer(PlayerErrorConstant.PLAYERING_TIMEOUT);
                break;
            case 3:
                s.d(TAG, "onPlaybackStateChanged : READY");
                this.mIsBuffering = false;
                hideLoading();
                postOnBufferinEndOnMainThread();
                onPrepared();
                break;
            case 4:
                s.d(TAG, "onPlaybackStateChanged : Ended : PLAY ENDED");
                cancelPlayProgressTimer();
                onCompletion();
                break;
        }
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Player.EventListener
    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        String message = "Play error and ExoPlayer have not message.";
        if (exoPlaybackException != null) {
            switch (exoPlaybackException.type) {
                case 0:
                    message = "Play error, because have a SourceException.";
                    break;
                case 1:
                    message = "Play error, because have a RendererException.";
                    break;
                case 2:
                    message = "Play error, because have a UnexpectedException.";
                    break;
            }
        }
        if (exoPlaybackException.getCause() != null && !TextUtils.isEmpty(exoPlaybackException.getCause().getMessage())) {
            message = exoPlaybackException.getCause().getMessage();
        }
        s.d(TAG, "onPlayerError : " + message);
        onError(exoPlaybackException.type, message);
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Player.EventListener
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
        s.d(TAG, "onPlaybackParametersChanged : " + playbackParameters.speed);
    }

    @Override // com.mbridge.msdk.playercommon.exoplayer2.Player.EventListener
    public void onPositionDiscontinuity(int i) {
        s.d(TAG, "onPositionDiscontinuity : " + i);
        if (i == 0) {
            onCompletion();
        }
    }
}
