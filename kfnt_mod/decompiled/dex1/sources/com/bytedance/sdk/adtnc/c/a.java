package com.bytedance.sdk.adtnc.c;

import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import java.net.URL;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements com.bytedance.sdk.adtnc.b.c {

    /* renamed from: a, reason: collision with root package name */
    private com.bytedance.sdk.adtnc.sdk.b.a f5078a;

    /* renamed from: b, reason: collision with root package name */
    private c f5079b;

    /* renamed from: c, reason: collision with root package name */
    private d f5080c;
    private e d;
    private com.bytedance.sdk.adtnc.f.d e;
    private com.bytedance.sdk.adtnc.f.c f;
    private com.bytedance.sdk.adtnc.f.e g;
    private com.bytedance.sdk.adtnc.f.b h;

    @Override // com.bytedance.sdk.adtnc.b.c
    public com.bytedance.sdk.adtnc.b.c a(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        this.f5078a = aVar;
        this.f5079b = new c(aVar);
        this.f5079b.a();
        this.f5080c = new d(aVar);
        this.f5080c.a(new com.bytedance.sdk.adtnc.b.b() { // from class: com.bytedance.sdk.adtnc.c.a.1
            @Override // com.bytedance.sdk.adtnc.b.b
            public void a(JSONObject jSONObject) {
                if (a.this.f5079b != null) {
                    a.this.f5079b.a(jSONObject);
                }
                if (a.this.d != null) {
                    a.this.d.a();
                }
            }
        });
        this.d = new e();
        com.bytedance.sdk.adtnc.b.a aVar2 = new com.bytedance.sdk.adtnc.b.a() { // from class: com.bytedance.sdk.adtnc.c.a.2
            @Override // com.bytedance.sdk.adtnc.b.a
            public void a(boolean z) {
                if (a.this.f5080c != null) {
                    a.this.f5080c.a(z);
                }
            }
        };
        this.e = new com.bytedance.sdk.adtnc.f.d(aVar);
        this.e.a(aVar2);
        this.f = new com.bytedance.sdk.adtnc.f.c(aVar);
        this.f.a(aVar2);
        this.g = new com.bytedance.sdk.adtnc.f.e(aVar);
        this.g.a(aVar2);
        this.h = new com.bytedance.sdk.adtnc.f.b(this.f5078a);
        this.h.a(aVar2);
        return this;
    }

    @Override // com.bytedance.sdk.adtnc.b.c
    public String a(String str) {
        return this.d.a(str, this.f5079b.c());
    }

    @Override // com.bytedance.sdk.adtnc.b.c
    public void a(com.bytedance.sdk.adtnc.sdk.a.a aVar, com.bytedance.sdk.adtnc.sdk.a.b bVar) throws NumberFormatException {
        com.bytedance.sdk.adtnc.a.a aVarC;
        if (aVar == null || bVar == null || (aVarC = this.f5079b.c()) == null) {
            return;
        }
        URL url = null;
        try {
            url = new URL(aVar.a());
        } catch (Exception e) {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onResponse", (Object) "new URL exception", (Throwable) e);
        }
        if (url == null) {
            return;
        }
        String protocol = url.getProtocol();
        String host = url.getHost();
        String path = url.getPath();
        String strB = aVar.b();
        int iA = bVar.a();
        com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onResponse", url, strB, Integer.valueOf(iA));
        if (!HttpConstant.HTTP.equals(protocol) && !"https".equals(protocol)) {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onResponse", "scheme is error", protocol);
            return;
        }
        if (TextUtils.isEmpty(strB)) {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onResponse", "ip is empty");
            return;
        }
        if (aVarC.f5076b) {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onResponse", "probe Enable");
            this.e.a(bVar, aVarC);
        } else {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onResponse", "probe disable");
        }
        if (aVarC.f5075a) {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onResponse", "local Enable ");
            this.f.a(iA, path, strB, aVarC);
        } else {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onResponse", "local disable");
        }
        this.d.a(host);
    }

    @Override // com.bytedance.sdk.adtnc.b.c
    public void a(com.bytedance.sdk.adtnc.sdk.a.a aVar, Throwable th) {
        if (aVar == null) {
            return;
        }
        if (!com.bytedance.sdk.adtnc.g.d.c(this.f5078a.a())) {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onError", "no internet connection");
            return;
        }
        com.bytedance.sdk.adtnc.a.a aVarC = this.f5079b.c();
        if (aVarC == null) {
            return;
        }
        URL url = null;
        try {
            url = new URL(aVar.a());
        } catch (Exception e) {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onError", (Object) "new URL exception", (Throwable) e);
        }
        if (url == null) {
            return;
        }
        String protocol = url.getProtocol();
        String host = url.getHost();
        String path = url.getPath();
        String strB = aVar.b();
        com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onError", "new URL ", url);
        if (!HttpConstant.HTTP.equals(protocol) && !"https".equals(protocol)) {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onError", "scheme is error", protocol);
            return;
        }
        if (aVarC.f5075a) {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onError", "local enable");
            this.f.a(path, strB, this.f5079b.c());
        } else {
            com.bytedance.sdk.adtnc.g.b.a("MainProcessTNCManager", "onError", "local disable");
        }
        this.d.b(host, this.f5079b.c());
    }
}
