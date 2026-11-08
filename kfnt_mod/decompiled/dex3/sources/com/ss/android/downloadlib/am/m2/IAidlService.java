package com.ss.android.downloadlib.am.m2;

import android.annotation.SuppressLint;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.am.m2.ICallback;
import com.ss.android.socialbase.appdownloader.util.DecryptUtils;
import com.umeng.commonsdk.proguard.o;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface IAidlService extends IInterface {
    void doAction(AidlMsg aidlMsg, ICallback iCallback) throws RemoteException;

    public static abstract class Stub extends Binder implements IAidlService {
        private static String DESCRIPTOR = "";

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            if (TextUtils.isEmpty(DESCRIPTOR)) {
                JSONObject downloadSettings = GlobalInfo.getDownloadSettings();
                DESCRIPTOR = DecryptUtils.decrypt(downloadSettings.optString("r"), downloadSettings.optString(o.at));
            }
            attachInterface(this, DESCRIPTOR);
        }

        public static IAidlService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof IAidlService)) {
                return new Proxy(iBinder);
            }
            return (IAidlService) iInterfaceQueryLocalInterface;
        }

        @Override // android.os.Binder
        @SuppressLint({"WrongConstant"})
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            if (i == 1) {
                parcel.enforceInterface(DESCRIPTOR);
                doAction(parcel.readInt() != 0 ? AidlMsg.CREATOR.createFromParcel(parcel) : null, ICallback.Stub.asInterface(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        private static class Proxy implements IAidlService {
            private IBinder mRemote;

            Proxy(IBinder iBinder) {
                if (TextUtils.isEmpty(Stub.DESCRIPTOR)) {
                    JSONObject downloadSettings = GlobalInfo.getDownloadSettings();
                    String unused = Stub.DESCRIPTOR = DecryptUtils.decrypt(downloadSettings.optString("r"), downloadSettings.optString(o.at));
                }
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.ss.android.downloadlib.am.m2.IAidlService
            public void doAction(AidlMsg aidlMsg, ICallback iCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (aidlMsg != null) {
                        parcelObtain.writeInt(1);
                        aidlMsg.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeStrongBinder(iCallback != null ? iCallback.asBinder() : null);
                    this.mRemote.transact(1, parcelObtain, parcelObtain2, 0);
                    parcelObtain2.readException();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }
    }
}
