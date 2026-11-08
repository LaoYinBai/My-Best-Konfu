package com.baidu.mobads.sdk.api;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.baidu.mobads.sdk.internal.aj;
import com.baidu.mobads.sdk.internal.ba;
import com.baidu.mobads.sdk.internal.r;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class FeedPortraitVideoView extends RelativeLayout {
    private static final String FEED_CLICK = "pauseBtnClick";
    private static final String PLAY_END = "playCompletion";
    private static final String PLAY_ERROR = "playError";
    private static final String PLAY_PAUSE = "playPause";
    private static final String PLAY_RESUME = "playResume";
    private static final String PLAY_START = "playRenderingStart";
    private static final String TAG = "FeedPortraitVideoView";
    private View mAdView;
    private IFeedPortraitListener mFeedVideoListener;
    private ClassLoader mLoader;
    private String mRemoteClassName;
    private Context mViewContext;
    private boolean useDownloadFrame;

    public FeedPortraitVideoView(Context context) {
        this(context, null);
    }

    public FeedPortraitVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public FeedPortraitVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRemoteClassName = r.d;
        this.useDownloadFrame = false;
        init(context);
    }

    private void init(Context context) {
        this.mViewContext = context;
        Object[] objArr = {this.mViewContext};
        this.mLoader = ba.a(context);
        this.mAdView = (View) aj.a(this.mRemoteClassName, this.mLoader, (Class<?>[]) new Class[]{Context.class}, objArr);
        if (this.mAdView != null) {
            addView(this.mAdView, new RelativeLayout.LayoutParams(-1, -1));
        }
    }

    public void setFeedPortraitListener(IFeedPortraitListener iFeedPortraitListener) {
        this.mFeedVideoListener = iFeedPortraitListener;
        try {
            Class<?> clsA = aj.a("com.component.feed.IFeedPortraitListener", this.mLoader);
            if (clsA != null) {
                Object objNewProxyInstance = Proxy.newProxyInstance(clsA.getClassLoader(), new Class[]{clsA}, new InvocationHandlerImp());
                if (this.mAdView != null) {
                    aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setFeedPortraitListener", new Class[]{clsA}, objNewProxyInstance);
                }
            }
        } catch (Throwable unused) {
        }
    }

    public void setAdData(AbstractData abstractData) {
        if (abstractData == null || this.mAdView == null) {
            return;
        }
        if (abstractData instanceof NativeCPUAdData) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setCpuAdData", new Class[]{Object.class}, abstractData);
        } else {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setAdData", new Class[]{Object.class}, abstractData);
        }
    }

    public void showNormalPic(AbstractData abstractData) {
        if (abstractData == null || this.mAdView == null) {
            return;
        }
        aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "showNormalPic", new Class[]{Object.class}, abstractData);
    }

    public void play() {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "play", new Class[0], new Object[0]);
        }
    }

    public void pause() {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "pause", new Class[0], new Object[0]);
        }
    }

    public void resume() {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "resume", new Class[0], new Object[0]);
        }
    }

    public void stop() {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "stop", new Class[0], new Object[0]);
        }
    }

    public void setVideoMute(boolean z) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setVideoMute", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void seekTo(int i) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "seekTo", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setPlayBackSpeed(float f) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setPlayBackSpeed", new Class[]{Float.TYPE}, Float.valueOf(f));
        }
    }

    public void showFeedVideoCover(AbstractData abstractData) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "showFeedVideoCover", new Class[]{Object.class}, abstractData);
        }
    }

    public void hideFeedPauseBtn(AbstractData abstractData) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "hidePauseBtn", new Class[]{Object.class}, abstractData);
        }
    }

    public void hideFeedCoverPic(AbstractData abstractData) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "hideFeedCoverPic", new Class[]{Object.class}, abstractData);
        }
    }

    public void handleFeedCover(AbstractData abstractData) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "handleCover", new Class[]{Object.class}, abstractData);
        }
    }

    public boolean isPlaying() {
        if (this.mAdView != null) {
            return ((Boolean) aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "isPlaying", new Class[0], new Object[0])).booleanValue();
        }
        return false;
    }

    public long getCurrentPosition() {
        if (this.mAdView != null) {
            return ((Long) aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "getCurrentPosition", new Class[0], new Object[0])).longValue();
        }
        return 0L;
    }

    public long getDuration() {
        if (this.mAdView != null) {
            return ((Long) aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "getDuration", new Class[0], new Object[0])).longValue();
        }
        return 0L;
    }

    public void setCanClickVideo(boolean z) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setCanClickVideo", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public boolean isShowEndFrame() {
        if (this.mAdView != null) {
            return ((Boolean) aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "isShowEndFrame", new Class[0], new Object[0])).booleanValue();
        }
        return false;
    }

    public void setShowProgress(boolean z) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setShowProgressBar", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setProgressBackgroundColor(int i) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setProgressBackgroundColor", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setProgressBarColor(int i) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setProgressBarColor", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setProgressHeightInDp(int i) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setProgressHeightDp", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    class InvocationHandlerImp implements InvocationHandler {
        InvocationHandlerImp() {
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            String name = method.getName();
            if (TextUtils.isEmpty(name)) {
                return null;
            }
            if (name.equals(FeedPortraitVideoView.PLAY_END)) {
                if (FeedPortraitVideoView.this.mFeedVideoListener != null) {
                    FeedPortraitVideoView.this.mFeedVideoListener.playCompletion();
                }
            } else if (name.equals(FeedPortraitVideoView.PLAY_ERROR)) {
                if (FeedPortraitVideoView.this.mFeedVideoListener != null) {
                    FeedPortraitVideoView.this.mFeedVideoListener.playError();
                }
            } else if (name.equals(FeedPortraitVideoView.PLAY_START)) {
                if (FeedPortraitVideoView.this.mFeedVideoListener != null) {
                    FeedPortraitVideoView.this.mFeedVideoListener.playRenderingStart();
                }
            } else if (name.equals(FeedPortraitVideoView.PLAY_PAUSE)) {
                if (FeedPortraitVideoView.this.mFeedVideoListener != null) {
                    FeedPortraitVideoView.this.mFeedVideoListener.playPause();
                }
            } else if (name.equals(FeedPortraitVideoView.PLAY_RESUME)) {
                if (FeedPortraitVideoView.this.mFeedVideoListener != null) {
                    FeedPortraitVideoView.this.mFeedVideoListener.playResume();
                }
            } else if (name.equals(FeedPortraitVideoView.FEED_CLICK) && FeedPortraitVideoView.this.mFeedVideoListener != null) {
                FeedPortraitVideoView.this.mFeedVideoListener.pauseBtnClick();
            }
            return null;
        }
    }

    public void setUseDownloadFrame(boolean z) {
        if (this.mAdView != null) {
            aj.a(this.mRemoteClassName, this.mAdView, this.mLoader, "setUseDownloadFrame", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }
}
