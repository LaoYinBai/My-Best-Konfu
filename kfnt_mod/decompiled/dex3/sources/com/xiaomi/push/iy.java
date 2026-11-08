package com.xiaomi.push;

import com.xiaomi.push.jb;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class iy {
    private final jf a;

    /* renamed from: a, reason: collision with other field name */
    private final jo f800a;

    public iy() {
        this(new jb.a());
    }

    public iy(jh jhVar) {
        this.f800a = new jo();
        this.a = jhVar.a(this.f800a);
    }

    public void a(iu iuVar, byte[] bArr) {
        try {
            this.f800a.a(bArr);
            iuVar.a(this.a);
        } finally {
            this.a.k();
        }
    }
}
