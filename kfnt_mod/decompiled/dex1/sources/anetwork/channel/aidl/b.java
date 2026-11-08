package anetwork.channel.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import anetwork.channel.aidl.h;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface b extends IInterface {
    h a(int i) throws RemoteException;

    public static abstract class a extends Binder implements b {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public a() {
            attachInterface(this, "anetwork.channel.aidl.IRemoteNetworkGetter");
        }

        public static b a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("anetwork.channel.aidl.IRemoteNetworkGetter");
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof b)) {
                return (b) iInterfaceQueryLocalInterface;
            }
            return new C0013a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("anetwork.channel.aidl.IRemoteNetworkGetter");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("anetwork.channel.aidl.IRemoteNetworkGetter");
            h hVarA = a(parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeStrongBinder(hVarA != null ? hVarA.asBinder() : null);
            return true;
        }

        /* renamed from: anetwork.channel.aidl.b$a$a, reason: collision with other inner class name */
        private static class C0013a implements b {

            /* renamed from: a, reason: collision with root package name */
            private IBinder f668a;

            C0013a(IBinder iBinder) {
                this.f668a = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f668a;
            }

            @Override // anetwork.channel.aidl.b
            public h a(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken("anetwork.channel.aidl.IRemoteNetworkGetter");
                    parcelObtain.writeInt(i);
                    this.f668a.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                    return h.a.a(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
