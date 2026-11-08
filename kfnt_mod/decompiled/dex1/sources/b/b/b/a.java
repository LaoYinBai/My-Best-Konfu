package b.b.b;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import b.b.b.a.c;
import b.b.b.x;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class a extends c<b.b.b.a.c> {
    public a() {
        super("com.asus.msa.SupplementaryDID");
    }

    @Override // b.b.b.c
    public Intent a(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.asus.msa.action.ACCESS_DID");
        intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
        return intent;
    }

    @Override // b.b.b.c
    public x.b<b.b.b.a.c, String> a() {
        return new C0027a(this);
    }

    /* renamed from: b.b.b.a$a, reason: collision with other inner class name */
    public class C0027a implements x.b<b.b.b.a.c, String> {
        public C0027a(a aVar) {
        }

        @Override // b.b.b.x.b
        public String a(b.b.b.a.c cVar) {
            b.b.b.a.c cVar2 = cVar;
            if (cVar2 == null) {
                return null;
            }
            c.a.C0031a c0031a = (c.a.C0031a) cVar2;
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
                c0031a.f1156a.transact(3, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }

        @Override // b.b.b.x.b
        public b.b.b.a.c a(IBinder iBinder) {
            return c.a.a(iBinder);
        }
    }
}
