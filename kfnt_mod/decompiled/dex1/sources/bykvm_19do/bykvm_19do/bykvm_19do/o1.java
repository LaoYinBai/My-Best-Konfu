package bykvm_19do.bykvm_19do.bykvm_19do;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface o1 extends IInterface {

    public static abstract class a extends Binder implements o1 {

        /* renamed from: bykvm_19do.bykvm_19do.bykvm_19do.o1$a$a, reason: collision with other inner class name */
        static class C0042a implements o1 {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1365a;

            C0042a(IBinder iBinder) {
                this.f1365a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1365a;
            }

            @Override // bykvm_19do.bykvm_19do.bykvm_19do.o1
            public String b() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
                    this.f1365a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static o1 a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.asus.msa.SupplementaryDID.IDidAidlInterface");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof o1)) ? new C0042a(iBinder) : (o1) iInterfaceQueryLocalInterface;
        }
    }

    String b();
}
