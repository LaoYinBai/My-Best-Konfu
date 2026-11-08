package com.bytedance.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface dp extends IInterface {

    public static abstract class a extends Binder implements dp {

        /* renamed from: com.bytedance.a.dp$a$a, reason: collision with other inner class name */
        public static class C0099a implements dp {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f3512a;

            public C0099a(IBinder iBinder) {
                this.f3512a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3512a;
            }
        }

        public static dp a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.zui.deviceidservice.IDeviceidInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof dp)) ? new C0099a(iBinder) : (dp) iInterfaceQueryLocalInterface;
        }
    }
}
