package b.b.b;

import android.os.IBinder;
import android.os.Parcel;
import android.util.Pair;
import b.b.b.u;
import b.b.b.x;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class k implements x.b<u, Pair<String, Boolean>> {
    @Override // b.b.b.x.b
    public Pair<String, Boolean> a(u uVar) {
        u uVar2 = uVar;
        if (uVar2 == null) {
            return null;
        }
        u.a.C0032a c0032a = (u.a.C0032a) uVar2;
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            c0032a.f1189a.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            String string = parcelObtain2.readString();
            parcelObtain2.recycle();
            parcelObtain.recycle();
            parcelObtain = Parcel.obtain();
            parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                c0032a.f1189a.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                boolean z = parcelObtain2.readInt() != 0;
                parcelObtain2.recycle();
                parcelObtain.recycle();
                return new Pair<>(string, Boolean.valueOf(z));
            } finally {
            }
        } finally {
        }
    }

    @Override // b.b.b.x.b
    public u a(IBinder iBinder) {
        return u.a.a(iBinder);
    }
}
