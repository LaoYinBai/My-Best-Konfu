package com.bytedance.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface f extends IInterface {

    public static abstract class a extends Binder implements f {

        /* renamed from: com.bytedance.a.f$a$a, reason: collision with other inner class name */
        public static class C0102a implements f {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f3523a;

            public C0102a(IBinder iBinder) {
                this.f3523a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3523a;
            }
        }

        public static f a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.bun.lib.MsaIdInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof f)) ? new C0102a(iBinder) : (f) iInterfaceQueryLocalInterface;
        }
    }
}
