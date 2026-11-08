package com.bumptech.glide.load.resource;

import com.bumptech.glide.load.Encoder;
import java.io.OutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class NullEncoder<T> implements Encoder<T> {
    private static final NullEncoder<?> NULL_ENCODER = new NullEncoder<>();

    @Override // com.bumptech.glide.load.Encoder
    public boolean encode(T t, OutputStream outputStream) {
        return false;
    }

    @Override // com.bumptech.glide.load.Encoder
    public String getId() {
        return "";
    }

    public static <T> Encoder<T> get() {
        return NULL_ENCODER;
    }
}
