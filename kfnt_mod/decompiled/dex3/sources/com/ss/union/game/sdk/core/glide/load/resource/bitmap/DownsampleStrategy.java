package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import com.ss.union.game.sdk.core.glide.load.Option;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class DownsampleStrategy {
    public static final DownsampleStrategy FIT_CENTER = new e();
    public static final DownsampleStrategy CENTER_OUTSIDE = new d();
    public static final DownsampleStrategy AT_LEAST = new a();
    public static final DownsampleStrategy AT_MOST = new b();
    public static final DownsampleStrategy CENTER_INSIDE = new c();
    public static final DownsampleStrategy NONE = new f();
    public static final DownsampleStrategy DEFAULT = CENTER_OUTSIDE;
    public static final Option<DownsampleStrategy> OPTION = Option.memory("resource.bitmap.Downsampler.DownsampleStrategy", DEFAULT);

    public enum SampleSizeRounding {
        MEMORY,
        QUALITY
    }

    public abstract SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4);

    public abstract float getScaleFactor(int i, int i2, int i3, int i4);

    private static class e extends DownsampleStrategy {
        e() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return Math.min(i3 / i, i4 / i2);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class d extends DownsampleStrategy {
        d() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return Math.max(i3 / i, i4 / i2);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class a extends DownsampleStrategy {
        a() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            if (Math.min(i2 / i4, i / i3) == 0) {
                return 1.0f;
            }
            return 1.0f / Integer.highestOneBit(r1);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class b extends DownsampleStrategy {
        b() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            int iCeil = (int) Math.ceil(Math.max(i2 / i4, i / i3));
            return 1.0f / (r2 << (Math.max(1, Integer.highestOneBit(iCeil)) >= iCeil ? 0 : 1));
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.MEMORY;
        }
    }

    private static class f extends DownsampleStrategy {
        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return 1.0f;
        }

        f() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }

    private static class c extends DownsampleStrategy {
        c() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public float getScaleFactor(int i, int i2, int i3, int i4) {
            return Math.min(1.0f, FIT_CENTER.getScaleFactor(i, i2, i3, i4));
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.DownsampleStrategy
        public SampleSizeRounding getSampleSizeRounding(int i, int i2, int i3, int i4) {
            return SampleSizeRounding.QUALITY;
        }
    }
}
