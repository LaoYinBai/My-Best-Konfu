package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import bykvm_19do.bykvm_19do.bykvm_19do.i1;
import bykvm_19do.bykvm_19do.bykvm_19do.n1;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class w0 extends p0<n1> {
    w0() {
        super("com.zui.deviceidservice");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.p0
    protected i1.b<n1, String> a() {
        return new a(this);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.p0
    protected Intent c(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
        return intent;
    }

    class a implements i1.b<n1, String> {
        a(w0 w0Var) {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public n1 a(IBinder iBinder) {
            return n1.a.a(iBinder);
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        public String a(n1 n1Var) {
            if (n1Var == null) {
                return null;
            }
            return n1Var.a();
        }
    }
}
