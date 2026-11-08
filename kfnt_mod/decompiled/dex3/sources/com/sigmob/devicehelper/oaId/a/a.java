package com.sigmob.devicehelper.oaId.a;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import com.sigmob.logger.SigmobLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface a extends IInterface {

    /* renamed from: com.sigmob.devicehelper.oaId.a.a$a, reason: collision with other inner class name */
    public static final class C0004a implements a {
        private IBinder a;

        public C0004a(IBinder iBinder) {
            this.a = iBinder;
        }

        public String a() {
            String string;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
                this.a.transact(3, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                string = parcelObtain2.readString();
            } catch (Throwable th) {
                parcelObtain.recycle();
                parcelObtain2.recycle();
                SigmobLog.e(th.getMessage());
                string = null;
            }
            parcelObtain.recycle();
            parcelObtain2.recycle();
            return string;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this.a;
        }
    }
}
