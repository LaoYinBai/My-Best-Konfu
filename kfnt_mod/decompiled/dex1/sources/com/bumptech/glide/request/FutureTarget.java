package com.bumptech.glide.request;

import com.bumptech.glide.request.target.Target;
import java.util.concurrent.Future;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface FutureTarget<R> extends Future<R>, Target<R> {
    void clear();
}
