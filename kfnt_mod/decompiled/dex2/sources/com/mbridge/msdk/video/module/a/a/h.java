package com.mbridge.msdk.video.module.a.a;

/* compiled from: ProxyOnNotifyListener.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class h extends e {
    protected com.mbridge.msdk.video.module.a.a b;

    public h(com.mbridge.msdk.video.module.a.a aVar) {
        this.b = aVar;
    }

    @Override // com.mbridge.msdk.video.module.a.a.e, com.mbridge.msdk.video.module.a.a
    public void a(int i, Object obj) {
        super.a(i, obj);
        if (this.b != null) {
            this.b.a(i, obj);
        }
    }
}
