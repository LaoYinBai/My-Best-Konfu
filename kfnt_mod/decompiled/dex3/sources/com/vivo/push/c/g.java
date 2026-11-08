package com.vivo.push.c;

import java.util.ArrayList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class g extends o {
    public g(com.vivo.push.g gVar) {
        super(gVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(com.vivo.push.g gVar) {
        com.vivo.push.b.t tVar = (com.vivo.push.b.t) gVar;
        ArrayList<String> arrayList = tVar.c;
        ArrayList<String> arrayList2 = tVar.d;
        final ArrayList arrayList3 = new ArrayList();
        final ArrayList arrayList4 = new ArrayList();
        final ArrayList arrayList5 = new ArrayList();
        final ArrayList arrayList6 = new ArrayList();
        final int i = tVar.g;
        final String str = tVar.f;
        if (arrayList != null) {
            for (String str2 : arrayList) {
                if (str2.startsWith("ali/")) {
                    arrayList4.add(str2.replace("ali/", ""));
                } else if (str2.startsWith("tag/")) {
                    arrayList3.add(str2.replace("tag/", ""));
                }
            }
        }
        if (arrayList2 != null) {
            for (String str3 : arrayList2) {
                if (str3.startsWith("ali/")) {
                    arrayList6.add(str3.replace("ali/", ""));
                } else if (str3.startsWith("tag/")) {
                    arrayList5.add(str3.replace("tag/", ""));
                }
            }
        }
        if (arrayList3.size() > 0 || arrayList5.size() > 0) {
            if (arrayList3.size() > 0) {
                com.vivo.push.d.a().b(arrayList3);
            }
            com.vivo.push.d.a().a(tVar.f, arrayList5.size() > 0 ? 10000 : i);
            com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.g.1
                @Override // java.lang.Runnable
                public final void run() {
                    g.this.c.onDelTags(g.this.a, i, arrayList3, arrayList5, str);
                }
            });
        }
        if (arrayList4.size() > 0 || arrayList6.size() > 0) {
            if (arrayList4.size() > 0) {
                com.vivo.push.d dVarA = com.vivo.push.d.a();
                if (arrayList4.contains(dVarA.i)) {
                    dVarA.d();
                }
            }
            com.vivo.push.d.a().a(tVar.f, i);
            com.vivo.push.f.b(new Runnable() { // from class: com.vivo.push.c.g.2
                @Override // java.lang.Runnable
                public final void run() {
                    g.this.c.onDelAlias(g.this.a, i, arrayList4, arrayList6, str);
                }
            });
        }
    }
}
