package bykvm_19do.bykvm_19do.bykvm_19do;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface p1 extends IInterface {

    public static abstract class a extends Binder implements p1 {

        /* renamed from: bykvm_19do.bykvm_19do.bykvm_19do.p1$a$a, reason: collision with other inner class name */
        static class C0043a implements p1 {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f1369a;

            C0043a(IBinder iBinder) {
                this.f1369a = iBinder;
            }

            @Override // bykvm_19do.bykvm_19do.bykvm_19do.p1
            public String a(String str, String str2, String str3) {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    parcelObtain.writeString(str3);
                    this.f1369a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1369a;
            }
        }

        public static p1 a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof p1)) ? new C0043a(iBinder) : (p1) iInterfaceQueryLocalInterface;
        }
    }

    String a(String str, String str2, String str3);
}
