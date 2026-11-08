package bykvm_19do.bykvm_19do.bykvm_19do;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface q1 extends IInterface {

    public static abstract class a extends Binder implements q1 {

        /* renamed from: bykvm_19do.bykvm_19do.bykvm_19do.q1$a$a, reason: collision with other inner class name */
        static class C0044a implements q1 {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1372a;

            C0044a(IBinder iBinder) {
                this.f1372a = iBinder;
            }

            @Override // bykvm_19do.bykvm_19do.bykvm_19do.q1
            public String a() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                    this.f1372a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1372a;
            }
        }

        public static q1 a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.bun.lib.MsaIdInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof q1)) ? new C0044a(iBinder) : (q1) iInterfaceQueryLocalInterface;
        }
    }

    String a();
}
