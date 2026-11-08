package com.asus.msa.SupplementaryDID;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IDidAidlInterface extends IInterface {

    @Keep
    public static abstract class Stub extends Binder implements IDidAidlInterface {

        @Keep
        public static class Proxy implements IDidAidlInterface {

            /* renamed from: a, reason: collision with root package name */
            @Keep
            public IBinder f3086a;

            @Keep
            public Proxy(IBinder iBinder) {
                this.f3086a = iBinder;
            }

            @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
            @Keep
            public native boolean a();

            @Override // android.os.IInterface
            @Keep
            public native IBinder asBinder();

            @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
            @Keep
            public native String getAAID();

            @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
            @Keep
            public native String getOAID();

            @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
            @Keep
            public native String getUDID();

            @Override // com.asus.msa.SupplementaryDID.IDidAidlInterface
            @Keep
            public native String getVAID();
        }

        @Keep
        public Stub() {
            attachInterface(this, "com.asus.msa.SupplementaryDID.IDidAidlInterface");
        }

        @Keep
        public static native IDidAidlInterface a(IBinder iBinder);

        @Override // android.os.IInterface
        @Keep
        public native IBinder asBinder();

        @Override // android.os.Binder
        @Keep
        public native boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2);
    }

    @Keep
    boolean a();

    @Keep
    String getAAID();

    @Keep
    String getOAID();

    @Keep
    String getUDID();

    @Keep
    String getVAID();
}
