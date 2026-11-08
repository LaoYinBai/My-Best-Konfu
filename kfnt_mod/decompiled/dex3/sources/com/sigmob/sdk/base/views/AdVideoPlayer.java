package com.sigmob.sdk.base.views;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.sigmob.logger.SigmobLog;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AdVideoPlayer extends RelativeLayout implements MediaPlayer.OnBufferingUpdateListener, SurfaceHolder.Callback {
    SurfaceView a;
    private MediaPlayer b;
    private SurfaceHolder c;
    private String d;
    private int e;
    private int f;
    private int g;

    public AdVideoPlayer(Context context) {
        super(context);
        this.b = null;
        this.d = "";
        this.a = new SurfaceView(context);
        addView(this.a, new ViewGroup.LayoutParams(-1, -1));
        this.c = this.a.getHolder();
        this.c.addCallback(this);
        this.b = new MediaPlayer();
        this.b.setLooping(true);
        this.b.setOnBufferingUpdateListener(this);
    }

    private void e() throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        try {
            this.b.reset();
            this.b.setDataSource(this.d);
            this.b.prepareAsync();
        } catch (IOException e) {
            SigmobLog.e(e.getMessage());
        }
    }

    public void a() throws IllegalStateException {
        if (this.b == null || !this.b.isPlaying()) {
            return;
        }
        this.b.pause();
        this.g = this.b.getCurrentPosition();
    }

    public void a(int i, int i2) {
        this.e = i;
        this.f = i2;
    }

    public void b() throws IllegalStateException {
        if (this.b != null) {
            this.b.seekTo(this.g);
            this.b.start();
        }
    }

    public void c() throws IllegalStateException {
        if (this.b != null) {
            this.b.stop();
            this.b.reset();
            this.b.release();
            this.b = null;
        }
    }

    public void d() {
    }

    @Override // android.media.MediaPlayer.OnBufferingUpdateListener
    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

    @Override // android.widget.RelativeLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int defaultSize = getDefaultSize(0, i);
        int defaultSize2 = getDefaultSize(0, i2);
        if (defaultSize2 > defaultSize) {
            if (this.f <= this.e) {
                defaultSize2 = (int) (defaultSize * (this.f / this.e));
            }
        } else if (this.f > this.e) {
            defaultSize = (int) (defaultSize2 * (this.e / this.f));
        }
        if (this.f != this.e) {
            setMeasuredDimension(defaultSize, defaultSize2);
        } else if (this.f == -1) {
            setMeasuredDimension(View.MeasureSpec.getSize(i), View.MeasureSpec.getSize(i2));
        } else {
            super.onMeasure(i, i2);
        }
    }

    public void setOnPreparedListener(MediaPlayer.OnPreparedListener onPreparedListener) {
        this.b.setOnPreparedListener(onPreparedListener);
    }

    public void setOnSeekCompleteListener(MediaPlayer.OnSeekCompleteListener onSeekCompleteListener) {
        this.b.setOnSeekCompleteListener(onSeekCompleteListener);
    }

    public void setVideoPath(String str) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        this.d = str;
        e();
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.b.setDisplay(surfaceHolder);
    }

    @Override // android.view.SurfaceHolder.Callback
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) throws IllegalStateException {
        if (this.b == null || !this.b.isPlaying()) {
            return;
        }
        this.b.stop();
        this.b.release();
    }
}
