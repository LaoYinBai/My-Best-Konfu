package com.mbridge.msdk.foundation.same.net;

import com.mbridge.msdk.foundation.tools.s;
import org.json.JSONObject;

/* compiled from: Listener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f<T> implements e<T> {
    private long a;
    public String d = "";
    public String e = "";
    public int f = 0;
    private com.mbridge.msdk.foundation.same.report.a.a b = null;

    @Override // com.mbridge.msdk.foundation.same.net.e
    public void a(long j, long j2) {
    }

    @Override // com.mbridge.msdk.foundation.same.net.e
    public void a(com.mbridge.msdk.foundation.same.net.a.a aVar) {
    }

    @Override // com.mbridge.msdk.foundation.same.net.e
    public void b() {
    }

    @Override // com.mbridge.msdk.foundation.same.net.e
    public void c() {
    }

    @Override // com.mbridge.msdk.foundation.same.net.e
    public final void d() {
    }

    @Override // com.mbridge.msdk.foundation.same.net.e
    public void a() {
        this.a = System.currentTimeMillis();
    }

    @Override // com.mbridge.msdk.foundation.same.net.e
    public void a(k<T> kVar) {
        try {
            String str = com.mbridge.msdk.foundation.controller.a.f().k() + "_" + this.e + "_" + this.d + "_" + this.f;
            s.d("Listener", kVar.a + " " + str);
            if (kVar.a instanceof JSONObject) {
                com.mbridge.msdk.foundation.same.net.g.c.a().a(str, ((JSONObject) kVar.a).optInt("status"), ((JSONObject) kVar.a).toString(), System.currentTimeMillis());
            }
            if (kVar.a instanceof String) {
                com.mbridge.msdk.foundation.same.net.g.c.a().a(str, new JSONObject((String) kVar.a).optInt("status"), (String) kVar.a, System.currentTimeMillis());
            }
        } catch (Exception e) {
            s.d("Listener", e.getMessage());
        }
    }

    public final void a(long j) {
        this.b = new com.mbridge.msdk.foundation.same.report.a.a(new com.mbridge.msdk.foundation.entity.k());
        this.b.b(this.d);
        this.b.a(1);
        this.b.a((j - this.a) + "");
    }

    public final void a(int i) {
        if (this.b != null) {
            this.b.b(i);
            this.b.a();
        }
    }

    public final void b(int i) {
        if (this.b != null) {
            this.b.c(i);
        }
    }
}
