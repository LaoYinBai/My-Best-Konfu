package com.bun.miitmdid.supplier.sumsung;

import android.content.Context;
import androidx.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements com.bun.miitmdid.c.e.a, InnerIdSupplier {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    public SupplierListener f3328a;

    /* renamed from: b, reason: collision with root package name */
    @Keep
    private SumsungCore f3329b;

    @Keep
    public a(Context context, SupplierListener supplierListener) {
        this.f3328a = supplierListener;
        this.f3329b = new SumsungCore(context, this);
    }

    @Override // com.bun.supplier.InnerIdSupplier
    @Keep
    public native void a(SupplierListener supplierListener);

    @Override // com.bun.miitmdid.c.e.a
    @Keep
    public native void a(boolean z);

    @Override // com.bun.supplier.InnerIdSupplier
    @Keep
    public native boolean a();

    @Override // com.bun.miitmdid.c.e.a
    @Keep
    public native void b();

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
