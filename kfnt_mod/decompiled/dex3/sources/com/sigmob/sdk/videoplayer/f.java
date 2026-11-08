package com.sigmob.sdk.videoplayer;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import com.sigmob.logger.SigmobLog;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f extends e implements MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener {
    public MediaPlayer f;

    public f(VideoAdView videoAdView) {
        super(videoAdView);
    }

    @Override // com.sigmob.sdk.videoplayer.e
    public void a() {
        if (this.c == null) {
            return;
        }
        this.c.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.6
            @Override // java.lang.Runnable
            public void run() throws IllegalStateException {
                if (f.this.f == null) {
                    return;
                }
                f.this.f.start();
            }
        });
    }

    @Override // com.sigmob.sdk.videoplayer.e
    public void a(final float f) {
        if (this.c == null) {
            return;
        }
        this.c.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.10
            @Override // java.lang.Runnable
            public void run() {
                if (f.this.f == null) {
                    return;
                }
                f.this.f.setVolume(f, f);
            }
        });
    }

    @Override // com.sigmob.sdk.videoplayer.e
    public void a(final long j) {
        if (this.c == null) {
            return;
        }
        this.c.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.8
            @Override // java.lang.Runnable
            public void run() throws IllegalStateException {
                try {
                    if (f.this.f == null) {
                        return;
                    }
                    f.this.f.seekTo((int) j);
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // com.sigmob.sdk.videoplayer.e
    public void a(Surface surface) {
        SigmobLog.d("setSurface() called with: surface = [" + surface + "]");
        if (this.f == null) {
            return;
        }
        this.f.setSurface(surface);
    }

    @Override // com.sigmob.sdk.videoplayer.e
    public void b() {
        d();
        this.b = new HandlerThread("VideoPlayerMediaSystem");
        this.b.start();
        this.c = new Handler(this.b.getLooper());
        this.d = new Handler();
        this.c.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.1
            @Override // java.lang.Runnable
            public void run() throws IllegalStateException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
                try {
                    f.this.f = new MediaPlayer();
                    f.this.f.setAudioStreamType(3);
                    f.this.f.setLooping(f.this.e.j.d);
                    f.this.f.setOnPreparedListener(f.this);
                    f.this.f.setOnCompletionListener(f.this);
                    f.this.f.setOnBufferingUpdateListener(f.this);
                    f.this.f.setScreenOnWhilePlaying(true);
                    f.this.f.setOnSeekCompleteListener(f.this);
                    f.this.f.setOnErrorListener(f.this);
                    f.this.f.setOnInfoListener(f.this);
                    f.this.f.setOnVideoSizeChangedListener(f.this);
                    MediaPlayer.class.getDeclaredMethod("setDataSource", String.class, Map.class).invoke(f.this.f, f.this.e.j.a().toString(), f.this.e.j.c);
                    f.this.f.prepareAsync();
                    f.this.f.setSurface(new Surface(f.this.a));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override // com.sigmob.sdk.videoplayer.e
    public void c() {
        if (this.c == null || this.f == null) {
            return;
        }
        this.c.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.7
            @Override // java.lang.Runnable
            public void run() throws IllegalStateException {
                if (f.this.f == null) {
                    return;
                }
                f.this.f.pause();
            }
        });
    }

    @Override // com.sigmob.sdk.videoplayer.e
    public void d() {
        if (this.c == null || this.b == null || this.f == null) {
            return;
        }
        final HandlerThread handlerThread = this.b;
        final MediaPlayer mediaPlayer = this.f;
        this.c.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.9
            @Override // java.lang.Runnable
            public void run() {
                mediaPlayer.setSurface(null);
                mediaPlayer.release();
                handlerThread.quit();
                f.this.c = null;
            }
        });
        this.f = null;
    }

    @Override // com.sigmob.sdk.videoplayer.e
    public long e() {
        if (this.f != null) {
            return this.f.getCurrentPosition();
        }
        return 0L;
    }

    @Override // com.sigmob.sdk.videoplayer.e
    public long f() {
        if (this.f != null) {
            return this.f.getDuration();
        }
        return 0L;
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, final int i) {
        if (this.d == null) {
            return;
        }
        this.d.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.13
            @Override // java.lang.Runnable
            public void run() {
                if (f.this.e == null) {
                    return;
                }
                f.this.e.setBufferProgress(i);
            }
        });
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.d == null) {
            return;
        }
        this.d.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.12
            @Override // java.lang.Runnable
            public void run() {
                if (f.this.e == null) {
                    return;
                }
                f.this.e.j();
            }
        });
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, final int i, final int i2) {
        if (this.d == null) {
            return false;
        }
        this.d.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.3
            @Override // java.lang.Runnable
            public void run() {
                if (f.this.e == null) {
                    return;
                }
                f.this.e.b(i, i2);
            }
        });
        return true;
    }

    @Override // android.media.MediaPlayer.OnInfoListener
    public boolean onInfo(MediaPlayer mediaPlayer, final int i, final int i2) {
        if (this.d != null && this.e != null) {
            this.d.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.4
                @Override // java.lang.Runnable
                public void run() {
                    if (f.this.e == null) {
                        return;
                    }
                    f.this.e.a(i, i2);
                }
            });
        }
        return false;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        if (this.d == null) {
            return;
        }
        this.d.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.11
            @Override // java.lang.Runnable
            public void run() {
                if (f.this.e == null) {
                    return;
                }
                f.this.e.e();
            }
        });
    }

    @Override // android.media.MediaPlayer.OnSeekCompleteListener
    public void onSeekComplete(MediaPlayer mediaPlayer) {
        if (this.d == null) {
            return;
        }
        this.d.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.2
            @Override // java.lang.Runnable
            public void run() {
                if (f.this.e == null) {
                    return;
                }
                f.this.e.w();
            }
        });
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.e == null) {
            return;
        }
        SigmobLog.d("onSurfaceTextureAvailable() called with: surface = [" + surfaceTexture + "], width = [" + i + "], height = [" + i2 + "]");
        if (this.a != null) {
            this.e.D.setSurfaceTexture(this.a);
        } else {
            this.a = surfaceTexture;
            b();
        }
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        SigmobLog.d("onSurfaceTextureDestroyed() called with: surface = [" + surfaceTexture + "]");
        return false;
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        SigmobLog.d("onSurfaceTextureSizeChanged() called with: surface = [" + surfaceTexture + "], width = [" + i + "], height = [" + i2 + "]");
    }

    @Override // android.view.TextureView.SurfaceTextureListener
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, final int i, final int i2) {
        if (this.d == null) {
            return;
        }
        this.d.post(new Runnable() { // from class: com.sigmob.sdk.videoplayer.f.5
            @Override // java.lang.Runnable
            public void run() {
                if (f.this.e == null) {
                    return;
                }
                f.this.e.c(i, i2);
            }
        });
    }
}
