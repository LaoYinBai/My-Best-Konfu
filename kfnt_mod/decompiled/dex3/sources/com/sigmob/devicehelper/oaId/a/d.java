package com.sigmob.devicehelper.oaId.a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.util.Log;
import com.sigmob.logger.SigmobLog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface d extends IInterface {

    public static abstract class a extends Binder implements d {

        /* renamed from: com.sigmob.devicehelper.oaId.a.d$a$a, reason: collision with other inner class name */
        public static class C0006a implements d {
            public IBinder a;

            public C0006a(IBinder iBinder) {
                this.a = iBinder;
            }

            public String a(String str, String str2, String str3) {
                String string;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    try {
                        parcelObtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                        parcelObtain.writeString(str);
                        parcelObtain.writeString(str2);
                        parcelObtain.writeString(str3);
                        this.a.transact(1, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        string = parcelObtain2.readString();
                    } finally {
                        parcelObtain.recycle();
                        parcelObtain2.recycle();
                    }
                } catch (Exception e) {
                    e = e;
                    string = null;
                }
                try {
                    Log.d("oaid", "getSerID() called with: res = [" + string + "]");
                } catch (Exception e2) {
                    e = e2;
                    Log.d("oaid", "getSerID() called with: Exception = [" + e.getMessage() + "]");
                    SigmobLog.e(e.getMessage());
                    return string;
                }
                return string;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.a;
            }
        }

        public static d a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.heytap.openid.IOpenID");
            return (iInterfaceQueryLocalInterface == null || !(iInterfaceQueryLocalInterface instanceof d)) ? new C0006a(iBinder) : (d) iInterfaceQueryLocalInterface;
        }
    }
}
