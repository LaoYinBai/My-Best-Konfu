package com.appsflyer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Parcel;
import android.os.RemoteException;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class g {
    g() {
    }

    static final class d {

        /* renamed from: ˊ, reason: contains not printable characters */
        private final String f191;

        /* renamed from: ˎ, reason: contains not printable characters */
        private final boolean f192;

        d(String str, boolean z) {
            this.f191 = str;
            this.f192 = z;
        }

        /* renamed from: ˊ, reason: contains not printable characters */
        public final String m164() {
            return this.f191;
        }

        /* renamed from: ॱ, reason: contains not printable characters */
        final boolean m165() {
            return this.f192;
        }
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    static d m160(Context context) throws Exception {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        try {
            byte b2 = 0;
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            b bVar = new b(b2);
            Intent intent = new Intent("com.google.android.gms.ads.identifier.service.START");
            intent.setPackage("com.google.android.gms");
            try {
                try {
                    if (context.bindService(intent, bVar, 1)) {
                        c cVar = new c(bVar.m161());
                        return new d(cVar.m162(), cVar.m163());
                    }
                    if (context != null) {
                        context.unbindService(bVar);
                    }
                    throw new IOException("Google Play connection failed");
                } catch (Exception e) {
                    throw e;
                }
            } finally {
                if (context != null) {
                    context.unbindService(bVar);
                }
            }
        } catch (Exception e2) {
            throw e2;
        }
    }

    static final class b implements ServiceConnection {

        /* renamed from: ˊ, reason: contains not printable characters */
        private boolean f188;

        /* renamed from: ˏ, reason: contains not printable characters */
        private final LinkedBlockingQueue<IBinder> f189;

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
        }

        private b() {
            this.f188 = false;
            this.f189 = new LinkedBlockingQueue<>(1);
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            try {
                this.f189.put(iBinder);
            } catch (InterruptedException unused) {
            }
        }

        /* renamed from: ˏ, reason: contains not printable characters */
        public final IBinder m161() throws InterruptedException {
            if (this.f188) {
                throw new IllegalStateException();
            }
            this.f188 = true;
            return this.f189.take();
        }
    }

    static final class c implements IInterface {

        /* renamed from: ˎ, reason: contains not printable characters */
        private IBinder f190;

        c(IBinder iBinder) {
            this.f190 = iBinder;
        }

        @Override // android.os.IInterface
        public final IBinder asBinder() {
            return this.f190;
        }

        /* renamed from: ˋ, reason: contains not printable characters */
        public final String m162() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                this.f190.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        /* renamed from: ˎ, reason: contains not printable characters */
        final boolean m163() throws RemoteException {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.google.android.gms.ads.identifier.internal.IAdvertisingIdService");
                parcelObtain.writeInt(1);
                this.f190.transact(2, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readInt() != 0;
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }
}
