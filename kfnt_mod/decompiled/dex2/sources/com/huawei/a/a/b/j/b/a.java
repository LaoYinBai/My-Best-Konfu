package com.huawei.a.a.b.j.b;

import java.util.List;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements com.huawei.a.a.b.j.c.a {
    private byte[] a;
    private String b;
    private String c;
    private String d;
    private String e;
    private List<com.huawei.a.a.b.c.b.d> f;

    public a(byte[] bArr, String str, String str2, String str3, String str4, List<com.huawei.a.a.b.c.b.d> list) {
        this.a = (byte[]) bArr.clone();
        this.b = str;
        this.c = str2;
        this.e = str3;
        this.d = str4;
        this.f = list;
    }

    private com.huawei.a.a.b.f.c a(Map<String, String> map) {
        return com.huawei.a.a.b.f.b.a(this.b, this.a, map);
    }

    private Map<String, String> a() {
        return com.huawei.a.a.d.a.c(this.c, this.e, this.d);
    }

    private void b() {
        com.huawei.a.a.b.j.c.b.a().a(new d(this.f, this.c, this.d, this.e));
    }

    @Override // java.lang.Runnable
    public void run() {
        com.huawei.a.a.b.e.a.b("DataSendTask", "send data running");
        int iA = a(a()).a();
        if (iA != 200) {
            b();
            return;
        }
        com.huawei.a.a.b.e.a.a("DataSendTask", "events PostRequest sendevent TYPE : %s, TAG : %s, resultCode: %d ,reqID:" + this.d, this.e, this.c, Integer.valueOf(iA));
    }
}
