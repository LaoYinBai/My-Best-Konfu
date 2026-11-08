package com.sigmob.sdk.base.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.sigmob.logger.SigmobLog;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GifImageView extends ImageView {
    private int a;
    private Movie b;
    private long c;
    private int d;
    private float e;
    private float f;
    private float g;
    private float h;
    private int i;
    private int j;
    private boolean k;
    private volatile boolean l;

    public GifImageView(Context context) {
        this(context, null);
    }

    public GifImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public GifImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.d = 0;
        this.k = true;
        this.l = false;
        if (Build.VERSION.SDK_INT >= 11) {
            setLayerType(1, null);
        }
    }

    @SuppressLint({"NewApi"})
    private void a() {
        if (this.k) {
            if (Build.VERSION.SDK_INT >= 16) {
                postInvalidateOnAnimation();
            } else {
                invalidate();
            }
        }
    }

    private void a(Canvas canvas) {
        this.b.setTime(this.d);
        canvas.save();
        canvas.scale(this.g, this.h);
        this.b.draw(canvas, this.e / this.g, this.f / this.h);
        canvas.restore();
    }

    private void b() {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.c == 0) {
            this.c = jUptimeMillis;
        }
        int iDuration = this.b.duration();
        if (iDuration == 0) {
            iDuration = 1000;
        }
        this.d = (int) ((jUptimeMillis - this.c) % iDuration);
    }

    private byte[] getGiftBytes() throws Resources.NotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStreamOpenRawResource = getResources().openRawResource(this.a);
        byte[] bArr = new byte[com.ss.union.game.sdk.feedback.picture.select.d.d];
        while (true) {
            try {
                try {
                    try {
                        int i = inputStreamOpenRawResource.read(bArr, 0, com.ss.union.game.sdk.feedback.picture.select.d.d);
                        if (i == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                    } catch (Throwable th) {
                        SigmobLog.e(th.getMessage());
                        if (inputStreamOpenRawResource != null) {
                            inputStreamOpenRawResource.close();
                        }
                    }
                } catch (Throwable th2) {
                    if (inputStreamOpenRawResource != null) {
                        try {
                            inputStreamOpenRawResource.close();
                        } catch (Throwable th3) {
                            SigmobLog.e(th3.getMessage());
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                SigmobLog.e(th4.getMessage());
            }
        }
        byteArrayOutputStream.flush();
        if (inputStreamOpenRawResource != null) {
            inputStreamOpenRawResource.close();
        }
        return byteArrayOutputStream.toByteArray();
    }

    public Movie getMovie() {
        return this.b;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.b == null) {
            super.onDraw(canvas);
            return;
        }
        b();
        a(canvas);
        a();
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        this.e = (getWidth() - this.i) / 2.0f;
        this.f = (getHeight() - this.j) / 2.0f;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.b == null) {
            super.onMeasure(i, i2);
            return;
        }
        int iWidth = this.b.width();
        int iHeight = this.b.height();
        int size = View.MeasureSpec.getSize(i);
        this.g = 1.0f / (iWidth / size);
        this.h = 1.0f / (iHeight / View.MeasureSpec.getSize(i2));
        this.i = size;
        this.j = View.MeasureSpec.getSize(i2);
        setMeasuredDimension(this.i, this.j);
    }

    @Override // android.view.View
    @SuppressLint({"NewApi"})
    public void onScreenStateChanged(int i) {
        super.onScreenStateChanged(i);
        this.k = i == 1;
        a();
    }

    @Override // android.view.View
    @SuppressLint({"NewApi"})
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.k = i == 0;
        a();
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        this.k = i == 0;
        a();
    }

    public void setMovie(Movie movie) {
        this.b = movie;
        requestLayout();
    }

    public void setMovieResource(int i) throws Resources.NotFoundException {
        this.a = i;
        byte[] giftBytes = getGiftBytes();
        this.b = Movie.decodeByteArray(giftBytes, 0, giftBytes.length);
        requestLayout();
    }

    public void setMovieTime(int i) {
        this.d = i;
        invalidate();
    }

    public void setPaused(boolean z) {
        this.l = z;
        if (!z) {
            this.c = SystemClock.uptimeMillis() - this.d;
        }
        invalidate();
    }
}
