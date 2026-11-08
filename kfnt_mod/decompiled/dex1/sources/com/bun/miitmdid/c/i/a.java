package com.bun.miitmdid.c.i;

import android.content.Context;
import androidx.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;
import com.heytap.openid.sdk.OpenIDSDK;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements InnerIdSupplier {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private Context f3301a;

    @Keep
    /* renamed from: com.bun.miitmdid.c.i.a$a, reason: collision with other inner class name */
    class RunnableC0094a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        @Keep
        final /* synthetic */ SupplierListener f3302a;

        @Keep
        RunnableC0094a(SupplierListener supplierListener) {
            this.f3302a = supplierListener;
        }

        @Override // java.lang.Runnable
        @Keep
        public native void run();
    }

    @Keep
    public a(Context context) {
        OpenIDSDK.d(context);
        this.f3301a = context;
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
