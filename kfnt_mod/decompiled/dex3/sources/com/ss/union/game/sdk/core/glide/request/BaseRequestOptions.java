package com.ss.union.game.sdk.core.glide.request;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DecodeFormat;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.MultiTransformation;
import com.ss.union.game.sdk.core.glide.load.Option;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.engine.DiskCacheStrategy;
import com.ss.union.game.sdk.core.glide.load.model.stream.HttpGlideUrlLoader;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapEncoder;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.CenterCrop;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.CenterInside;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.CircleCrop;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.DrawableTransformation;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.FitCenter;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.VideoDecoder;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifDrawable;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifDrawableTransformation;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifOptions;
import com.ss.union.game.sdk.core.glide.request.BaseRequestOptions;
import com.ss.union.game.sdk.core.glide.signature.EmptySignature;
import com.ss.union.game.sdk.core.glide.util.CachedHashCodeArrayMap;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.Map;
import kotlin.ULong;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class BaseRequestOptions<T extends BaseRequestOptions<T>> implements Cloneable {
    private static final int a = -1;
    private static final int b = 2;
    private static final int c = 4;
    private static final int d = 8;
    private static final int e = 16;
    private static final int f = 32;
    private static final int g = 64;
    private static final int h = 128;
    private static final int i = 256;
    private static final int j = 512;
    private static final int k = 1024;
    private static final int l = 2048;
    private static final int m = 4096;
    private static final int n = 8192;
    private static final int o = 16384;
    private static final int p = 32768;
    private static final int q = 65536;
    private static final int r = 131072;
    private static final int s = 262144;
    private static final int t = 524288;
    private static final int u = 1048576;
    private int A;
    private Drawable B;
    private int C;
    private boolean H;
    private Drawable J;
    private int K;
    private boolean O;
    private Resources.Theme P;
    private boolean Q;
    private boolean R;
    private boolean S;
    private boolean U;
    private int v;
    private Drawable z;
    private float w = 1.0f;
    private DiskCacheStrategy x = DiskCacheStrategy.AUTOMATIC;
    private Priority y = Priority.NORMAL;
    private boolean D = true;
    private int E = -1;
    private int F = -1;
    private Key G = EmptySignature.obtain();
    private boolean I = true;
    private Options L = new Options();
    private Map<Class<?>, Transformation<?>> M = new CachedHashCodeArrayMap();
    private Class<?> N = Object.class;
    private boolean T = true;

    private static boolean a(int i2, int i3) {
        return (i2 & i3) != 0;
    }

    private T c() {
        return this;
    }

    public T sizeMultiplier(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        if (this.Q) {
            return (T) mo3clone().sizeMultiplier(f2);
        }
        if (f2 < 0.0f || f2 > 1.0f) {
            throw new IllegalArgumentException("sizeMultiplier must be between 0 and 1");
        }
        this.w = f2;
        this.v |= 2;
        return (T) b();
    }

    public T useUnlimitedSourceGeneratorsPool(boolean z) {
        if (this.Q) {
            return (T) mo3clone().useUnlimitedSourceGeneratorsPool(z);
        }
        this.R = z;
        this.v |= 262144;
        return (T) b();
    }

    public T useAnimationPool(boolean z) {
        if (this.Q) {
            return (T) mo3clone().useAnimationPool(z);
        }
        this.U = z;
        this.v |= 1048576;
        return (T) b();
    }

    public T onlyRetrieveFromCache(boolean z) {
        if (this.Q) {
            return (T) mo3clone().onlyRetrieveFromCache(z);
        }
        this.S = z;
        this.v |= 524288;
        return (T) b();
    }

    public T diskCacheStrategy(DiskCacheStrategy diskCacheStrategy) {
        if (this.Q) {
            return (T) mo3clone().diskCacheStrategy(diskCacheStrategy);
        }
        this.x = (DiskCacheStrategy) Preconditions.checkNotNull(diskCacheStrategy);
        this.v |= 4;
        return (T) b();
    }

    public T priority(Priority priority) {
        if (this.Q) {
            return (T) mo3clone().priority(priority);
        }
        this.y = (Priority) Preconditions.checkNotNull(priority);
        this.v |= 8;
        return (T) b();
    }

    public T placeholder(Drawable drawable) {
        if (this.Q) {
            return (T) mo3clone().placeholder(drawable);
        }
        this.B = drawable;
        this.v |= 64;
        this.C = 0;
        this.v &= -129;
        return (T) b();
    }

    public T placeholder(int i2) {
        if (this.Q) {
            return (T) mo3clone().placeholder(i2);
        }
        this.C = i2;
        this.v |= h;
        this.B = null;
        this.v &= -65;
        return (T) b();
    }

    public T fallback(Drawable drawable) {
        if (this.Q) {
            return (T) mo3clone().fallback(drawable);
        }
        this.J = drawable;
        this.v |= 8192;
        this.K = 0;
        this.v &= -16385;
        return (T) b();
    }

    public T fallback(int i2) {
        if (this.Q) {
            return (T) mo3clone().fallback(i2);
        }
        this.K = i2;
        this.v |= 16384;
        this.J = null;
        this.v &= -8193;
        return (T) b();
    }

    public T error(Drawable drawable) {
        if (this.Q) {
            return (T) mo3clone().error(drawable);
        }
        this.z = drawable;
        this.v |= 16;
        this.A = 0;
        this.v &= -33;
        return (T) b();
    }

    public T error(int i2) {
        if (this.Q) {
            return (T) mo3clone().error(i2);
        }
        this.A = i2;
        this.v |= 32;
        this.z = null;
        this.v &= -17;
        return (T) b();
    }

    public T theme(Resources.Theme theme) {
        if (this.Q) {
            return (T) mo3clone().theme(theme);
        }
        this.P = theme;
        this.v |= 32768;
        return (T) b();
    }

    public T skipMemoryCache(boolean z) {
        if (this.Q) {
            return (T) mo3clone().skipMemoryCache(true);
        }
        this.D = !z;
        this.v |= 256;
        return (T) b();
    }

    public T override(int i2, int i3) {
        if (this.Q) {
            return (T) mo3clone().override(i2, i3);
        }
        this.F = i2;
        this.E = i3;
        this.v |= 512;
        return (T) b();
    }

    public T override(int i2) {
        return (T) override(i2, i2);
    }

    public T signature(Key key) {
        if (this.Q) {
            return (T) mo3clone().signature(key);
        }
        this.G = (Key) Preconditions.checkNotNull(key);
        this.v |= 1024;
        return (T) b();
    }

    @Override // 
    /* renamed from: clone */
    public T mo3clone() {
        try {
            T t2 = (T) super.clone();
            t2.L = new Options();
            t2.L.putAll(this.L);
            t2.M = new CachedHashCodeArrayMap();
            t2.M.putAll(this.M);
            t2.O = false;
            t2.Q = false;
            return t2;
        } catch (CloneNotSupportedException e2) {
            throw new RuntimeException(e2);
        }
    }

    public <Y> T set(Option<Y> option, Y y) {
        if (this.Q) {
            return (T) mo3clone().set(option, y);
        }
        Preconditions.checkNotNull(option);
        Preconditions.checkNotNull(y);
        this.L.set(option, y);
        return (T) b();
    }

    public T decode(Class<?> cls) {
        if (this.Q) {
            return (T) mo3clone().decode(cls);
        }
        this.N = (Class) Preconditions.checkNotNull(cls);
        this.v |= 4096;
        return (T) b();
    }

    public final boolean isTransformationAllowed() {
        return this.I;
    }

    public final boolean isTransformationSet() {
        return a(2048);
    }

    public final boolean isLocked() {
        return this.O;
    }

    public T encodeFormat(Bitmap.CompressFormat compressFormat) {
        return (T) set(BitmapEncoder.COMPRESSION_FORMAT, Preconditions.checkNotNull(compressFormat));
    }

    public T encodeQuality(@IntRange(from = ULong.MIN_VALUE, to = 100) int i2) {
        return (T) set(BitmapEncoder.COMPRESSION_QUALITY, Integer.valueOf(i2));
    }

    public T frame(@IntRange(from = ULong.MIN_VALUE) long j2) {
        return (T) set(VideoDecoder.TARGET_FRAME, Long.valueOf(j2));
    }

    public T format(DecodeFormat decodeFormat) {
        Preconditions.checkNotNull(decodeFormat);
        return (T) set(Downsampler.DECODE_FORMAT, decodeFormat).set(GifOptions.DECODE_FORMAT, decodeFormat);
    }

    public T disallowHardwareConfig() {
        return (T) set(Downsampler.ALLOW_HARDWARE_CONFIG, false);
    }

    public T downsample(DownsampleStrategy downsampleStrategy) {
        return (T) set(DownsampleStrategy.OPTION, Preconditions.checkNotNull(downsampleStrategy));
    }

    public T timeout(@IntRange(from = ULong.MIN_VALUE) int i2) {
        return (T) set(HttpGlideUrlLoader.TIMEOUT, Integer.valueOf(i2));
    }

    public T optionalCenterCrop() {
        return (T) a(DownsampleStrategy.CENTER_OUTSIDE, new CenterCrop());
    }

    public T centerCrop() {
        return (T) b(DownsampleStrategy.CENTER_OUTSIDE, new CenterCrop());
    }

    public T optionalFitCenter() {
        return (T) d(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    public T fitCenter() {
        return (T) c(DownsampleStrategy.FIT_CENTER, new FitCenter());
    }

    public T optionalCenterInside() {
        return (T) d(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    public T centerInside() {
        return (T) c(DownsampleStrategy.CENTER_INSIDE, new CenterInside());
    }

    public T optionalCircleCrop() {
        return (T) a(DownsampleStrategy.CENTER_OUTSIDE, new CircleCrop());
    }

    public T circleCrop() {
        return (T) b(DownsampleStrategy.CENTER_INSIDE, new CircleCrop());
    }

    final T a(DownsampleStrategy downsampleStrategy, Transformation<Bitmap> transformation) {
        if (this.Q) {
            return (T) mo3clone().a(downsampleStrategy, transformation);
        }
        downsample(downsampleStrategy);
        return (T) a(transformation, false);
    }

    final T b(DownsampleStrategy downsampleStrategy, Transformation<Bitmap> transformation) {
        if (this.Q) {
            return (T) mo3clone().b(downsampleStrategy, transformation);
        }
        downsample(downsampleStrategy);
        return (T) transform(transformation);
    }

    private T c(DownsampleStrategy downsampleStrategy, Transformation<Bitmap> transformation) {
        return (T) a(downsampleStrategy, transformation, true);
    }

    private T d(DownsampleStrategy downsampleStrategy, Transformation<Bitmap> transformation) {
        return (T) a(downsampleStrategy, transformation, false);
    }

    private T a(DownsampleStrategy downsampleStrategy, Transformation<Bitmap> transformation, boolean z) {
        T t2 = z ? (T) b(downsampleStrategy, transformation) : (T) a(downsampleStrategy, transformation);
        t2.T = true;
        return t2;
    }

    public T transform(Transformation<Bitmap> transformation) {
        return (T) a(transformation, true);
    }

    public T transform(Transformation<Bitmap>... transformationArr) {
        if (transformationArr.length > 1) {
            return (T) a((Transformation<Bitmap>) new MultiTransformation(transformationArr), true);
        }
        if (transformationArr.length == 1) {
            return (T) transform(transformationArr[0]);
        }
        return (T) b();
    }

    @Deprecated
    public T transforms(Transformation<Bitmap>... transformationArr) {
        return (T) a((Transformation<Bitmap>) new MultiTransformation(transformationArr), true);
    }

    public T optionalTransform(Transformation<Bitmap> transformation) {
        return (T) a(transformation, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    T a(Transformation<Bitmap> transformation, boolean z) {
        if (this.Q) {
            return (T) mo3clone().a(transformation, z);
        }
        DrawableTransformation drawableTransformation = new DrawableTransformation(transformation, z);
        a(Bitmap.class, transformation, z);
        a(Drawable.class, drawableTransformation, z);
        a(BitmapDrawable.class, drawableTransformation.asBitmapDrawable(), z);
        a(GifDrawable.class, new GifDrawableTransformation(transformation), z);
        return (T) b();
    }

    public <Y> T optionalTransform(Class<Y> cls, Transformation<Y> transformation) {
        return (T) a((Class) cls, (Transformation) transformation, false);
    }

    <Y> T a(Class<Y> cls, Transformation<Y> transformation, boolean z) {
        if (this.Q) {
            return (T) mo3clone().a(cls, transformation, z);
        }
        Preconditions.checkNotNull(cls);
        Preconditions.checkNotNull(transformation);
        this.M.put(cls, transformation);
        this.v |= 2048;
        this.I = true;
        this.v |= 65536;
        this.T = false;
        if (z) {
            this.v |= r;
            this.H = true;
        }
        return (T) b();
    }

    public <Y> T transform(Class<Y> cls, Transformation<Y> transformation) {
        return (T) a((Class) cls, (Transformation) transformation, true);
    }

    public T dontTransform() {
        if (this.Q) {
            return (T) mo3clone().dontTransform();
        }
        this.M.clear();
        this.v &= -2049;
        this.H = false;
        this.v &= -131073;
        this.I = false;
        this.v |= 65536;
        this.T = true;
        return (T) b();
    }

    public T dontAnimate() {
        return (T) set(GifOptions.DISABLE_ANIMATION, true);
    }

    public T apply(BaseRequestOptions<?> baseRequestOptions) {
        if (this.Q) {
            return (T) mo3clone().apply(baseRequestOptions);
        }
        if (a(baseRequestOptions.v, 2)) {
            this.w = baseRequestOptions.w;
        }
        if (a(baseRequestOptions.v, 262144)) {
            this.R = baseRequestOptions.R;
        }
        if (a(baseRequestOptions.v, 1048576)) {
            this.U = baseRequestOptions.U;
        }
        if (a(baseRequestOptions.v, 4)) {
            this.x = baseRequestOptions.x;
        }
        if (a(baseRequestOptions.v, 8)) {
            this.y = baseRequestOptions.y;
        }
        if (a(baseRequestOptions.v, 16)) {
            this.z = baseRequestOptions.z;
            this.A = 0;
            this.v &= -33;
        }
        if (a(baseRequestOptions.v, 32)) {
            this.A = baseRequestOptions.A;
            this.z = null;
            this.v &= -17;
        }
        if (a(baseRequestOptions.v, 64)) {
            this.B = baseRequestOptions.B;
            this.C = 0;
            this.v &= -129;
        }
        if (a(baseRequestOptions.v, h)) {
            this.C = baseRequestOptions.C;
            this.B = null;
            this.v &= -65;
        }
        if (a(baseRequestOptions.v, 256)) {
            this.D = baseRequestOptions.D;
        }
        if (a(baseRequestOptions.v, 512)) {
            this.F = baseRequestOptions.F;
            this.E = baseRequestOptions.E;
        }
        if (a(baseRequestOptions.v, 1024)) {
            this.G = baseRequestOptions.G;
        }
        if (a(baseRequestOptions.v, 4096)) {
            this.N = baseRequestOptions.N;
        }
        if (a(baseRequestOptions.v, 8192)) {
            this.J = baseRequestOptions.J;
            this.K = 0;
            this.v &= -16385;
        }
        if (a(baseRequestOptions.v, 16384)) {
            this.K = baseRequestOptions.K;
            this.J = null;
            this.v &= -8193;
        }
        if (a(baseRequestOptions.v, 32768)) {
            this.P = baseRequestOptions.P;
        }
        if (a(baseRequestOptions.v, 65536)) {
            this.I = baseRequestOptions.I;
        }
        if (a(baseRequestOptions.v, r)) {
            this.H = baseRequestOptions.H;
        }
        if (a(baseRequestOptions.v, 2048)) {
            this.M.putAll(baseRequestOptions.M);
            this.T = baseRequestOptions.T;
        }
        if (a(baseRequestOptions.v, 524288)) {
            this.S = baseRequestOptions.S;
        }
        if (!this.I) {
            this.M.clear();
            this.v &= -2049;
            this.H = false;
            this.v &= -131073;
            this.T = true;
        }
        this.v |= baseRequestOptions.v;
        this.L.putAll(baseRequestOptions.L);
        return (T) b();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof BaseRequestOptions)) {
            return false;
        }
        BaseRequestOptions baseRequestOptions = (BaseRequestOptions) obj;
        return Float.compare(baseRequestOptions.w, this.w) == 0 && this.A == baseRequestOptions.A && Util.bothNullOrEqual(this.z, baseRequestOptions.z) && this.C == baseRequestOptions.C && Util.bothNullOrEqual(this.B, baseRequestOptions.B) && this.K == baseRequestOptions.K && Util.bothNullOrEqual(this.J, baseRequestOptions.J) && this.D == baseRequestOptions.D && this.E == baseRequestOptions.E && this.F == baseRequestOptions.F && this.H == baseRequestOptions.H && this.I == baseRequestOptions.I && this.R == baseRequestOptions.R && this.S == baseRequestOptions.S && this.x.equals(baseRequestOptions.x) && this.y == baseRequestOptions.y && this.L.equals(baseRequestOptions.L) && this.M.equals(baseRequestOptions.M) && this.N.equals(baseRequestOptions.N) && Util.bothNullOrEqual(this.G, baseRequestOptions.G) && Util.bothNullOrEqual(this.P, baseRequestOptions.P);
    }

    public int hashCode() {
        return Util.hashCode(this.P, Util.hashCode(this.G, Util.hashCode(this.N, Util.hashCode(this.M, Util.hashCode(this.L, Util.hashCode(this.y, Util.hashCode(this.x, Util.hashCode(this.S, Util.hashCode(this.R, Util.hashCode(this.I, Util.hashCode(this.H, Util.hashCode(this.F, Util.hashCode(this.E, Util.hashCode(this.D, Util.hashCode(this.J, Util.hashCode(this.K, Util.hashCode(this.B, Util.hashCode(this.C, Util.hashCode(this.z, Util.hashCode(this.A, Util.hashCode(this.w)))))))))))))))))))));
    }

    public T lock() {
        this.O = true;
        return (T) c();
    }

    public T autoClone() {
        if (this.O && !this.Q) {
            throw new IllegalStateException("You cannot auto lock an already locked options object, try clone() first");
        }
        this.Q = true;
        return (T) lock();
    }

    private T b() {
        if (this.O) {
            throw new IllegalStateException("You cannot modify locked T, consider clone()");
        }
        return (T) c();
    }

    protected boolean isAutoCloneEnabled() {
        return this.Q;
    }

    public final boolean isDiskCacheStrategySet() {
        return a(4);
    }

    public final boolean isSkipMemoryCacheSet() {
        return a(256);
    }

    public final Map<Class<?>, Transformation<?>> getTransformations() {
        return this.M;
    }

    public final boolean isTransformationRequired() {
        return this.H;
    }

    public final Options getOptions() {
        return this.L;
    }

    public final Class<?> getResourceClass() {
        return this.N;
    }

    public final DiskCacheStrategy getDiskCacheStrategy() {
        return this.x;
    }

    public final Drawable getErrorPlaceholder() {
        return this.z;
    }

    public final int getErrorId() {
        return this.A;
    }

    public final int getPlaceholderId() {
        return this.C;
    }

    public final Drawable getPlaceholderDrawable() {
        return this.B;
    }

    public final int getFallbackId() {
        return this.K;
    }

    public final Drawable getFallbackDrawable() {
        return this.J;
    }

    public final Resources.Theme getTheme() {
        return this.P;
    }

    public final boolean isMemoryCacheable() {
        return this.D;
    }

    public final Key getSignature() {
        return this.G;
    }

    public final boolean isPrioritySet() {
        return a(8);
    }

    public final Priority getPriority() {
        return this.y;
    }

    public final int getOverrideWidth() {
        return this.F;
    }

    public final boolean isValidOverride() {
        return Util.isValidDimensions(this.F, this.E);
    }

    public final int getOverrideHeight() {
        return this.E;
    }

    public final float getSizeMultiplier() {
        return this.w;
    }

    boolean a() {
        return this.T;
    }

    private boolean a(int i2) {
        return a(this.v, i2);
    }

    public final boolean getUseUnlimitedSourceGeneratorsPool() {
        return this.R;
    }

    public final boolean getUseAnimationPool() {
        return this.U;
    }

    public final boolean getOnlyRetrieveFromCache() {
        return this.S;
    }
}
