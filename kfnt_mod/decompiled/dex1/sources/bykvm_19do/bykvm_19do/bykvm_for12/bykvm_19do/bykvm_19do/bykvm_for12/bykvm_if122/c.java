package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_if122;

import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_int108.d;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.f;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.r;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.u;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.v;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.y;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.z;
import com.bytedance.b.a.a.c.e;
import java.io.IOException;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends b {
    z e;

    public c(v vVar) {
        super(vVar);
        this.e = null;
    }

    public void a(String str, byte[] bArr) {
        this.e = z.a(u.a(str), bArr);
    }

    public bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.b b() {
        y.a aVar = new y.a();
        if (TextUtils.isEmpty(this.d)) {
            d.a("PostExecutor", "execute: Url is Empty");
            return null;
        }
        try {
            aVar.b(this.d);
            if (this.e == null) {
                d.a("PostExecutor", "RequestBody is null, content type is not support!!");
                return null;
            }
            a(aVar);
            aVar.a((Object) a());
            try {
                a0 a0VarI = this.f1442a.a(aVar.a(this.e).a()).i();
                if (a0VarI != null) {
                    HashMap map = new HashMap();
                    r rVarM = a0VarI.m();
                    if (rVarM != null) {
                        for (int i = 0; i < rVarM.b(); i++) {
                            map.put(rVarM.a(i), rVarM.b(i));
                        }
                        return new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.b(a0VarI.n(), a0VarI.k(), a0VarI.o(), map, a0VarI.i().l(), a0VarI.t(), a0VarI.r());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } catch (IllegalArgumentException unused) {
            d.a("PostExecutor", "execute: Url is not a valid HTTP or HTTPS URL");
            return null;
        }
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "{}";
        }
        this.e = z.a(u.a(e.f3610c), str);
    }

    class a implements f {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a f1445a;

        a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a aVar) {
            this.f1445a = aVar;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.f
        public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.e eVar, IOException iOException) {
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a aVar = this.f1445a;
            if (aVar != null) {
                aVar.a(c.this, iOException);
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.f
        public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.e eVar, a0 a0Var) throws IOException {
            if (this.f1445a != null) {
                HashMap map = new HashMap();
                if (a0Var != null) {
                    r rVarM = a0Var.m();
                    if (rVarM != null) {
                        for (int i = 0; i < rVarM.b(); i++) {
                            map.put(rVarM.a(i), rVarM.b(i));
                        }
                    }
                    this.f1445a.a(c.this, new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.b(a0Var.n(), a0Var.k(), a0Var.o(), map, a0Var.i().l(), a0Var.t(), a0Var.r()));
                }
            }
        }
    }

    public void a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.a aVar) {
        y.a aVar2 = new y.a();
        if (TextUtils.isEmpty(this.d)) {
            aVar.a(this, new IOException("Url is Empty"));
            return;
        }
        try {
            aVar2.b(this.d);
            if (this.e == null) {
                if (aVar != null) {
                    aVar.a(this, new IOException("RequestBody is null, content type is not support!!"));
                }
            } else {
                a(aVar2);
                aVar2.a((Object) a());
                this.f1442a.a(aVar2.a(this.e).a()).a(new a(aVar));
            }
        } catch (IllegalArgumentException unused) {
            aVar.a(this, new IOException("Url is not a valid HTTP or HTTPS URL"));
        }
    }
}
