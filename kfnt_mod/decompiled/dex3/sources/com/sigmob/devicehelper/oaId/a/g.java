package com.sigmob.devicehelper.oaId.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface g extends IInterface {

    public static abstract class a extends Binder implements g {

        /* renamed from: com.sigmob.devicehelper.oaId.a.g$a$a, reason: collision with other inner class name */
        public static class C0008a implements g {
            private IBinder a;

            public C0008a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // com.sigmob.devicehelper.oaId.a.g
            public String a() {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                    this.a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    string = parcelObtain2.readString();
                } catch (Throwable unused) {
                    parcelObtain.recycle();
                    parcelObtain2.recycle();
                    string = null;
                }
                parcelObtain.recycle();
                parcelObtain2.recycle();
                return string;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }
        }
    }

    String a();
}
