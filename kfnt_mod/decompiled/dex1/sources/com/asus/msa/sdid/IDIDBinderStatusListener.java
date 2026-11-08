package com.asus.msa.sdid;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.annotation.Keep;
import com.asus.msa.SupplementaryDID.IDidAidlInterface;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IDIDBinderStatusListener extends IInterface {

    @Keep
    public static abstract class Stub extends Binder implements IDIDBinderStatusListener {

        @Keep
        public static class Proxy implements IDIDBinderStatusListener {

            /* renamed from: a, reason: collision with root package name */
            @Keep
            public IBinder f3087a;

            @Override // com.asus.msa.sdid.IDIDBinderStatusListener
            @Keep
            public native void a(IDidAidlInterface iDidAidlInterface);

            @Override // android.os.IInterface
            @Keep
            public native IBinder asBinder();

            @Override // com.asus.msa.sdid.IDIDBinderStatusListener
            @Keep
            public native void b();
        }

        @Keep
        public Stub() {
            attachInterface(this, "com.asus.msa.sdid.IDIDBinderStatusListener");
        }

        @Override // android.os.IInterface
        @Keep
        public native IBinder asBinder();

        @Override // android.os.Binder
        @Keep
        public native boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2);
    }

    @Keep
    void a(IDidAidlInterface iDidAidlInterface);

    @Keep
    void b();
}
