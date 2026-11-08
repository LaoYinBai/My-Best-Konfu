package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_19do.a1;
import bykvm_19do.bykvm_19do.bykvm_19do.i1;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
abstract class p0<SERVICE> implements a1 {

    /* renamed from: a, reason: collision with root package name */
    private final String f1366a;

    /* renamed from: b, reason: collision with root package name */
    private o0<Boolean> f1367b = new a();

    class a extends o0<Boolean> {
        a() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // bykvm_19do.bykvm_19do.bykvm_19do.o0
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Boolean a(Object... objArr) {
            return Boolean.valueOf(u0.a((Context) objArr[0], p0.this.f1366a));
        }
    }

    p0(String str) {
        this.f1366a = str;
    }

    protected abstract i1.b<SERVICE, String> a();

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.a1
    public a1.a b(Context context) {
        return a((String) new i1(context, c(context), a()).a());
    }

    protected abstract Intent c(Context context);

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.a1
    public boolean a(Context context) {
        if (context == null) {
            return false;
        }
        return this.f1367b.b(context).booleanValue();
    }

    private a1.a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        a1.a aVar = new a1.a();
        aVar.f1312a = str;
        return aVar;
    }
}
