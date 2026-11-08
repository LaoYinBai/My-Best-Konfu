package com.bytedance.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import com.bytedance.a.da;
import com.bytedance.a.du;

/* renamed from: com.bytedance.a.do, reason: invalid class name */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class Cdo extends dy<du> {

    /* renamed from: com.bytedance.a.do$a */
    public class a implements da.b<du, String> {
        public a(Cdo cdo) {
        }

        @Override // com.bytedance.a.da.b
        public du a(IBinder iBinder) {
            return du.a.a(iBinder);
        }

        @Override // com.bytedance.a.da.b
        public String a(du duVar) {
            du duVar2 = duVar;
            if (duVar2 == null) {
                return null;
            }
            du.a.C0100a c0100a = (du.a.C0100a) duVar2;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
                c0100a.f3517a.transact(3, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }

    public Cdo() {
        super("com.asus.msa.SupplementaryDID");
    }

    @Override // com.bytedance.a.dy
    public da.b<du, String> a() {
        return new a(this);
    }

    @Override // com.bytedance.a.dy
    public Intent b(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.asus.msa.action.ACCESS_DID");
        intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
        return intent;
    }
}
