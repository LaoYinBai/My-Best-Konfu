package com.ss.union.game.sdk.core.glide.load.resource.transcode;

import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ResourceTranscoder<Z, R> {
    Resource<R> transcode(Resource<Z> resource, Options options);
}
