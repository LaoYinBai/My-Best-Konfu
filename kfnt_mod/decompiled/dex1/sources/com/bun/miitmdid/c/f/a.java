package com.bun.miitmdid.c.f;

import android.content.Context;
import androidx.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements InnerIdSupplier {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private Context f3290a;

    @Keep
    public a(Context context) {
        this.f3290a = context;
    }

    @Override // com.bun.supplier.InnerIdSupplier
    @Keep
    public native void a(SupplierListener supplierListener);

    @Override // com.bun.supplier.InnerIdSupplier
    @Keep
    public native boolean a();

    @Override // com.bun.supplier.IdSupplier
    @Keep
    public native String getAAID();

    @Override // com.bun.supplier.IdSupplier
    @Keep
    public native String getOAID();

    @Override // com.bun.supplier.InnerIdSupplier
    @Keep
    public native String getUDID();

    @Override // com.bun.supplier.IdSupplier
    @Keep
    public native String getVAID();

    @Override // com.bun.supplier.IdSupplier
    @Keep
    public native boolean isSupported();

    @Override // com.bun.supplier.InnerIdSupplier
    @Keep
    public native void shutDown();
}
