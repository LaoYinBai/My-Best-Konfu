package com.xiaomi.push;

import com.xiaomi.push.ai;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bz extends ai.a {
    final /* synthetic */ bw a;

    bz(bw bwVar) {
        this.a = bwVar;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "10053";
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.f169a != null) {
            this.a.f169a.b(this.a.f166a);
            this.a.b("delete_time");
        }
    }
}
