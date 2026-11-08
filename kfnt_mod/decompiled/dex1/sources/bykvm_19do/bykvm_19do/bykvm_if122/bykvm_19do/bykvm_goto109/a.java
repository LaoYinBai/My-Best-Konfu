package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_goto109;

import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.c;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_try19.i;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {
    public static c a(c cVar, String str) {
        if (cVar == null || TextUtils.isEmpty(str) || cVar.u() == null || cVar.u().size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<i> it = cVar.u().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            i next = it.next();
            if (str.equals(next.d())) {
                arrayList.add(next.b());
                break;
            }
        }
        if (arrayList.size() == 0) {
            return null;
        }
        c cVarA = cVar.a();
        cVarA.a(arrayList);
        i iVar = arrayList.get(0);
        iVar.e(0);
        if (iVar.f() == 0) {
            cVarA.b(iVar.h());
        }
        if (iVar.f() == 2) {
            cVarA.b(true);
        }
        if (iVar.f() == 1 || iVar.f() == 3) {
            cVarA.a(true);
        }
        cVarA.f(0);
        return cVarA;
    }
}
