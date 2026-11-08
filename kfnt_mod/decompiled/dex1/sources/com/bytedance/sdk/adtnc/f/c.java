package com.bytedance.sdk.adtnc.f;

import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c extends a {

    /* renamed from: c, reason: collision with root package name */
    private boolean f5111c;
    private int d;
    private HashMap<String, Integer> e;
    private HashMap<String, Integer> f;
    private int g;
    private HashMap<String, Integer> h;
    private HashMap<String, Integer> i;

    public c(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        super(aVar);
        this.f5111c = true;
        this.d = 0;
        this.e = new HashMap<>();
        this.f = new HashMap<>();
        this.g = 0;
        this.h = new HashMap<>();
        this.i = new HashMap<>();
    }

    private void a() {
        com.bytedance.sdk.adtnc.g.b.a("NetErrorStrategy", "resetTNCControlState", "reset data");
        if (this.d > 0 || this.g > 0) {
            this.d = 0;
            this.e.clear();
            this.f.clear();
            this.g = 0;
            this.h.clear();
            this.i.clear();
        }
    }

    private void b() {
        com.bytedance.sdk.adtnc.g.b.a("NetErrorStrategy", "triggerTNC", "strategy trigger focus: ", Boolean.valueOf(this.f5111c));
        a(this.f5111c);
        a();
        this.f5111c = false;
    }

    public void a(int i, String str, String str2, com.bytedance.sdk.adtnc.a.a aVar) {
        if (i > 0) {
            if (i >= 200 && i < 400) {
                com.bytedance.sdk.adtnc.g.b.a("NetErrorStrategy", "onResponse", "success");
                a();
                this.f5111c = true;
                return;
            }
            this.g++;
            this.h.put(str, 0);
            this.i.put(str2, 0);
            com.bytedance.sdk.adtnc.g.b.a("NetErrorStrategy", "onResponse", "net error mReqToCnt:", Integer.valueOf(this.d));
            if (this.g < aVar.h || this.h.size() < aVar.i || this.i.size() < aVar.j) {
                return;
            }
            b();
        }
    }

    public void a(String str, String str2, com.bytedance.sdk.adtnc.a.a aVar) {
        this.d++;
        this.e.put(str, 0);
        this.f.put(str2, 0);
        com.bytedance.sdk.adtnc.g.b.a("NetErrorStrategy", "onError", "net error  mReqToCnt:", Integer.valueOf(this.d));
        if (this.d < aVar.e || this.e.size() < aVar.f || this.f.size() < aVar.g) {
            return;
        }
        b();
    }
}
