package com.ss.union.game.sdk.core.glide.load.resource.transcode;

import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.resource.bytes.BytesResource;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifDrawable;
import com.ss.union.game.sdk.core.glide.util.ByteBufferUtil;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GifDrawableBytesTranscoder implements ResourceTranscoder<GifDrawable, byte[]> {
    @Override // com.ss.union.game.sdk.core.glide.load.resource.transcode.ResourceTranscoder
    public Resource<byte[]> transcode(Resource<GifDrawable> resource, Options options) {
        return new BytesResource(ByteBufferUtil.toBytes(resource.get().getBuffer()));
    }
}
