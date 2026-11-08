package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.Resource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface Transformation<T> {
    String getId();

    Resource<T> transform(Resource<T> resource, int i, int i2);
}
