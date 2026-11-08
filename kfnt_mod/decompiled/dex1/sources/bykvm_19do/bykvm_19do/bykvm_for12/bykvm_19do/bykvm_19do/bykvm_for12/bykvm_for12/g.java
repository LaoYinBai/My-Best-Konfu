package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.bykvm_for12;

import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.a0;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.y;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g implements t {

    /* renamed from: a, reason: collision with root package name */
    private int f1436a;

    public void a(int i) {
        this.f1436a = i;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.t
    public a0 a(t.a aVar) throws IOException {
        a0 a0VarA;
        y yVarB = aVar.b();
        if (h.a().a(this.f1436a).a() != null) {
            h.a().a(this.f1436a).a().e();
        }
        String string = yVarB.g().toString();
        String strA = h.a().a(this.f1436a).a(string);
        if (!string.equals(strA)) {
            yVarB = yVarB.f().b(strA).a();
        }
        try {
            a0VarA = aVar.a(yVarB);
        } catch (Exception e) {
            h.a().a(this.f1436a).a(yVarB, e);
            a0VarA = null;
        }
        h.a().a(this.f1436a).a(yVarB, a0VarA);
        return a0VarA == null ? aVar.a(yVarB) : a0VarA;
    }
}
