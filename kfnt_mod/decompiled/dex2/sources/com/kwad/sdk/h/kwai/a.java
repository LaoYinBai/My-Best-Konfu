package com.kwad.sdk.h.kwai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private final List<b> a = new ArrayList();

    public void a(b bVar) {
        if (bVar != null) {
            this.a.add(bVar);
        }
    }

    public void a(b bVar, int i) {
        if (bVar != null) {
            this.a.add(i, bVar);
        }
    }

    public boolean a() {
        Iterator<b> it = this.a.iterator();
        while (it.hasNext()) {
            if (it.next().a_()) {
                return true;
            }
        }
        return false;
    }

    public void b(b bVar) {
        if (bVar != null) {
            this.a.remove(bVar);
        }
    }
}
