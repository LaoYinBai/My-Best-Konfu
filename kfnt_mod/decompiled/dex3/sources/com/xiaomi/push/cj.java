package com.xiaomi.push;

import com.xiaomi.push.ai;
import com.xiaomi.push.ci;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class cj extends ai.a {
    final /* synthetic */ ci a;

    cj(ci ciVar) {
        this.a = ciVar;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "100957";
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.a.f181a) {
            if (this.a.f181a.size() > 0) {
                if (this.a.f181a.size() > 1) {
                    this.a.a(this.a.f181a);
                } else {
                    this.a.b((ci.a) this.a.f181a.get(0));
                }
                this.a.f181a.clear();
                System.gc();
            }
        }
    }
}
