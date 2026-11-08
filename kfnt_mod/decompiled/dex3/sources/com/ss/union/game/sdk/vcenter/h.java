package com.ss.union.game.sdk.vcenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.ss.union.game.sdk.vcenter.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface h extends IInterface {
    void a(int i, String str, String str2) throws RemoteException;

    void a(String str, g gVar) throws RemoteException;

    void b(String str, g gVar) throws RemoteException;

    public static abstract class a extends Binder implements h {
        static final int a = 1;
        static final int b = 2;
        static final int c = 3;
        private static final String d = "com.ss.union.game.sdk.vcenter.ISdkBindCallback";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, d);
        }

        public static h a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(d);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof h)) {
                return (h) iInterfaceQueryLocalInterface;
            }
            return new C0110a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(d);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(d);
                    a(parcel.readString(), g.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(d);
                    a(parcel.readInt(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface(d);
                    b(parcel.readString(), g.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* renamed from: com.ss.union.game.sdk.vcenter.h$a$a, reason: collision with other inner class name */
        private static class C0110a implements h {
            private IBinder a;

            public String a() {
                return a.d;
            }

            C0110a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            @Override // com.ss.union.game.sdk.vcenter.h
            public void a(String str, g gVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(a.d);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                    this.a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.union.game.sdk.vcenter.h
            public void a(int i, String str, String str2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(a.d);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeString(str);
                    parcelObtain.writeString(str2);
                    this.a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // com.ss.union.game.sdk.vcenter.h
            public void b(String str, g gVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(a.d);
                    parcelObtain.writeString(str);
                    parcelObtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                    this.a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
