package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import anetwork.channel.aidl.f;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface a extends IInterface {
    f a() throws RemoteException;

    int b() throws RemoteException;

    String c() throws RemoteException;

    Map d() throws RemoteException;

    anetwork.channel.i.a e() throws RemoteException;

    void f() throws RemoteException;

    /* renamed from: anetwork.channel.aidl.a$a, reason: collision with other inner class name */
    public static abstract class AbstractBinderC0011a extends Binder implements a {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public AbstractBinderC0011a() {
            attachInterface(this, "anetwork.channel.aidl.Connection");
        }

        public static a a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("anetwork.channel.aidl.Connection");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof a)) {
                return (a) iInterfaceQueryLocalInterface;
            }
            return new C0012a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString("anetwork.channel.aidl.Connection");
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    f fVarA = a();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(fVarA != null ? fVarA.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    int iB = b();
                    parcel2.writeNoException();
                    parcel2.writeInt(iB);
                    return true;
                case 3:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    String strC = c();
                    parcel2.writeNoException();
                    parcel2.writeString(strC);
                    return true;
                case 4:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    Map mapD = d();
                    parcel2.writeNoException();
                    parcel2.writeMap(mapD);
                    return true;
                case 5:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    anetwork.channel.i.a aVarE = e();
                    parcel2.writeNoException();
                    if (aVarE != null) {
                        parcel2.writeInt(1);
                        parcel2.writeSerializable(aVarE);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 6:
                    parcel.enforceInterface("anetwork.channel.aidl.Connection");
                    f();
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* renamed from: anetwork.channel.aidl.a$a$a, reason: collision with other inner class name */
        private static class C0012a implements a {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f652a;

            C0012a(IBinder iBinder) {
                this.f652a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f652a;
            }

            @Override // anetwork.channel.aidl.a
            public f a() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.Connection");
                    this.f652a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return f.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.a
            public int b() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.Connection");
                    this.f652a.transact(2, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.a
            public String c() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.Connection");
                    this.f652a.transact(3, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readString();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.a
            public Map d() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.Connection");
                    this.f652a.transact(4, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readHashMap(getClass().getClassLoader());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.a
            public anetwork.channel.i.a e() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.Connection");
                    this.f652a.transact(5, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() == 1 ? (anetwork.channel.i.a) parcelObtain2.readSerializable() : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // anetwork.channel.aidl.a
            public void f() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.Connection");
                    this.f652a.transact(6, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
