package com.bumptech.glide.request;

import com.bumptech.glide.load.engine.Resource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ResourceCallback {
    void onException(Exception exc);

    void onResourceReady(Resource<?> resource);
}
