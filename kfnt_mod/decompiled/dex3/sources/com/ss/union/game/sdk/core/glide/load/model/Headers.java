package com.ss.union.game.sdk.core.glide.load.model;

import com.ss.union.game.sdk.core.glide.load.model.LazyHeaders;
import java.util.Collections;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface Headers {

    @Deprecated
    public static final Headers NONE = new Headers() { // from class: com.ss.union.game.sdk.core.glide.load.model.Headers.1
        @Override // com.ss.union.game.sdk.core.glide.load.model.Headers
        public Map<String, String> getHeaders() {
            return Collections.emptyMap();
        }
    };
    public static final Headers DEFAULT = new LazyHeaders.Builder().build();

    Map<String, String> getHeaders();
}
