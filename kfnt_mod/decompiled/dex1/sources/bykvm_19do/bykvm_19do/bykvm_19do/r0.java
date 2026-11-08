package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import bykvm_19do.bykvm_19do.bykvm_19do.a1;
import bykvm_19do.bykvm_19do.bykvm_19do.i1;
import bykvm_19do.bykvm_19do.bykvm_19do.q1;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class r0 extends p0<q1> {
    r0() {
        super("com.mdid.msa");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.p0
    protected i1.b<q1, String> a() {
        return new a(this);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.p0, bykvm_19do.bykvm_19do.bykvm_19do.a1
    public a1.a b(Context context) {
        a(context, context.getPackageName());
        return super.b(context);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.p0
    protected Intent c(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
        intent.setAction("com.bun.msa.action.bindto.service");
        intent.putExtra("com.bun.msa.param.pkgname", context.getPackageName());
        return intent;
    }

    class a implements i1.b<q1, String> {
        a(r0 r0Var) {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public q1 a(IBinder iBinder) {
            return q1.a.a(iBinder);
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        public String a(q1 q1Var) {
            if (q1Var == null) {
                return null;
            }
            return q1Var.a();
        }
    }

    private void a(Context context, String str) {
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaKlService");
        intent.setAction("com.bun.msa.action.start.service");
        intent.putExtra("com.bun.msa.param.pkgname", str);
        try {
            intent.putExtra("com.bun.msa.param.runinset", true);
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
