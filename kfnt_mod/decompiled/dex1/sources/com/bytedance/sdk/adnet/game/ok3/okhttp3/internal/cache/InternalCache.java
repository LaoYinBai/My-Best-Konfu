package com.bytedance.sdk.adnet.game.ok3.okhttp3.internal.cache;

import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Response;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface InternalCache {
    Response get(Request request);

    CacheRequest put(Response response);

    void remove(Request request);

    void trackConditionalCacheHit();

    void trackResponse(CacheStrategy cacheStrategy);

    void update(Response response, Response response2);
}
