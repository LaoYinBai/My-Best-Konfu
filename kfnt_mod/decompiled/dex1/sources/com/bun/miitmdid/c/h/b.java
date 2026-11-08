package com.bun.miitmdid.c.h;

import android.content.Context;
import androidx.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements InnerIdSupplier {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private Context f3298a;

    @Keep
    class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        @Keep
        final /* synthetic */ SupplierListener f3299a;

        @Keep
        a(SupplierListener supplierListener) {
            this.f3299a = supplierListener;
        }

        @Override // java.lang.Runnable
        @Keep
        public native void run();
    }

    @Keep
    public b(Context context) {
        this.f3298a = context;
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
