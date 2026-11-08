package com.vivo.push.b;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c extends com.vivo.push.g {
    private long c;
    public String e;
    public String f;
    public int g;
    public int h;
    public String i;

    @Override // com.vivo.push.g
    public String toString() {
        return "BaseAppCommand";
    }

    public c(int i, String str) {
        super(i);
        this.c = -1L;
        this.g = -1;
        this.e = null;
        this.f = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public void b(com.vivo.push.a aVar) {
        aVar.a("req_id", this.e);
        aVar.a("package_name", this.f);
        aVar.a("sdk_version", 305L);
        aVar.a("PUSH_APP_STATUS", this.g);
        if (TextUtils.isEmpty(this.i)) {
            return;
        }
        aVar.a("BaseAppCommand.EXTRA__HYBRIDVERSION", this.i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public void c(com.vivo.push.a aVar) {
        this.e = aVar.a("req_id");
        this.f = aVar.a("package_name");
        this.c = aVar.b("sdk_version", 0L);
        this.g = aVar.b("PUSH_APP_STATUS", 0);
        this.i = aVar.a("BaseAppCommand.EXTRA__HYBRIDVERSION");
    }
}
