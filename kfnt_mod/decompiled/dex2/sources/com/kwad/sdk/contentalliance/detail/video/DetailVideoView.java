package com.kwad.sdk.contentalliance.detail.video;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.kwad.sdk.core.response.model.PhotoInfo;
import com.kwad.sdk.utils.bb;
import com.kwad.sdk.widget.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DetailVideoView extends FrameLayout implements View.OnClickListener {
    public com.kwad.sdk.core.page.widget.c a;
    public Surface b;
    private com.kwad.sdk.contentalliance.detail.video.a c;
    private SurfaceTexture d;
    private a e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    private Matrix j;
    private PhotoInfo.VideoInfo k;
    private final RectF l;

    public interface a {
        void a();

        void b();
    }

    public DetailVideoView(Context context) {
        super(context);
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.l = new RectF();
        a(context);
    }

    public DetailVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.l = new RectF();
        a(context);
    }

    private void a() {
        this.a.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.kwad.sdk.contentalliance.detail.video.DetailVideoView.1
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                if (DetailVideoView.this.d == surfaceTexture) {
                    return;
                }
                DetailVideoView.this.d = surfaceTexture;
                DetailVideoView.this.b();
                DetailVideoView.this.b = new Surface(surfaceTexture);
                if (DetailVideoView.this.c != null) {
                    DetailVideoView.this.c.a(DetailVideoView.this.b);
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
        });
    }

    private void a(Context context) {
        this.j = new Matrix();
        this.a = new com.kwad.sdk.core.page.widget.c(context);
        addView(this.a, 0, new FrameLayout.LayoutParams(-1, -1, 17));
        a();
    }

    private void a(View view, long j, long j2) {
        View view2;
        if (view == null || j == 0 || j2 == 0 || (view2 = (View) view.getParent()) == null) {
            return;
        }
        int width = view2.getWidth();
        int height = view2.getHeight();
        if (width == 0 || height == 0) {
            return;
        }
        view.getLayoutParams();
        float f = j / j2;
        float f2 = height * f;
        float f3 = width;
        if (f2 > f3) {
            height = (int) (f3 / f);
        } else {
            width = (int) f2;
        }
        int i = -1;
        if (width == 0 || height == 0) {
            height = -1;
        } else {
            i = width;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = height;
        this.a.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.b != null) {
            try {
                this.b.release();
            } catch (Throwable th) {
                com.kwad.sdk.core.d.a.a(th);
            }
            this.b = null;
        }
    }

    public void a(int i) {
        if (this.a == null) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).gravity = i;
            this.a.requestLayout();
        }
    }

    public void a(int i, int i2) {
        if (this.f) {
            com.kwad.sdk.a.kwai.a.b(this.a, i, i2);
            return;
        }
        if (this.h) {
            com.kwad.sdk.a.kwai.a.c(this.a, i, i2);
            return;
        }
        if (this.g) {
            com.kwad.sdk.a.kwai.a.a(this.a, i, i2);
            return;
        }
        if (this.i) {
            a(this.a, i, i2);
            return;
        }
        View view = (View) this.a.getParent();
        if (view == null) {
            return;
        }
        int width = view.getWidth();
        int height = view.getHeight();
        if (width == 0 || height == 0) {
            return;
        }
        if (this.k == null || !bb.a(this.j, width, height, this.k)) {
            ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
            layoutParams.width = width;
            layoutParams.height = (int) ((i2 / (i * 1.0f)) * width);
            this.j.reset();
            this.a.setTransform(this.j);
            this.a.setLayoutParams(layoutParams);
        } else {
            ViewGroup.LayoutParams layoutParams2 = this.a.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.height = -1;
            this.a.setTransform(this.j);
            this.a.setLayoutParams(layoutParams2);
        }
        this.l.set(this.a.getLeft(), this.a.getTop(), this.a.getRight(), this.a.getBottom());
    }

    public void a(boolean z) {
        this.h = z;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.a) {
            if (this.e != null) {
                this.e.a();
            }
        } else if (this.e != null) {
            this.e.b();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b();
        if (this.d != null) {
            this.d.release();
            this.d = null;
        }
    }

    public void setAd(boolean z) {
        this.g = z;
    }

    public void setClickListener(a aVar) {
        this.e = aVar;
        setOnClickListener(this);
    }

    public void setForce(boolean z) {
        this.f = z;
    }

    public void setHorizontalVideo(boolean z) {
        this.i = z;
    }

    public void setMediaPlayer(com.kwad.sdk.contentalliance.detail.video.a aVar) {
        this.c = aVar;
        if (this.b == null || this.c == null) {
            return;
        }
        this.c.a(this.b);
    }

    public void setRadius(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            g.a(this, f);
        }
    }

    public void setVideoInfo(PhotoInfo.VideoInfo videoInfo) {
        this.k = videoInfo;
    }
}
