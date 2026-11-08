package b.b.b.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface a extends IInterface {

    /* renamed from: b.b.b.a.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0028a extends Binder implements a {

        /* renamed from: b.b.b.a.a$a$a, reason: collision with other inner class name */
        public static class C0029a implements a {

            /* renamed from: a, reason: collision with root package name */
            public IBinder f1154a;

            public C0029a(IBinder iBinder) {
                this.f1154a = iBinder;
            }

            public String a() {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.samsung.android.deviceidservice.IDeviceIdService");
                    this.f1154a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f1154a;
            }
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.samsung.android.deviceidservice.IDeviceIdService");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof a)) ? new C0029a(iBinder) : (a) iInterfaceQueryLocalInterface;
        }
    }
}
