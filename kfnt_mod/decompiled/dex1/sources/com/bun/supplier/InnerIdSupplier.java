package com.bun.supplier;

import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface InnerIdSupplier extends IdSupplier {
    @Keep
    void a(SupplierListener supplierListener);

    @Keep
    boolean a();

    @Keep
    String getUDID();

    @Keep
    void shutDown();
}
