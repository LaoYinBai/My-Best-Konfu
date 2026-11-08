package b.b.b;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import b.b.b.p;
import b.b.b.x;
import b.c.a.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class e extends c<b.c.a.a> {
    public e() {
        super("com.mdid.msa");
    }

    @Override // b.b.b.c
    public Intent a(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
        intent.setAction("com.bun.msa.action.bindto.service");
        intent.putExtra("com.bun.msa.param.pkgname", context.getPackageName());
        return intent;
    }

    @Override // b.b.b.c, b.b.b.p
    public p.a c(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaKlService");
        intent.setAction("com.bun.msa.action.start.service");
        intent.putExtra("com.bun.msa.param.pkgname", packageName);
        try {
            intent.putExtra("com.bun.msa.param.runinset", true);
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.c(context);
    }

    @Override // b.b.b.c
    public x.b<b.c.a.a, String> a() {
        return new a(this);
    }

    public class a implements x.b<b.c.a.a, String> {
        public a(e eVar) {
        }

        @Override // b.b.b.x.b
        public String a(b.c.a.a aVar) {
            b.c.a.a aVar2 = aVar;
            if (aVar2 == null) {
                return null;
            }
            a.AbstractBinderC0033a.C0034a c0034a = (a.AbstractBinderC0033a.C0034a) aVar2;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                c0034a.f1216a.transact(3, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // b.b.b.x.b
        public b.c.a.a a(IBinder iBinder) {
            return a.AbstractBinderC0033a.a(iBinder);
        }
    }
}
