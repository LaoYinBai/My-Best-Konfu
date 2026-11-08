package com.bytedance.a;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import com.bytedance.a.bm;
import com.bytedance.a.da;
import com.bytedance.a.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class j extends dy<f> {

    public class a implements da.b<f, String> {
        public a(j jVar) {
        }

        @Override // com.bytedance.a.da.b
        public f a(IBinder iBinder) {
            return f.a.a(iBinder);
        }

        @Override // com.bytedance.a.da.b
        public String a(f fVar) {
            f fVar2 = fVar;
            if (fVar2 == null) {
                return null;
            }
            f.a.C0102a c0102a = (f.a.C0102a) fVar2;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                c0102a.f3523a.transact(3, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }

    public j() {
        super("com.mdid.msa");
    }

    @Override // com.bytedance.a.dy, com.bytedance.a.bm
    public bm.a a(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaKlService");
        intent.setAction("com.bun.msa.action.start.service");
        intent.putExtra("com.bun.msa.param.pkgname", packageName);
        try {
            intent.putExtra("com.bun.msa.param.runinset", true);
            context.startService(intent);
        } catch (Exception e) {
            de.a("", e);
        }
        return super.a(context);
    }

    @Override // com.bytedance.a.dy
    public da.b<f, String> a() {
        return new a(this);
    }

    @Override // com.bytedance.a.dy
    public Intent b(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
        intent.setAction("com.bun.msa.action.bindto.service");
        intent.putExtra("com.bun.msa.param.pkgname", context.getPackageName());
        return intent;
    }
}
