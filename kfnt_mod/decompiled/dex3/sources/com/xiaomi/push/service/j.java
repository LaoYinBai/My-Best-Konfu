package com.xiaomi.push.service;

import com.xiaomi.push.hi;
import com.xiaomi.push.hn;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j implements hi {
    private final XMPushService a;

    public j(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        return "com.xiaomi.xmsf".equals(str) ? "1000271" : this.a.getSharedPreferences("pref_registered_pkg_names", 0).getString(str, null);
    }

    @Override // com.xiaomi.push.hi
    public void a(List<hn> list, String str, String str2) {
        this.a.a(new k(this, 4, str, list, str2));
    }
}
