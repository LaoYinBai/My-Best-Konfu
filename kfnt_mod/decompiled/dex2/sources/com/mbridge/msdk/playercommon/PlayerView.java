package com.mbridge.msdk.playercommon;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class PlayerView extends LinearLayout {
    public static final String TAG = "PlayerView";
    private boolean isBTVideo;
    private boolean isBTVideoPlaying;
    private boolean mInitState;
    private boolean mIsCovered;
    private boolean mIsFirstCreateHolder;
    private boolean mIsNeedToRepeatPrepare;
    private boolean mIsSurfaceHolderDestoryed;
    private LinearLayout mLlSurContainer;
    private LinearLayout mLoadingView;
    private String mPlayUrl;
    private SurfaceHolder mSurfaceHolder;
    private VideoFeedsPlayer mVideoFeedsPlayer;

    public PlayerView(Context context) {
        super(context);
        this.mInitState = false;
        this.mIsFirstCreateHolder = true;
        this.mIsSurfaceHolderDestoryed = false;
        this.mIsCovered = false;
        this.mIsNeedToRepeatPrepare = false;
        this.isBTVideo = false;
        this.isBTVideoPlaying = false;
        init();
    }

    public PlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mInitState = false;
        this.mIsFirstCreateHolder = true;
        this.mIsSurfaceHolderDestoryed = false;
        this.mIsCovered = false;
        this.mIsNeedToRepeatPrepare = false;
        this.isBTVideo = false;
        this.isBTVideoPlaying = false;
        init();
    }

    public void setIsBTVideo(boolean z) {
        this.isBTVideo = z;
    }

    public void setIsBTVideoPlaying(boolean z) {
        this.isBTVideoPlaying = z;
    }

    private void init() {
        try {
            initView();
            initPlayer();
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    private void initPlayer() {
        this.mVideoFeedsPlayer = new VideoFeedsPlayer();
    }

    public void addSurfaceView() {
        try {
            s.b(TAG, "addSurfaceView");
            SurfaceView surfaceView = new SurfaceView(getContext().getApplicationContext());
            this.mSurfaceHolder = surfaceView.getHolder();
            this.mSurfaceHolder.setType(3);
            this.mSurfaceHolder.setKeepScreenOn(true);
            this.mSurfaceHolder.addCallback(new MySurfaceHoldeCallback());
            this.mLlSurContainer.addView(surfaceView, -1, -1);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void removeSurface() {
        try {
            s.b(TAG, "removeSurface");
            this.mLlSurContainer.removeAllViews();
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(getContext()).inflate(m.a(getContext(), "mbridge_playercommon_player_view", TtmlNode.TAG_LAYOUT), (ViewGroup) null);
        if (viewInflate != null) {
            this.mLlSurContainer = (LinearLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_playercommon_ll_sur_container", "id"));
            this.mLoadingView = (LinearLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_playercommon_ll_loading", "id"));
            addSurfaceView();
            addView(viewInflate, -1, -1);
        }
    }

    public boolean initVFPData(String str, String str2, VideoPlayerStatusListener videoPlayerStatusListener) {
        if (TextUtils.isEmpty(str)) {
            s.b(TAG, "playUrl==null");
            return false;
        }
        this.mPlayUrl = str;
        this.mVideoFeedsPlayer.initPlayer(getContext(), this.mLoadingView, str, videoPlayerStatusListener);
        this.mInitState = true;
        return true;
    }

    public boolean playVideo(int i) {
        try {
            if (this.mVideoFeedsPlayer == null) {
                s.b(TAG, "player init error 播放失败");
                return false;
            }
            if (!this.mInitState) {
                s.b(TAG, "vfp init failed 播放失败");
                return false;
            }
            this.mVideoFeedsPlayer.play(this.mPlayUrl, i);
            this.mIsNeedToRepeatPrepare = false;
            return true;
        } catch (Throwable th) {
            s.b(TAG, th.getMessage(), th);
            return false;
        }
    }

    public boolean playVideo() {
        return playVideo(0);
    }

    public void onPause() {
        try {
            pause();
            if (this.mVideoFeedsPlayer != null) {
                this.mVideoFeedsPlayer.setIsFrontDesk(false);
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void pause() {
        try {
            if (this.mVideoFeedsPlayer != null) {
                this.mVideoFeedsPlayer.pause();
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void resumeStart() {
        try {
            start(true);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void setDataSource() {
        try {
            if (this.mVideoFeedsPlayer != null) {
                this.mVideoFeedsPlayer.showLoading();
                this.mVideoFeedsPlayer.setDataSource();
                this.mIsNeedToRepeatPrepare = false;
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void start(boolean z) {
        try {
            if (this.mVideoFeedsPlayer == null || this.mIsCovered) {
                return;
            }
            this.mVideoFeedsPlayer.start(z);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void prepare() {
        try {
            if (this.mVideoFeedsPlayer != null) {
                this.mVideoFeedsPlayer.prepare();
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void start(int i) {
        try {
            if (this.mVideoFeedsPlayer != null) {
                this.mVideoFeedsPlayer.start(i);
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void justSeekTo(int i) {
        try {
            if (this.mVideoFeedsPlayer != null) {
                this.mVideoFeedsPlayer.justSeekTo(i);
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void seekTo(int i) {
        try {
            if (this.mVideoFeedsPlayer != null) {
                this.mVideoFeedsPlayer.seekTo(i);
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void stop() {
        try {
            if (this.mVideoFeedsPlayer != null) {
                this.mVideoFeedsPlayer.stop();
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void openSound() {
        if (this.mVideoFeedsPlayer != null) {
            this.mVideoFeedsPlayer.openSound();
        }
    }

    public void closeSound() {
        if (this.mVideoFeedsPlayer != null) {
            this.mVideoFeedsPlayer.closeSound();
        }
    }

    public void setDesk(boolean z) {
        this.mVideoFeedsPlayer.setIsFrontDesk(z);
    }

    public void onResume() {
        try {
            this.mVideoFeedsPlayer.setIsFrontDesk(true);
            if (this.mVideoFeedsPlayer != null && !this.mIsFirstCreateHolder && !this.mIsSurfaceHolderDestoryed && !isComplete() && !this.mIsCovered) {
                s.b(TAG, "onresume========");
                if (this.mVideoFeedsPlayer.hasPrepare()) {
                    resumeStart();
                } else {
                    playVideo(0);
                }
            }
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public void coverUnlockResume() {
        try {
            this.mVideoFeedsPlayer.setIsFrontDesk(true);
            if (this.mVideoFeedsPlayer != null) {
                s.b(TAG, "coverUnlockResume========");
                if (!this.mVideoFeedsPlayer.hasPrepare() || this.mIsNeedToRepeatPrepare) {
                    playVideo(0);
                } else {
                    start(true);
                }
            }
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    public void release() {
        try {
            if (this.mVideoFeedsPlayer != null) {
                this.mVideoFeedsPlayer.releasePlayer();
            }
            if (this.mSurfaceHolder != null) {
                s.d(TAG, "mSurfaceHolder release");
                this.mSurfaceHolder.getSurface().release();
            }
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
        }
    }

    public int getCurPosition() {
        try {
            if (this.mVideoFeedsPlayer != null) {
                return this.mVideoFeedsPlayer.getCurPosition();
            }
            return 0;
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
            return 0;
        }
    }

    public int getDuration() {
        if (this.mVideoFeedsPlayer != null) {
            return this.mVideoFeedsPlayer.getDuration();
        }
        return 0;
    }

    private class MySurfaceHoldeCallback implements SurfaceHolder.Callback {
        private MySurfaceHoldeCallback() {
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            try {
                s.b(PlayerView.TAG, "surfaceCreated");
                if (PlayerView.this.mVideoFeedsPlayer != null && surfaceHolder != null) {
                    PlayerView.this.mSurfaceHolder = surfaceHolder;
                    PlayerView.this.mVideoFeedsPlayer.setDisplay(surfaceHolder);
                }
                PlayerView.this.mIsFirstCreateHolder = false;
            } catch (Exception e) {
                s.d(PlayerView.TAG, e.getMessage());
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            try {
                s.b(PlayerView.TAG, "surfaceDestroyed ");
                PlayerView.this.mIsSurfaceHolderDestoryed = true;
                PlayerView.this.mIsNeedToRepeatPrepare = true;
                PlayerView.this.mVideoFeedsPlayer.pause();
            } catch (Exception e) {
                s.d(PlayerView.TAG, e.getMessage());
            }
        }

        @Override // android.view.SurfaceHolder.Callback
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            try {
                s.b(PlayerView.TAG, "surfaceChanged");
                if (PlayerView.this.mIsSurfaceHolderDestoryed && !PlayerView.this.mIsCovered && !PlayerView.this.isComplete() && !PlayerView.this.isBTVideo) {
                    if (PlayerView.this.mVideoFeedsPlayer.hasPrepare()) {
                        s.b(PlayerView.TAG, "surfaceChanged  start====");
                        PlayerView.this.resumeStart();
                    } else {
                        s.b(PlayerView.TAG, "surfaceChanged  PLAY====");
                        PlayerView.this.playVideo(0);
                    }
                    if (PlayerView.this.isBTVideo) {
                        if (PlayerView.this.isBTVideoPlaying) {
                            if (!PlayerView.this.mVideoFeedsPlayer.hasPrepare()) {
                                PlayerView.this.mVideoFeedsPlayer.prepare();
                            }
                            PlayerView.this.mVideoFeedsPlayer.start(false);
                        } else {
                            PlayerView.this.pause();
                        }
                    }
                }
                PlayerView.this.mIsSurfaceHolderDestoryed = false;
            } catch (Exception e) {
                s.d(PlayerView.TAG, e.getMessage());
            }
        }
    }

    public boolean isComplete() {
        try {
            if (this.mVideoFeedsPlayer != null) {
                return this.mVideoFeedsPlayer.isComplete();
            }
            return false;
        } catch (Throwable th) {
            s.b(TAG, th.getMessage(), th);
            return false;
        }
    }

    public void initBufferIngParam(int i) {
        if (this.mVideoFeedsPlayer != null) {
            this.mVideoFeedsPlayer.initBufferIngParam(i);
        }
    }

    public boolean isPlayIng() {
        try {
            if (this.mVideoFeedsPlayer != null) {
                return this.mVideoFeedsPlayer.isPlayIng();
            }
            return false;
        } catch (Throwable th) {
            s.d(TAG, th.getMessage());
            return false;
        }
    }

    public void setIsCovered(boolean z) {
        try {
            this.mIsCovered = z;
            s.d(TAG, "mIsCovered:" + z);
        } catch (Exception e) {
            s.d(TAG, e.getMessage());
        }
    }

    public boolean isSilent() {
        return this.mVideoFeedsPlayer.isSilent();
    }

    public void setVolume(float f, float f2) {
        if (this.mVideoFeedsPlayer != null) {
            this.mVideoFeedsPlayer.setVolume(f, f2);
        }
    }

    public void setPlaybackParams(float f) {
        if (this.mVideoFeedsPlayer != null) {
            this.mVideoFeedsPlayer.setPlaybackParams(f);
        }
    }
}
