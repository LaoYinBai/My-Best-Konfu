package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import bykvm_19do.bykvm_19do.bykvm_19do.i1;
import bykvm_19do.bykvm_19do.bykvm_19do.o1;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class n0 extends p0<o1> {
    n0() {
        super("com.asus.msa.SupplementaryDID");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.p0
    protected i1.b<o1, String> a() {
        return new a(this);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.p0
    protected Intent c(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.asus.msa.action.ACCESS_DID");
        intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
        return intent;
    }

    class a implements i1.b<o1, String> {
        a(n0 n0Var) {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public o1 a(IBinder iBinder) {
            return o1.a.a(iBinder);
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        public String a(o1 o1Var) {
            if (o1Var == null) {
                return null;
            }
            return o1Var.b();
        }
    }
}
