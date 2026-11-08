package com.bun.supplier;

import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IRemoteIdSupplier extends InnerIdSupplier {
    @Keep
    String getAAID(String str);

    @Keep
    String getVAID(String str);
}
