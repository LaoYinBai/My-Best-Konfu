package com.ss.union.game.sdk.core.glide.load.resource.transcode;

import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UnitTranscoder<Z> implements ResourceTranscoder<Z, Z> {
    private static final UnitTranscoder<?> a = new UnitTranscoder<>();

    @Override // com.ss.union.game.sdk.core.glide.load.resource.transcode.ResourceTranscoder
    public Resource<Z> transcode(Resource<Z> resource, Options options) {
        return resource;
    }

    public static <Z> ResourceTranscoder<Z, Z> get() {
        return a;
    }
}
