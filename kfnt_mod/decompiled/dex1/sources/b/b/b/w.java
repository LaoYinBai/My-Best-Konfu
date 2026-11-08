package b.b.b;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import b.b.b.a.a;
import b.b.b.x;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class w extends c<b.b.b.a.a> {

    public class a implements x.b<b.b.b.a.a, String> {
        public a(w wVar) {
        }

        @Override // b.b.b.x.b
        public b.b.b.a.a a(IBinder iBinder) {
            return a.AbstractBinderC0028a.a(iBinder);
        }

        @Override // b.b.b.x.b
        public String a(b.b.b.a.a aVar) {
            return ((a.AbstractBinderC0028a.C0029a) aVar).a();
        }
    }

    public w() {
        super("com.samsung.android.deviceidservice");
    }

    @Override // b.b.b.c
    public Intent a(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
        return intent;
    }

    @Override // b.b.b.c
    public x.b<b.b.b.a.a, String> a() {
        return new a(this);
    }
}
