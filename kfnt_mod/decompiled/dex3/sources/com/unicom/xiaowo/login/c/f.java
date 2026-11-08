package com.unicom.xiaowo.login.c;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class f implements d {
    private /* synthetic */ j a;

    f(e eVar, j jVar) {
        this.a = jVar;
    }

    @Override // com.unicom.xiaowo.login.c.d
    public final void a(String str) {
        com.unicom.xiaowo.login.d.f.a("precheck:" + str);
        com.unicom.xiaowo.login.d.a.a().b();
        if (TextUtils.isEmpty(str)) {
            this.a.a(2, "");
        } else {
            this.a.a(1, str);
        }
    }
}
