package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.DecodeFormat;
import com.ss.union.game.sdk.core.glide.load.ImageHeaderParser;
import com.ss.union.game.sdk.core.glide.load.ImageHeaderParserUtils;
import com.ss.union.game.sdk.core.glide.load.Option;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy;
import com.ss.union.game.sdk.core.glide.util.LogTime;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.Util;
import com.umeng.message.proguard.l;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Downsampler {
    static final String a = "Downsampler";
    private static final int h = 10485760;
    private final BitmapPool i;
    private final DisplayMetrics j;
    private final ArrayPool k;
    private final List<ImageHeaderParser> l;
    private final b m = b.a();
    public static final Option<DecodeFormat> DECODE_FORMAT = Option.memory("resource.bitmap.Downsampler.DecodeFormat", DecodeFormat.DEFAULT);

    @Deprecated
    public static final Option<DownsampleStrategy> DOWNSAMPLE_STRATEGY = DownsampleStrategy.OPTION;
    public static final Option<Boolean> FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS = Option.memory("resource.bitmap.Downsampler.FixBitmapSize", false);
    public static final Option<Boolean> ALLOW_HARDWARE_CONFIG = Option.memory("resource.bitmap.Downsampler.AllowHardwareDecode", false);
    private static final String b = "image/vnd.wap.wbmp";
    private static final String c = "image/x-ico";
    private static final Set<String> d = Collections.unmodifiableSet(new HashSet(Arrays.asList(b, c)));
    private static final DecodeCallbacks e = new DecodeCallbacks() { // from class: com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler.1
        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onObtainBounds() {
        }
    };
    private static final Set<ImageHeaderParser.ImageType> f = Collections.unmodifiableSet(EnumSet.of(ImageHeaderParser.ImageType.JPEG, ImageHeaderParser.ImageType.PNG_A, ImageHeaderParser.ImageType.PNG));
    private static final Queue<BitmapFactory.Options> g = Util.createQueue(0);

    public interface DecodeCallbacks {
        void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException;

        void onObtainBounds();
    }

    private static int c(double d2) {
        return (int) (d2 + 0.5d);
    }

    public boolean handles(InputStream inputStream) {
        return true;
    }

    public boolean handles(ByteBuffer byteBuffer) {
        return true;
    }

    public Downsampler(List<ImageHeaderParser> list, DisplayMetrics displayMetrics, BitmapPool bitmapPool, ArrayPool arrayPool) {
        this.l = list;
        this.j = (DisplayMetrics) Preconditions.checkNotNull(displayMetrics);
        this.i = (BitmapPool) Preconditions.checkNotNull(bitmapPool);
        this.k = (ArrayPool) Preconditions.checkNotNull(arrayPool);
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options) throws IOException {
        return decode(inputStream, i, i2, options, e);
    }

    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options, DecodeCallbacks decodeCallbacks) throws IOException {
        Preconditions.checkArgument(inputStream.markSupported(), "You must provide an InputStream that supports mark()");
        byte[] bArr = (byte[]) this.k.get(65536, byte[].class);
        BitmapFactory.Options optionsA = a();
        optionsA.inTempStorage = bArr;
        DecodeFormat decodeFormat = (DecodeFormat) options.get(DECODE_FORMAT);
        try {
            return BitmapResource.obtain(a(inputStream, optionsA, (DownsampleStrategy) options.get(DownsampleStrategy.OPTION), decodeFormat, options.get(ALLOW_HARDWARE_CONFIG) != null && ((Boolean) options.get(ALLOW_HARDWARE_CONFIG)).booleanValue(), i, i2, ((Boolean) options.get(FIX_BITMAP_SIZE_TO_REQUESTED_DIMENSIONS)).booleanValue(), decodeCallbacks), this.i);
        } finally {
            c(optionsA);
            this.k.put(bArr);
        }
    }

    private Bitmap a(InputStream inputStream, BitmapFactory.Options options, DownsampleStrategy downsampleStrategy, DecodeFormat decodeFormat, boolean z, int i, int i2, boolean z2, DecodeCallbacks decodeCallbacks) throws IOException {
        int i3;
        int i4;
        Downsampler downsampler;
        int iRound;
        int iRound2;
        int i5;
        long logTime = LogTime.getLogTime();
        int[] iArrA = a(inputStream, options, decodeCallbacks, this.i);
        int i6 = iArrA[0];
        int i7 = iArrA[1];
        String str = options.outMimeType;
        boolean z3 = (i6 == -1 || i7 == -1) ? false : z;
        int orientation = ImageHeaderParserUtils.getOrientation(this.l, inputStream, this.k);
        int exifOrientationDegrees = TransformationUtils.getExifOrientationDegrees(orientation);
        boolean zIsExifOrientationRequired = TransformationUtils.isExifOrientationRequired(orientation);
        if (i == Integer.MIN_VALUE) {
            i3 = i2;
            i4 = i6;
        } else {
            i3 = i2;
            i4 = i;
        }
        int i8 = i3 == Integer.MIN_VALUE ? i7 : i3;
        ImageHeaderParser.ImageType type = ImageHeaderParserUtils.getType(this.l, inputStream, this.k);
        a(type, inputStream, decodeCallbacks, this.i, downsampleStrategy, exifOrientationDegrees, i6, i7, i4, i8, options);
        a(inputStream, decodeFormat, z3, zIsExifOrientationRequired, options, i4, i8);
        boolean z4 = Build.VERSION.SDK_INT >= 19;
        if (options.inSampleSize == 1 || z4) {
            downsampler = this;
            if (downsampler.a(type)) {
                if (i6 < 0 || i7 < 0 || !z2 || !z4) {
                    float f2 = a(options) ? options.inTargetDensity / options.inDensity : 1.0f;
                    int i9 = options.inSampleSize;
                    float f3 = i9;
                    int iCeil = (int) Math.ceil(i6 / f3);
                    int iCeil2 = (int) Math.ceil(i7 / f3);
                    iRound = Math.round(iCeil * f2);
                    iRound2 = Math.round(iCeil2 * f2);
                    if (Log.isLoggable(a, 2)) {
                        Log.v(a, "Calculated target [" + iRound + "x" + iRound2 + "] for source [" + i6 + "x" + i7 + "], sampleSize: " + i9 + ", targetDensity: " + options.inTargetDensity + ", density: " + options.inDensity + ", density multiplier: " + f2);
                    }
                } else {
                    iRound = i4;
                    iRound2 = i8;
                }
                if (iRound > 0 && iRound2 > 0) {
                    a(options, downsampler.i, iRound, iRound2);
                }
            }
        } else {
            downsampler = this;
        }
        Bitmap bitmapB = b(inputStream, options, decodeCallbacks, downsampler.i);
        decodeCallbacks.onDecodeComplete(downsampler.i, bitmapB);
        if (Log.isLoggable(a, 2)) {
            i5 = orientation;
            a(i6, i7, str, options, bitmapB, i, i2, logTime);
        } else {
            i5 = orientation;
        }
        Bitmap bitmapRotateImageExif = null;
        if (bitmapB != null) {
            bitmapB.setDensity(downsampler.j.densityDpi);
            bitmapRotateImageExif = TransformationUtils.rotateImageExif(downsampler.i, bitmapB, i5);
            if (!bitmapB.equals(bitmapRotateImageExif)) {
                downsampler.i.put(bitmapB);
            }
        }
        return bitmapRotateImageExif;
    }

    private static void a(ImageHeaderParser.ImageType imageType, InputStream inputStream, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool, DownsampleStrategy downsampleStrategy, int i, int i2, int i3, int i4, int i5, BitmapFactory.Options options) throws IOException {
        float scaleFactor;
        int iMin;
        int i6;
        int iFloor;
        int iFloor2;
        if (i2 <= 0 || i3 <= 0) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Unable to determine dimensions for: " + imageType + " with target [" + i4 + "x" + i5 + "]");
                return;
            }
            return;
        }
        if (i == 90 || i == 270) {
            scaleFactor = downsampleStrategy.getScaleFactor(i3, i2, i4, i5);
        } else {
            scaleFactor = downsampleStrategy.getScaleFactor(i2, i3, i4, i5);
        }
        if (scaleFactor <= 0.0f) {
            throw new IllegalArgumentException("Cannot scale with factor: " + scaleFactor + " from: " + downsampleStrategy + ", source: [" + i2 + "x" + i3 + "], target: [" + i4 + "x" + i5 + "]");
        }
        DownsampleStrategy.SampleSizeRounding sampleSizeRounding = downsampleStrategy.getSampleSizeRounding(i2, i3, i4, i5);
        if (sampleSizeRounding == null) {
            throw new IllegalArgumentException("Cannot round with null rounding");
        }
        float f2 = i2;
        float f3 = i3;
        int iC = i2 / c(scaleFactor * f2);
        int iC2 = i3 / c(scaleFactor * f3);
        if (sampleSizeRounding == DownsampleStrategy.SampleSizeRounding.MEMORY) {
            iMin = Math.max(iC, iC2);
        } else {
            iMin = Math.min(iC, iC2);
        }
        if (Build.VERSION.SDK_INT > 23 || !d.contains(options.outMimeType)) {
            int iMax = Math.max(1, Integer.highestOneBit(iMin));
            i6 = (sampleSizeRounding != DownsampleStrategy.SampleSizeRounding.MEMORY || ((float) iMax) >= 1.0f / scaleFactor) ? iMax : iMax << 1;
        } else {
            i6 = 1;
        }
        options.inSampleSize = i6;
        if (imageType == ImageHeaderParser.ImageType.JPEG) {
            float fMin = Math.min(i6, 8);
            iFloor = (int) Math.ceil(f2 / fMin);
            iFloor2 = (int) Math.ceil(f3 / fMin);
            int i7 = i6 / 8;
            if (i7 > 0) {
                iFloor /= i7;
                iFloor2 /= i7;
            }
        } else if (imageType == ImageHeaderParser.ImageType.PNG || imageType == ImageHeaderParser.ImageType.PNG_A) {
            float f4 = i6;
            iFloor = (int) Math.floor(f2 / f4);
            iFloor2 = (int) Math.floor(f3 / f4);
        } else if (imageType == ImageHeaderParser.ImageType.WEBP || imageType == ImageHeaderParser.ImageType.WEBP_A) {
            if (Build.VERSION.SDK_INT >= 24) {
                float f5 = i6;
                iFloor = Math.round(f2 / f5);
                iFloor2 = Math.round(f3 / f5);
            } else {
                float f6 = i6;
                iFloor = (int) Math.floor(f2 / f6);
                iFloor2 = (int) Math.floor(f3 / f6);
            }
        } else if (i2 % i6 != 0 || i3 % i6 != 0) {
            int[] iArrA = a(inputStream, options, decodeCallbacks, bitmapPool);
            iFloor = iArrA[0];
            iFloor2 = iArrA[1];
        } else {
            iFloor = i2 / i6;
            iFloor2 = i3 / i6;
        }
        double scaleFactor2 = downsampleStrategy.getScaleFactor(iFloor, iFloor2, i4, i5);
        if (Build.VERSION.SDK_INT >= 19) {
            options.inTargetDensity = a(scaleFactor2);
            options.inDensity = b(scaleFactor2);
        }
        if (a(options)) {
            options.inScaled = true;
        } else {
            options.inTargetDensity = 0;
            options.inDensity = 0;
        }
        if (Log.isLoggable(a, 2)) {
            Log.v(a, "Calculate scaling, source: [" + i2 + "x" + i3 + "], target: [" + i4 + "x" + i5 + "], power of two scaled: [" + iFloor + "x" + iFloor2 + "], exact scale factor: " + scaleFactor + ", power of 2 sample size: " + i6 + ", adjusted scale factor: " + scaleFactor2 + ", target density: " + options.inTargetDensity + ", density: " + options.inDensity);
        }
    }

    private static int a(double d2) {
        return c((d2 / (r1 / r0)) * c(b(d2) * d2));
    }

    private static int b(double d2) {
        if (d2 > 1.0d) {
            d2 = 1.0d / d2;
        }
        return (int) Math.round(d2 * 2.147483647E9d);
    }

    private boolean a(ImageHeaderParser.ImageType imageType) {
        if (Build.VERSION.SDK_INT >= 19) {
            return true;
        }
        return f.contains(imageType);
    }

    private void a(InputStream inputStream, DecodeFormat decodeFormat, boolean z, boolean z2, BitmapFactory.Options options, int i, int i2) {
        boolean zHasAlpha;
        if (this.m.a(i, i2, options, decodeFormat, z, z2)) {
            return;
        }
        if (decodeFormat == DecodeFormat.PREFER_ARGB_8888 || Build.VERSION.SDK_INT == 16) {
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return;
        }
        try {
            zHasAlpha = ImageHeaderParserUtils.getType(this.l, inputStream, this.k).hasAlpha();
        } catch (IOException e2) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Cannot determine whether the image has alpha or not from header, format " + decodeFormat, e2);
            }
            zHasAlpha = false;
        }
        options.inPreferredConfig = zHasAlpha ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        if (options.inPreferredConfig == Bitmap.Config.RGB_565) {
            options.inDither = true;
        }
    }

    private static int[] a(InputStream inputStream, BitmapFactory.Options options, DecodeCallbacks decodeCallbacks, BitmapPool bitmapPool) throws IOException {
        options.inJustDecodeBounds = true;
        b(inputStream, options, decodeCallbacks, bitmapPool);
        options.inJustDecodeBounds = false;
        return new int[]{options.outWidth, options.outHeight};
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:?, code lost:
    
        throw r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static android.graphics.Bitmap b(java.io.InputStream r5, android.graphics.BitmapFactory.Options r6, com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler.DecodeCallbacks r7, com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool r8) throws java.io.IOException {
        /*
            boolean r0 = r6.inJustDecodeBounds
            if (r0 == 0) goto La
            r0 = 10485760(0xa00000, float:1.469368E-38)
            r5.mark(r0)
            goto Ld
        La:
            r7.onObtainBounds()
        Ld:
            int r0 = r6.outWidth
            int r1 = r6.outHeight
            java.lang.String r2 = r6.outMimeType
            java.util.concurrent.locks.Lock r3 = com.ss.union.game.sdk.core.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r3.lock()
            r3 = 0
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeStream(r5, r3, r6)     // Catch: java.lang.Throwable -> L2e java.lang.IllegalArgumentException -> L30
            java.util.concurrent.locks.Lock r7 = com.ss.union.game.sdk.core.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r7.unlock()
            boolean r6 = r6.inJustDecodeBounds
            if (r6 == 0) goto L2d
            r5.reset()
        L2d:
            return r4
        L2e:
            r5 = move-exception
            goto L61
        L30:
            r4 = move-exception
            java.io.IOException r0 = a(r4, r0, r1, r2, r6)     // Catch: java.lang.Throwable -> L2e
            java.lang.String r1 = "Downsampler"
            r2 = 3
            boolean r1 = android.util.Log.isLoggable(r1, r2)     // Catch: java.lang.Throwable -> L2e
            if (r1 == 0) goto L45
            java.lang.String r1 = "Downsampler"
            java.lang.String r2 = "Failed to decode with inBitmap, trying again without Bitmap re-use"
            android.util.Log.d(r1, r2, r0)     // Catch: java.lang.Throwable -> L2e
        L45:
            android.graphics.Bitmap r1 = r6.inBitmap     // Catch: java.lang.Throwable -> L2e
            if (r1 == 0) goto L60
            r5.reset()     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L5f
            android.graphics.Bitmap r1 = r6.inBitmap     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L5f
            r8.put(r1)     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L5f
            r6.inBitmap = r3     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L5f
            android.graphics.Bitmap r5 = b(r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L5f
            java.util.concurrent.locks.Lock r6 = com.ss.union.game.sdk.core.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r6.unlock()
            return r5
        L5f:
            throw r0     // Catch: java.lang.Throwable -> L2e
        L60:
            throw r0     // Catch: java.lang.Throwable -> L2e
        L61:
            java.util.concurrent.locks.Lock r6 = com.ss.union.game.sdk.core.glide.load.resource.bitmap.TransformationUtils.getBitmapDrawableLock()
            r6.unlock()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler.b(java.io.InputStream, android.graphics.BitmapFactory$Options, com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler$DecodeCallbacks, com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool):android.graphics.Bitmap");
    }

    private static boolean a(BitmapFactory.Options options) {
        return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity;
    }

    private static void a(int i, int i2, String str, BitmapFactory.Options options, Bitmap bitmap, int i3, int i4, long j) {
        Log.v(a, "Decoded " + a(bitmap) + " from [" + i + "x" + i2 + "] " + str + " with inBitmap " + b(options) + " for [" + i3 + "x" + i4 + "], sample size: " + options.inSampleSize + ", density: " + options.inDensity + ", target density: " + options.inTargetDensity + ", thread: " + Thread.currentThread().getName() + ", duration: " + LogTime.getElapsedMillis(j));
    }

    private static String b(BitmapFactory.Options options) {
        return a(options.inBitmap);
    }

    @TargetApi(19)
    private static String a(Bitmap bitmap) {
        String str;
        if (bitmap == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 19) {
            str = " (" + bitmap.getAllocationByteCount() + l.t;
        } else {
            str = "";
        }
        return "[" + bitmap.getWidth() + "x" + bitmap.getHeight() + "] " + bitmap.getConfig() + str;
    }

    private static IOException a(IllegalArgumentException illegalArgumentException, int i, int i2, String str, BitmapFactory.Options options) {
        return new IOException("Exception decoding bitmap, outWidth: " + i + ", outHeight: " + i2 + ", outMimeType: " + str + ", inBitmap: " + b(options), illegalArgumentException);
    }

    @TargetApi(26)
    private static void a(BitmapFactory.Options options, BitmapPool bitmapPool, int i, int i2) {
        Bitmap.Config config;
        if (Build.VERSION.SDK_INT < 26) {
            config = null;
        } else if (options.inPreferredConfig == Bitmap.Config.HARDWARE) {
            return;
        } else {
            config = options.outConfig;
        }
        if (config == null) {
            config = options.inPreferredConfig;
        }
        options.inBitmap = bitmapPool.getDirty(i, i2, config);
    }

    private static synchronized BitmapFactory.Options a() {
        BitmapFactory.Options optionsPoll;
        synchronized (g) {
            optionsPoll = g.poll();
        }
        if (optionsPoll == null) {
            optionsPoll = new BitmapFactory.Options();
            d(optionsPoll);
        }
        return optionsPoll;
    }

    private static void c(BitmapFactory.Options options) {
        d(options);
        synchronized (g) {
            g.offer(options);
        }
    }

    private static void d(BitmapFactory.Options options) {
        options.inTempStorage = null;
        options.inDither = false;
        options.inScaled = false;
        options.inSampleSize = 1;
        options.inPreferredConfig = null;
        options.inJustDecodeBounds = false;
        options.inDensity = 0;
        options.inTargetDensity = 0;
        options.outWidth = 0;
        options.outHeight = 0;
        options.outMimeType = null;
        options.inBitmap = null;
        options.inMutable = true;
    }
}
