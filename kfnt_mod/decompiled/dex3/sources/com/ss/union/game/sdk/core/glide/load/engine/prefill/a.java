package com.ss.union.game.sdk.core.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapResource;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.security.MessageDigest;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class a implements Runnable {
    static final String a = "PreFillRunner";
    static final long b = 32;
    static final long c = 40;
    static final int d = 4;
    private final BitmapPool g;
    private final MemoryCache h;
    private final com.ss.union.game.sdk.core.glide.load.engine.prefill.b i;
    private final C0082a j;
    private final Set<PreFillType> k;
    private final Handler l;
    private long m;
    private boolean n;
    private static final C0082a f = new C0082a();
    static final long e = TimeUnit.SECONDS.toMillis(1);

    public a(BitmapPool bitmapPool, MemoryCache memoryCache, com.ss.union.game.sdk.core.glide.load.engine.prefill.b bVar) {
        this(bitmapPool, memoryCache, bVar, f, new Handler(Looper.getMainLooper()));
    }

    a(BitmapPool bitmapPool, MemoryCache memoryCache, com.ss.union.game.sdk.core.glide.load.engine.prefill.b bVar, C0082a c0082a, Handler handler) {
        this.k = new HashSet();
        this.m = c;
        this.g = bitmapPool;
        this.h = memoryCache;
        this.i = bVar;
        this.j = c0082a;
        this.l = handler;
    }

    public void a() {
        this.n = true;
    }

    boolean b() {
        Bitmap bitmapCreateBitmap;
        long jA = this.j.a();
        while (!this.i.c() && !a(jA)) {
            PreFillType preFillTypeA = this.i.a();
            if (!this.k.contains(preFillTypeA)) {
                this.k.add(preFillTypeA);
                bitmapCreateBitmap = this.g.getDirty(preFillTypeA.a(), preFillTypeA.b(), preFillTypeA.c());
            } else {
                bitmapCreateBitmap = Bitmap.createBitmap(preFillTypeA.a(), preFillTypeA.b(), preFillTypeA.c());
            }
            int bitmapByteSize = Util.getBitmapByteSize(bitmapCreateBitmap);
            if (c() >= bitmapByteSize) {
                this.h.put(new b(), BitmapResource.obtain(bitmapCreateBitmap, this.g));
            } else {
                this.g.put(bitmapCreateBitmap);
            }
            if (Log.isLoggable(a, 3)) {
                Log.d(a, "allocated [" + preFillTypeA.a() + "x" + preFillTypeA.b() + "] " + preFillTypeA.c() + " size: " + bitmapByteSize);
            }
        }
        return (this.n || this.i.c()) ? false : true;
    }

    private boolean a(long j) {
        return this.j.a() - j >= b;
    }

    private long c() {
        return this.h.getMaxSize() - this.h.getCurrentSize();
    }

    @Override // java.lang.Runnable
    public void run() {
        if (b()) {
            this.l.postDelayed(this, d());
        }
    }

    private long d() {
        long j = this.m;
        this.m = Math.min(this.m * 4, e);
        return j;
    }

    private static final class b implements Key {
        b() {
        }

        @Override // com.ss.union.game.sdk.core.glide.load.Key
        public void updateDiskCacheKey(MessageDigest messageDigest) {
            throw new UnsupportedOperationException();
        }
    }

    /* renamed from: com.ss.union.game.sdk.core.glide.load.engine.prefill.a$a, reason: collision with other inner class name */
    static class C0082a {
        C0082a() {
        }

        long a() {
            return SystemClock.currentThreadTimeMillis();
        }
    }
}
