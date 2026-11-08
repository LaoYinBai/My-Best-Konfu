package com.ss.union.game.sdk.core.glide.request;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DecodeFormat;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.Option;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.engine.DiskCacheStrategy;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy;
import kotlin.ULong;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RequestOptions extends BaseRequestOptions<RequestOptions> {
    private static RequestOptions a;
    private static RequestOptions b;
    private static RequestOptions c;
    private static RequestOptions d;
    private static RequestOptions e;
    private static RequestOptions f;
    private static RequestOptions g;
    private static RequestOptions h;

    public static RequestOptions sizeMultiplierOf(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        return new RequestOptions().sizeMultiplier(f2);
    }

    public static RequestOptions diskCacheStrategyOf(DiskCacheStrategy diskCacheStrategy) {
        return new RequestOptions().diskCacheStrategy(diskCacheStrategy);
    }

    public static RequestOptions priorityOf(Priority priority) {
        return new RequestOptions().priority(priority);
    }

    public static RequestOptions placeholderOf(Drawable drawable) {
        return new RequestOptions().placeholder(drawable);
    }

    public static RequestOptions placeholderOf(int i) {
        return new RequestOptions().placeholder(i);
    }

    public static RequestOptions errorOf(Drawable drawable) {
        return new RequestOptions().error(drawable);
    }

    public static RequestOptions errorOf(int i) {
        return new RequestOptions().error(i);
    }

    public static RequestOptions skipMemoryCacheOf(boolean z) {
        if (z) {
            if (a == null) {
                a = new RequestOptions().skipMemoryCache(true).autoClone();
            }
            return a;
        }
        if (b == null) {
            b = new RequestOptions().skipMemoryCache(false).autoClone();
        }
        return b;
    }

    public static RequestOptions overrideOf(@IntRange(from = ULong.MIN_VALUE) int i, @IntRange(from = ULong.MIN_VALUE) int i2) {
        return new RequestOptions().override(i, i2);
    }

    public static RequestOptions overrideOf(@IntRange(from = ULong.MIN_VALUE) int i) {
        return overrideOf(i, i);
    }

    public static RequestOptions signatureOf(Key key) {
        return new RequestOptions().signature(key);
    }

    public static RequestOptions fitCenterTransform() {
        if (c == null) {
            c = new RequestOptions().fitCenter().autoClone();
        }
        return c;
    }

    public static RequestOptions centerInsideTransform() {
        if (d == null) {
            d = new RequestOptions().centerInside().autoClone();
        }
        return d;
    }

    public static RequestOptions centerCropTransform() {
        if (e == null) {
            e = new RequestOptions().centerCrop().autoClone();
        }
        return e;
    }

    public static RequestOptions circleCropTransform() {
        if (f == null) {
            f = new RequestOptions().circleCrop().autoClone();
        }
        return f;
    }

    public static RequestOptions bitmapTransform(Transformation<Bitmap> transformation) {
        return new RequestOptions().transform(transformation);
    }

    public static RequestOptions noTransformation() {
        if (g == null) {
            g = new RequestOptions().dontTransform().autoClone();
        }
        return g;
    }

    public static <T> RequestOptions option(Option<T> option, T t) {
        return new RequestOptions().set(option, t);
    }

    public static RequestOptions decodeTypeOf(Class<?> cls) {
        return new RequestOptions().decode(cls);
    }

    public static RequestOptions formatOf(DecodeFormat decodeFormat) {
        return new RequestOptions().format(decodeFormat);
    }

    public static RequestOptions frameOf(@IntRange(from = ULong.MIN_VALUE) long j) {
        return new RequestOptions().frame(j);
    }

    public static RequestOptions downsampleOf(DownsampleStrategy downsampleStrategy) {
        return new RequestOptions().downsample(downsampleStrategy);
    }

    public static RequestOptions timeoutOf(@IntRange(from = ULong.MIN_VALUE) int i) {
        return new RequestOptions().timeout(i);
    }

    public static RequestOptions encodeQualityOf(@IntRange(from = ULong.MIN_VALUE, to = 100) int i) {
        return new RequestOptions().encodeQuality(i);
    }

    public static RequestOptions encodeFormatOf(Bitmap.CompressFormat compressFormat) {
        return new RequestOptions().encodeFormat(compressFormat);
    }

    public static RequestOptions noAnimation() {
        if (h == null) {
            h = new RequestOptions().dontAnimate().autoClone();
        }
        return h;
    }
}
