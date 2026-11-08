package com.ss.union.game.sdk.core.glide.request;

import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.engine.GlideException;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface ResourceCallback {
    void onLoadFailed(GlideException glideException);

    void onResourceReady(Resource<?> resource, DataSource dataSource);
}
