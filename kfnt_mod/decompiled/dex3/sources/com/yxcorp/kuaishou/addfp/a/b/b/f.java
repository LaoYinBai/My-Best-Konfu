package com.yxcorp.kuaishou.addfp.a.b.b;

import android.os.IBinder;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class f implements d {
    private IBinder a;

    f(IBinder iBinder) {
        this.a = iBinder;
    }

    @Override // com.yxcorp.kuaishou.addfp.a.b.b.d
    public final String a() {
        String string;
        Parcel parcelObtain = Parcel.obtain();
        Parcel parcelObtain2 = Parcel.obtain();
        try {
            try {
                parcelObtain.writeInterfaceToken("com.uodis.opendevice.aidl.OpenDeviceIdentifierService");
                this.a.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                string = parcelObtain2.readString();
            } catch (Throwable th) {
                th.printStackTrace();
                string = "";
            }
            return string;
        } finally {
            parcelObtain2.recycle();
            parcelObtain.recycle();
        }
    }

    @Override // android.os.IInterface
    public final IBinder asBinder() {
        return this.a;
    }
}
