package com.bun.supplier;

import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IdSupplier {
    @Keep
    String getAAID();

    @Keep
    String getOAID();

    @Keep
    String getVAID();

    @Keep
    boolean isSupported();
}
