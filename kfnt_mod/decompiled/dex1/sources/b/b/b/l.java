package b.b.b;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import b.b.b.a.b;
import b.b.b.x;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class l extends c<b.b.b.a.b> {
    public l() {
        super("com.zui.deviceidservice");
    }

    @Override // b.b.b.c
    public Intent a(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        return intent;
    }

    @Override // b.b.b.c
    public x.b<b.b.b.a.b, String> a() {
        return new a(this);
    }

    public class a implements x.b<b.b.b.a.b, String> {
        public a(l lVar) {
        }

        @Override // b.b.b.x.b
        public String a(b.b.b.a.b bVar) {
            b.b.b.a.b bVar2 = bVar;
            if (bVar2 == null) {
                return null;
            }
            b.a.C0030a c0030a = (b.a.C0030a) bVar2;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.zui.deviceidservice.IDeviceidInterface");
                c0030a.f1155a.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // b.b.b.x.b
        public b.b.b.a.b a(IBinder iBinder) {
            return b.a.a(iBinder);
        }
    }
}
