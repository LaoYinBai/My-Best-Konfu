package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface f extends IInterface {
    int a(byte[] bArr) throws RemoteException;

    int a(byte[] bArr, int i, int i2) throws RemoteException;

    long a(int i) throws RemoteException;

    int b() throws RemoteException;

    void c() throws RemoteException;

    int d() throws RemoteException;

    int e() throws RemoteException;

    public static abstract class a extends Binder implements f {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "anetwork.channel.aidl.ParcelableInputStream");
        }

        public static f a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("anetwork.channel.aidl.ParcelableInputStream");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof f)) {
                return (f) iInterfaceQueryLocalInterface;
            }
            return new C0016a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            byte[] bArr;
            if (i == 1598968902) {
                parcel2.writeString("anetwork.channel.aidl.ParcelableInputStream");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    int iB = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 2:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    c();
                    parcel2.writeNoException();
                    return true;
                case 3:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    int iD = d();
                    parcel2.writeNoException();
                    parcel2.writeInt(iD);
                    return true;
                case 4:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    int i3 = parcel.readInt();
                    bArr = i3 >= 0 ? new byte[i3] : null;
                    int iA = a(bArr, parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iA);
                    parcel2.writeByteArray(bArr);
                    return true;
                case 5:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    int i4 = parcel.readInt();
                    bArr = i4 >= 0 ? new byte[i4] : null;
                    int iA2 = a(bArr);
                    parcel2.writeNoException();
                    parcel2.writeInt(iA2);
                    parcel2.writeByteArray(bArr);
                    return true;
                case 6:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    long jA = a(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeLong(jA);
                    return true;
                case 7:
                    parcel.enforceInterface("anetwork.channel.aidl.ParcelableInputStream");
                    int iE = e();
                    parcel2.writeNoException();
                    parcel2.writeInt(iE);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* renamed from: anetwork.channel.aidl.f$a$a, reason: collision with other inner class name */
        private static class C0016a implements f {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f672a;

            C0016a(IBinder iBinder) {
                this.f672a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f672a;
            }

            @Override // anetwork.channel.aidl.f
            public int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                    this.f672a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.f
            public void c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                    this.f672a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.f
            public int d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                    this.f672a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.f
            public int a(byte[] bArr, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                    if (bArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(bArr.length);
                    }
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    this.f672a.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    int i3 = parcelObtain2.readInt();
                    parcelObtain2.readByteArray(bArr);
                    return i3;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.f
            public int a(byte[] bArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                    if (bArr == null) {
                        parcelObtain.writeInt(-1);
                    } else {
                        parcelObtain.writeInt(bArr.length);
                    }
                    this.f672a.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    int i = parcelObtain2.readInt();
                    parcelObtain2.readByteArray(bArr);
                    return i;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.f
            public long a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                    parcelObtain.writeInt(i);
                    this.f672a.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readLong();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.f
            public int e() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.ParcelableInputStream");
                    this.f672a.transact(7, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
