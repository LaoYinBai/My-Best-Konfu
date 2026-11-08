package com.xiaomi.clientreport.manager;

import com.xiaomi.push.ai;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class d extends ai.a {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "100888";
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.a() > 0) {
            this.a.f37a.execute(new e(this));
        }
    }
}
