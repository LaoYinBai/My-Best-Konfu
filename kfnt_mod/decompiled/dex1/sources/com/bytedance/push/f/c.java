package com.bytedance.push.f;

import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c implements com.bytedance.push.c.e {

    /* renamed from: a, reason: collision with root package name */
    private final com.bytedance.push.d f4802a;

    public c(com.bytedance.push.d dVar) {
        this.f4802a = dVar;
        e.a(dVar.t);
    }

    @Override // com.bytedance.push.c.e
    public void a() {
        b.a(this.f4802a);
    }

    @Override // com.bytedance.push.c.e
    public void b() {
        b.a();
    }

    @Override // com.bytedance.push.c.e
    public void a(int i, int i2, String str, String str2) throws JSONException {
        f.a(i, i2, str, str2);
    }

    @Override // com.bytedance.push.c.e
    public void a(int i) {
        f.a(i);
    }

    @Override // com.bytedance.push.c.e
    public void a(boolean z, String str) throws JSONException {
        f.a(z, str);
    }

    @Override // com.bytedance.push.c.e
    public void c() throws JSONException {
        f.a();
    }

    @Override // com.bytedance.push.c.e
    public void a(int i, String str) throws JSONException {
        f.a(i, str);
    }

    @Override // com.bytedance.push.c.e
    public void b(int i) throws JSONException {
        f.b(i);
    }

    @Override // com.bytedance.push.c.e
    public void d() throws JSONException {
        f.b();
    }

    @Override // com.bytedance.push.c.e
    public void b(int i, String str) throws JSONException {
        f.b(i, str);
    }
}
