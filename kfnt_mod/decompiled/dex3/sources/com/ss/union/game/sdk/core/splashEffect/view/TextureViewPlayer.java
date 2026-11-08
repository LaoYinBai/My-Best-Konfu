package com.ss.union.game.sdk.core.splashEffect.view;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import com.ss.union.game.sdk.core.splashEffect.util.TimeCollector;
import com.ss.union.game.sdk.core.splashEffect.view.a.b;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TextureViewPlayer extends FrameLayout implements b {
    private int a;
    private int b;
    private Context c;
    private AudioManager d;
    private MediaPlayer e;
    private FrameLayout f;
    private SimpleTextureView g;
    private SurfaceTexture h;
    private Surface i;
    private String j;
    private Map<String, String> k;
    private int l;
    private AudioFocusRequest m;
    private com.ss.union.game.sdk.core.splashEffect.view.a.a n;
    private boolean o;
    private MediaPlayer.OnPreparedListener p;
    private MediaPlayer.OnCompletionListener q;
    private MediaPlayer.OnBufferingUpdateListener r;
    private MediaPlayer.OnSeekCompleteListener s;
    private MediaPlayer.OnErrorListener t;
    private MediaPlayer.OnInfoListener u;
    private String v;
    private MediaPlayer.OnVideoSizeChangedListener w;

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public View getView() {
        return this;
    }

    public TextureViewPlayer(Context context) {
        this(context, null);
    }

    public TextureViewPlayer(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TextureViewPlayer(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 0;
        this.b = 1001;
        this.p = new MediaPlayer.OnPreparedListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.TextureViewPlayer.1
            @Override // android.media.MediaPlayer.OnPreparedListener
            public void onPrepared(MediaPlayer mediaPlayer) throws IllegalStateException {
                TimeCollector.effect().end("视频解析好了");
                TextureViewPlayer.this.a = 2;
                com.ss.union.game.sdk.core.splashEffect.a.a.a("onPrepared ——> STATE_PREPARED");
                if (TextureViewPlayer.this.n != null) {
                    TextureViewPlayer.this.n.b(mediaPlayer);
                }
                TextureViewPlayer.this.o = true;
                if (TextureViewPlayer.this.i != null) {
                    TextureViewPlayer.this.e.setSurface(TextureViewPlayer.this.i);
                    TextureViewPlayer.this.e.start();
                }
            }
        };
        this.q = new MediaPlayer.OnCompletionListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.TextureViewPlayer.2
            @Override // android.media.MediaPlayer.OnCompletionListener
            public void onCompletion(MediaPlayer mediaPlayer) {
                TextureViewPlayer.this.a = 7;
                com.ss.union.game.sdk.core.splashEffect.a.a.a("onCompletion ——> STATE_COMPLETED");
                TextureViewPlayer.this.f.setKeepScreenOn(false);
                if (TextureViewPlayer.this.n != null) {
                    TextureViewPlayer.this.n.a(mediaPlayer);
                }
            }
        };
        this.r = new MediaPlayer.OnBufferingUpdateListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.TextureViewPlayer.3
            final int a = 97;

            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(MediaPlayer mediaPlayer, int i2) {
                TextureViewPlayer.this.l = i2;
                if (i2 > 97) {
                    TextureViewPlayer.this.l = 100;
                }
                com.ss.union.game.sdk.core.splashEffect.a.a.a("onBufferingUpdate ——> " + i2);
                if (TextureViewPlayer.this.n != null) {
                    TextureViewPlayer.this.n.a(mediaPlayer, i2);
                }
            }
        };
        this.s = new MediaPlayer.OnSeekCompleteListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.TextureViewPlayer.4
            @Override // android.media.MediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(MediaPlayer mediaPlayer) {
                if (TextureViewPlayer.this.n != null) {
                    TextureViewPlayer.this.n.c(mediaPlayer);
                }
            }
        };
        this.t = new MediaPlayer.OnErrorListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.TextureViewPlayer.5
            @Override // android.media.MediaPlayer.OnErrorListener
            public boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
                if (i2 != -38 && i2 != Integer.MIN_VALUE && i3 != -38 && i3 != Integer.MIN_VALUE) {
                    TextureViewPlayer.this.a = -1;
                }
                com.ss.union.game.sdk.core.splashEffect.a.a.a("onError ——> STATE_ERROR ———— what：" + i2 + ", extra: " + i3);
                if (TextureViewPlayer.this.n == null) {
                    return true;
                }
                TextureViewPlayer.this.n.a(mediaPlayer, i2, i3);
                return true;
            }
        };
        this.u = new MediaPlayer.OnInfoListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.TextureViewPlayer.6
            @Override // android.media.MediaPlayer.OnInfoListener
            public boolean onInfo(MediaPlayer mediaPlayer, int i2, int i3) {
                if (i2 == 3) {
                    TextureViewPlayer.this.a = 3;
                    com.ss.union.game.sdk.core.splashEffect.a.a.a("onInfo ——> MEDIA_INFO_VIDEO_RENDERING_START：STATE_PLAYING");
                } else if (i2 == 701) {
                    if (TextureViewPlayer.this.a == 4 || TextureViewPlayer.this.a == 6) {
                        TextureViewPlayer.this.a = 6;
                        com.ss.union.game.sdk.core.splashEffect.a.a.a("onInfo ——> MEDIA_INFO_BUFFERING_START：STATE_BUFFERING_PAUSED");
                    } else {
                        TextureViewPlayer.this.a = 5;
                        com.ss.union.game.sdk.core.splashEffect.a.a.a("onInfo ——> MEDIA_INFO_BUFFERING_START：STATE_BUFFERING_PLAYING");
                    }
                } else if (i2 == 702) {
                    if (TextureViewPlayer.this.a == 5) {
                        TextureViewPlayer.this.a = 3;
                        com.ss.union.game.sdk.core.splashEffect.a.a.a("onInfo ——> MEDIA_INFO_BUFFERING_END： STATE_PLAYING");
                    }
                    if (TextureViewPlayer.this.a == 6) {
                        TextureViewPlayer.this.a = 4;
                        com.ss.union.game.sdk.core.splashEffect.a.a.a("onInfo ——> MEDIA_INFO_BUFFERING_END： STATE_PAUSED");
                    }
                } else if (i2 == 801) {
                    com.ss.union.game.sdk.core.splashEffect.a.a.a("视频不能seekTo，为直播视频");
                } else {
                    com.ss.union.game.sdk.core.splashEffect.a.a.a("onInfo ——> what：" + i2);
                }
                if (TextureViewPlayer.this.n == null) {
                    return true;
                }
                TextureViewPlayer.this.n.b(mediaPlayer, i2, i3);
                return true;
            }
        };
        this.w = new MediaPlayer.OnVideoSizeChangedListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.TextureViewPlayer.7
            @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i2, int i3) {
                if (TextureViewPlayer.this.n != null) {
                    TextureViewPlayer.this.n.c(mediaPlayer, i2, i3);
                }
            }
        };
        this.c = context;
        f();
    }

    private void f() {
        this.f = new FrameLayout(this.c);
        this.f.setBackgroundColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        addView(this.f, new FrameLayout.LayoutParams(-1, -1));
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        com.ss.union.game.sdk.core.splashEffect.a.a.a("onAttachedToWindow");
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() throws IllegalStateException {
        super.onDetachedFromWindow();
        com.ss.union.game.sdk.core.splashEffect.a.a.a("onDetachedFromWindow");
        d();
    }

    public final void a(String str, Map<String, String> map) {
        if (str == null || str.length() == 0) {
            com.ss.union.game.sdk.core.splashEffect.a.a.a("设置的视频链接不能为空");
        }
        this.j = str;
        this.k = map;
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public void setUri(String str) {
        a(str, new HashMap());
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public void setAssetName(String str) {
        this.v = str;
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public void setMediaPlayerListener(com.ss.union.game.sdk.core.splashEffect.view.a.a aVar) {
        this.n = aVar;
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public void a() throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        if (this.a == 0) {
            h();
            i();
            g();
            j();
            return;
        }
        com.ss.union.game.sdk.core.splashEffect.a.a.a("VideoPlayer只有在mCurrentState == STATE_IDLE时才能调用start方法.");
    }

    public void a(long j) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        if (j < 0) {
            com.ss.union.game.sdk.core.splashEffect.a.a.a("设置开始播放的播放位置不能小于0");
        }
        a();
    }

    public void b() throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        if (this.a == 4) {
            this.e.start();
            this.a = 3;
            com.ss.union.game.sdk.core.splashEffect.a.a.a("STATE_PLAYING");
            return;
        }
        if (this.a == 6) {
            this.e.start();
            this.a = 5;
            com.ss.union.game.sdk.core.splashEffect.a.a.a("STATE_BUFFERING_PLAYING");
        } else {
            if (this.a == 7 || this.a == -1) {
                this.e.reset();
                j();
                return;
            }
            com.ss.union.game.sdk.core.splashEffect.a.a.a("VideoPlayer在mCurrentState == " + this.a + "时不能调用restart()方法.");
        }
    }

    @Override // com.ss.union.game.sdk.core.splashEffect.view.a.b
    public void c() throws IllegalStateException {
        if (this.a == 3) {
            this.e.pause();
            this.a = 4;
            com.ss.union.game.sdk.core.splashEffect.a.a.a("STATE_PAUSED");
        } else if (this.a == 5) {
            this.e.pause();
            this.a = 6;
            com.ss.union.game.sdk.core.splashEffect.a.a.a("STATE_BUFFERING_PAUSED");
        }
        k();
    }

    public void setVolume(int i) {
        if (this.d != null) {
            this.d.setStreamVolume(3, i, 0);
        }
    }

    public long getDuration() {
        if (this.e != null) {
            return this.e.getDuration();
        }
        return 0L;
    }

    public long getCurrentPosition() {
        if (this.e != null) {
            return this.e.getCurrentPosition();
        }
        return 0L;
    }

    public int getBufferPercentage() {
        return this.l;
    }

    public int getCurrentState() {
        return this.a;
    }

    @RequiresApi(api = 8)
    private void g() {
        if (this.d == null) {
            this.d = (AudioManager) getContext().getSystemService("audio");
            if (this.d != null) {
                if (Build.VERSION.SDK_INT < 26) {
                    com.ss.union.game.sdk.core.splashEffect.a.a.a("VideoPlayer", "request audio result ==" + this.d.requestAudioFocus(null, 3, 2));
                    return;
                }
                this.m = new AudioFocusRequest.Builder(2).build();
                com.ss.union.game.sdk.core.splashEffect.a.a.a("VideoPlayer", ">= O request audio result ==" + this.d.requestAudioFocus(this.m));
            }
        }
    }

    private void h() {
        if (this.e == null) {
            this.e = new MediaPlayer();
            this.e.setAudioStreamType(3);
        }
    }

    @RequiresApi(api = 14)
    private void i() {
        if (this.g == null) {
            this.g = new SimpleTextureView(this.c);
            this.g.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.ss.union.game.sdk.core.splashEffect.view.TextureViewPlayer.8
                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) throws IllegalStateException {
                    if (TextureViewPlayer.this.h == null) {
                        TextureViewPlayer.this.h = surfaceTexture;
                    } else if (Build.VERSION.SDK_INT >= 16) {
                        TextureViewPlayer.this.g.setSurfaceTexture(TextureViewPlayer.this.h);
                    }
                    if (TextureViewPlayer.this.i == null) {
                        TextureViewPlayer.this.i = new Surface(TextureViewPlayer.this.h);
                    }
                    TimeCollector.effect().end("获取画布");
                    if (TextureViewPlayer.this.e == null || !TextureViewPlayer.this.o) {
                        return;
                    }
                    TextureViewPlayer.this.e.setSurface(TextureViewPlayer.this.i);
                    TextureViewPlayer.this.e.start();
                }

                @Override // android.view.TextureView.SurfaceTextureListener
                public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                    return TextureViewPlayer.this.h == null;
                }
            });
            this.g.a(this.e);
        }
        this.f.removeView(this.g);
        this.f.addView(this.g, 0, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    @RequiresApi(api = 14)
    private void j() throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        this.f.setKeepScreenOn(true);
        this.e.setOnPreparedListener(this.p);
        this.e.setOnCompletionListener(this.q);
        this.e.setOnBufferingUpdateListener(this.r);
        this.e.setOnSeekCompleteListener(this.s);
        this.e.setOnErrorListener(this.t);
        this.e.setOnInfoListener(this.u);
        this.e.setOnVideoSizeChangedListener(this.w);
        try {
            if (!TextUtils.isEmpty(this.v)) {
                AssetFileDescriptor assetFileDescriptorOpenFd = this.c.getAssets().openFd(this.v);
                this.e.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
            } else {
                this.e.setDataSource(this.c, Uri.parse(this.j));
            }
            this.e.setScreenOnWhilePlaying(true);
            this.e.prepareAsync();
            this.a = 1;
            com.ss.union.game.sdk.core.splashEffect.a.a.a("STATE_PREPARING");
        } catch (IOException e) {
            e.printStackTrace();
            com.ss.union.game.sdk.core.splashEffect.a.a.b("打开播放器发生错误" + e.getMessage());
        }
    }

    public void d() throws IllegalStateException {
        this.b = 1001;
        e();
        Runtime.getRuntime().gc();
    }

    private void k() {
        if (this.d != null) {
            if (Build.VERSION.SDK_INT < 26) {
                this.d.abandonAudioFocus(null);
            } else if (this.m != null) {
                this.d.abandonAudioFocusRequest(this.m);
            }
            this.d = null;
        }
    }

    public void e() throws IllegalStateException {
        k();
        if (this.e != null) {
            this.e.stop();
            this.e.release();
            this.e = null;
        }
        if (this.f != null) {
            this.f.removeView(this.g);
        }
        if (this.i != null) {
            if (Build.VERSION.SDK_INT >= 14) {
                this.i.release();
            }
            this.i = null;
        }
        if (this.h != null) {
            if (Build.VERSION.SDK_INT >= 14) {
                this.h.release();
            }
            this.h = null;
        }
        this.a = 0;
    }
}
