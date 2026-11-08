package bykvm_19do.bykvm_19do.bykvm_19do;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface m1 extends IInterface {

    public static abstract class a extends Binder implements m1 {

        /* renamed from: bykvm_19do.bykvm_19do.bykvm_19do.m1$a$a, reason: collision with other inner class name */
        static class C0040a implements m1 {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1362a;

            C0040a(IBinder iBinder) {
                this.f1362a = iBinder;
            }

            @Override // bykvm_19do.bykvm_19do.bykvm_19do.m1
            public String a() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.samsung.android.deviceidservice.IDeviceIdService");
                    this.f1362a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1362a;
            }
        }

        public static m1 a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.deviceidservice.IDeviceIdService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof m1)) ? new C0040a(iBinder) : (m1) iInterfaceQueryLocalInterface;
        }
    }

    String a();
}
