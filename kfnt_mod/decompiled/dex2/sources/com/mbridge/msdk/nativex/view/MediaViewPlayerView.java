package com.mbridge.msdk.nativex.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import com.mbridge.msdk.foundation.same.c.c;
import com.mbridge.msdk.foundation.tools.m;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.foundation.tools.z;
import com.mbridge.msdk.out.Campaign;
import com.mbridge.msdk.playercommon.VideoNativePlayer;
import com.mbridge.msdk.playercommon.VideoPlayerStatusListener;
import com.mbridge.msdk.playercommon.exoplayer2.text.ttml.TtmlNode;
import com.mbridge.msdk.videocommon.view.MyImageView;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class MediaViewPlayerView extends LinearLayout implements VideoPlayerStatusListener {
    private static Handler F = new Handler();
    private String A;
    private String B;
    private CampaignEx C;
    private com.mbridge.msdk.nativex.listener.a D;
    private Timer E;
    private com.mbridge.msdk.videocommon.download.a G;
    private VideoNativePlayer H;
    private a I;
    private boolean J;
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private boolean j;
    private boolean k;
    private volatile int l;
    private boolean m;
    private boolean n;
    private TextureView o;
    private LinearLayout p;
    private Surface q;
    private ProgressBar r;
    private MyImageView s;
    private ImageView t;
    private ImageView u;
    private ImageView v;
    private ImageView w;
    private View x;
    private AnimationDrawable y;
    private AlphaAnimation z;

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onBufferingEnd() {
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onBufferingStart(String str) {
    }

    public void setMediaViewPlayListener(a aVar) {
        this.I = aVar;
    }

    public MediaViewPlayerView(Context context) {
        super(context);
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = true;
        this.i = false;
        this.j = true;
        this.k = false;
        this.l = -1;
        this.m = true;
        this.n = true;
        this.J = false;
        a();
    }

    public MediaViewPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = true;
        this.i = false;
        this.j = true;
        this.k = false;
        this.l = -1;
        this.m = true;
        this.n = true;
        this.J = false;
        a();
    }

    private void a() {
        try {
            c();
            b();
        } catch (Exception e) {
            s.d("MediaViewPlayerView", e.getMessage());
        }
    }

    private void b() {
        this.H = new VideoNativePlayer();
        this.H.setSelfVideoFeedsPlayerListener(this);
    }

    private void c() {
        try {
            View viewInflate = LayoutInflater.from(getContext()).inflate(m.a(getContext(), "mbridge_nativex_playerview", TtmlNode.TAG_LAYOUT), (ViewGroup) null);
            if (viewInflate != null) {
                this.p = (LinearLayout) viewInflate.findViewById(m.a(getContext(), "mbridge_ll_loading", "id"));
                this.o = (TextureView) viewInflate.findViewById(m.a(getContext(), "mbridge_textureview", "id"));
                this.o.setKeepScreenOn(true);
                this.o.setSurfaceTextureListener(new b());
                this.r = (ProgressBar) viewInflate.findViewById(m.a(getContext(), "mbridge_progress", "id"));
                this.s = (MyImageView) viewInflate.findViewById(m.a(getContext(), "mbridge_iv_playend_pic", "id"));
                this.t = (ImageView) viewInflate.findViewById(m.a(getContext(), "mbridge_iv_play", "id"));
                this.u = (ImageView) viewInflate.findViewById(m.a(getContext(), "mbridge_iv_pause", "id"));
                this.v = (ImageView) viewInflate.findViewById(m.a(getContext(), "mbridge_iv_sound", "id"));
                this.x = viewInflate.findViewById(m.a(getContext(), "mbridge_view_cover", "id"));
                this.w = (ImageView) viewInflate.findViewById(m.a(getContext(), "mbridge_iv_sound_animation", "id"));
                this.y = (AnimationDrawable) this.w.getDrawable();
                this.y.start();
                d();
                addView(viewInflate, -1, -1);
            }
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    public boolean initPlayerViewData(String str, CampaignEx campaignEx, boolean z, VideoPlayerStatusListener videoPlayerStatusListener, com.mbridge.msdk.videocommon.download.a aVar, String str2) {
        try {
        } catch (Exception e) {
            s.d("MediaViewPlayerView", e.getMessage());
            this.a = false;
        }
        if (TextUtils.isEmpty(str)) {
            s.b("MediaViewPlayerView", "playUrl==null return ");
            return false;
        }
        if (campaignEx == null) {
            s.b("MediaViewPlayerView", "campaign ==null return ");
            return false;
        }
        this.B = str;
        this.b = z;
        this.C = campaignEx;
        this.G = aVar;
        this.A = str2;
        this.H.initParameter(this.C.getVideoUrlEncode(), true, this.j, this.s, videoPlayerStatusListener);
        try {
            if (this.C != null) {
                String imageUrl = this.C.getImageUrl();
                if (z.a(imageUrl)) {
                    s.b("MediaViewPlayerView", "imageUrl isnull initPlayEndPic return");
                } else if (getContext() != null) {
                    if (com.mbridge.msdk.foundation.same.c.b.a(getContext()).b(imageUrl)) {
                        Bitmap bitmapA = com.mbridge.msdk.foundation.same.c.b.a(com.mbridge.msdk.foundation.controller.a.f().j()).a(imageUrl);
                        if (this.s != null && bitmapA != null) {
                            this.s.setImageUrl(imageUrl);
                            this.s.setImageBitmap(bitmapA);
                            this.s.setVisibility(0);
                        }
                    } else {
                        com.mbridge.msdk.foundation.same.c.b.a(getContext()).a(imageUrl, new c() { // from class: com.mbridge.msdk.nativex.view.MediaViewPlayerView.6
                            @Override // com.mbridge.msdk.foundation.same.c.c
                            public final void onFailedLoad(String str3, String str4) {
                            }

                            @Override // com.mbridge.msdk.foundation.same.c.c
                            public final void onSuccessLoad(Bitmap bitmap, String str3) {
                                if (MediaViewPlayerView.this.s == null || bitmap == null) {
                                    return;
                                }
                                MediaViewPlayerView.this.s.setImageUrl(str3);
                                MediaViewPlayerView.this.s.setImageBitmap(bitmap);
                            }
                        });
                    }
                }
            }
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
        this.a = true;
        return true;
    }

    public void playVideo() {
        try {
            if (!this.a) {
                s.b("MediaViewPlayerView", "playVideo() init failed 播放失败");
                return;
            }
            if (this.H == null) {
                s.b("MediaViewPlayerView", "playVideo() mVideoFeedsPlayer is null 播放失败");
                return;
            }
            if (!this.g) {
                e();
                s.b("MediaViewPlayerView", "playVideo() mSurfaceTextureAvailable no init return");
                return;
            }
            if ((!TextUtils.isEmpty(this.B) && this.B.startsWith("http")) || this.B.startsWith("https")) {
                this.B = m();
            }
            s.b("MediaViewPlayerView", "playVideo() play");
            f();
            this.H.play(getContext(), this.B, this.q);
            if (this.f || this.e) {
                this.f = false;
            }
            if (this.k) {
                this.H.openSound();
            } else {
                this.H.closeSound();
            }
            this.c = false;
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    public void startOrPlayVideo() {
        try {
            s.b("MediaViewPlayerView", "startOrPlayVideo() mIsNeedToRepeatPrepare:" + this.c + " mhasprepare:" + hasPrepare());
            if (!this.g) {
                e();
                s.b("MediaViewPlayerView", "startOrPlayVideo() mSurfaceTextureAvailable no init return");
                return;
            }
            if (!hasPrepare()) {
                s.b("MediaViewPlayerView", "startOrPlayVideo() playVideo");
                playVideo();
                return;
            }
            s.b("MediaViewPlayerView", "startOrPlayVideo() start");
            try {
                if (this.H == null) {
                    s.b("MediaViewPlayerView", "start() mVideoFeedsPlayer is null return");
                    return;
                }
                f();
                if (this.i) {
                    s.d("MediaViewPlayerView", "start() startOrPlayVideo need setSurface final");
                    this.H.start(this.q);
                    this.i = false;
                } else {
                    s.b("MediaViewPlayerView", "start() startOrPlayVideo final");
                    this.H.start();
                }
                if (this.f || this.e) {
                    this.f = false;
                }
            } catch (Throwable th) {
                s.b("MediaViewPlayerView", th.getMessage(), th);
            }
        } catch (Exception e) {
            s.d("MediaViewPlayerView", e.getMessage());
        }
    }

    public void pause() {
        try {
            if (this.H != null) {
                this.H.pause();
                this.f = true;
            }
            h();
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    public void stop() {
        try {
            if (this.H != null) {
                this.H.stop();
            }
            h();
        } catch (Exception e) {
            s.d("MediaViewPlayerView", e.getMessage());
        }
    }

    public void release() {
        try {
            if (this.H != null) {
                this.H.releasePlayer();
                this.H = null;
            }
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    public boolean hasPrepare() {
        try {
            if (this.H != null) {
                return this.H.hasPrepare();
            }
            return false;
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
            return false;
        }
    }

    public boolean isComplete() {
        try {
            if (this.H != null) {
                return this.H.isComplete();
            }
            return false;
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
            return false;
        }
    }

    public boolean isPlaying() {
        try {
            if (this.H != null) {
                return this.H.isPlaying();
            }
            return false;
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
            return false;
        }
    }

    public void openSound() {
        this.k = true;
        try {
            if (this.H != null) {
                this.v.setImageResource(m.a(getContext(), "mbridge_nativex_sound_open", "drawable"));
                this.H.openSound();
            }
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    public void closeSound() {
        this.k = false;
        try {
            if (this.H != null) {
                this.v.setImageResource(m.a(getContext(), "mbridge_nativex_sound_close", "drawable"));
                this.H.closeSound();
            }
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    public void setIsFrontDesk(boolean z) {
        try {
            if (this.H != null) {
                this.H.setIsFrontDesk(z);
            }
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    public boolean halfLoadingViewisVisible() {
        try {
            if (this.H != null) {
                return this.H.loadingViewIsVisible();
            }
            return false;
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
            return false;
        }
    }

    public void setIsComplete(boolean z) {
        try {
            if (this.H != null) {
                this.H.setIsComplete(z);
            }
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    public void setEnterFullScreen() {
        try {
            s.b("MediaViewPlayerView", "setEnterFullScreen");
            this.d = true;
            this.h = true;
            this.v.setVisibility(0);
            h();
        } catch (Throwable th) {
            s.b("MediaViewPlayerView", th.getMessage(), th);
        }
    }

    public void setExitFullScreen() {
        try {
            s.b("MediaViewPlayerView", "setExitFullScreen");
            this.d = false;
            this.c = false;
            s.b("MediaViewPlayerView", "setExitFullScreen mIsNeedToRepeatPrepare=false");
            this.v.setVisibility(8);
            g();
            f();
        } catch (Throwable th) {
            s.b("MediaViewPlayerView", th.getMessage(), th);
        }
    }

    public boolean curIsFullScreen() {
        return this.d;
    }

    public void setIsActivePause(boolean z) {
        this.e = z;
    }

    public boolean getIsActiviePause() {
        return this.e;
    }

    public Campaign getCampaign() {
        return this.C;
    }

    public void setAllowLoopPlay(boolean z) {
        this.j = z;
    }

    public void setOnMediaViewPlayerViewListener(com.mbridge.msdk.nativex.listener.a aVar) {
        this.D = aVar;
    }

    private void d() {
        this.v.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.nativex.view.MediaViewPlayerView.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                try {
                    if (MediaViewPlayerView.this.k) {
                        MediaViewPlayerView.this.closeSound();
                        if (MediaViewPlayerView.this.D != null) {
                            MediaViewPlayerView.this.D.a();
                        }
                    } else {
                        MediaViewPlayerView.this.openSound();
                        if (MediaViewPlayerView.this.D != null) {
                            MediaViewPlayerView.this.D.b();
                        }
                    }
                } catch (Throwable th) {
                    s.d("MediaViewPlayerView", th.getMessage());
                }
            }
        });
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.nativex.view.MediaViewPlayerView.2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                try {
                    MediaViewPlayerView.this.pause();
                    MediaViewPlayerView.this.t.setVisibility(0);
                    MediaViewPlayerView.this.i();
                    MediaViewPlayerView.this.k();
                    if (MediaViewPlayerView.this.D != null) {
                        MediaViewPlayerView.this.D.c();
                    }
                    MediaViewPlayerView.this.e = true;
                } catch (Throwable th) {
                    s.b("MediaViewPlayerView", th.getMessage(), th);
                }
            }
        });
        this.t.setOnClickListener(new View.OnClickListener() { // from class: com.mbridge.msdk.nativex.view.MediaViewPlayerView.3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MediaViewPlayerView.this.onClickPlayButton();
            }
        });
    }

    public void onClickPlayButton() {
        try {
            f();
            j();
            setIsComplete(false);
            if (this.f) {
                this.H.play();
            } else if (hasPrepare() && !this.c) {
                s.b("MediaViewPlayerView", "startOrPlayVideo() hasPrepare():" + hasPrepare() + " mIsNeedToRepeatPrepare:" + this.c);
                startOrPlayVideo();
            } else {
                s.b("MediaViewPlayerView", "点击播放 playVideo()");
                playVideo();
            }
            if (this.e && this.D != null) {
                this.D.d();
            }
            this.e = false;
        } catch (Throwable th) {
            s.b("MediaViewPlayerView", th.getMessage(), th);
        }
    }

    public void onClickPlayerView() {
        try {
            if (this.s != null && this.s.getVisibility() == 0) {
                s.b("MediaViewPlayerView", "playend is visibility return");
                return;
            }
            if (!isPlaying()) {
                s.b("MediaViewPlayerView", "isplaying return");
                return;
            }
            if (this.u == null) {
                s.b("MediaViewPlayerView", "pause id is null return");
                return;
            }
            if (this.u.getVisibility() == 0) {
                s.b("MediaViewPlayerView", "gone durview");
                gonePauseView();
                l();
                return;
            }
            s.b("MediaViewPlayerView", "show durview");
            if (this.z != null) {
                this.z.cancel();
            }
            this.z = new AlphaAnimation(0.0f, 1.0f);
            this.z.setDuration(300L);
            this.z.setInterpolator(new DecelerateInterpolator());
            this.z.setAnimationListener(new Animation.AnimationListener() { // from class: com.mbridge.msdk.nativex.view.MediaViewPlayerView.4
                @Override // android.view.animation.Animation.AnimationListener
                public final void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public final void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public final void onAnimationEnd(Animation animation) {
                    MediaViewPlayerView.this.u.setVisibility(0);
                    MediaViewPlayerView.g(MediaViewPlayerView.this);
                }
            });
            i();
            this.x.startAnimation(this.z);
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    public void showPlayView() {
        this.t.setVisibility(0);
    }

    public void gonePauseView() {
        if (F != null) {
            F.post(new Runnable() { // from class: com.mbridge.msdk.nativex.view.MediaViewPlayerView.5
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        MediaViewPlayerView.this.k();
                        MediaViewPlayerView.this.j();
                        s.b("MediaViewPlayerView", "gonePauseView");
                    } catch (Exception e) {
                        s.d("MediaViewPlayerView", e.getMessage());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        try {
            this.s.setVisibility(0);
            this.t.setVisibility(0);
            i();
            k();
            this.r.setVisibility(8);
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    private void f() {
        try {
            this.s.setVisibility(8);
            this.t.setVisibility(8);
            j();
            showProgressView(this.n);
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    private void g() {
        if (this.d || this.w == null || this.w.getVisibility() == 0 || !this.m) {
            return;
        }
        this.w.setVisibility(0);
    }

    private void h() {
        if (this.w.getVisibility() == 0) {
            this.w.setVisibility(8);
        }
    }

    public void showProgressView(boolean z) {
        this.n = z;
        if (this.r != null) {
            this.r.setVisibility(z ? 0 : 4);
        }
    }

    public void showSoundIndicator(boolean z) {
        this.m = z;
        if (this.m) {
            g();
        } else {
            h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.x.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (this.t.getVisibility() != 0) {
            this.x.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.u.setVisibility(8);
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlayStarted(int i) {
        if (this.D == null || TextUtils.isEmpty(this.B)) {
            return;
        }
        this.D.a(this.B);
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlayProgress(int i, int i2) {
        try {
            f();
            g();
            this.e = false;
            this.c = false;
            this.l = i;
        } catch (Throwable th) {
            s.b("MediaViewPlayerView", th.getMessage(), th);
        }
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlayCompleted() {
        s.b("MediaViewPlayerView", "=========onPlayCompleted");
        if (this.D != null && !TextUtils.isEmpty(this.B)) {
            this.D.b(this.B);
        }
        if (this.I != null) {
            this.I.a();
            return;
        }
        try {
            if (this.j) {
                return;
            }
            s.b("MediaViewPlayerView", "播放结束 不能循环播放 显示endcardView");
            e();
        } catch (Exception e) {
            s.d("MediaViewPlayerView", e.getMessage());
        }
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlayError(String str) {
        try {
            s.b("MediaViewPlayerView", "onPlayError:" + str);
            this.c = true;
            e();
            if (this.J) {
                return;
            }
            this.H.play(getContext(), this.B, this.q);
            this.J = true;
        } catch (Exception e) {
            s.d("MediaViewPlayerView", e.getMessage());
        }
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlaySetDataSourceError(String str) {
        try {
            s.b("MediaViewPlayerView", "onPlaySetDataSourceError:" + str);
            this.c = true;
            try {
                if (URLUtil.isNetworkUrl(this.B)) {
                    s.b("MediaViewPlayerView", "playerview  dwLocalAddressplayError playurl is network return");
                } else {
                    String videoUrlEncode = this.C.getVideoUrlEncode();
                    if (z.b(videoUrlEncode)) {
                        this.B = videoUrlEncode;
                        s.b("MediaViewPlayerView", "playerview dwLocalAddressplayError 用网络地址抄底播放" + videoUrlEncode);
                    }
                }
            } catch (Throwable th) {
                s.d("MediaViewPlayerView", th.getMessage());
            }
        } catch (Exception e) {
            s.d("MediaViewPlayerView", e.getMessage());
        }
    }

    private void l() {
        try {
            if (F != null) {
                F.removeCallbacksAndMessages(null);
            }
            if (this.E != null) {
                this.E.cancel();
            }
        } catch (Exception e) {
            s.d("MediaViewPlayerView", e.getMessage());
        }
    }

    private String m() {
        String str;
        try {
        } catch (Exception e) {
            s.d("MediaViewPlayerView", e.getMessage());
        }
        if (this.C == null) {
            return null;
        }
        try {
            if (this.G == null) {
                if (this.C.getAdType() == 287 || this.C.getAdType() == 94) {
                    str = this.C.getRequestId() + this.C.getId() + this.C.getVideoUrlEncode();
                } else {
                    str = this.C.getId() + this.C.getVideoUrlEncode() + this.C.getBidToken();
                }
                this.G = com.mbridge.msdk.videocommon.download.b.getInstance().a(this.A, str);
            }
        } catch (Exception e2) {
            s.d("MediaViewPlayerView", e2.getMessage());
        }
        if (this.G != null) {
            int iF = this.G.f();
            s.d("MediaViewPlayerView", "本地已下载完 拿本地播放地址：Jinr state：" + iF);
            if (iF == 5) {
                String strD = this.G.d();
                if (new File(strD).exists()) {
                    s.d("MediaViewPlayerView", "本地已下载完 拿本地播放地址：" + strD + " state：" + iF);
                    return strD;
                }
            }
        }
        String videoUrlEncode = this.C.getVideoUrlEncode();
        if (z.b(videoUrlEncode)) {
            s.b("MediaViewPlayerView", "本地尚未下载完 拿网络地址：" + videoUrlEncode);
            return videoUrlEncode;
        }
        return null;
    }

    public static class a {
        private MediaViewPlayerView a;

        public a(MediaViewPlayerView mediaViewPlayerView) {
            this.a = mediaViewPlayerView;
        }

        public void a() {
            try {
                s.b("MediaViewPlayerView", "=========onPlayCompleted");
                if (this.a == null || this.a.j) {
                    return;
                }
                s.b("MediaViewPlayerView", "播放结束 不能循环播放 显示endcardView");
                this.a.e();
            } catch (Exception e) {
                s.d("MediaViewPlayerView", e.getMessage());
            }
        }
    }

    private class b implements TextureView.SurfaceTextureListener {
        @Override // android.view.TextureView.SurfaceTextureListener
        public final void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        private b() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public final void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("onSurfaceTextureAvailable 进来: ");
                sb.append(MediaViewPlayerView.this.C == null ? "appname" : MediaViewPlayerView.this.C.getAppName());
                sb.append(" url:");
                sb.append(MediaViewPlayerView.this.B);
                s.d("MediaViewPlayerView", sb.toString());
                MediaViewPlayerView.this.q = new Surface(surfaceTexture);
                MediaViewPlayerView.this.g = true;
                MediaViewPlayerView.this.i = true;
                if (MediaViewPlayerView.this.d) {
                    s.b("MediaViewPlayerView", "onSurfaceTextureAvailable 在全屏");
                    if (MediaViewPlayerView.this.h) {
                        MediaViewPlayerView.this.c = false;
                        MediaViewPlayerView.this.h = false;
                        s.b("MediaViewPlayerView", "onSurfaceTextureAvailable 在全屏 mIsNeedToRepeatPrepare置为false");
                    }
                    if (!MediaViewPlayerView.this.isComplete() && !MediaViewPlayerView.this.e) {
                        s.b("MediaViewPlayerView", "onSurfaceTextureAvailable 在全屏 startOrPlayVideo");
                        MediaViewPlayerView.this.startOrPlayVideo();
                        return;
                    }
                    s.b("MediaViewPlayerView", "onSurfaceTextureAvailable 在全屏 showPlayEndView");
                    MediaViewPlayerView.this.e();
                    return;
                }
                s.b("MediaViewPlayerView", "onSurfaceTextureAvailable 在半屏");
                if (MediaViewPlayerView.this.b) {
                    if (MediaViewPlayerView.this.hasPrepare() && MediaViewPlayerView.this.isComplete()) {
                        s.b("MediaViewPlayerView", "onSurfaceTextureAvailable 在半屏 自动播放showPlayEndView hasPrepare():" + MediaViewPlayerView.this.hasPrepare() + " isComplete:" + MediaViewPlayerView.this.isComplete());
                        MediaViewPlayerView.this.e();
                        return;
                    }
                    s.b("MediaViewPlayerView", "onSurfaceTextureAvailable 在半屏 自动播放startOrPlayVideo hasPrepare():" + MediaViewPlayerView.this.hasPrepare() + " isComplete:" + MediaViewPlayerView.this.isComplete());
                    MediaViewPlayerView.this.startOrPlayVideo();
                    return;
                }
                if (MediaViewPlayerView.this.hasPrepare() && !MediaViewPlayerView.this.isComplete()) {
                    s.b("MediaViewPlayerView", "onSurfaceTextureAvailable 在半屏 startOrPlayVideo hasPrepare():" + MediaViewPlayerView.this.hasPrepare() + " isComplete:" + MediaViewPlayerView.this.isComplete());
                    MediaViewPlayerView.this.startOrPlayVideo();
                    return;
                }
                s.b("MediaViewPlayerView", "onSurfaceTextureAvailable 在半屏 点击播放showPlayEndView hasPrepare():" + MediaViewPlayerView.this.hasPrepare() + " isComplete:" + MediaViewPlayerView.this.isComplete());
                MediaViewPlayerView.this.e();
            } catch (Exception e) {
                s.d("MediaViewPlayerView", e.getMessage());
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public final void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            s.b("MediaViewPlayerView", "onSurfaceTextureSizeChanged ");
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public final boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            try {
                StringBuilder sb = new StringBuilder();
                sb.append("onSurfaceTextureDestroyed:");
                sb.append(MediaViewPlayerView.this.C == null ? "appname" : MediaViewPlayerView.this.C.getAppName());
                s.d("MediaViewPlayerView", sb.toString());
                if (MediaViewPlayerView.this.H != null && MediaViewPlayerView.this.H.isPlayIng()) {
                    MediaViewPlayerView.this.pause();
                }
                MediaViewPlayerView.this.c = true;
                MediaViewPlayerView.this.g = false;
            } catch (Throwable th) {
                s.d("MediaViewPlayerView", th.getMessage());
            }
            return true;
        }
    }

    @Override // com.mbridge.msdk.playercommon.VideoPlayerStatusListener
    public void onPlayProgressMS(int i, int i2) {
        try {
            if (this.r == null || this.r.getVisibility() != 0) {
                return;
            }
            if (i2 > 0) {
                this.r.setMax(i2);
            }
            if (i >= 0) {
                this.r.setProgress(i + 1);
            }
        } catch (Throwable th) {
            s.d("MediaViewPlayerView", th.getMessage());
        }
    }

    static /* synthetic */ void g(MediaViewPlayerView mediaViewPlayerView) {
        mediaViewPlayerView.l();
        mediaViewPlayerView.E = new Timer();
        mediaViewPlayerView.E.schedule(new TimerTask() { // from class: com.mbridge.msdk.nativex.view.MediaViewPlayerView.7
            @Override // java.util.TimerTask, java.lang.Runnable
            public final void run() {
                try {
                    MediaViewPlayerView.this.gonePauseView();
                } catch (Throwable th) {
                    s.b("MediaViewPlayerView", th.getMessage(), th);
                }
            }
        }, 2000L);
    }
}
