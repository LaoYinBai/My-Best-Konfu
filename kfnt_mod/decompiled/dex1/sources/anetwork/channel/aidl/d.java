package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface d extends IInterface {
    int a(byte[] bArr) throws RemoteException;

    boolean a() throws RemoteException;

    public static abstract class a extends Binder implements d {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("anetwork.channel.aidl.ParcelableBodyHandler");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof d)) {
                return (d) iInterfaceQueryLocalInterface;
            }
            return new C0014a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("anetwork.channel.aidl.ParcelableBodyHandler");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableBodyHandler");
                    byte[] bArrCreateByteArray = parcel.createByteArray();
                    int iA = a(bArrCreateByteArray);
                    parcel2.writeNoException();
                    parcel2.writeInt(iA);
                    parcel2.writeByteArray(bArrCreateByteArray);
                    return true;
                case 2:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableBodyHandler");
                    boolean zA = a();
                    parcel2.writeNoException();
                    parcel2.writeInt(zA ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* renamed from: anetwork.channel.aidl.d$a$a, reason: collision with other inner class name */
        private static class C0014a implements d {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f670a;

            C0014a(IBinder iBinder) {
                this.f670a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f670a;
            }

            @Override // anetwork.channel.aidl.d
            public int a(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableBodyHandler");
                    parcelObtain.writeByteArray(bArr);
                    this.f670a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    int i = parcelObtain2.readInt();
                    parcelObtain2.readByteArray(bArr);
                    return i;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.d
            public boolean a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableBodyHandler");
                    this.f670a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
