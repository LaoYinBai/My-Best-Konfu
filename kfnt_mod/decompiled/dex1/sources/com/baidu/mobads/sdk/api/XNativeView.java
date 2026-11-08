package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.view.ViewCompat;
import com.baidu.mobads.sdk.api.NativeResponse;
import com.baidu.mobads.sdk.internal.bb;
import com.baidu.mobads.sdk.internal.bx;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class XNativeView extends RelativeLayout implements View.OnClickListener {
    private static final String TAG = "XNativeView";
    private bb mAdLogger;
    private XAdNativeResponse mCurrentNativeItem;
    private INativeVideoListener mFeedVideoListener;
    private INativeViewClickListener mNativeViewListener;
    private int mProgressBg;
    private int mProgressColor;
    private int mProgressHeight;
    private boolean mShowProgressBar;
    private FeedPortraitVideoView mVideoView;
    private boolean videoMute;

    public interface INativeViewClickListener {
        void onNativeViewClick(XNativeView xNativeView);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    public XNativeView(Context context) {
        this(context, null);
    }

    public XNativeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public XNativeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.videoMute = true;
        this.mProgressColor = -1;
        this.mProgressBg = ViewCompat.MEASURED_STATE_MASK;
        this.mShowProgressBar = false;
        this.mProgressHeight = 1;
        this.mAdLogger = bb.a();
        setBackgroundColor(Color.parseColor("#000000"));
    }

    public void setNativeItem(NativeResponse nativeResponse) {
        this.mCurrentNativeItem = (XAdNativeResponse) nativeResponse;
        renderView();
        initAdVideoView();
    }

    public void setNativeViewClickListener(INativeViewClickListener iNativeViewClickListener) {
        this.mNativeViewListener = iNativeViewClickListener;
    }

    public void setNativeVideoListener(INativeVideoListener iNativeVideoListener) {
        this.mFeedVideoListener = iNativeVideoListener;
    }

    public void setVideoMute(boolean z) {
        this.videoMute = z;
        if (this.mVideoView != null) {
            this.mVideoView.setVideoMute(this.videoMute);
        }
    }

    private void renderView() {
        if (this.mCurrentNativeItem == null) {
            return;
        }
        NativeResponse.MaterialType materialType = this.mCurrentNativeItem.getMaterialType();
        if (this.mVideoView == null) {
            this.mVideoView = new FeedPortraitVideoView(getContext());
            this.mVideoView.setShowProgress(this.mShowProgressBar);
            this.mVideoView.setProgressBarColor(this.mProgressColor);
            this.mVideoView.setProgressBackgroundColor(this.mProgressBg);
            this.mVideoView.setProgressHeightInDp(this.mProgressHeight);
            addView(this.mVideoView, new RelativeLayout.LayoutParams(-1, -1));
            this.mVideoView.setFeedPortraitListener(new IFeedPortraitListener() { // from class: com.baidu.mobads.sdk.api.XNativeView.1
                @Override // com.baidu.mobads.sdk.api.IFeedPortraitListener
                public void playCompletion() {
                    if (XNativeView.this.mFeedVideoListener != null) {
                        XNativeView.this.mFeedVideoListener.onCompletion();
                    }
                }

                @Override // com.baidu.mobads.sdk.api.IFeedPortraitListener
                public void playError() {
                    if (XNativeView.this.mFeedVideoListener != null) {
                        XNativeView.this.mFeedVideoListener.onError();
                    }
                }

                @Override // com.baidu.mobads.sdk.api.IFeedPortraitListener
                public void pauseBtnClick() {
                    XNativeView.this.callNativeViewClick();
                }

                @Override // com.baidu.mobads.sdk.api.IFeedPortraitListener
                public void playRenderingStart() {
                    if (XNativeView.this.mVideoView != null) {
                        XNativeView.this.mVideoView.hideFeedCoverPic(XNativeView.this.mCurrentNativeItem);
                    }
                    XNativeViewManager.getInstance().resetAllPlayer(XNativeView.this);
                    if (XNativeView.this.mFeedVideoListener != null) {
                        XNativeView.this.mFeedVideoListener.onRenderingStart();
                    }
                }

                @Override // com.baidu.mobads.sdk.api.IFeedPortraitListener
                public void playPause() {
                    if (XNativeView.this.mFeedVideoListener != null) {
                        XNativeView.this.mFeedVideoListener.onPause();
                    }
                }

                @Override // com.baidu.mobads.sdk.api.IFeedPortraitListener
                public void playResume() {
                    if (XNativeView.this.mFeedVideoListener != null) {
                        XNativeView.this.mFeedVideoListener.onResume();
                    }
                }
            });
        }
        if (materialType == NativeResponse.MaterialType.NORMAL) {
            this.mVideoView.showNormalPic(this.mCurrentNativeItem);
        } else if (materialType == NativeResponse.MaterialType.VIDEO) {
            showView(this.mCurrentNativeItem);
        }
    }

    public void pause() {
        if (this.mVideoView != null) {
            this.mVideoView.pause();
        }
    }

    public void resume() {
        if (this.mVideoView != null) {
            this.mVideoView.resume();
        }
    }

    public void render() {
        if (shouldAutoPlay()) {
            play();
        }
    }

    public void stop() {
        if (this.mVideoView != null) {
            this.mVideoView.stop();
        }
    }

    private void play() {
        if (this.mVideoView != null) {
            initAdVideoView();
            this.mVideoView.hideFeedPauseBtn(this.mCurrentNativeItem);
            this.mVideoView.play();
        }
    }

    private boolean shouldAutoPlay() {
        boolean z = this.mCurrentNativeItem != null && this.mCurrentNativeItem.isAutoPlay();
        boolean z2 = this.mCurrentNativeItem == null || this.mCurrentNativeItem.isNonWifiAutoPlay();
        Boolean boolA = bx.a().a(getContext().getApplicationContext());
        if (z && boolA.booleanValue()) {
            return true;
        }
        return z2 && !boolA.booleanValue();
    }

    public void setShowProgress(boolean z) {
        this.mShowProgressBar = z;
        if (this.mVideoView != null) {
            this.mVideoView.setShowProgress(z);
        }
    }

    public void setProgressBackgroundColor(int i) {
        this.mProgressBg = i;
        if (this.mVideoView != null) {
            this.mVideoView.setProgressBackgroundColor(i);
        }
    }

    public void setProgressBarColor(int i) {
        this.mProgressColor = i;
        if (this.mVideoView != null) {
            this.mVideoView.setProgressBarColor(i);
        }
    }

    public void setProgressHeightInDp(int i) {
        this.mProgressHeight = i;
        if (this.mVideoView != null) {
            this.mVideoView.setProgressHeightInDp(i);
        }
    }

    private void showView(XAdNativeResponse xAdNativeResponse) {
        if (this.mVideoView == null) {
            return;
        }
        this.mVideoView.showFeedVideoCover(xAdNativeResponse);
    }

    public void handleCover() {
        if (this.mVideoView == null) {
            return;
        }
        this.mVideoView.handleFeedCover(this.mCurrentNativeItem);
    }

    private void initAdVideoView() {
        if (this.mVideoView == null) {
            return;
        }
        this.mVideoView.setCanClickVideo(true);
        this.mVideoView.setAdData(this.mCurrentNativeItem);
        this.mVideoView.setVideoMute(this.videoMute);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        XNativeViewManager.getInstance().removeNativeView(this);
        if (this.mCurrentNativeItem != null) {
            this.mCurrentNativeItem.clearImpressionTaskWhenBack();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        XNativeViewManager.getInstance().addItem(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void callNativeViewClick() {
        if (this.mNativeViewListener != null) {
            this.mNativeViewListener.onNativeViewClick(this);
        }
    }

    private boolean isVisible(View view, int i) {
        if (view == null || view.getVisibility() != 0 || view.getParent() == null) {
            return false;
        }
        if (!view.getGlobalVisibleRect(new Rect())) {
            return false;
        }
        long height = view.getHeight() * view.getWidth();
        return height > 0 && (r1.height() * r1.width()) * 100 >= ((long) i) * height;
    }

    public void setUseDownloadFrame(boolean z) {
        if (this.mVideoView != null) {
            this.mVideoView.setUseDownloadFrame(z);
        }
    }
}
