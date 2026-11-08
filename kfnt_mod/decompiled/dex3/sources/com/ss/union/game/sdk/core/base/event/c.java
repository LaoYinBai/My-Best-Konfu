package com.ss.union.game.sdk.core.base.event;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c {
    private List<b> a = new ArrayList();

    c() {
    }

    c(b bVar) {
        a(bVar);
    }

    public void a(b bVar) {
        this.a.add(bVar);
    }

    public synchronized int a() {
        return this.a.size();
    }

    public List<b> b() {
        return this.a;
    }

    public JSONArray c() {
        JSONArray jSONArray = new JSONArray();
        Iterator<b> it = this.a.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().a());
        }
        return jSONArray;
    }

    public void a(c cVar) {
        List<b> listB = cVar.b();
        if (listB != null) {
            Iterator<b> it = listB.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
        }
    }
}
