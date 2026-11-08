package com.bun.miitmdid.c.b;

import android.content.Context;
import android.os.IBinder;
import androidx.annotation.Keep;
import com.asus.msa.SupplementaryDID.IDidAidlInterface;
import com.asus.msa.sdid.IDIDBinderStatusListener;
import com.asus.msa.sdid.SupplementaryDIDManager;
import com.bun.supplier.InnerIdSupplier;
import com.bun.supplier.SupplierListener;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements IDIDBinderStatusListener, InnerIdSupplier {

    /* renamed from: a, reason: collision with root package name */
    @Keep
    private SupplierListener f3283a;

    @Keep
    private SupplementaryDIDManager f;

    /* renamed from: b, reason: collision with root package name */
    @Keep
    private String f3284b = "";

    /* renamed from: c, reason: collision with root package name */
    @Keep
    private String f3285c = "";

    @Keep
    private String d = "";

    @Keep
    private String e = "";

    @Keep
    private boolean g = false;

    @Keep
    private boolean h = false;

    @Keep
    public a(Context context, SupplierListener supplierListener) {
        this.f3283a = supplierListener;
        this.f = new SupplementaryDIDManager(context);
    }

    @Override // com.asus.msa.sdid.IDIDBinderStatusListener
    @Keep
    public native void a(IDidAidlInterface iDidAidlInterface);

    @Override // com.bun.supplier.InnerIdSupplier
    @Keep
    public native void a(SupplierListener supplierListener);

    @Override // com.bun.supplier.InnerIdSupplier
    @Keep
    public native boolean a();

    @Override // android.os.IInterface
    @Keep
    public native IBinder asBinder();

    @Override // com.asus.msa.sdid.IDIDBinderStatusListener
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
