package com.ss.union.game.sdk.core.glide.load.engine.prefill;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import com.ss.union.game.sdk.core.glide.load.DecodeFormat;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache;
import com.ss.union.game.sdk.core.glide.load.engine.prefill.PreFillType;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class BitmapPreFiller {
    private final MemoryCache a;
    private final BitmapPool b;
    private final DecodeFormat c;
    private final Handler d = new Handler(Looper.getMainLooper());
    private a e;

    public BitmapPreFiller(MemoryCache memoryCache, BitmapPool bitmapPool, DecodeFormat decodeFormat) {
        this.a = memoryCache;
        this.b = bitmapPool;
        this.c = decodeFormat;
    }

    public void preFill(PreFillType.Builder... builderArr) {
        if (this.e != null) {
            this.e.a();
        }
        PreFillType[] preFillTypeArr = new PreFillType[builderArr.length];
        for (int i = 0; i < builderArr.length; i++) {
            PreFillType.Builder builder = builderArr[i];
            if (builder.a() == null) {
                builder.setConfig(this.c == DecodeFormat.PREFER_ARGB_8888 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            }
            preFillTypeArr[i] = builder.b();
        }
        this.e = new a(this.b, this.a, a(preFillTypeArr));
        this.d.post(this.e);
    }

    b a(PreFillType... preFillTypeArr) {
        long maxSize = (this.a.getMaxSize() - this.a.getCurrentSize()) + this.b.getMaxSize();
        int iD = 0;
        for (PreFillType preFillType : preFillTypeArr) {
            iD += preFillType.d();
        }
        float f = maxSize / iD;
        HashMap map = new HashMap();
        for (PreFillType preFillType2 : preFillTypeArr) {
            map.put(preFillType2, Integer.valueOf(Math.round(preFillType2.d() * f) / a(preFillType2)));
        }
        return new b(map);
    }

    private static int a(PreFillType preFillType) {
        return Util.getBitmapByteSize(preFillType.a(), preFillType.b(), preFillType.c());
    }
}
