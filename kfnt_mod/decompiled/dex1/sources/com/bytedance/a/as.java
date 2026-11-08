package com.bytedance.a;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import com.bytedance.a.da;
import com.bytedance.a.dp;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class as extends dy<dp> {

    public class a implements da.b<dp, String> {
        public a(as asVar) {
        }

        @Override // com.bytedance.a.da.b
        public dp a(IBinder iBinder) {
            return dp.a.a(iBinder);
        }

        @Override // com.bytedance.a.da.b
        public String a(dp dpVar) {
            dp dpVar2 = dpVar;
            if (dpVar2 == null) {
                return null;
            }
            dp.a.C0099a c0099a = (dp.a.C0099a) dpVar2;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                c0099a.f3512a.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }

    public as() {
        super("com.zui.deviceidservice");
    }

    @Override // com.bytedance.a.dy
    public da.b<dp, String> a() {
        return new a(this);
    }

    @Override // com.bytedance.a.dy
    public Intent b(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        return intent;
    }
}
