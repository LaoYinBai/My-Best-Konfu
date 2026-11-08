package com.ss.union.game.sdk.core.glide.load.resource.bytes;

import com.ss.union.game.sdk.core.glide.load.data.DataRewinder;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ByteBufferRewinder implements DataRewinder<ByteBuffer> {
    private final ByteBuffer a;

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder
    public void cleanup() {
    }

    public ByteBufferRewinder(ByteBuffer byteBuffer) {
        this.a = byteBuffer;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder
    public ByteBuffer rewindAndGet() {
        this.a.position(0);
        return this.a;
    }

    public static class Factory implements DataRewinder.Factory<ByteBuffer> {
        @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder.Factory
        public DataRewinder<ByteBuffer> build(ByteBuffer byteBuffer) {
            return new ByteBufferRewinder(byteBuffer);
        }

        @Override // com.ss.union.game.sdk.core.glide.load.data.DataRewinder.Factory
        public Class<ByteBuffer> getDataClass() {
            return ByteBuffer.class;
        }
    }
}
