package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import anetwork.channel.aidl.a;
import anetwork.channel.aidl.e;
import anetwork.channel.aidl.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface h extends IInterface {
    NetworkResponse a(ParcelableRequest parcelableRequest) throws RemoteException;

    e a(ParcelableRequest parcelableRequest, g gVar) throws RemoteException;

    anetwork.channel.aidl.a b(ParcelableRequest parcelableRequest) throws RemoteException;

    public static abstract class a extends Binder implements h {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "anetwork.channel.aidl.RemoteNetwork");
        }

        public static h a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("anetwork.channel.aidl.RemoteNetwork");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof h)) {
                return (h) iInterfaceQueryLocalInterface;
            }
            return new C0018a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("anetwork.channel.aidl.RemoteNetwork");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("anetwork.channel.aidl.RemoteNetwork");
                    NetworkResponse networkResponseA = a(parcel.readInt() != 0 ? ParcelableRequest.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    if (networkResponseA != null) {
                        parcel2.writeInt(1);
                        networkResponseA.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 2:
                    parcel.enforceInterface("anetwork.channel.aidl.RemoteNetwork");
                    e eVarA = a(parcel.readInt() != 0 ? ParcelableRequest.CREATOR.createFromParcel(parcel) : null, g.a.a(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(eVarA != null ? eVarA.asBinder() : null);
                    return true;
                case 3:
                    parcel.enforceInterface("anetwork.channel.aidl.RemoteNetwork");
                    anetwork.channel.aidl.a aVarB = b(parcel.readInt() != 0 ? ParcelableRequest.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(aVarB != null ? aVarB.asBinder() : null);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* renamed from: anetwork.channel.aidl.h$a$a, reason: collision with other inner class name */
        private static class C0018a implements h {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f674a;

            C0018a(IBinder iBinder) {
                this.f674a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f674a;
            }

            @Override // anetwork.channel.aidl.h
            public NetworkResponse a(ParcelableRequest parcelableRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.RemoteNetwork");
                    if (parcelableRequest != null) {
                        parcelObtain.writeInt(1);
                        parcelableRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f674a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? NetworkResponse.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.h
            public e a(ParcelableRequest parcelableRequest, g gVar) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.RemoteNetwork");
                    if (parcelableRequest != null) {
                        parcelObtain.writeInt(1);
                        parcelableRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(gVar != null ? gVar.asBinder() : null);
                    this.f674a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return e.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.h
            public anetwork.channel.aidl.a b(ParcelableRequest parcelableRequest) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.RemoteNetwork");
                    if (parcelableRequest != null) {
                        parcelObtain.writeInt(1);
                        parcelableRequest.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    this.f674a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return a.AbstractBinderC0011a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
