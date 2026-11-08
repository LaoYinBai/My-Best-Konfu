package com.bumptech.glide.provider;

import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.resource.transcode.ResourceTranscoder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface LoadProvider<A, T, Z, R> extends DataLoadProvider<T, Z> {
    ModelLoader<A, T> getModelLoader();

    ResourceTranscoder<Z, R> getTranscoder();
}
