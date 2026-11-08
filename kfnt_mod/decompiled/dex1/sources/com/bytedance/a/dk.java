package com.bytedance.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface dk extends IInterface {

    public static abstract class a extends Binder implements dk {

        /* renamed from: com.bytedance.a.dk$a$a, reason: collision with other inner class name */
        public static class C0098a implements dk {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f3510a;

            public C0098a(IBinder iBinder) {
                this.f3510a = iBinder;
            }

            public String a() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.samsung.android.deviceidservice.IDeviceIdService");
                    this.f3510a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f3510a;
            }
        }

        public static dk a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.deviceidservice.IDeviceIdService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof dk)) ? new C0098a(iBinder) : (dk) iInterfaceQueryLocalInterface;
        }
    }
}
