package com.bytedance.bdturing.c;

import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private HashMap<String, h> f3782a = new HashMap<>();

    public g() {
        this.f3782a.put(h.f3783a, new a());
        this.f3782a.put(h.d, new d());
        this.f3782a.put(h.f3784b, new b());
        this.f3782a.put(h.f3785c, new c());
        this.f3782a.put(h.e, new e());
    }

    public void a(String str, h hVar) {
        this.f3782a.put(str, hVar);
    }

    public void a(String str) {
        this.f3782a.remove(str);
    }

    public boolean a(com.bytedance.bdturing.f fVar, String str) {
        h hVar;
        i iVar = new i(str);
        if (iVar.s == null || (hVar = this.f3782a.get(iVar.s)) == null) {
            return false;
        }
        hVar.a(fVar, iVar);
        return true;
    }

    public void a() {
        this.f3782a.clear();
    }
}
