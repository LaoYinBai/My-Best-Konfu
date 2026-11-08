package com.sigmob.sdk.videoplayer;

import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Surface;
import android.view.TextureView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class e implements TextureView.SurfaceTextureListener {
    public SurfaceTexture a;
    public HandlerThread b;
    public Handler c;
    public Handler d;
    public VideoAdView e;

    public e(VideoAdView videoAdView) {
        this.e = videoAdView;
    }

    public abstract void a();

    public abstract void a(float f);

    public abstract void a(long j);

    public abstract void a(Surface surface);

    public abstract void b();

    public abstract void c();

    public abstract void d();

    public abstract long e();

    public abstract long f();
}
