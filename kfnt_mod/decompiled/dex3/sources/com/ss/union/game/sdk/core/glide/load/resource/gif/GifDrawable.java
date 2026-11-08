package com.ss.union.game.sdk.core.glide.load.resource.gif;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifFrameLoader;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GifDrawable extends Drawable implements Animatable, GifFrameLoader.FrameCallback {
    public static final int LOOP_FOREVER = -1;
    public static final int LOOP_INTRINSIC = 0;
    private static final int a = 119;
    private final a b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private int g;
    private int h;
    private boolean i;
    private Paint j;
    private Rect k;
    private List<Animatable2.AnimationCallback> l;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    @Deprecated
    public GifDrawable(Context context, GifDecoder gifDecoder, BitmapPool bitmapPool, Transformation<Bitmap> transformation, int i, int i2, Bitmap bitmap) {
        this(context, gifDecoder, transformation, i, i2, bitmap);
    }

    public GifDrawable(Context context, GifDecoder gifDecoder, Transformation<Bitmap> transformation, int i, int i2, Bitmap bitmap) {
        this(new a(new GifFrameLoader(Glide.get(context), gifDecoder, i, i2, transformation, bitmap)));
    }

    GifDrawable(a aVar) {
        this.f = true;
        this.h = -1;
        this.b = (a) Preconditions.checkNotNull(aVar);
    }

    GifDrawable(GifFrameLoader gifFrameLoader, Paint paint) {
        this(new a(gifFrameLoader));
        this.j = paint;
    }

    public int getSize() {
        return this.b.a.e();
    }

    public Bitmap getFirstFrame() {
        return this.b.a.b();
    }

    public void setFrameTransformation(Transformation<Bitmap> transformation, Bitmap bitmap) {
        this.b.a.a(transformation, bitmap);
    }

    public Transformation<Bitmap> getFrameTransformation() {
        return this.b.a.a();
    }

    public ByteBuffer getBuffer() {
        return this.b.a.g();
    }

    public int getFrameCount() {
        return this.b.a.h();
    }

    public int getFrameIndex() {
        return this.b.a.f();
    }

    private void b() {
        this.g = 0;
    }

    public void startFromFirstFrame() {
        Preconditions.checkArgument(!this.c, "You cannot restart a currently running animation.");
        this.b.a.l();
        start();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        this.d = true;
        b();
        if (this.f) {
            c();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.d = false;
        d();
    }

    private void c() {
        Preconditions.checkArgument(!this.e, "You cannot start a recycled Drawable. Ensure thatyou clear any references to the Drawable when clearing the corresponding request.");
        if (this.b.a.h() == 1) {
            invalidateSelf();
        } else {
            if (this.c) {
                return;
            }
            this.c = true;
            this.b.a.a(this);
            invalidateSelf();
        }
    }

    private void d() {
        this.c = false;
        this.b.a.b(this);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Preconditions.checkArgument(!this.e, "Cannot change the visibility of a recycled resource. Ensure that you unset the Drawable from your View before changing the View's visibility.");
        this.f = z;
        if (!z) {
            d();
        } else if (this.d) {
            c();
        }
        return super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.b.a.c();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.b.a.d();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.c;
    }

    void a(boolean z) {
        this.c = z;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.i = true;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.e) {
            return;
        }
        if (this.i) {
            Gravity.apply(119, getIntrinsicWidth(), getIntrinsicHeight(), getBounds(), e());
            this.i = false;
        }
        canvas.drawBitmap(this.b.a.k(), (Rect) null, e(), f());
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        f().setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        f().setColorFilter(colorFilter);
    }

    private Rect e() {
        if (this.k == null) {
            this.k = new Rect();
        }
        return this.k;
    }

    private Paint f() {
        if (this.j == null) {
            this.j = new Paint(2);
        }
        return this.j;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Drawable.Callback g() {
        Drawable.Callback callback = getCallback();
        while (callback instanceof Drawable) {
            callback = ((Drawable) callback).getCallback();
        }
        return callback;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.resource.gif.GifFrameLoader.FrameCallback
    public void onFrameReady() {
        if (g() == null) {
            stop();
            invalidateSelf();
            return;
        }
        invalidateSelf();
        if (getFrameIndex() == getFrameCount() - 1) {
            this.g++;
        }
        if (this.h == -1 || this.g < this.h) {
            return;
        }
        h();
        stop();
    }

    private void h() {
        if (this.l != null) {
            int size = this.l.size();
            for (int i = 0; i < size; i++) {
                if (Build.VERSION.SDK_INT >= 23) {
                    this.l.get(i).onAnimationEnd(this);
                }
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        return this.b;
    }

    public void recycle() {
        this.e = true;
        this.b.a.j();
    }

    boolean a() {
        return this.e;
    }

    public void setLoopCount(int i) {
        if (i <= 0 && i != -1 && i != 0) {
            throw new IllegalArgumentException("Loop count must be greater than 0, or equal to GlideDrawable.LOOP_FOREVER, or equal to GlideDrawable.LOOP_INTRINSIC");
        }
        if (i == 0) {
            int i2 = this.b.a.i();
            if (i2 == 0) {
                i2 = -1;
            }
            this.h = i2;
            return;
        }
        this.h = i;
    }

    static final class a extends Drawable.ConstantState {
        final GifFrameLoader a;

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return 0;
        }

        a(GifFrameLoader gifFrameLoader) {
            this.a = gifFrameLoader;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return newDrawable();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new GifDrawable(this);
        }
    }
}
