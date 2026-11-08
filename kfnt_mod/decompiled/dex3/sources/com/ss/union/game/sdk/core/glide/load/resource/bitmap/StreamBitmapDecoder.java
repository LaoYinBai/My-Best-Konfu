package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler;
import com.ss.union.game.sdk.core.glide.util.ExceptionCatchingInputStream;
import com.ss.union.game.sdk.core.glide.util.MarkEnforcingInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StreamBitmapDecoder implements ResourceDecoder<InputStream, Bitmap> {
    private final Downsampler a;
    private final ArrayPool b;

    public StreamBitmapDecoder(Downsampler downsampler, ArrayPool arrayPool) {
        this.a = downsampler;
        this.b = arrayPool;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(InputStream inputStream, Options options) {
        return this.a.handles(inputStream);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(InputStream inputStream, int i, int i2, Options options) throws IOException {
        RecyclableBufferedInputStream recyclableBufferedInputStream;
        boolean z;
        if (inputStream instanceof RecyclableBufferedInputStream) {
            recyclableBufferedInputStream = (RecyclableBufferedInputStream) inputStream;
            z = false;
        } else {
            recyclableBufferedInputStream = new RecyclableBufferedInputStream(inputStream, this.b);
            z = true;
        }
        ExceptionCatchingInputStream exceptionCatchingInputStreamObtain = ExceptionCatchingInputStream.obtain(recyclableBufferedInputStream);
        try {
            return this.a.decode(new MarkEnforcingInputStream(exceptionCatchingInputStreamObtain), i, i2, options, new a(recyclableBufferedInputStream, exceptionCatchingInputStreamObtain));
        } finally {
            exceptionCatchingInputStreamObtain.release();
            if (z) {
                recyclableBufferedInputStream.release();
            }
        }
    }

    static class a implements Downsampler.DecodeCallbacks {
        private final RecyclableBufferedInputStream a;
        private final ExceptionCatchingInputStream b;

        a(RecyclableBufferedInputStream recyclableBufferedInputStream, ExceptionCatchingInputStream exceptionCatchingInputStream) {
            this.a = recyclableBufferedInputStream;
            this.b = exceptionCatchingInputStream;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onObtainBounds() {
            this.a.fixMarkLimit();
        }

        @Override // com.ss.union.game.sdk.core.glide.load.resource.bitmap.Downsampler.DecodeCallbacks
        public void onDecodeComplete(BitmapPool bitmapPool, Bitmap bitmap) throws IOException {
            IOException exception = this.b.getException();
            if (exception != null) {
                if (bitmap != null) {
                    bitmapPool.put(bitmap);
                    throw exception;
                }
                throw exception;
            }
        }
    }
}
