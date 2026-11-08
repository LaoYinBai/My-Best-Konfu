package com.zui.deviceidservice;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.annotation.Keep;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IDeviceidInterface extends IInterface {

    @Keep
    public static abstract class Stub extends Binder implements IDeviceidInterface {

        @Keep
        private static class Proxy implements IDeviceidInterface {

            @Keep
            private IBinder a;

            @Keep
            Proxy(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // com.zui.deviceidservice.IDeviceidInterface
            @Keep
            public native boolean a();

            @Override // android.os.IInterface
            @Keep
            public native IBinder asBinder();

            @Override // com.zui.deviceidservice.IDeviceidInterface
            @Keep
            public native String getAAID(String str);

            @Override // com.zui.deviceidservice.IDeviceidInterface
            @Keep
            public native String getOAID();

            @Override // com.zui.deviceidservice.IDeviceidInterface
            @Keep
            public native String getUDID();

            @Override // com.zui.deviceidservice.IDeviceidInterface
            @Keep
            public native String getVAID(String str);
        }

        @Keep
        public Stub() {
            attachInterface(this, "com.zui.deviceidservice.IDeviceidInterface");
        }

        @Keep
        public static native IDeviceidInterface a(IBinder iBinder);

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
    String getAAID(String str);

    @Keep
    String getOAID();

    @Keep
    String getUDID();

    @Keep
    String getVAID(String str);
}
