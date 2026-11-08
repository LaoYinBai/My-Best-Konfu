package com.alipay.sdk.widget;

import java.util.Iterator;
import java.util.Stack;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f {

    /* renamed from: a, reason: collision with root package name */
    private Stack<e> f2706a = new Stack<>();

    f() {
    }

    e a() {
        return this.f2706a.pop();
    }

    void a(e eVar) {
        this.f2706a.push(eVar);
    }

    boolean b() {
        return this.f2706a.isEmpty();
    }

    void c() {
        if (b()) {
            return;
        }
        Iterator<e> it = this.f2706a.iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f2706a.clear();
    }
}
