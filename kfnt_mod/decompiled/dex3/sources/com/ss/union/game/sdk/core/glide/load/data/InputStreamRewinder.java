package com.ss.union.game.sdk.core.glide.load.data;

import com.ss.union.game.sdk.core.glide.load.data.DataRewinder;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.RecyclableBufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class InputStreamRewinder implements DataRewinder<InputStream> {
    private static final int a = 5242880;
    private final RecyclableBufferedInputStream b;

    InputStreamRewinder(InputStream inputStream, ArrayPool arrayPool) {
        this.b = new RecyclableBufferedInputStream(inputStream, arrayPool);
        this.b.mark(5242880);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder
    public InputStream rewindAndGet() throws IOException {
        this.b.reset();
        return this.b;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder
    public void cleanup() {
        this.b.release();
    }

    public static final class Factory implements DataRewinder.Factory<InputStream> {
        private final ArrayPool a;

        public Factory(ArrayPool arrayPool) {
            this.a = arrayPool;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder.Factory
        public DataRewinder<InputStream> build(InputStream inputStream) {
            return new InputStreamRewinder(inputStream, this.a);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder.Factory
        public Class<InputStream> getDataClass() {
            return InputStream.class;
        }
    }
}
