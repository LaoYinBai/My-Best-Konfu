package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import bykvm_19do.bykvm_19do.bykvm_19do.i1;
import bykvm_19do.bykvm_19do.bykvm_19do.m1;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class h1 extends p0<m1> {
    h1() {
        super("com.samsung.android.deviceidservice");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.p0
    protected i1.b<m1, String> a() {
        return new a(this);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.p0
    protected Intent c(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
        return intent;
    }

    class a implements i1.b<m1, String> {
        a(h1 h1Var) {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public m1 a(IBinder iBinder) {
            return m1.a.a(iBinder);
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        public String a(m1 m1Var) {
            return m1Var.a();
        }
    }
}
