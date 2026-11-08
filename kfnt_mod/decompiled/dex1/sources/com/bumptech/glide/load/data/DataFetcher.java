package com.bumptech.glide.load.data;

import com.bumptech.glide.Priority;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface DataFetcher<T> {
    void cancel();

    void cleanup();

    String getId();

    T loadData(Priority priority) throws Exception;
}
