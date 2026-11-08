package com.bytedance.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface dz extends IInterface {

    public static abstract class a extends Binder implements dz {

        /* renamed from: com.bytedance.a.dz$a$a, reason: collision with other inner class name */
        public static class C0101a implements dz {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f3521a;

            public C0101a(IBinder iBinder) {
                this.f3521a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3521a;
            }
        }

        public static dz a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof dz)) ? new C0101a(iBinder) : (dz) iInterfaceQueryLocalInterface;
        }
    }
}
