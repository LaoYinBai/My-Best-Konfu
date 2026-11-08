package com.bun.miitmdid.c.j;

import android.content.Context;
import androidx.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements InnerIdSupplier {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private String f3304a = "";

    /* renamed from: b, reason: collision with root package name */
    @Keep
    private Context f3305b;

    @Keep
    /* renamed from: com.bun.miitmdid.c.j.a$a, reason: collision with other inner class name */
    class RunnableC0095a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        @Keep
        final /* synthetic */ SupplierListener f3306a;

        @Keep
        RunnableC0095a(SupplierListener supplierListener) {
            this.f3306a = supplierListener;
        }

        @Override // java.lang.Runnable
        @Keep
        public native void run();
    }

    @Keep
    public a(Context context) {
        this.f3305b = context;
    }

    @Override // com.bun.supplier.InnerIdSupplier
    @Keep
    public native void a(SupplierListener supplierListener);

    @Keep
    public native void a(String str);

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
