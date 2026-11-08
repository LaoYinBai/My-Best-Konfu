package com.kwad.sdk.core.video.videoview;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.net.NetworkInfo;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.video.kwai.c;
import com.kwad.sdk.core.view.AdBasePvFrameLayout;
import com.kwad.sdk.utils.as;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends AdBasePvFrameLayout implements TextureView.SurfaceTextureListener, d {
    private static AtomicBoolean o = new AtomicBoolean(false);
    private BroadcastReceiver A;
    private int a;
    private Context b;
    private AudioManager c;
    private com.kwad.sdk.core.video.kwai.c d;
    private FrameLayout e;
    private com.kwad.sdk.core.video.a f;
    private c g;
    private SurfaceTexture h;
    private Surface i;
    private String j;
    private Map<String, String> k;
    private int l;
    private boolean m;
    private long n;
    private boolean p;
    private boolean q;
    private com.kwad.sdk.contentalliance.detail.video.c r;
    private com.kwad.sdk.contentalliance.detail.video.b s;
    private ImageView t;
    private c.e u;
    private c.h v;
    private c.b w;
    private c.InterfaceC0046c x;
    private c.d y;
    private c.a z;

    public b(Context context) {
        this(context, null);
    }

    public b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 0;
        this.m = false;
        this.p = false;
        this.q = false;
        this.u = new c.e() { // from class: com.kwad.sdk.core.video.videoview.b.1
            @Override // com.kwad.sdk.core.video.kwai.c.e
            public void a(com.kwad.sdk.core.video.kwai.c cVar) {
                b.this.a = 2;
                b.this.g.a(b.this.a);
                com.kwad.sdk.core.d.a.c("KSVideoPlayerViewView", "onPrepared ——> STATE_PREPARED");
                cVar.f();
                if (b.this.m) {
                    cVar.a((int) as.a(b.this.b, b.this.j));
                }
                if (b.this.n != 0) {
                    cVar.a((int) b.this.n);
                }
            }
        };
        this.v = new c.h() { // from class: com.kwad.sdk.core.video.videoview.b.2
            @Override // com.kwad.sdk.core.video.kwai.c.h
            public void a(com.kwad.sdk.core.video.kwai.c cVar, int i, int i2) {
                if (!b.this.q || i2 <= i) {
                    b.this.f.a(i, i2);
                    com.kwad.sdk.core.d.a.c("KSVideoPlayerViewView", "onVideoSizeChanged ——> width：" + i + "， height：" + i2);
                }
            }
        };
        this.w = new c.b() { // from class: com.kwad.sdk.core.video.videoview.b.3
            @Override // com.kwad.sdk.core.video.kwai.c.b
            public void a(com.kwad.sdk.core.video.kwai.c cVar) {
                if (b.this.a != 9) {
                    b.this.a = 9;
                    b.this.g.a(b.this.a);
                    com.kwad.sdk.core.d.a.c("KSVideoPlayerViewView", "onCompletion ——> STATE_COMPLETED");
                    b.this.e.setKeepScreenOn(false);
                }
            }
        };
        this.x = new c.InterfaceC0046c() { // from class: com.kwad.sdk.core.video.videoview.b.4
            @Override // com.kwad.sdk.core.video.kwai.c.InterfaceC0046c
            public boolean a(com.kwad.sdk.core.video.kwai.c cVar, int i, int i2) {
                if (i == -38) {
                    return true;
                }
                b.this.a = -1;
                b.this.g.a(i, i2);
                b.this.g.a(b.this.a);
                com.kwad.sdk.core.d.a.c("KSVideoPlayerViewView", "onError ——> STATE_ERROR ———— what：" + i + ", extra: " + i2);
                return true;
            }
        };
        this.y = new c.d() { // from class: com.kwad.sdk.core.video.videoview.b.5
            @Override // com.kwad.sdk.core.video.kwai.c.d
            public boolean a(com.kwad.sdk.core.video.kwai.c cVar, int i, int i2) {
                String str;
                String str2;
                String str3;
                String str4;
                if (i == 3) {
                    b.this.a = 4;
                    b.this.g.a(b.this.a);
                    str = "KSVideoPlayerViewView";
                    str2 = "onInfo ——> MEDIA_INFO_VIDEO_RENDERING_START：STATE_PLAYING";
                } else {
                    if (i == 701) {
                        if (b.this.a == 5 || b.this.a == 7) {
                            b.this.a = 7;
                            str3 = "KSVideoPlayerViewView";
                            str4 = "onInfo ——> MEDIA_INFO_BUFFERING_START：STATE_BUFFERING_PAUSED";
                        } else {
                            b.this.a = 6;
                            str3 = "KSVideoPlayerViewView";
                            str4 = "onInfo ——> MEDIA_INFO_BUFFERING_START：STATE_BUFFERING_PLAYING";
                        }
                        com.kwad.sdk.core.d.a.c(str3, str4);
                        b.this.g.a(b.this.a);
                        return true;
                    }
                    if (i == 702) {
                        if (b.this.a == 6) {
                            b.this.a = 4;
                            b.this.g.a(b.this.a);
                            com.kwad.sdk.core.d.a.c("KSVideoPlayerViewView", "onInfo ——> MEDIA_INFO_BUFFERING_END： STATE_PLAYING");
                        }
                        if (b.this.a != 7) {
                            return true;
                        }
                        b.this.a = 5;
                        b.this.g.a(b.this.a);
                        str = "KSVideoPlayerViewView";
                        str2 = "onInfo ——> MEDIA_INFO_BUFFERING_END： STATE_PAUSED";
                    } else if (i == 10001) {
                        if (b.this.f == null) {
                            return true;
                        }
                        b.this.f.setRotation(i2);
                        str = "KSVideoPlayerViewView";
                        str2 = "视频旋转角度：" + i2;
                    } else if (i == 801) {
                        str = "KSVideoPlayerViewView";
                        str2 = "视频不能seekTo，为直播视频";
                    } else {
                        str = "KSVideoPlayerViewView";
                        str2 = "onInfo ——> what：" + i;
                    }
                }
                com.kwad.sdk.core.d.a.c(str, str2);
                return true;
            }
        };
        this.z = new c.a() { // from class: com.kwad.sdk.core.video.videoview.b.6
            @Override // com.kwad.sdk.core.video.kwai.c.a
            public void a(com.kwad.sdk.core.video.kwai.c cVar, int i) {
                b.this.l = i;
            }
        };
        this.A = new BroadcastReceiver() { // from class: com.kwad.sdk.core.video.videoview.b.7
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                NetworkInfo networkInfoC = com.ksad.download.c.b.c(context2);
                if (networkInfoC != null) {
                    networkInfoC.isConnected();
                }
            }
        };
        this.b = context;
        p();
    }

    private void a(AdTemplate adTemplate) {
        KSImageLoader.loadImage(this.t, com.kwad.sdk.core.response.a.d.n(adTemplate), adTemplate);
    }

    private void p() {
        setBackgroundColor(-16777216);
        this.t = q();
        this.e = new FrameLayout(this.b);
        addView(this.e, new FrameLayout.LayoutParams(-1, -1));
    }

    private ImageView q() {
        ImageView imageView = new ImageView(this.b);
        addView(imageView, new FrameLayout.LayoutParams(-1, -1));
        return imageView;
    }

    private void r() {
        if (this.d == null) {
            this.d = com.kwad.sdk.contentalliance.detail.video.a.a(this.b, false);
            this.d.b(3);
            if (this.p) {
                return;
            }
            this.d.a(0.0f, 0.0f);
        }
    }

    private void s() {
        if (this.f == null) {
            this.f = new com.kwad.sdk.core.video.a(this.b);
            this.f.setSurfaceTextureListener(this);
        }
    }

    private void setPlayType(int i) {
        if (this.r == null || this.r.d == null) {
            return;
        }
        this.r.d.mVideoPlayerType = i;
    }

    private void t() {
        this.e.removeView(this.f);
        this.e.addView(this.f, 0, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    private void u() {
        this.e.setKeepScreenOn(true);
        this.d.a(this.u);
        this.d.a(this.v);
        this.d.a(this.w);
        this.d.a(this.x);
        this.d.a(this.y);
        this.d.a(this.z);
        try {
            if (this.r != null && this.s != null) {
                this.r.e = this.s;
            }
            this.d.a(this.r);
            if (this.i == null) {
                this.i = new Surface(this.h);
            }
            this.d.a(this.i);
            if (this.d.e()) {
                this.a = 1;
                this.g.a(this.a);
                com.kwad.sdk.core.d.a.c("KSVideoPlayerViewView", "STATE_PREPARING");
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
            com.kwad.sdk.core.d.a.a("KSVideoPlayerViewView", "打开播放器发生错误", e);
        }
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public void a() {
        if (this.a != 0) {
            com.kwad.sdk.core.d.a.c("KSVideoPlayerViewView", "KSVideoPlayer只有在状态为STATE_IDLE时才能调用start方法.");
            return;
        }
        r();
        s();
        t();
        if (this.r == null || this.r.d == null) {
            return;
        }
        setPlayType(this.r.d.mVideoPlayerType == 0 ? 1 : 3);
    }

    public void a(int i) {
        if (this.d != null) {
            this.d.a(i);
        }
    }

    public void a(@NonNull com.kwad.sdk.contentalliance.detail.video.c cVar, Map<String, String> map) {
        this.r = cVar;
        this.j = cVar.b;
        this.k = map;
        a(cVar.a);
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public void b() {
        String str;
        String str2;
        if (this.a == 5) {
            this.d.f();
            this.a = 4;
            this.g.a(this.a);
            setPlayType(2);
            str = "KSVideoPlayerViewView";
            str2 = "STATE_PLAYING";
        } else if (this.a == 7) {
            this.d.f();
            this.a = 6;
            this.g.a(this.a);
            str = "KSVideoPlayerViewView";
            str2 = "STATE_BUFFERING_PLAYING";
        } else if (this.a == 9 || this.a == -1) {
            this.d.m();
            u();
            setPlayType(3);
            return;
        } else {
            str = "KSVideoPlayerViewView";
            str2 = "KSVideoPlayer在状态为 " + this.a + " 时不能调用restart()方法.";
        }
        com.kwad.sdk.core.d.a.c(str, str2);
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public void c() {
        String str;
        String str2;
        if (this.d == null) {
            return;
        }
        if (this.a == 4) {
            this.d.g();
            this.a = 5;
            this.g.a(this.a);
            str = "KSVideoPlayerViewView";
            str2 = "STATE_PAUSED";
        } else {
            if (this.a != 6) {
                return;
            }
            this.d.g();
            this.a = 7;
            this.g.a(this.a);
            str = "KSVideoPlayerViewView";
            str2 = "STATE_BUFFERING_PAUSED";
        }
        com.kwad.sdk.core.d.a.c(str, str2);
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public boolean d() {
        return this.a == 0;
    }

    public boolean e() {
        return this.a == 6;
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public boolean f() {
        return this.a == 7;
    }

    public boolean g() {
        return this.a == 4;
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public int getBufferPercentage() {
        return this.l;
    }

    public c getController() {
        return this.g;
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public long getCurrentPosition() {
        if (this.d != null) {
            return this.d.j();
        }
        return 0L;
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public long getDuration() {
        if (this.d != null) {
            return this.d.k();
        }
        return 0L;
    }

    public int getMaxVolume() {
        if (this.c != null) {
            return this.c.getStreamMaxVolume(3);
        }
        return 0;
    }

    public c getVideoController() {
        return this.g;
    }

    public int getVolume() {
        if (this.c != null) {
            return this.c.getStreamVolume(3);
        }
        return 0;
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public boolean h() {
        return this.a == 5;
    }

    public boolean i() {
        return this.a == 9;
    }

    public void j() {
        if (this.c != null) {
            this.c.abandonAudioFocus(null);
            this.c = null;
        }
        if (this.d != null) {
            this.d.l();
            this.d = null;
        }
        this.e.removeView(this.f);
        if (this.i != null) {
            this.i.release();
            this.i = null;
        }
        if (this.h != null) {
            this.h.release();
            this.h = null;
        }
        this.a = 0;
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public void k() {
        Context context;
        String str;
        long currentPosition;
        if (this.m) {
            if (g() || e() || f() || h()) {
                context = this.b;
                str = this.j;
                currentPosition = getCurrentPosition();
            } else if (i()) {
                context = this.b;
                str = this.j;
                currentPosition = 0;
            }
            as.a(context, str, currentPosition);
        }
        j();
        if (this.g != null) {
            this.g.e();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.h != null) {
            this.f.setSurfaceTexture(this.h);
        } else {
            this.h = surfaceTexture;
            u();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void setController(c cVar) {
        this.e.removeView(this.g);
        this.g = cVar;
        this.g.e();
        this.e.addView(this.g, new FrameLayout.LayoutParams(-1, -1));
    }

    @Override // com.kwad.sdk.core.video.videoview.d
    public void setKsPlayLogParam(@NonNull com.kwad.sdk.contentalliance.detail.video.b bVar) {
        this.s = bVar;
    }

    public void setPortraitFullscreen(boolean z) {
        this.q = z;
    }

    public void setVideoSoundEnable(boolean z) {
        com.kwad.sdk.core.video.kwai.c cVar;
        float f;
        this.p = z;
        if (this.d != null) {
            if (z) {
                cVar = this.d;
                f = 1.0f;
            } else {
                cVar = this.d;
                f = 0.0f;
            }
            cVar.a(f, f);
        }
    }

    public void setVolume(int i) {
        if (this.c != null) {
            this.c.setStreamVolume(3, i, 0);
        }
    }
}
