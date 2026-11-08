package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.Context;
import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class b1 {
    static a1 a(Context context) {
        if (i.e()) {
            return new f1(new l1());
        }
        if (l1.a()) {
            return new l1();
        }
        if (e1.a()) {
            return new e1(context);
        }
        if (i.a(context) || i.c()) {
            return new v0();
        }
        if (i.g()) {
            return new f1();
        }
        if (i.f()) {
            return new x0();
        }
        if (Build.VERSION.SDK_INT > 28) {
            return i.h() ? new h1() : i.j() ? new y0() : i.i() ? new w0() : i.k() ? new n0() : new r0();
        }
        if (i.d() || !v0.d(context)) {
            return null;
        }
        return new v0();
    }
}
