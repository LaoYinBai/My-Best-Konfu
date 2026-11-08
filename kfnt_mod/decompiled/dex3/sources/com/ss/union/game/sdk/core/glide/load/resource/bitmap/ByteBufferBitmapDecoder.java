package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.util.ByteBufferUtil;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ByteBufferBitmapDecoder implements ResourceDecoder<ByteBuffer, Bitmap> {
    private final Downsampler a;

    public ByteBufferBitmapDecoder(Downsampler downsampler) {
        this.a = downsampler;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(ByteBuffer byteBuffer, Options options) {
        return this.a.handles(byteBuffer);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(ByteBuffer byteBuffer, int i, int i2, Options options) throws IOException {
        return this.a.decode(ByteBufferUtil.toStream(byteBuffer), i, i2, options);
    }
}
