package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LruBitmapPool implements BitmapPool {
    private static final String a = "LruBitmapPool";
    private static final Bitmap.Config b = Bitmap.Config.ARGB_8888;
    private final d c;
    private final Set<Bitmap.Config> d;
    private final long e;
    private final a f;
    private long g;
    private long h;
    private int i;
    private int j;
    private int k;
    private int l;

    private interface a {
        void a(Bitmap bitmap);

        void b(Bitmap bitmap);
    }

    LruBitmapPool(long j, d dVar, Set<Bitmap.Config> set) {
        this.e = j;
        this.g = j;
        this.c = dVar;
        this.d = set;
        this.f = new b();
    }

    public LruBitmapPool(long j) {
        this(j, d(), e());
    }

    public LruBitmapPool(long j, Set<Bitmap.Config> set) {
        this(j, d(), set);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public long getMaxSize() {
        return this.g;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public synchronized void setSizeMultiplier(float f) {
        this.g = Math.round(this.e * f);
        a();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public synchronized void put(Bitmap bitmap) {
        if (bitmap == null) {
            throw new NullPointerException("Bitmap must not be null");
        }
        if (bitmap.isRecycled()) {
            throw new IllegalStateException("Cannot pool recycled bitmap");
        }
        if (bitmap.isMutable() && this.c.getSize(bitmap) <= this.g && this.d.contains(bitmap.getConfig())) {
            int size = this.c.getSize(bitmap);
            this.c.put(bitmap);
            this.f.a(bitmap);
            this.k++;
            this.h += size;
            if (Log.isLoggable(a, 2)) {
                Log.v(a, "Put bitmap in pool=" + this.c.logBitmap(bitmap));
            }
            b();
            a();
            return;
        }
        if (Log.isLoggable(a, 2)) {
            Log.v(a, "Reject bitmap from pool, bitmap: " + this.c.logBitmap(bitmap) + ", is mutable: " + bitmap.isMutable() + ", is allowed config: " + this.d.contains(bitmap.getConfig()));
        }
        bitmap.recycle();
    }

    private void a() {
        a(this.g);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        Bitmap bitmapB = b(i, i2, config);
        if (bitmapB != null) {
            bitmapB.eraseColor(0);
            return bitmapB;
        }
        return a(i, i2, config);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        Bitmap bitmapB = b(i, i2, config);
        return bitmapB == null ? a(i, i2, config) : bitmapB;
    }

    private static Bitmap a(int i, int i2, Bitmap.Config config) {
        if (config == null) {
            config = b;
        }
        return Bitmap.createBitmap(i, i2, config);
    }

    @TargetApi(26)
    private static void a(Bitmap.Config config) {
        if (Build.VERSION.SDK_INT >= 26 && config == Bitmap.Config.HARDWARE) {
            throw new IllegalArgumentException("Cannot create a mutable Bitmap with config: " + config + ". Consider setting Downsampler#ALLOW_HARDWARE_CONFIG to false in your RequestOptions and/or in GlideBuilder.setDefaultRequestOptions");
        }
    }

    private synchronized Bitmap b(int i, int i2, Bitmap.Config config) {
        Bitmap bitmap;
        a(config);
        bitmap = this.c.get(i, i2, config != null ? config : b);
        if (bitmap == null) {
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Missing bitmap=" + this.c.logBitmap(i, i2, config));
            }
            this.j++;
        } else {
            this.i++;
            this.h -= this.c.getSize(bitmap);
            this.f.b(bitmap);
            a(bitmap);
        }
        if (Log.isLoggable(a, 2)) {
            Log.v(a, "Get bitmap=" + this.c.logBitmap(i, i2, config));
        }
        b();
        return bitmap;
    }

    private static void a(Bitmap bitmap) {
        bitmap.setHasAlpha(true);
        b(bitmap);
    }

    @TargetApi(19)
    private static void b(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= 19) {
            bitmap.setPremultiplied(true);
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public void clearMemory() {
        if (Log.isLoggable(a, 3)) {
            Log.d(a, "clearMemory");
        }
        a(0L);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    @SuppressLint({"InlinedApi"})
    public void trimMemory(int i) {
        if (Log.isLoggable(a, 3)) {
            Log.d(a, "trimMemory, level=" + i);
        }
        if (i >= 40) {
            clearMemory();
        } else if (i >= 20 || i == 15) {
            a(getMaxSize() / 2);
        }
    }

    private synchronized void a(long j) {
        while (this.h > j) {
            Bitmap bitmapRemoveLast = this.c.removeLast();
            if (bitmapRemoveLast == null) {
                if (Log.isLoggable(a, 5)) {
                    Log.w(a, "Size mismatch, resetting");
                    c();
                }
                this.h = 0L;
                return;
            }
            this.f.b(bitmapRemoveLast);
            this.h -= this.c.getSize(bitmapRemoveLast);
            this.l++;
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "Evicting bitmap=" + this.c.logBitmap(bitmapRemoveLast));
            }
            b();
            bitmapRemoveLast.recycle();
        }
    }

    private void b() {
        if (Log.isLoggable(a, 2)) {
            c();
        }
    }

    private void c() {
        Log.v(a, "Hits=" + this.i + ", misses=" + this.j + ", puts=" + this.k + ", evictions=" + this.l + ", currentSize=" + this.h + ", maxSize=" + this.g + "\nStrategy=" + this.c);
    }

    private static d d() {
        if (Build.VERSION.SDK_INT >= 19) {
            return new SizeConfigStrategy();
        }
        return new com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.b();
    }

    @TargetApi(26)
    private static Set<Bitmap.Config> e() {
        HashSet hashSet = new HashSet(Arrays.asList(Bitmap.Config.values()));
        if (Build.VERSION.SDK_INT >= 19) {
            hashSet.add(null);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            hashSet.remove(Bitmap.Config.HARDWARE);
        }
        return Collections.unmodifiableSet(hashSet);
    }

    private static class c implements a {
        private final Set<Bitmap> a = Collections.synchronizedSet(new HashSet());

        private c() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.LruBitmapPool.a
        public void a(Bitmap bitmap) {
            if (this.a.contains(bitmap)) {
                throw new IllegalStateException("Can't add already added bitmap: " + bitmap + " [" + bitmap.getWidth() + "x" + bitmap.getHeight() + "]");
            }
            this.a.add(bitmap);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.LruBitmapPool.a
        public void b(Bitmap bitmap) {
            if (!this.a.contains(bitmap)) {
                throw new IllegalStateException("Cannot remove bitmap not in tracker");
            }
            this.a.remove(bitmap);
        }
    }

    private static final class b implements a {
        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.LruBitmapPool.a
        public void a(Bitmap bitmap) {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.LruBitmapPool.a
        public void b(Bitmap bitmap) {
        }

        b() {
        }
    }
}
