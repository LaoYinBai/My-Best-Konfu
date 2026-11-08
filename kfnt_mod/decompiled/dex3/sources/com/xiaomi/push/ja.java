package com.xiaomi.push;

import com.xiaomi.push.jb;
import java.io.ByteArrayOutputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ja {
    private jf a;

    /* renamed from: a, reason: collision with other field name */
    private final jm f803a;

    /* renamed from: a, reason: collision with other field name */
    private final ByteArrayOutputStream f804a;

    public ja() {
        this(new jb.a());
    }

    public ja(jh jhVar) {
        this.f804a = new ByteArrayOutputStream();
        this.f803a = new jm(this.f804a);
        this.a = jhVar.a(this.f803a);
    }

    public byte[] a(iu iuVar) {
        this.f804a.reset();
        iuVar.b(this.a);
        return this.f804a.toByteArray();
    }
}
