package com.ss.union.game.sdk.core.glide.load;

import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ResourceDecoder<T, Z> {
    Resource<Z> decode(T t, int i, int i2, Options options) throws IOException;

    boolean handles(T t, Options options) throws IOException;
}
