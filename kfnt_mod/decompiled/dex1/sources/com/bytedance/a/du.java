package com.bytedance.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface du extends IInterface {

    public static abstract class a extends Binder implements du {

        /* renamed from: com.bytedance.a.du$a$a, reason: collision with other inner class name */
        public static class C0100a implements du {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f3517a;

            public C0100a(IBinder iBinder) {
                this.f3517a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3517a;
            }
        }

        public static du a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.asus.msa.SupplementaryDID.IDidAidlInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof du)) ? new C0100a(iBinder) : (du) iInterfaceQueryLocalInterface;
        }
    }
}
