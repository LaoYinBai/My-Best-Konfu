package com.ss.union.game.sdk.core.glide.load;

import com.ss.union.game.sdk.core.glide.load.engine.Resource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ResourceEncoder<T> extends Encoder<Resource<T>> {
    EncodeStrategy getEncodeStrategy(Options options);
}
