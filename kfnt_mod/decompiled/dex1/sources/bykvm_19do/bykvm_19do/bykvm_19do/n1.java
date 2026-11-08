package bykvm_19do.bykvm_19do.bykvm_19do;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface n1 extends IInterface {

    public static abstract class a extends Binder implements n1 {

        /* renamed from: bykvm_19do.bykvm_19do.bykvm_19do.n1$a$a, reason: collision with other inner class name */
        static class C0041a implements n1 {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1363a;

            C0041a(IBinder iBinder) {
                this.f1363a = iBinder;
            }

            @Override // bykvm_19do.bykvm_19do.bykvm_19do.n1
            public String a() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                    this.f1363a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1363a;
            }
        }

        public static n1 a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.zui.deviceidservice.IDeviceidInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof n1)) ? new C0041a(iBinder) : (n1) iInterfaceQueryLocalInterface;
        }
    }

    String a();
}
