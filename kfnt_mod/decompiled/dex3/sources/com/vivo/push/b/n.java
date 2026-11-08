package com.vivo.push.b;

import android.os.Bundle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class n extends s {
    public String c;
    public int d;
    public boolean e;

    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final String toString() {
        return "OnLogCommand";
    }

    public n() {
        super(7);
        this.d = 0;
        this.e = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void b(com.vivo.push.a aVar) {
        super.b(aVar);
        aVar.a(com.umeng.commonsdk.framework.c.a, this.c);
        aVar.a("log_level", this.d);
        boolean z = this.e;
        if (aVar.a == null) {
            aVar.a = new Bundle();
        }
        aVar.a.putBoolean("is_server_log", z);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public final void c(com.vivo.push.a aVar) {
        super.c(aVar);
        this.c = aVar.a(com.umeng.commonsdk.framework.c.a);
        this.d = aVar.b("log_level", 0);
        this.e = aVar.a != null ? aVar.a.getBoolean("is_server_log", false) : false;
    }
}
