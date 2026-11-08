package com.bytedance.sdk.adtnc.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b implements com.bytedance.sdk.adtnc.b.c {

    /* renamed from: a, reason: collision with root package name */
    private c f5083a;

    /* renamed from: b, reason: collision with root package name */
    private e f5084b;

    /* renamed from: c, reason: collision with root package name */
    private com.bytedance.sdk.adtnc.f.e f5085c;
    private com.bytedance.sdk.adtnc.f.b d;

    @Override // com.bytedance.sdk.adtnc.b.c
    public com.bytedance.sdk.adtnc.b.c a(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        this.f5083a = new c(aVar);
        this.f5084b = new e();
        com.bytedance.sdk.adtnc.b.a aVar2 = new com.bytedance.sdk.adtnc.b.a() { // from class: com.bytedance.sdk.adtnc.c.b.1
            @Override // com.bytedance.sdk.adtnc.b.a
            public void a(boolean z) {
                if (b.this.f5083a != null) {
                    b.this.f5083a.b();
                }
            }
        };
        this.f5085c = new com.bytedance.sdk.adtnc.f.e(aVar);
        this.f5085c.a(aVar2);
        this.d = new com.bytedance.sdk.adtnc.f.b(aVar);
        this.d.a(aVar2);
        return this;
    }

    @Override // com.bytedance.sdk.adtnc.b.c
    public String a(String str) {
        return this.f5084b.a(str, this.f5083a.c());
    }

    @Override // com.bytedance.sdk.adtnc.b.c
    public void a(com.bytedance.sdk.adtnc.sdk.a.a aVar, com.bytedance.sdk.adtnc.sdk.a.b bVar) {
    }

    @Override // com.bytedance.sdk.adtnc.b.c
    public void a(com.bytedance.sdk.adtnc.sdk.a.a aVar, Throwable th) {
    }
}
