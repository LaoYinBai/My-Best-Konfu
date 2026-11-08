package com.ss.union.game.sdk.vcenter;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface m extends IInterface {
    void a(String str) throws RemoteException;

    public static abstract class a extends Binder implements m {
        static final int a = 1;
        private static final String b = "com.ss.union.game.sdk.vcenter.IUniversalCmdCallback";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, b);
        }

        public static m a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(b);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof m)) {
                return (m) iInterfaceQueryLocalInterface;
            }
            return new C0115a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(b);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(b);
            a(parcel.readString());
            parcel2.writeNoException();
            return true;
        }

        /* renamed from: com.ss.union.game.sdk.vcenter.m$a$a, reason: collision with other inner class name */
        private static class C0115a implements m {
            private IBinder a;

            public String a() {
                return a.b;
            }

            C0115a(IBinder iBinder) {
                this.a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }

            @Override // com.ss.union.game.sdk.vcenter.m
            public void a(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(a.b);
                    parcelObtain.writeString(str);
                    this.a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
