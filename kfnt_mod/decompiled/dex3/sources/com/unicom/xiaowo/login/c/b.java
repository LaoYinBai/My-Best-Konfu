package com.unicom.xiaowo.login.c;

import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class b implements Runnable {
    private /* synthetic */ String a;
    private /* synthetic */ String b;
    private /* synthetic */ HashMap c;
    private /* synthetic */ d d;
    private /* synthetic */ a e;

    b(a aVar, String str, String str2, HashMap map, d dVar) {
        this.e = aVar;
        this.a = str;
        this.b = str2;
        this.c = map;
        this.d = dVar;
    }

    @Override // java.lang.Runnable
    public final void run() throws Throwable {
        try {
            this.d.a(this.e.b.a(this.a, this.b, this.c));
        } catch (Exception e) {
            com.unicom.xiaowo.login.d.f.b(e.getMessage());
        }
    }
}
