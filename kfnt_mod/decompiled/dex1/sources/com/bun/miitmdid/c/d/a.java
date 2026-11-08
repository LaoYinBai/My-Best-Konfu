package com.bun.miitmdid.c.d;

import android.content.Context;
import androidx.annotation.Keep;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements InnerIdSupplier {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private Context f3286a;

    @Keep
    private SupplierListener f;

    @Keep
    private boolean e = false;

    /* renamed from: b, reason: collision with root package name */
    @Keep
    private String f3287b = "";

    /* renamed from: c, reason: collision with root package name */
    @Keep
    private String f3288c = "";

    @Keep
    private String d = "";

    @Keep
    /* renamed from: com.bun.miitmdid.c.d.a$a, reason: collision with other inner class name */
    class RunnableC0092a implements Runnable {
        @Keep
        RunnableC0092a() {
        }

        @Override // java.lang.Runnable
        @Keep
        public native void run();
    }

    @Keep
    public a(Context context) {
        this.f3286a = context;
    }

    @Keep
    static native /* synthetic */ Context a(a aVar);

    @Keep
    static native /* synthetic */ String a(a aVar, String str);

    @Keep
    private native void b();

    @Keep
    static native /* synthetic */ void b(a aVar);

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
