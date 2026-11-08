package com.yxcorp.kuaishou.addfp.a.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class c extends Binder implements b {
    public static b a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.asus.msa.SupplementaryDID.IDidAidlInterface");
        return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof b)) ? new d(iBinder) : (b) iInterfaceQueryLocalInterface;
    }
}
