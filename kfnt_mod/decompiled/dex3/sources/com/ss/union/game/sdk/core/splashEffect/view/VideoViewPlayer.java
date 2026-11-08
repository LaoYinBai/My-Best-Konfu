package com.ss.union.game.sdk.core.splashEffect.view;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.VideoView;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import com.ss.union.game.sdk.core.splashEffect.util.TimeCollector;
import com.ss.union.game.sdk.core.splashEffect.view.a.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VideoViewPlayer extends FrameLayout implements b {
    private VideoView a;
    private com.ss.union.game.sdk.core.splashEffect.view.a.a b;
    private MediaPlayer c;

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public View getView() {
        return this;
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public void setAssetName(String str) {
    }

    public VideoViewPlayer(Context context) {
        this(context, null);
    }

    public VideoViewPlayer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoViewPlayer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        setBackgroundColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        this.a = new SSVideoView(context);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
        layoutParams.gravity = 17;
        addView(this.a, layoutParams);
        b(context);
    }

    private void b(Context context) {
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        setOnKeyListener(new View.OnKeyListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.VideoViewPlayer.1
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return false;
                }
                com.ss.union.game.sdk.core.splashEffect.a.a.b("成功拦截返回键");
                return true;
            }
        });
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public void setUri(String str) {
        this.a.setVideoURI(Uri.parse(str));
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public void setMediaPlayerListener(final com.ss.union.game.sdk.core.splashEffect.view.a.a aVar) {
        this.b = aVar;
        this.a.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.VideoViewPlayer.2
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) {
                VideoViewPlayer.this.c = mediaPlayer;
                TimeCollector.effect().endAll("视频准备好了");
                aVar.b(mediaPlayer);
            }
        });
        this.a.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.VideoViewPlayer.3
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                VideoViewPlayer.this.d();
                aVar.a(mediaPlayer);
            }
        });
        this.a.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.VideoViewPlayer.4
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                VideoViewPlayer.this.d();
                return aVar.a(mediaPlayer, i, i2);
            }
        });
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public void a() {
        this.a.start();
        b();
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public void c() {
        this.a.pause();
    }

    public void b() {
        final Application application = (Application) getContext().getApplicationContext();
        application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.ss.union.game.sdk.core.splashEffect.view.VideoViewPlayer.5
            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityCreated(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityDestroyed(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStarted(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityResumed(Activity activity) {
                application.unregisterActivityLifecycleCallbacks(this);
                if (VideoViewPlayer.this.b != null) {
                    VideoViewPlayer.this.b.a(VideoViewPlayer.this.c);
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityPaused(Activity activity) {
                try {
                    VideoViewPlayer.this.a.pause();
                    VideoViewPlayer videoViewPlayer = VideoViewPlayer.this;
                    ViewParent parent = VideoViewPlayer.this.getParent();
                    if (parent instanceof ViewGroup) {
                        videoViewPlayer.setVisibility(8);
                        ((ViewGroup) parent).removeView(videoViewPlayer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
