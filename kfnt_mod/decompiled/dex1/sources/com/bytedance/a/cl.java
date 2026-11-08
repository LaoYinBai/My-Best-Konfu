package com.bytedance.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface cl extends IInterface {

    public static abstract class a extends Binder implements cl {

        /* renamed from: com.bytedance.a.cl$a$a, reason: collision with other inner class name */
        public static class C0097a implements cl {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f3473a;

            public C0097a(IBinder iBinder) {
                this.f3473a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3473a;
            }
        }

        public static cl a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof cl)) ? new C0097a(iBinder) : (cl) iInterfaceQueryLocalInterface;
        }
    }
}
