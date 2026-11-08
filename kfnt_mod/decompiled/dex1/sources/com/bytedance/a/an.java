package com.bytedance.a;

import android.os.IBinder;
import android.os.Parcel;
import android.util.Pair;
import com.bytedance.a.cl;
import com.bytedance.a.da;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class an implements da.b<cl, Pair<String, Boolean>> {
    @Override // com.bytedance.a.da.b
    public cl a(IBinder iBinder) {
        return cl.a.a(iBinder);
    }

    @Override // com.bytedance.a.da.b
    public Pair<String, Boolean> a(cl clVar) {
        cl clVar2 = clVar;
        if (clVar2 == null) {
            return null;
        }
        cl.a.C0097a c0097a = (cl.a.C0097a) clVar2;
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
            c0097a.f3473a.transact(1, parcelObtain, parcelObtain2, 0);
            parcelObtain2.readException();
            String string = parcelObtain2.readString();
            parcelObtain2.recycle();
            parcelObtain.recycle();
            parcelObtain = Parcel.obtain();
            parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                c0097a.f3473a.transact(2, parcelObtain, parcelObtain2, 0);
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
}
