package com.kwad.sdk.pngencrypt.chunk;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ah {
    private final e a;
    private final boolean b;

    public ah(e eVar) {
        this.a = eVar;
        this.b = !(eVar instanceof f);
    }

    public List<? extends ae> a(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.a.a("tEXt", str));
        arrayList.addAll(this.a.a("zTXt", str));
        arrayList.addAll(this.a.a("iTXt", str));
        return arrayList;
    }

    public String b(String str) {
        List<? extends ae> listA = a(str);
        if (listA.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<? extends ae> it = listA.iterator();
        while (it.hasNext()) {
            sb.append(it.next().d());
            sb.append("\n");
        }
        return sb.toString().trim();
    }
}
