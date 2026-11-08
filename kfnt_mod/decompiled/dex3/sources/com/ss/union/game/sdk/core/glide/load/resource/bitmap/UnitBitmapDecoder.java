package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.util.Util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class UnitBitmapDecoder implements ResourceDecoder<Bitmap, Bitmap> {
    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(Bitmap bitmap, Options options) {
        return true;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(Bitmap bitmap, int i, int i2, Options options) {
        return new a(bitmap);
    }

    private static final class a implements Resource<Bitmap> {
        private final Bitmap a;

        @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
        public void recycle() {
        }

        a(Bitmap bitmap) {
            this.a = bitmap;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
        public Class<Bitmap> getResourceClass() {
            return Bitmap.class;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Bitmap get() {
            return this.a;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
        public int getSize() {
            return Util.getBitmapByteSize(this.a);
        }
    }
}
