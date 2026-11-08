package com.samsung.android.deviceidservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IDeviceIdService extends IInterface {

    @Keep
    public static abstract class Stub extends Binder implements IDeviceIdService {

        @Keep
        private static class Proxy implements IDeviceIdService {

            @Keep
            private IBinder a;

            @Keep
            Proxy(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.os.IInterface
            @Keep
            public native IBinder asBinder();

            @Override // com.samsung.android.deviceidservice.IDeviceIdService
            @Keep
            public native String getAAID(String str);

            @Override // com.samsung.android.deviceidservice.IDeviceIdService
            @Keep
            public native String getOAID();

            @Override // com.samsung.android.deviceidservice.IDeviceIdService
            @Keep
            public native String getVAID(String str);
        }

        @Keep
        public Stub() {
            attachInterface(this, "com.samsung.android.deviceidservice.IDeviceIdService");
        }

        @Keep
        public static native IDeviceIdService a(IBinder iBinder);

        @Override // android.os.IInterface
        @Keep
        public native IBinder asBinder();

        @Override // android.os.Binder
        @Keep
        public native boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2);
    }

    @Keep
    String getAAID(String str);

    @Keep
    String getOAID();

    @Keep
    String getVAID(String str);
}
