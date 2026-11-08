package com.bumptech.glide.load;

import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface Encoder<T> {
    boolean encode(T t, OutputStream outputStream);

    String getId();
}
